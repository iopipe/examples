package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iopipe.IOpipeExecution;
import com.iopipe.plugin.trace.TraceMeasurement;
import com.iopipe.plugin.trace.TraceUtils;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.Objects;

/**
 * This just reports any messages that were sent via SQS.
 *
 * @since 2018/08/06
 */
public class SQSHandler
	implements RequestHandler<SQSEvent, Object>
{
	/** The number of processed events since last cold start. */
	private static final AtomicInteger _COUNT =
		new AtomicInteger();
	
	/**
	 * {@inheritDoc}
	 * @since 2018/08/06
	 */
	@Override
	public final Object handleRequest(SQSEvent __e, Context __context)
	{
		IOpipeExecution exec = IOpipeExecution.currentExecution();
		
		// When was this processed
		exec.customMetric("date", LocalDateTime.now().toString());
		
		try (TraceMeasurement q = TraceUtils.measure(exec, "processing"))
		{
			List<SQSEvent.SQSMessage> records = __e.getRecords();
			
			// No records here
			if (records == null)
				return null;
			
			// Record all passed messages
			for (SQSEvent.SQSMessage m : records)
			{
				String b = m.getBody();
				
				if (b != null)
					exec.customMetric("message-" + _COUNT.incrementAndGet(),
						b);
			}
			
			// The return value is not used
			return null;
		}
	}
}
