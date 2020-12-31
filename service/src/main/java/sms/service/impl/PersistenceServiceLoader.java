package sms.service.impl;

import java.util.Optional;
import java.util.ServiceLoader;

import sms.persistence.PersistenceService;

public class PersistenceServiceLoader {
	@SuppressWarnings("exports")
	public static PersistenceService persistenceService;
	
	static {
		final Optional<PersistenceService> optionalService = ServiceLoader.load(PersistenceService.class).findFirst();
		if(optionalService.isPresent())
			persistenceService = optionalService.get();
		else {
			throw new RuntimeException("no persistence service.");
		}
	}
}
