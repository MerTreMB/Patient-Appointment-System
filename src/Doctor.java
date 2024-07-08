
public class Doctor extends User {
	private String title;
	private String field;
	private String phoneNumber;
	
	public Doctor() {
	}
	
	public Doctor(int id, String title, String name, String surName, String tcKimlik, String field, String phoneNumber) {
		super(id, name, surName, tcKimlik);
		this.title = title;
		this.field = field;
		this.phoneNumber = phoneNumber;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return String.join(",", String.valueOf(id), title, name, surName, tcKimlik, field, phoneNumber);
	}

}
