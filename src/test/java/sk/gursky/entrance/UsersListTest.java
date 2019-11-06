package sk.gursky.entrance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.gursky.entrance.storage.MemoryUserDAO;
import sk.gursky.entrance.storage.User;
import sk.gursky.entrance.storage.UserDAO;

class UsersListTest {

	private UserDAO list;
	
	@BeforeEach
	void setUp() throws Exception {
		list = new MemoryUserDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void addUserTest() {
		User u = new User();
		u.setName("Jano");
		u.setChipId("12345");
		list.addUser(u);
		assertEquals(1, list.getSize());
	}
	
	@Test
	void validateTest() {
		User u = new User();
		u.setName("Jano");
		u.setChipId("12345");
		list.addUser(u);
		assertTrue(list.validate("12345"));
		assertFalse(list.validate("abcd"));
		assertTrue(list.validate(null));
	}

}
