package sk.gursky.entrance.biznis;

import sk.gursky.entrance.storage.CardReader;
import sk.gursky.entrance.storage.DaoFactory;
import sk.gursky.entrance.storage.User;
import sk.gursky.entrance.storage.UserDAO;

public class DefaultEntranceManager implements EntranceManager {

	UserDAO userDAO = DaoFactory.INSTANCE.getUserDao();
	
	@Override
	public boolean validate(long chipId, CardReader cr) {
		User user = userDAO.getByChipId(chipId);
		if (user == null)
			return false;
		
		if (user.getCardReaders().contains(cr)) {
				return true;
		}

		return false;
	}

}
