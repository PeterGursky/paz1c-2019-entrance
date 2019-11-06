package sk.gursky.entrance.storage;

import java.util.List;

public interface UserDAO {

	User addUser(User user);

	boolean validate(String chipId);

	int getSize();

	List<User> getAll();
	
	User getByChipId(long chipId);

	User save(User user);

}