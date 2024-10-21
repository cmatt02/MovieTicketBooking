package singleton;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import factory.TicketFactory;
import model.Movies;
import model.Ticket;
import model.User;

public class Database {
	private static Database instance = null;
	public List<User> users;
	public List<Movies> movies;
	public List<Ticket> ticket;
	
	
	public Database() {
		users = new ArrayList<User>();
		movies = new ArrayList<Movies>();
		ticket = new ArrayList<Ticket>();
		addDefaultMovies();
	}
	
	public static Database getInstance() {
		if(instance == null) {
			synchronized (Database.class) {
				if(instance == null) instance = new Database();
			}
		}
		return instance;
	}
	
	private static int ticketCounter = 1;
	public void createTicketForUser(int userID, String movieName, TicketFactory factory, Date bookDate) {
	    User user = getUserById(userID);
	    if (user != null) {
	        Ticket newTicket = factory.createTicket(ticketCounter++, movieName, bookDate); // Use factory to create the ticket
	        user.addTicket(newTicket);
	        ticket.add(newTicket);
	        System.out.println("Ticket created and assigned to " + user.getName());
	    } else {
	        System.out.println("User not found!");
	    }
	}
	
	private User getUserById(int userID) {
        for (User user : users) {
            if (user.getId() == userID) {
                return user;
            }
        }
        return null;
    }
	
	public void addUsers(User user) {
		users.add(user);
	}
	
	public void addDefaultMovies() {
		movies.add(new Movies("Inception", Timestamp.valueOf("2024-12-16 19:30:00"), "A mind-bending thriller by Christopher Nolan."));
        movies.add(new Movies("The Matrix", Timestamp.valueOf("2024-12-17 21:00:00"), "A sci-fi action film where humans fight against intelligent machines."));
        movies.add(new Movies("Interstellar", Timestamp.valueOf("2024-12-18 18:00:00"), "A journey beyond the stars to save humanity, directed by Christopher Nolan."));
        movies.add(new Movies("The Dark Knight", Timestamp.valueOf("2024-12-19 20:30:00"), "The Batman faces his greatest nemesis, The Joker, in this epic superhero film."));
        movies.add(new Movies("Avatar", Timestamp.valueOf("2024-12-20 17:45:00"), "A sci-fi epic about humans on the planet Pandora."));
	}
	
	public void addTicket(Ticket tickets) {
		ticket.add(tickets);
	}
	
	
	
	public List<User> getUsers(){
		return users;
	}
	
	public List<Movies> getMovies(){
		return movies;
	}
	
	public List<Ticket> getTicket(){
		return ticket;
	}
	
	public boolean isEmpty() {
		return users.isEmpty();
	}
	
	
}
