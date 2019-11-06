package sk.gursky.entrance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	public void start(Stage stage) throws Exception {
		EntranceMainController controller = new EntranceMainController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EntranceMain.fxml"));
		fxmlLoader.setController(controller);
		Parent rootPane = fxmlLoader.load();
		Scene scene = new Scene(rootPane);
		stage.setTitle("Entrance application");
		stage.setScene(scene);
		stage.show();
		}
	
    public static void main( String[] args ) {
        launch(args);
    }
}
