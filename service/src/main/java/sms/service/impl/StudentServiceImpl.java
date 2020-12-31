package sms.service.impl;

import java.util.List;
import java.util.Optional;

import sms.model.Student;
import sms.persistence.PersistenceException;
import sms.persistence.PersistenceService;
import sms.service.ServiceException;
import sms.service.StudentService;

public class StudentServiceImpl implements StudentService {
	
	private PersistenceService persistenceService = PersistenceServiceLoader.persistenceService;
	@SuppressWarnings("exports")
	@Override
	public List<Student> list() throws ServiceException {
		try {
			return persistenceService.list(Student.class);
		} catch (PersistenceException e) {
			throw new ServiceException(e);
		}
	}

	@SuppressWarnings("exports")
	@Override
	public Optional<Student> getStudent(String id) throws ServiceException {
		try {
			return persistenceService.get(Student.class, id);
		} catch (PersistenceException e) {
			throw new ServiceException(e);
		}
	}

	@SuppressWarnings("exports")
	@Override
	public void addStudent(Student student) throws ServiceException {
		try {
			persistenceService.save(student);
		} catch (PersistenceException e) {
			throw new ServiceException(e);
		}

	}

	@SuppressWarnings("exports")
	@Override
	public void updateStudent(Student student) throws ServiceException {
		try {
			persistenceService.save(student);
		} catch (PersistenceException e) {
			throw new ServiceException(e);
		}

	}

	@SuppressWarnings("exports")
	@Override
	public void deleteStudent(Student student) throws ServiceException {
		try {
			persistenceService.delete(Student.class, student.getId());
		} catch (PersistenceException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void add(String id, String name, String group) throws ServiceException {
		addStudent(new Student(id, name, group));
	}

}
