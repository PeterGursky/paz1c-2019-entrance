package sk.gursky.entrance;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.gursky.entrance.storage.DaoFactory;
import sk.gursky.entrance.storage.User;
import sk.gursky.entrance.storage.UserDAO;

public class EntranceMainController {

	private UserDAO userDAO = DaoFactory.INSTANCE.getUserDao();
	
    @FXML
    private ListView<User> usersListView;
    
    @FXML
    private TextField chipIdTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button addUserButton;
    
    private UserFxModel editedUser = new UserFxModel();
    private User selectedUser = null;

    @FXML
    void initialize() {
    	usersListView.setItems(FXCollections.observableArrayList(userDAO.getAll()));
    	chipIdTextField.textProperty().bindBidirectional(editedUser.chipIdProperty());
    	nameTextField.textProperty().bindBidirectional(editedUser.nameProperty());
    	
    	addUserButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				User user = editedUser.getUser();
				editedUser.clear();
				user = userDAO.addUser(user);
				usersListView.getItems().add(user);
			}
		});
    	usersListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

			@Override
			public void changed(ObservableValue<? extends User> observable, 
					User oldValue, User newValue) {
				selectedUser = newValue;
			}
		});
    	
    	usersListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					// ideme editovat selectedUser
					try {
						UserEditController controller = new UserEditController(selectedUser);
						FXMLLoader fxmlLoader = new FXMLLoader(
								getClass().getResource("UserEdit.fxml"));
						fxmlLoader.setController(controller);
						Parent rootPane = fxmlLoader.load();
						Scene scene = new Scene(rootPane);
						Stage dialog = new Stage();
						dialog.initModality(Modality.APPLICATION_MODAL);
						dialog.setTitle("User edit dialog");
						dialog.setScene(scene);
						dialog.showAndWait();
						
						// toto sa vykona az po zatvoreni dialogu
				    	usersListView.setItems(FXCollections.observableArrayList(userDAO.getAll()));
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
    }
}