package org.test.sales.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.test.sales.model.AmendSaleMessage;
import org.test.sales.model.MessageType;

public class SalesRepoImpl implements SalesRepo {

	private static Map<String, List<MessageType>> salesCache = new HashMap<>();
	private static Map<String, List<AmendSaleMessage>> amendCache = new HashMap<>();

	@Override
	public void save(MessageType message) {
		String productType = message.getProductType();
		List<MessageType> messages = salesCache.get(productType);
		if (messages == null) {
			messages = new ArrayList<>();
		}
		messages.add(message);
		salesCache.put(productType, messages);
	}

	@Override
	public List<MessageType> getProductSales(String productType) {
		List<MessageType> list = salesCache.get(productType);
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	@Override
	public List<AmendSaleMessage> getAmendSales(String saleType) {
		List<AmendSaleMessage> list = amendCache.get(saleType);
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	@Override
	public List<String> getProductList() {
		return new ArrayList<>(salesCache.keySet());
	}

	@Override
	public void saveAmends(AmendSaleMessage amendSaleMessage, String messageType) {
		List<AmendSaleMessage> messages = amendCache.get(messageType);
		if (messages == null) {
			messages = new ArrayList<>();
		}
		messages.add(amendSaleMessage);
		amendCache.put(messageType, messages);
	}

	@Override
	public List<String> getMessageType() {
		return new ArrayList<>(amendCache.keySet());
	}

	@Override
	public void reset() {
		salesCache = new HashMap<>();
		amendCache = new HashMap<>();

	}

}
