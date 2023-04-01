package com.rathifitnesss.onlineShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

//user will be the product for this project
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="yarn_id")
	private String yarnId;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="count")
	private String count;
	
	@Column(name="category")
	private String category;
	
	@Column(name="rate")
	private float rate;
	
	@Column(name="quantity")
	private String quantity;
	
	@Column(name="updateTime")
	private String updateTime;
	
	@Column(name="ownerId")
	private String ownerId;
	
	public User()
	{
		
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", yarnId=" + yarnId + ", name=" + name + ", count=" + count + ", category="
				+ category + ", rate=" + rate + ", quantity=" + quantity + ", updateTime=" + updateTime + ", ownerId="
				+ ownerId + "]";
	}

	public User(int id, String yarnId, String name, String count, String category, float rate, String quantity,
			String updateTime, String ownerId) {
		super();
		Id = id;
		this.yarnId = yarnId;
		this.name = name;
		this.count = count;
		this.category = category;
		this.rate = rate;
		this.quantity = quantity;
		this.updateTime = updateTime;
		this.ownerId = ownerId;
	}

	public User(String yarnId, String name, String count, String category, float rate, String quantity,
			String updateTime, String ownerId) {
		super();
		this.yarnId = yarnId;
		this.name = name;
		this.count = count;
		this.category = category;
		this.rate = rate;
		this.quantity = quantity;
		this.updateTime = updateTime;
		this.ownerId = ownerId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getYarnId() {
		return yarnId;
	}

	public void setYarnId(String yarnId) {
		this.yarnId = yarnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	
	
	
	
}
