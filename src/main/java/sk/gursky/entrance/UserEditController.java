package sk.gursky.entrance;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import sk.gursky.entrance.storage.CardReader;
import sk.gursky.entrance.storage.DaoFactory;
import sk.gursky.entrance.storage.User;

public class UserEditController {
	private static final Logger logger = LoggerFactory.getLogger(UserEditController.class);

    @FXML
    private CheckBox isAdminCheckBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField chipIdTextField;

    @FXML
    private FlowPane cardReadersFlowPane;

    private UserFxModel model = new UserFxModel();
    
    public UserEditController(User user) {
    	model.setUser(user);
    }

	@FXML
    void initialize() {
		logger.error("UserEdit inicialize");
		logger.warn("UserEdit inicialize");
		logger.info("UserEdit inicialize");
		logger.debug("UserEdit inicialize");
		logger.trace("UserEdit inicialize");
		nameTextField.textProperty().bindBidirectional(model.nameProperty());
		chipIdTextField.textProperty().bindBidirectional(model.chipIdProperty());
		isAdminCheckBox.selectedProperty().bindBidirectional(model.adminProperty());
		
		for (Entry<CardReader, BooleanProperty> pair: model.getCardReaders().entrySet()) {
			CheckBox checkBox = new CheckBox(pair.getKey().getName());
			checkBox.selectedProperty().bindBidirectional(pair.getValue());
			cardReadersFlowPane.getChildren().add(checkBox);
		}
    }
	
    @FXML
    void saveButtonClick(ActionEvent event) {
    	DaoFactory.INSTANCE.getUserDao().save(model.getUser());
    	nameTextField.getScene().getWindow().hide();
    }
}

