package sms.service;

import java.util.List;
import java.util.Optional;

import sms.model.Student;

public interface StudentService {
	@SuppressWarnings("exports")
	List<Student> list() throws ServiceException;
	@SuppressWarnings("exports")
	Optional<Student> getStudent(String id) throws ServiceException;
	@SuppressWarnings("exports")
	void addStudent(Student student) throws ServiceException;
	@SuppressWarnings("exports")
	void updateStudent(Student student) throws ServiceException;
	@SuppressWarnings("exports")
	void deleteStudent(Student student) throws ServiceException;
	void add(String id,String name,String group) throws ServiceException;
}
