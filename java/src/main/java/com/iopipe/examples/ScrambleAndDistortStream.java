package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.iopipe.IOpipeExecution;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Random;

/**
 * This reads from an input stream and produces a result which is a scrambling
 * of all the letters.
 *
 * @since 2018/08/28
 */
public class ScrambleAndDistortStream
	implements RequestStreamHandler
{
	/**
	 * {@inheritDoc}
	 * @since 2018/08/28
	 */
	@Override
	public final void handleRequest(InputStream __in,
		OutputStream __out, Context __context)
		throws IOException
	{
		IOpipeExecution exec = IOpipeExecution.currentExecution();
		
		// Read in the string which was passed to use
		StringBuilder src = new StringBuilder();
		try (Reader r = new InputStreamReader(__in, "utf-8"))
		{
			char[] buf = new char[512];
			for (;;)
			{
				int rc = r.read(buf);
				
				if (rc < 0)
					break;
				
				src.append(buf);
			}
		}
		
		// Store original message
		exec.customMetric("original", src.toString());
		
		// This makes handling more characters easier
		int n = src.length();
		
		// Mapping of which characters are upper case and which ones are actual
		// characters to be shuffled around
		boolean[] upper = new boolean[n],
			alpha = new boolean[n];
		
		// Pool of characters which are available for usage
		char[] pool = new char[n];
		int pooldx = 0;
		
		// Determine which characters are uppercase and which ones are alpha
		// numeric characters, put those characters in the pool of characters
		for (int i = 0; i < n; i++)
		{
			char c = src.charAt(i);
			
			// Remember the cases of strings
			upper[i] = Character.isUpperCase(c);
			
			// If this is a letter add it to the stack
			boolean isalpha;
			alpha[i] = (isalpha = Character.isLetter(c));
			if (isalpha)
				pool[pooldx++] = c;
		}
		
		// Shuffle the character pool, this will effectively randomize the
		// character layout, n - 1 is used because the bound has to be non-zero
		// and the last character will effectively be randomized anyway
		Random rand = new Random();
		for (int i = 0; i < pooldx - 1; i++)
		{
			// The index to be swapped is derived from the current index to
			// the ones which are left so that earlier characters are not
			// shuffled back in place
			int swapdx = i + rand.nextInt(pooldx - i);
			
			// Swap the character
			char x = pool[i];
			pool[i] = pool[swapdx];
			pool[swapdx] = x;
		}
		
		// Go through the original string and rebuild
		StringBuilder dest = new StringBuilder();
		for (int i = 0; i < n; i++)
		{
			// If it is not an alphabetical character then the character is
			// copied as-is with no adjustment
			if (!alpha[i])
				dest.append((char)src.charAt(i));
			
			// Otherwise a character is taken from the pool
			else
			{
				char c = pool[--pooldx];
				
				// Preserve the case of the shuffled characters so that it
				// always stays the same
				dest.append((char)(upper[i] ? Character.toUpperCase(c) :
					Character.toLowerCase(c)));
			}
		}
		
		// Store resulting message
		exec.customMetric("result", dest.toString());
		
		// Write the resulting scrambled and fudged string
		__out.write(dest.toString().getBytes("utf-8"));
	}
}
