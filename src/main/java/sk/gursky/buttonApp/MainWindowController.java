package sk.gursky.buttonApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindowController {

    @FXML
    private Button printHuraButton;

    @FXML
    void initialize() {
    	System.out.println("inicializujem");
    	printHuraButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hurá");
			}
		});
    }
}
