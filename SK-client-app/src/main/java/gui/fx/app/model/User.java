package gui.fx.app.model;

public class User {

	private Long id;
	private String role;
	private String username;

	public User() {
		
	}
	
	public User(Long id, String role, String username) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", role='" + role + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}
