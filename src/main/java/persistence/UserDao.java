package persistence;

import dto.User;

public interface UserDao {

	User getUserById(Long id);

}
