
public class User {
	protected int id;
	protected String name;
	protected String surName;
	protected String tcKimlik;
	private String password;
	private String securityQuestionAnswer;
	private String gender;
	private String birthday;
	
	public User() {
		
	}
	
	public User(int id, String name, String surName, String tcKimlik, String password, String securityQuestionAnswer, String gender, String birthday) {
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.tcKimlik = tcKimlik;
		this.password = password;
		this.securityQuestionAnswer = securityQuestionAnswer;
		this.gender = gender;
		this.birthday = birthday;
		
	}
	
	public User(int id, String name, String surName, String tcKimlik) {
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.tcKimlik = tcKimlik;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	public String getTcKimlik() {
		return tcKimlik;
	}
	
	public void setTcKimlik(String tcKimlik) {
		this.tcKimlik = tcKimlik;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSecurityQuestionAnswer() {
		return securityQuestionAnswer;
	}
	
	public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return String.join(",", String.valueOf(id), name, surName, tcKimlik, password, securityQuestionAnswer, gender, birthday);
	}
}
