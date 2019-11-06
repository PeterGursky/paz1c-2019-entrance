package sk.gursky.entrance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.gursky.entrance.storage.CardReader;
import sk.gursky.entrance.storage.DaoFactory;
import sk.gursky.entrance.storage.User;

public class UserFxModel {
	private Long id;
	private StringProperty name = new SimpleStringProperty();
	private StringProperty chipId = new SimpleStringProperty();
	private BooleanProperty admin = new SimpleBooleanProperty();
	private Map<CardReader, BooleanProperty> cardReaders 
		= new HashMap<CardReader, BooleanProperty>();
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	public String getChipId() {
		return chipId.get();
	}
	public void setChipId(String chipId) {
		this.chipId.set(chipId);
	}
	public StringProperty chipIdProperty() {
		return chipId;
	}
	public boolean isAdmin() {
		return admin.get();
	}
	public void setAdmin(boolean isAdmin) {
		admin.set(isAdmin);
	}
	public BooleanProperty adminProperty() {
		return admin;
	}
	public void clear() {
		name.set("");
		chipId.set("");
	}
	
	public Map<CardReader, BooleanProperty> getCardReaders() {
		return cardReaders;
	}
	
	public void setUser(User user) {
		this.id = user.getId();
		setName(user.getName());
		setChipId(user.getChipId());
		setAdmin(user.isAdmin());
		cardReaders.clear();
		List<CardReader> allCR = DaoFactory.INSTANCE.getCardReaderDao().getAll();
		for (CardReader cr: allCR) {
			BooleanProperty bp = new SimpleBooleanProperty();
			if (user.getCardReaders().contains(cr)) {
				bp.set(true);
			} else {
				bp.set(false);
			}
			cardReaders.put(cr, bp);
		}
	}
	
	public User getUser() {
		User user = new User();
		user.setId(id);
		user.setName(getName());
		user.setChipId(getChipId());
		user.setAdmin(isAdmin());
		List<CardReader> userCardReaders = new ArrayList<CardReader>();
		for (Entry<CardReader, BooleanProperty> pair: getCardReaders().entrySet()) {
			if (pair.getValue().get()) {
				userCardReaders.add(pair.getKey());
			}
		}
		user.setCardReaders(userCardReaders);
		return user;
	}
}
