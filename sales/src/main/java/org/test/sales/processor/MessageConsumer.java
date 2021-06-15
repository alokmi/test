package org.test.sales.processor;

import org.test.sales.model.MessageType;

public class MessageConsumer {

	private static final int REPORTING_THRESHOLD = 10;
	private static final int MAX_MESSAGES = 50;
	private MessageProcessor processor = new MessageProcessor();
	private ReportGenerator reportGenerator = new ReportGenerator();
	private static int counter = 0;

	public void consume(MessageType message) {
		if (counter < 0) {
			System.out.println("Not processing any further messages, maximum threshold reached");
			return;
		}
		counter++;
		processor.process(message);
		if (counter % REPORTING_THRESHOLD == 0) {
			reportGenerator.generateSalesPerProduct();
		}

		if (counter % MAX_MESSAGES == 0) {
			counter = -1;
			System.out.println("\nApplication is pausing.. ");
			reportGenerator.generateAdjustment();
		}

	}
}
