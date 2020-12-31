package sms.persistence;
import java.util.List;
import java.util.Optional;
import sms.model.Entity;

public interface PersistenceService {
	<T extends Entity> List<T> list(Class<T> type) throws PersistenceException;
	<T extends Entity> Optional<T> get(Class<T> type,String id) throws PersistenceException;
	@SuppressWarnings("exports")
	void save(Entity entity) throws PersistenceException;
	<T extends Entity> void delete(Class<T> type,String id) throws PersistenceException;
}
