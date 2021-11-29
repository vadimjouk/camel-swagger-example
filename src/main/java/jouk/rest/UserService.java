package jouk.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

	static Map<String, User> allUsers = new HashMap<>();
	static {
		for (int i = 0; i < 3; i++) {
			User user = new User();
			allUsers.put(user.getId(), user);
		}
	}

	public UserService() {
		// default
	}

	public List<User> getUserList() {
		return new ArrayList<>(allUsers.values());
	}

	public User getUser(String id) {
		return allUsers.get(id);
	}

	public User updateUser(User user) {
		return user;
	}

}
