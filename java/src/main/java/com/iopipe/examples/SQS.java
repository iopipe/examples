package com.iopipe.examples;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.iopipe.IOpipeExecution;
import com.iopipe.plugin.trace.TraceMeasurement;
import com.iopipe.plugin.trace.TraceUtils;
import com.iopipe.SimpleRequestHandlerWrapper;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.Objects;

/**
 * This just reports any messages that were sent via SQS.
 *
 * @since 2018/08/06
 */
public class SQS
  extends SimpleRequestHandlerWrapper<SQSEvent, Object>
{
	/** The number of processed events since last cold start. */
	private static final AtomicInteger _COUNT =
		new AtomicInteger();
	
	/**
	 * {@inheritDoc}
	 * @since 2018/08/06
	 */
	@Override
	protected final String wrappedHandleRequest(IOpipeExecution __exec,
		SQSEvent e)
	{
		// When was this processed
		__exec.customMetric("date", LocalDateTime.now().toString());
		
		try (TraceMeasurement q = TraceUtils.measure(__exec, "processing"))
		{
			List<SQSEvent.SQSMessage> records = e.getRecords();
			
			// No records here
			if (records == null)
				return null;
			
			// Record all passed messages
			for (SQSEvent.SQSMessage m : records)
			{
				String b = m.getBody();
				
				if (b != null)
					__exec.customMetric("message-" + _COUNT.incrementAndGet(),
						b);
			}
			
			// The return value is not used
			return null;
		}
	}
}
