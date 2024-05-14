import java.io.Serializable;

/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */
public class User implements Serializable {


	String userName ;
     String userSurname ;
     String email;
 	String createPass ;
    // String userID;
	String role;

	public User(String userName, String userSurname, String email, String createPass , String role) {
		super();
		this.userName = userName;
		this.userSurname = userSurname;
		this.email = email;
		this.createPass = createPass;
		this.role = role;
	}
	
	//Getters and Setters
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userSurname
	 */
	public String getUserSurname() {
		return userSurname;
	}
	/**
	 * @param userSurname the userSurname to set
	 */
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	/**
	 * @return the contact
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the createPass
	 */
	public String getCreatePass() {
		return createPass;
	}
	/**
	 * @param createPass the createPass to set
	 */
	public void setCreatePass(String createPass) {
		this.createPass = createPass;
	}
	
    /**
	 * @return the group
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param group the group to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
