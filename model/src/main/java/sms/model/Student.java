package sms.model;

@SuppressWarnings("serial")
public class Student extends NameEntity {

	private String group ;
	
	public Student() {
	}
	
	public Student(String id,String name,String group) {
		super(id,name);
		this.group = group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return "Student [group=" + group + ", name=" + name + ", id=" + id + "]";
	}
	
	
	
}
