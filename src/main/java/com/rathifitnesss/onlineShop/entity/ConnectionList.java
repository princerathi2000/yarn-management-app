package com.rathifitnesss.onlineShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="connectionList")
public class ConnectionList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="parent")
	private String parent;
	
	@Column(name="child")
	private String child;
	
	@Column(name="accessibility")
	private String accessibility;
	

	public ConnectionList()
	{
		
	}


	@Override
	public String toString() {
		return "ConnectionList [id=" + id + ", parent=" + parent + ", child=" + child
				+ ", accessibility=" + accessibility + "]";
	}


	public ConnectionList(int id, String parent, String child, String accessibility) {
		super();
		this.id = id;
		this.parent = parent;
		this.child = child;
		this.accessibility = accessibility;
	}


	public ConnectionList(String parent, String child, String accessibility) {
		super();
		this.parent = parent;
		this.child = child;
		this.accessibility = accessibility;
	}


	public int getConnectionId() {
		return id;
	}


	public void setConnectionId(int id) {
		this.id = id;
	}


	public String getParent() {
		return parent;
	}


	public void setParent(String parent) {
		this.parent = parent;
	}


	public String getChild() {
		return child;
	}


	public void setChild(String child) {
		this.child = child;
	}


	public String getAccessibility() {
		return accessibility;
	}


	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}

	
}
