package sk.gursky.entrance.biznis;

import sk.gursky.entrance.storage.CardReader;

public interface EntranceManager {
	boolean validate(long chipId, CardReader cr);
}	
