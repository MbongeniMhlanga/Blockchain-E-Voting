import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbongeni Mhlanga 
 *@version Mini Project
 */
public class FileHandler {
	public void writeUser(User user) {
		 // Read all the existing users (both voters and officers)
        List<User> userList = new ArrayList<>();
        
     // Add the new user to the list(both voters and officers)
        userList.add(user); 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userdatabase.dat"))) {
        	// Write the updated list back to the userdatabase
            oos.writeObject(userList); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	@SuppressWarnings("unchecked")
	public List<User> readUsers() {
	    List<User> users = new ArrayList<>();
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userdatabase.dat"))) {
	        Object obj = ois.readObject();
	        if (obj instanceof List<?>) {
	        	// Casting the object to a list of users(voters and officers)
	            users = (List<User>) obj; 
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return users;
	}
}
