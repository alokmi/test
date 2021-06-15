package org.test.sales.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.test.sales.model.AmendSaleMessage;
import org.test.sales.model.MessageType;
import org.test.sales.model.MultiSaleMessage;
import org.test.sales.model.OperationType;
import org.test.sales.model.SingleSaleMessage;

public class MessageReader {

	private static final String AMEND_MESSAGE = "amend";
	private static final String MULTI_MESSAGE = "multi";
	private static final String SINGLE_MESSAGE = "single";
	private static final String SPLITTER = ",";
	private static final String MESSAGE_FILE = "/messages.txt";
	BufferedReader br = null;

	public MessageReader() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream(MESSAGE_FILE);
		br = new BufferedReader(new InputStreamReader(inputStream));
		br.readLine();
	}

	public MessageType readNextMessage() throws IOException {
		String line;
		if ((line = br.readLine()) != null) {
			return convertMessage(line);
		}
		return null;
	}

	private MessageType convertMessage(String line) {
		String[] columns = line.split(SPLITTER);
		switch (columns[0]) {
		case SINGLE_MESSAGE:
			SingleSaleMessage singleMessage = new SingleSaleMessage();
			singleMessage.setProductType(columns[1]);
			singleMessage.setValue(Float.valueOf(columns[2]));
			return singleMessage;
		case MULTI_MESSAGE:
			MultiSaleMessage multiMessage = new MultiSaleMessage();
			multiMessage.setProductType(columns[1]);
			multiMessage.setValue(Float.valueOf(columns[2]));
			multiMessage.setQuantity(Integer.parseInt(columns[3]));
			return multiMessage;
		case AMEND_MESSAGE:
			AmendSaleMessage amendMessage = new AmendSaleMessage();
			amendMessage.setProductType(columns[1]);
			amendMessage.setValue(Float.valueOf(columns[2]));
			amendMessage.setOperationType(OperationType.valueOf(columns[4]));
			return amendMessage;
		}

		return null;
	}
}
