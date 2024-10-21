package adapter;

public class CashPayment implements Payment {

	@Override
	public void processPayment(int price) {
		// TODO Auto-generated method stub
		System.out.println("Paid " + price + " in cash.");
	}

	
}
