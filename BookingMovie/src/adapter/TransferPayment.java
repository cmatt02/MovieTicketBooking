package adapter;

import java.util.Random;

public class TransferPayment implements Payment {

	@Override
	public void processPayment(int price) {
		// TODO Auto-generated method stub
		String accountNumber = generateRandomNumber(10);
		System.out.println("Virtual Bank Account number: " + accountNumber);
		System.out.println("Paid " + (price) + " using transfer.");
	}
	
	private String generateRandomNumber(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<length; i++) sb.append(random.nextInt(10));
		return sb.toString();
	}

}
