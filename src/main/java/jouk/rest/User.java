package jouk.rest;

import java.util.UUID;

public class User {
	String id = UUID.randomUUID().toString();

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
