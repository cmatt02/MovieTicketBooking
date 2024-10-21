package factory;

import java.sql.Date;

import model.Ticket;
import model.VIPTicket;

public class VIPTicketFactory extends TicketFactory {

	@Override
	public Ticket createTicket(Integer ticketID, String movieName, Date bookDate) {
		// TODO Auto-generated method stub
		return new VIPTicket(ticketID, movieName, bookDate);
	}

}
