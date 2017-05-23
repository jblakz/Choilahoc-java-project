package beans;

//Model cho bảng admins trong DB
public class HoangHien_04_Admin {
	private int idAdmins;
	private String name;
	private String email;
	private String username;
	private String genPassword;
	private byte[] salt;

	public HoangHien_04_Admin() {
		super();
	}
	public HoangHien_04_Admin(int idAdmins) {
		super();
		this.idAdmins = idAdmins;
	}
	public HoangHien_04_Admin(int idAdmins, String name, String email, String username,
			String password, byte[] salt) {
		super();
		this.idAdmins = idAdmins;
		this.name = name;
		this.email = email;
		this.username = username;
		this.genPassword = password;
		this.salt = salt;
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
		return genPassword;
	}
	public void setPassword(String password) {
		this.genPassword = password;
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
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
}
