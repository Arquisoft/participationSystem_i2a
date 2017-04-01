package persistence;

import java.util.List;

import dto.User;

public interface UserDao {

	User getUserById(Integer id);

	List<String> findAllEmails();
}
