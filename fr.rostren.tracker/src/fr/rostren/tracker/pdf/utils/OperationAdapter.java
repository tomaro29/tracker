package fr.rostren.tracker.pdf.utils;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.TrackerFactory;

public class OperationAdapter {

	/**
	 * Adapts an {@link Operation} instance to an {@link OperationData} instance
	 * @param operation the operation to adapt
	 * @return the adapted {@link OperationData} instance
	 */
	public static OperationData adaptOperation(Operation operation) {
		return new OperationData(OperationType.valueOf(operation.eClass().getName().toUpperCase()), operation.getOperationTitle(), operation.getTotalAmount(), operation.getDate(),
				operation.getOrigin(), operation.getSubAmounts());
	}

	/**
	 * Adapts an {@link OperationData} instance to an {@link Operation} instance
	 * @param operation the operation data to adapt
	 * @return the adapted {@link Operation} instance
	 */
	public static Operation adaptOperation(OperationData operation) {
		Operation adapted;
		if (OperationType.CREDIT.equals(operation.getType())) {
			adapted=TrackerFactory.eINSTANCE.createCredit();
		}
		else if (OperationType.DEBIT.equals(operation.getType())) {
			adapted=TrackerFactory.eINSTANCE.createDebit();
		}
		else {
			//Operation type is not defined, it cannot be adapted
			return null;
		}

		adapted.setDate(operation.getDate());
		adapted.setOperationTitle(operation.getOperationTitle());
		adapted.setOrigin(operation.getOrigin());
		adapted.setTotalAmount(operation.getTotalAmount());
		adapted.getSubAmounts().addAll(operation.getSubAmounts());
		return adapted;
	}
}
