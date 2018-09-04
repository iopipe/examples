package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iopipe.IOpipeExecution;

/**
 * Uppercases the input string.
 *
 * @since 2018/09/04
 */
public class Uppercase
	implements RequestHandler<String, String>
{
	/**
	 * {@inheritDoc}
	 * @since 2018/09/04
	 */
	@Override
	public String handleRequest(String __in, Context __context)
	{
		IOpipeExecution exec = IOpipeExecution.currentExecution();
		
		String rv = __in.toUpperCase();
		
		exec.customMetric("input", __in);
		exec.customMetric("output", rv);
		
		return rv;
	}
}

