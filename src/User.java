import java.io.Serializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;

/**
 * 
 */

/**
 * @author Mbongeni
 *
 */
public class User implements Serializable {

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
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
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
	String userName ;
     String userSurname ;
     String contact;
     @SuppressWarnings("rawtypes")
	String role;
     /**
	 * @return the group
	 */
	public String getGroup() {
		return role;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String role) {
		this.role = role;
	}
	public User(String userName, String userSurname, String contact, String createPass , String role) {
		super();
		this.userName = userName;
		this.userSurname = userSurname;
		this.contact = contact;
		this.createPass = createPass;
		this.role = role;
	}
	String createPass ;
     //String reEnterPass ;

}
