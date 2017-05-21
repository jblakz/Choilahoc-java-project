package beans;

import java.sql.Timestamp;;

//Model cho bảng Activities trong DB
public class HoangHien_04_Activity {
	private int idActivities;
	private String title;
	private byte[] image;
	private String content;
	private String summary;
	private Timestamp time;
	private int idCategory;
	private int idAdmins;
	private String catName;
	
	public HoangHien_04_Activity(){}
	
	public HoangHien_04_Activity(int idActivities, String title, byte[] image, String content,
			String summary, Timestamp time, int idCategory, int idAdmins) {
		super();
		this.idActivities = idActivities;
		this.title = title;
		this.image = image;
		this.content = content;
		this.summary = summary;
		this.time = time;
		this.idCategory = idCategory;
		this.idAdmins = idAdmins;
	}
	public HoangHien_04_Activity(String title, byte[] image, String content,
			String summary, Timestamp time, int idCategory, int idAdmins) {
		super();
		this.title = title;
		this.image = image;
		this.content = content;
		this.summary = summary;
		this.time = time;
		this.idCategory = idCategory;
		this.idAdmins = idAdmins;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getIdActivities() {
		return idActivities;
	}

	public int getIdAdmins() {
		return idAdmins;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

}
