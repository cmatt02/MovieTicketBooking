package factory;

import java.sql.Date;

import model.PremiereTicket;
import model.Ticket;

public class PremiereTicketFactory extends TicketFactory {

	@Override
	public Ticket createTicket(Integer ticketID, String movieName, Date bookDate) {
		// TODO Auto-generated method stub
		return new PremiereTicket(ticketID, movieName, bookDate);
	}



}
