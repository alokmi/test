package org.test.sales.processor;

import java.util.List;

import org.test.sales.factory.RepoFactory;
import org.test.sales.model.AmendSaleMessage;
import org.test.sales.model.MessageType;
import org.test.sales.repo.SalesRepo;

public class MessageProcessor {

	public void process(MessageType inputMessage) {
		SalesRepo repo = RepoFactory.getSalesRepo();
		if (inputMessage instanceof AmendSaleMessage)
			amendSale(inputMessage, repo);
		else
			repo.save(inputMessage);
	}

	private void amendSale(MessageType inputMessage, SalesRepo repo) {
		AmendSaleMessage amendMessage = (AmendSaleMessage) inputMessage;
		List<MessageType> messages = repo.getProductSales(inputMessage.getProductType());

		for (MessageType message : messages) {
			updateValue(amendMessage, message);
			repo.saveAmends(amendMessage, message.getType());
		}

	}

	private void updateValue(AmendSaleMessage amendMessage, MessageType message) {
		float offset = amendMessage.getValue();
		float val = message.getValue();
		switch (amendMessage.getOperationType()) {
		case ADD:
			message.setValue(val + offset);
			break;
		case SUBSTRACT:
			message.setValue(val - offset);
			break;
		case MULTIPLY:
			message.setValue(val * offset);
			break;
		case DIVIDE:
			message.setValue(val / offset);
			break;
		}
	}

}
