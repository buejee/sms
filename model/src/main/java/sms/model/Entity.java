package sms.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id ;
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public Entity() {
	}
	
	public Entity(String id) {
		this.id= id;
	}
}
