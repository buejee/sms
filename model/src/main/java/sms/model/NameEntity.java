package sms.model;

@SuppressWarnings("serial")
public abstract class NameEntity extends Entity {
	protected String name ;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public NameEntity() {
	}
	
	public NameEntity(String id,String name) {
		super(id);
		this.name = name;
	}
}
