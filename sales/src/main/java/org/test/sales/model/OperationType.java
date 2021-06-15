package org.test.sales.model;

/**
 * OPERATION_TYPE("OPERATION Name")
 */

public enum OperationType {
	ADD("add"), SUBSTRACT("substract"), MULTIPLY("multiply"), DIVIDE("divide");

	private final String operationType;

	/**
	 * @param label The plain text name of the operation
	 */
	OperationType(String operationType) {
		this.operationType = operationType;
	}

	/**
	 * @return The plain operation type
	 */
	public String operationType() {
		return this.operationType;
	}

}
