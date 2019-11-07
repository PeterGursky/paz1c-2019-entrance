package sk.gursky.entrance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	public void start(Stage stage) throws Exception {
		logger.error("Start JavaFX Entrance aplikacie");
		logger.warn("Start JavaFX Entrance aplikacie");
		logger.info("Start JavaFX Entrance aplikacie");
		logger.debug("Start JavaFX Entrance aplikacie");
		logger.trace("Start JavaFX Entrance aplikacie");
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
