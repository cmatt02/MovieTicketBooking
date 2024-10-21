package factory;

import java.sql.Date;

import model.RegularTicket;
import model.Ticket;

public class RegularTicketFactory extends TicketFactory {

	@Override
	public Ticket createTicket(Integer ticketID, String movieName, Date bookDate) {
		// TODO Auto-generated method stub
		return new RegularTicket(ticketID, movieName, bookDate);
	}

}
