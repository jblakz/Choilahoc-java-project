package beans;

//Model cho bảng admins trong DB
public class HoangHien_04_Admin {
	private int idAdmins;
	private String name;
	private String email;
	private String username;
	private String password;

	public HoangHien_04_Admin() {
		super();
	}
	public HoangHien_04_Admin(int idAdmins) {
		super();
		this.idAdmins = idAdmins;
	}
	public HoangHien_04_Admin(int idAdmins, String name, String email, String username,
			String password) {
		super();
		this.idAdmins = idAdmins;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdAdmins() {
		return idAdmins;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
