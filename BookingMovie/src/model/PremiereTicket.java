package model;

import java.sql.Date;

public class PremiereTicket extends Ticket {

	public PremiereTicket(int ticketID, String movieName, Date bookDate) {
		super(ticketID, movieName, "Premiere", bookDate, 100000);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void displayTicketInfo() {
		// TODO Auto-generated method stub
		System.out.println("Movie Ticket - ID: " + ticketID + ", Type: " + type + ", Booking Date: " + bookDate + ", Price: " + price);
	}

}
