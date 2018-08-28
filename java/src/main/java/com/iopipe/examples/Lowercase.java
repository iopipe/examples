package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.iopipe.IOpipeExecution;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This request handler takes the given input stream and lowercases all
 * characters which have been input. It just translates simple ASCII as an
 * example.
 *
 * @since 2018/08/16
 */
public class Lowercase
	implements RequestStreamHandler
{
	/**
	 * {@inheritDoc}
	 * @since 2018/08/16
	 */
	@Override
	public final void handleRequest(InputStream __in,
		OutputStream __out, Context __context)
		throws IOException
	{
		IOpipeExecution exec = IOpipeExecution.currentExecution();
		
		int total = 0,
			lowercased = 0;
		for (;;)
		{
			int c = __in.read();
			
			if (c < 0)
				break;
			
			if (c >= 'A' && c <= 'Z')
			{
				c = (c - 'A') + 'a';
				lowercased++;
			}
			
			__out.write(c);
			total++;
		}
		
		exec.customMetric("total", total);
		exec.customMetric("lowercased", lowercased);
	}
}
