package org.test.sales.processor;

import java.util.List;

import org.test.sales.factory.RepoFactory;
import org.test.sales.model.AmendSaleMessage;
import org.test.sales.model.MessageType;
import org.test.sales.repo.SalesRepo;

public class ReportGenerator {

	public void generateSalesPerProductReport() {
		SalesRepo repo = RepoFactory.getSalesRepo();
		List<String> products = repo.getProductList();

		System.out.println("\nProduct, Quantity, totalValue");
		products.stream().forEach(productType -> {
			List<MessageType> messages = repo.getProductSales(productType);
			float totalValue = 0f;
			int totalQuantitySale = 0;
			for (MessageType message : messages) {
				totalValue += message.getValue() * message.getQuantity();
				totalQuantitySale += message.getQuantity();
			}
			System.out.println(productType + ", " + totalQuantitySale + ", " + totalValue);
		});
	}

	public void generateAdjustmentReport() {
		SalesRepo repo = RepoFactory.getSalesRepo();
		List<String> saleTypes = repo.getMessageType();
		saleTypes.stream().forEach(saleType -> {
			List<AmendSaleMessage> amendSales = repo.getAmendSales(saleType);
			System.out.println(saleType + " : " + amendSales);
		});
		System.out.println();
	}

}
