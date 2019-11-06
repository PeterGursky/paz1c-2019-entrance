package sk.gursky.entrance.storage;

import java.util.ArrayList;
import java.util.List;

public class MemoryUserDAO implements UserDAO {

	private long lastId = 0L;
	private List<User> users = new ArrayList<User>();
	
	public MemoryUserDAO() {
		User jano = new User();
		jano.setName("Jano");
		jano.setChipId("12345");
		addUser(jano);
		User anka = new User();
		anka.setName("Anka");
		anka.setChipId("abcde");
		anka.setAdmin(true);
		addUser(anka);
	}
	
	@Override
	public User addUser(User user) {
		user.setId(++lastId);
		users.add(user);
		return user;
	}
	
	@Override
	public boolean validate(String chipId) {
		for(User u: users) {
			if (u.getChipId().equals(chipId)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getSize() {
		return users.size();
	}
	
	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public User getByChipId(long chipId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}
 }
