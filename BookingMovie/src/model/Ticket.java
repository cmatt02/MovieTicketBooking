package model;

import java.sql.Date;

public abstract class Ticket {
	protected int ticketID;
	protected String movieName;
	protected String type;
	protected Date bookDate;
	protected Integer price;
	
	public Ticket(int ticketID, String movieName, String type, Date bookDate, Integer price) {
		super();
		this.ticketID = ticketID;
		this.movieName = movieName;
		this.type = type;
		this.bookDate = bookDate;
		this.price = price;
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public abstract void displayTicketInfo();

}
