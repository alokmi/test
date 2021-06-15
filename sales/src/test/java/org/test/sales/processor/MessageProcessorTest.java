package org.test.sales.processor;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.test.sales.factory.RepoFactory;
import org.test.sales.model.AmendSaleMessage;
import org.test.sales.model.MessageType;
import org.test.sales.model.MultiSaleMessage;
import org.test.sales.model.OperationType;
import org.test.sales.model.SingleSaleMessage;
import org.test.sales.repo.SalesRepo;

public class MessageProcessorTest {

	private MessageProcessor processor = new MessageProcessor();
	@Before
	public void init() {
		SalesRepo repo = RepoFactory.getSalesRepo();
		repo.reset();
	}

	@Test
	public void testProcessSingleMessage() {
		SalesRepo repo = RepoFactory.getSalesRepo();
		SingleSaleMessage message = createSingleSaleMessage();
		processor.process(message);

		List<MessageType> messages = repo.getProductSales("apple");
		assertEquals("Check number of messages", 1, messages.size());
		assertEquals("Check proiductType", "apple", messages.get(0).getProductType());
	}

	private SingleSaleMessage createSingleSaleMessage() {
		SingleSaleMessage message = new SingleSaleMessage();
		message.setProductType("apple");
		message.setValue(10);
		return message;
	}

	@Test
	public void testProcessMultiMessage() {
		SalesRepo repo = RepoFactory.getSalesRepo();
		MultiSaleMessage message = createMultiSaleMessage();
		processor.process(message);

		List<MessageType> messages = repo.getProductSales("orange");
		assertEquals("Check number of messages", 1, messages.size());
		assertEquals("Check proiductType", "orange", messages.get(0).getProductType());
	}

	private MultiSaleMessage createMultiSaleMessage() {
		MultiSaleMessage message = new MultiSaleMessage();
		message.setProductType("orange");
		message.setValue(10);
		message.setQuantity(10);
		return message;
	}

	@Test
	public void testProcessAmendMessageADD() {
		SingleSaleMessage singleMessage = createSingleSaleMessage();
		processor.process(singleMessage);
		
		SalesRepo repo = RepoFactory.getSalesRepo();
		AmendSaleMessage message = new AmendSaleMessage();
		message.setProductType("apple");
		message.setValue(10);
		message.setOperationType(OperationType.ADD);
		processor.process(message);

		List<String> messageTypes = repo.getMessageType();
		List<AmendSaleMessage> messages = repo.getAmendSales(messageTypes.get(0));
		assertEquals("Check number of messages", 1, messageTypes.size());
		assertEquals("Check proiductType", "apple", messages.get(0).getProductType());
		List<MessageType> productSales = repo.getProductSales("apple");
		assertEquals(20f, productSales.get(0).getValue(), 0);
	}

	@Test
	public void testProcessAmendMessageSUBSTRACT() {
		SingleSaleMessage singleMessage = createSingleSaleMessage();
		processor.process(singleMessage);
		
		SalesRepo repo = RepoFactory.getSalesRepo();
		AmendSaleMessage message = new AmendSaleMessage();
		message.setProductType("apple");
		message.setValue(2);
		message.setOperationType(OperationType.SUBSTRACT);
		processor.process(message);

		List<String> messageTypes = repo.getMessageType();
		List<AmendSaleMessage> messages = repo.getAmendSales(messageTypes.get(0));
		assertEquals("Check number of messages", 1, messageTypes.size());
		assertEquals("Check proiductType", "apple", messages.get(0).getProductType());
		
		List<MessageType> productSales = repo.getProductSales("apple");
		assertEquals(8f, productSales.get(0).getValue(), 0);
	}

	@Test
	public void testProcessAmendMessageMULTIPLY() {
		MultiSaleMessage multiMessage = createMultiSaleMessage();
		processor.process(multiMessage);
		
		SalesRepo repo = RepoFactory.getSalesRepo();
		AmendSaleMessage message = new AmendSaleMessage();
		message.setProductType("orange");
		message.setValue(3);
		message.setOperationType(OperationType.MULTIPLY);
		processor.process(message);

		List<String> messageTypes = repo.getMessageType();
		List<AmendSaleMessage> messages = repo.getAmendSales(messageTypes.get(0));
		assertEquals("Check number of messages", 1, messageTypes.size());
		assertEquals("Check proiductType", "orange", messages.get(0).getProductType());
		
		List<MessageType> productSales = repo.getProductSales("orange");
		assertEquals(30f, productSales.get(0).getValue(), 0);
	}
	
	@Test
	public void testProcessAmendMessageDIVIDE() {
		MultiSaleMessage multiMessage = createMultiSaleMessage();
		processor.process(multiMessage);
		
		SalesRepo repo = RepoFactory.getSalesRepo();
		AmendSaleMessage message = new AmendSaleMessage();
		message.setProductType("orange");
		message.setValue(5);
		message.setOperationType(OperationType.DIVIDE);
		processor.process(message);

		List<String> messageTypes = repo.getMessageType();
		List<AmendSaleMessage> messages = repo.getAmendSales(messageTypes.get(0));
		assertEquals("Check number of messages", 1, messageTypes.size());
		assertEquals("Check proiductType", "orange", messages.get(0).getProductType());
		
		List<MessageType> productSales = repo.getProductSales("orange");
		assertEquals(2f, productSales.get(0).getValue(), 0);
	}

}
