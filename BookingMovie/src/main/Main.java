package main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import adapter.CashPayment;
import adapter.CreditCardPayment;
import adapter.Payment;
import adapter.TransferPayment;
import factory.PremiereTicketFactory;
import factory.RegularTicketFactory;
import factory.TicketFactory;
import factory.UserFactory;
import factory.VIPTicketFactory;
import model.Movies;
import model.Ticket;
import model.User;
import singleton.Database;

public class Main {
	private static Scanner scan = new Scanner(System.in);
	private static Database db = Database.getInstance();
	private static UserFactory userFactory;
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("Welcome to TicketBooking");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. View Users");
			System.out.println("4. Exit");
			System.out.print("Input: ");
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				viewUsers();
				break;
			case 4:
				System.out.println("Thank you for using TicketBooking");
				return;
			}
			
		}
	}
	
	public static void register() {
		userFactory = new UserFactory();
		
		int id = 1;
		List<User> users = db.getUsers();
		for(User user : users) {
			user.setId(id++);
		}
		
		String name;
		while(true) {
			System.out.print("Please input your name[name length must be between 5-20]: ");
			name = scan.nextLine();
			if(name.length()>=5 && name.length()<=20) {
				break;
			}
			else {
				System.out.println("Please input between 5 - 20 in length");
			}
		}
		
		String email;
		while(true) {
			System.out.print("Please input your email: ");
			email = scan.nextLine();
			if(!email.isEmpty() && email.contains("@")) {
				break;
			}
			else {
				System.out.println("Please input a valid email address");
			}
		}
		
		String password;
		while(true) {
			System.out.print("Please input your password: ");
			password = scan.nextLine();
			
			if(isValidPassword(password)) {
				break;
			}
			else {
				System.out.println("Password must be at least 7 characters long and contain a number, a letter, and a special character.");
			}
		}
		
		User u = userFactory.createNewUser(id, name, email, password);
		Database.getInstance().addUsers(u);
		System.out.println("User added!");
	}
	
	public static boolean isValidPassword(String password) {
		if(password.length() < 7) {
			return false;
		}
		
		boolean hasLetter = false;
		boolean hasDigit = false;
		boolean hasSpecialChar = false;
		
		for(char c: password.toCharArray()) {
			if(Character.isLetter(c)) {
				hasLetter = true;
			}
			else if(Character.isDigit(c)) {
				hasDigit = true;
			}
			else if(!Character.isLetterOrDigit(c)) {
				hasSpecialChar = true;
			}
		}
		
		return hasLetter && hasDigit && hasSpecialChar;
	}
	
	public static void login() {
		List<User> users = db.getUsers();
		
		boolean isLoggedIn = false;
		User loggedInUser = null;
		
		while(!isLoggedIn) {
			System.out.print("Please input your email: ");
			String email = scan.nextLine();
			
			System.out.print("Please input your password: ");
			String password = scan.nextLine();
			
			for(User user: users) {
				if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
					loggedInUser = user;
					isLoggedIn = true;
					break;
				}
			}
			
			if(loggedInUser != null) {
				System.out.println("Login successful! Welcome, " + loggedInUser.getName());
				userSession(loggedInUser);
			}
			else {
				System.out.println("Invalid email or password. Please try again.");
			}
		}
	}
	
	public static void userSession(User loggedInUser) {
		while(true) {
			System.out.println("1. Buy ticket");
			System.out.println("2. View tickets");
			System.out.println("3. Logout");
			System.out.print("Input: ");
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch(choice) {
			case 1:
				buyTicket(loggedInUser);
				break;
			case 2:
				viewTickets(loggedInUser);
				break;
			case 3:
				System.out.println("Logging out...");
				return;
			}
		}
	}
	
	public static void buyTicket(User loggedInUser) {
		viewMovies();
		List<Movies> movies = db.getMovies();
		Movies selectedMovie = null;
	    while (selectedMovie == null) {
	        System.out.print("Choose a movie by entering its title: ");
	        String movieTitle = scan.nextLine();
	        
	        // Validate if the movie title exists
	        for (Movies movie : movies) {
	            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
	                selectedMovie = movie;
	                break;
	            }
	        }
	        
	        if (selectedMovie == null) {
	            System.out.println("Movie not found. Please try again.");
	        }
	    }

	    // Step 3: Enter ticket type (validation included)
	    TicketFactory factory = null;
	    while (factory == null) {
	        System.out.print("Enter ticket type (VIP, Regular, Premiere): ");
	        String ticketType = scan.nextLine().trim().toLowerCase();

	        switch (ticketType) {
	            case "vip":
	                factory = new VIPTicketFactory();
	                break;
	            case "regular":
	                factory = new RegularTicketFactory();
	                break;
	            case "premiere":
	                factory = new PremiereTicketFactory();
	                break;
	            default:
	                System.out.println("Invalid ticket type. Please choose between VIP, Regular, or Premiere.");
	        }
	    }

	    // Step 4: Enter booking date with validation
	    Date bookDate = null;
	    while (bookDate == null) {
	        System.out.print("Enter booking date (yyyy-mm-dd): ");
	        String dateInput = scan.nextLine();
	        try {
	            bookDate = Date.valueOf(dateInput);  // Convert string to Date
	        } catch (IllegalArgumentException e) {
	            System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format.");
	        }
	    }
	    int ticketCounter = 1;
		Ticket newTicket = factory.createTicket(ticketCounter ++, selectedMovie.getTitle(), bookDate);
	    db.createTicketForUser(loggedInUser.getId(), selectedMovie.getTitle(), factory, bookDate);
	    
	    Payment paymentMethod = null;
	    while (paymentMethod == null) {
	        System.out.print("Choose payment method (Cash, Transfer, CreditCard): ");
	        String paymentType = scan.nextLine().trim().toLowerCase();

	        switch (paymentType) {
	            case "cash":
	                paymentMethod = new CashPayment();
	                break;
	            case "transfer":
	                paymentMethod = new TransferPayment();
	                break;
	            case "creditcard":
	                paymentMethod = new CreditCardPayment();
	                break;
	            default:
	                System.out.println("Invalid payment method. Please choose Cash, Transfer, or CreditCard.");
	        }
	    }

	    // Process the payment
	    paymentMethod.processPayment(newTicket.getPrice());
	    
	    
	    System.out.println("Ticket created successfully!");
	}
	
	
	public static void viewUsers() {
		if(db.isEmpty()) {
			System.out.println("There is no users..");
			return;
		}
		else {
			List<User> users = db.getUsers();
			for(User user: users) {
				System.out.println("Name: " + user.getName());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Password: " + user.getPassword());
			}
		}
	}
	
	public static void viewMovies() {
		List<Movies> movies = db.getMovies();
		if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }
		else {
			for(Movies movie: movies) {
				System.out.println("Title: " + movie.getTitle());
				System.out.println("Play date and time: " +movie.getMovieDate());
				System.out.println("Description: " + movie.getDescription());
				System.out.println("---------------");
			}
		}
	}
	
	public static void viewTickets(User loggedInUser) {
		List<Ticket> tickets = loggedInUser.getTickets();
		
		if(tickets.isEmpty()) {
			System.out.println("You have no tickets");
		}
		else {
			System.out.println("Your tickets: ");
			for(Ticket ticket: tickets) {
				ticket.displayTicketInfo();
			}
		}
	}

}
