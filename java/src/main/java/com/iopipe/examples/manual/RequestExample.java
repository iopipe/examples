package com.iopipe.examples.manual;

import com.amazonaws.services.lambda.runtime.Context;
import com.iopipe.IOpipeExecution;
import com.iopipe.plugin.trace.TraceMeasurement;
import com.iopipe.plugin.trace.TraceUtils;
import com.iopipe.SimpleRequestHandlerWrapper;

/**
 * This class wraps the simple request handler and prefixes "hello" to
 * a string input.
 *
 * This example uses {@link SimpleRequestHandlerWrapper}.
 *
 * @since 2017/12/18
 */
public class RequestExample
	extends SimpleRequestHandlerWrapper<String, String>
{
	/**
	 * {@inheritDoc}
	 * @since 2017/12/13
	 */
	@Override
	protected final String wrappedHandleRequest(IOpipeExecution __exec,
		String name)
	{

		// Add a custom metric to your invocation
		__exec.customMetric("hello", "world");

		// Measure performance of this method via the trace plugin
		try (TraceMeasurement m = TraceUtils.measure(__exec, "math"))
		{
			long result = 0;
			for (int i = 1; i < 10000; i++)
				result += new Long(i);

			for (int i = 1; i < 10000; i++)
				result *= new Long(i);

			// Store the result of the math
			__exec.customMetric("result", (long)result);
		}

		// Say RequestExample!
		return "hello " + name + "!";
	}
}
