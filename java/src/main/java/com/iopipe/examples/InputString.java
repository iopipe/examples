package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iopipe.IOpipeExecution;

/**
 * Returns the input string.
 *
 * @since 2018/11/26
 */
public class Uppercase
	implements RequestHandler<String, String>
{
	/**
	 * {@inheritDoc}
	 * @since 2018/11/26
	 */
	@Override
	public String handleRequest(String __in, Context __context)
	{
		return __in;
	}
}

