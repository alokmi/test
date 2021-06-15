package org.test.sales.main;

import java.io.IOException;

import org.test.sales.model.MessageType;
import org.test.sales.processor.MessageConsumer;
import org.test.sales.reader.MessageReader;

public class ApplicationRunner {

	public static void main(String[] args) throws IOException {
		MessageReader reader = new MessageReader();
		MessageConsumer consumer = new MessageConsumer();
		MessageType message;
		while ((message = reader.readNextMessage()) != null) {
			consumer.consume(message);
		}
	}
}
