package sms.model;

@SuppressWarnings("serial")
public class Course extends NameEntity {
	public Course() {
	}
	
	public Course(String id,String name) {
		super(id,name);
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", id=" + id + "]";
	}
	
}
