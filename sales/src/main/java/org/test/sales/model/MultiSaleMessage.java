package org.test.sales.model;

public class MultiSaleMessage extends SingleSaleMessage implements MessageType {
	private static final String MULTI_SALE = "Multi sale";
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getType() {
		return MULTI_SALE;
	}

	@Override
	public String toString() {
		return "MultiSaleMessage [productType=" + productType + ", quantity=" + quantity + ", value=" + value + "]";
	}
}
