import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbongeni
 *
 */
public class FileHandler {
	public void writeUser(User user) {
        List<User> userList = new ArrayList<>(); // Read existing users
        userList.add(user); // Add the new user to the list
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userdatabase.dat"))) {
            oos.writeObject(userList); // Write the updated list back to the file
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
	            users = (List<User>) obj; // Cast the object to a list of users
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return users;
	}
}
