package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This reads from an input stream and produces a result which is a scrambling
 * of all the letters while randomly fudging each line.
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
		for (;;)
		{
			int c = __in.read();
			
			if (c < 0)
				break;
			
			if (c >= 'A' && c <= 'Z')
				c = (c - 'A') + 'a';
			__out.write(c);
		}
	}
}
