package sk.gursky.buttonApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	public void start(Stage stage) throws Exception {
		MainWindowController controller = new MainWindowController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		fxmlLoader.setController(controller);
		Parent rootPane = fxmlLoader.load();
		
//		Parent rootPane = FXMLLoader.load(
//				getClass().getResource("MainWindow.fxml"));

//		Button button = new Button("stlač ma!");
//		button.setOnAction(event -> System.out.println("Anonymny klik"));
//		AnchorPane rootPane = new AnchorPane();
//		rootPane.getChildren().add(button);
//		rootPane.setPrefSize(400, 300);
		Scene scene = new Scene(rootPane);
		stage.setTitle("Hello World");
		stage.setScene(scene);
		stage.show();
		}
	
    public static void main( String[] args ) {
        launch(args);
    }
}
