package com.iopipe.examples.manual;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iopipe.IOpipeService;
import com.iopipe.plugin.trace.TraceMeasurement;
import com.iopipe.plugin.trace.TraceUtils;

/**
 * This class wraps the simple request handler and just prefixes "Hello" to
 * an input object containing {name:"Foo"}
 *
 * This manually initializes the IOpipe service by initializing the wrapper
 * and then using the invoke method.
 *
 * @since 2017/12/18
 */
public class ServiceRunExample
	implements RequestHandler<String, String>
{
	/**
	 * {@inheritDoc}
	 * @since 2017/12/13
	 */
	@Override
	public final String handleRequest(String name, Context __context)
	{
		return IOpipeService.instance().<String>run(__context, (__exec) ->
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

				// Say hello!
				return "Hello " + name + "!";
			});
	}
}
