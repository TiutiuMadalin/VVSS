package pizzashop.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.PaymentType;

public class PaymentAlert implements PaymentOperation {
	private static final String NEW_LINE_SEPARATOR = "--------------------------";
	private final PizzaService service;
	
	public PaymentAlert(PizzaService service) {
		this.service = service;
	}
	
	public void showPaymentAlert(int tableNumber, double totalAmount) {
		Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
		paymentAlert.setTitle("Payment for Table " + tableNumber);
		paymentAlert.setHeaderText("Total amount: " + totalAmount);
		paymentAlert.setContentText("Please choose payment option");
		
		ButtonType cardPayment = new ButtonType("Pay by Card");
		ButtonType cashPayment = new ButtonType("Pay Cash");
		ButtonType cancel = new ButtonType("Cancel");
		
		paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
		
		ButtonType result = paymentAlert.showAndWait().orElse(cancel);
		if (result == cardPayment) {
			cardPayment();
			service.addPayment(tableNumber, PaymentType.Card, totalAmount);
		} else if (result == cashPayment) {
			cashPayment();
			service.addPayment(tableNumber, PaymentType.Cash, totalAmount);
		} else {
			cancelPayment();
		}
	}
	
	@Override
	public void cardPayment() {
		System.out.println(NEW_LINE_SEPARATOR);
		System.out.println("Paying by card...");
		System.out.println("Please insert your card!");
		System.out.println(NEW_LINE_SEPARATOR);
	}
	
	@Override
	public void cashPayment() {
		System.out.println(NEW_LINE_SEPARATOR);
		System.out.println("Paying cash...");
		System.out.println("Please show the cash...!");
		System.out.println(NEW_LINE_SEPARATOR);
	}
	
	@Override
	public void cancelPayment() {
		System.out.println(NEW_LINE_SEPARATOR);
		System.out.println("Payment choice needed...");
		System.out.println(NEW_LINE_SEPARATOR);
	}
}
