package persistence;

import java.util.List;

import dto.User;

public interface UserDao {

	User getUserById(Integer id);
	
	void createUser(User user);

	List<String> findAllEmails();

	User getUserByEmail(String email);
}
