package factory;

import model.User;

public class UserFactory {
	public User createNewUser(int id, String name, String email, String password) {
		return new User(id, name, email, password);
	}
}
