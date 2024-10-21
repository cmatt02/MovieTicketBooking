package model;

import java.sql.Date;

public class RegularTicket extends Ticket {

	public RegularTicket(int ticketID, String movieName, Date bookDate) {
		super(ticketID, movieName, "Regular", bookDate, 50000);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void displayTicketInfo() {
		// TODO Auto-generated method stub
		System.out.println("Movie Ticket - ID: " + ticketID + ", Type: " + type + ", Booking Date: " + bookDate + ", Price: " + price);
	}

}
