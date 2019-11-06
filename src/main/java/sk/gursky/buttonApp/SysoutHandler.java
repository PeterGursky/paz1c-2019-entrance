package sk.gursky.buttonApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SysoutHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Kliklo sa");
		
	}

}
