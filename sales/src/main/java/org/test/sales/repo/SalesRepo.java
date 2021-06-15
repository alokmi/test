package org.test.sales.repo;

import java.util.List;

import org.test.sales.model.AmendSaleMessage;
import org.test.sales.model.MessageType;

public interface SalesRepo {

	public void save(MessageType message);

	public List<MessageType> getProductSales(String productType);

	public List<String> getProductList();

	void saveAmends(AmendSaleMessage message, String messageType);

	List<AmendSaleMessage> getAmendSales(String productType);

	public List<String> getMessageType();
	
	public void reset();

}
