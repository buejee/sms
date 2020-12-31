package sms.filestore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sms.model.Entity;
import sms.persistence.PersistenceException;
import sms.persistence.PersistenceService;

public class PersistenceServiceImpl implements PersistenceService {
	
	private final Path dataPath = Paths.get(".","data");

	@Override
	public <T extends Entity> List<T> list(Class<T> type) throws PersistenceException {
		List<T> results = new ArrayList<>();
		try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getEntitiesPath(type),"*.bin")){
			for(Path path:directoryStream) {
				results.add(readEntity(path));
			}
		}catch(IOException | ClassNotFoundException e) {
			throw new PersistenceException(e);
		}
		return results;
	}

	@Override
	public <T extends Entity> Optional<T> get(Class<T> type, String id) throws PersistenceException {
		
		try {
			return Optional.of(readEntity(getEntityPath(type, id)));
		} catch (ClassNotFoundException | IOException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public void save(Entity entity) throws PersistenceException {
		try {
			saveEntity(entity);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public <T extends Entity> void delete(Class<T> type, String id) throws PersistenceException {
		try {
			deleteEntity(type, id);
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}
	
	private Path getEntitiesPath(Class<?> type) {
		Path path= dataPath.resolve(type.getSimpleName());
		if(Files.notExists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}
	
	private Path getEntityPath(Class<?> type,String id) {
		return getEntitiesPath(type).resolve(String.format("%s.bin", id));
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Entity> T readEntity(Path path) throws IOException,ClassNotFoundException {
		try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))){
			return (T)objectInputStream.readObject();
		}
	}
	
	private void saveEntity(Entity entity) throws IOException{
		Path path= getEntityPath(entity.getClass(), entity.getId());
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))){
			objectOutputStream.writeObject(entity);
		}
	}
	
	private void deleteEntity(Class<?> type,String id) throws IOException {
		Files.deleteIfExists(getEntityPath(type, id));
	}
    
}
