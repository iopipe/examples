package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iopipe.IOpipeExecution;

/**
 * Adds multiple numbers together.
 *
 * @since 2018/08/27
 */
public class AddNumbers
	implements RequestHandler<int[], Integer>
{
	/**
	 * {@inheritDoc}
	 * @since 2018/08/27
	 */
	@Override
	public Integer handleRequest(int[] __in, Context __context)
	{
		IOpipeExecution exec = IOpipeExecution.currentExecution();
		
		int result = 0;
		
		for (int i : __in)
			result += i;
		
		exec.customMetric("result", result);
		
		return result;
	}
}

