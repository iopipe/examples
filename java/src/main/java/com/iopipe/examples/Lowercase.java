package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
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
public class UnwrappedLowercase
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
