package model;

import java.util.ArrayList;
import java.util.List;

public class User {
	protected Integer id;
	protected String name;
	protected String email;
	protected String password;
	protected List<Ticket> tickets;
	
	public User(Integer id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.tickets = new ArrayList<>();
	}
	
	

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}
	
	
}
