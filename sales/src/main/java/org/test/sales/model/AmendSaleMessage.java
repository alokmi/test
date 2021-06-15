package org.test.sales.model;

public class AmendSaleMessage extends SingleSaleMessage implements MessageType {
	private OperationType operationType;

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	@Override
	public String toString() {
		return "{operationType=" + operationType + ", productType=" + productType + ", value=" + value
				+ "}";
	}
}
