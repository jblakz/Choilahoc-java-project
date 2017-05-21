package beans;

//Model cho bảng categories trong DB
public class HoangHien_04_Category {
	private int idCategory;
	private String catName;
	private Boolean hide;
	public HoangHien_04_Category(int idCategory, String catName, Boolean hide) {
		super();
		this.idCategory = idCategory;
		this.catName = catName;
		this.hide = hide;
	}
	public HoangHien_04_Category(String catName) {
		super();
		this.catName = catName;
		hide=false;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Boolean getHide() {
		return hide;
	}
	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	public int getIdCategory() {
		return idCategory;
	}
	
}
