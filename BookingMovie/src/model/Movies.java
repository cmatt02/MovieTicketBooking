package model;

import java.sql.Timestamp;

public class Movies {
	protected String title;
	protected Timestamp movieDate;
	protected String description;
	
	public Movies(String title, Timestamp movieDate, String description) {
		super();
		this.title = title;
		this.movieDate = movieDate;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(Timestamp movieDate) {
		this.movieDate = movieDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
