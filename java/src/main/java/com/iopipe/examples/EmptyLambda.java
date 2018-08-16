package com.iopipe.examples;


import com.amazonaws.services.lambda.runtime.Context;
import com.iopipe.IOpipeExecution;
import com.iopipe.SimpleRequestHandlerWrapper;

/**
 * This is a blank lambda that is wrapped by IOpipe with the basic handler.
 *
 * @since 2018/08/16
 */
public class EmptyLambda
	extends SimpleRequestHandlerWrapper<Object, String>
{
	/**
	 * {@inheritDoc}
	 * @since 2018/08/16
	 */
	@Override
	protected final String wrappedHandleRequest(IOpipeExecution __exec,
		Object __input)
	{
		return "";
	}
}
