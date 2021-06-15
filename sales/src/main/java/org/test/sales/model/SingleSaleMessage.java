package org.test.sales.model;

public class SingleSaleMessage implements MessageType {

	private static final String SINGLE_SALE = "Single sale";
	protected String productType;
	protected float value;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public int getQuantity() {
		return 1;
	}

	@Override
	public String getType() {
		return SINGLE_SALE;
	}

}
