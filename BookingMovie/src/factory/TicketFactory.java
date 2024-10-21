package factory;

import java.sql.Date;

import model.Ticket;

public abstract class TicketFactory {
	public abstract Ticket createTicket(Integer ticketID, String movieName, Date bookDate);
}
