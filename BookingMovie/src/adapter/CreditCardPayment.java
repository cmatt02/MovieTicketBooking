package adapter;

import java.util.Random;

public class CreditCardPayment implements Payment {

	@Override
	public void processPayment(int price) {
		// TODO Auto-generated method stub
		String cardNumber = generateRandomNumber(16);  // Simulate a credit card number
        System.out.println("Credit Card Number: " + cardNumber);
        System.out.println("Paid " + price + " using credit card.");
	}
	private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
