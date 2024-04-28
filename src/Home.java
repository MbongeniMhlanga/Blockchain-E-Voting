import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Mbongeni Mhlanga
 * @version Mini Project
 *
 */
public class Home  extends GridPane{
	GridPane hPane;
	Stage stage;
	Scene votePageScene;
	TextArea txtArea;
	Label lblArea;
	public Home(Stage v3Stage, Scene votePageScene) {
		stage = v3Stage;
	     this.votePageScene = votePageScene;
		hPane = new GridPane();
		txtArea = new TextArea("Welcome To The Electronic Voting System");
		//System.out.println("Home Page");
		
		//hPane.add(lblArea, 0, 0);
		hPane.add(txtArea, 1, 0);
		// Adding The Children to the GridPane
		getChildren().addAll(hPane);
	}

}
