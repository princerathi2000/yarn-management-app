package com.rathifitnesss.onlineShop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;

	@Column(name="user_id")
	private int userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="firm_name")
	private String firmName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id",referencedColumnName = "category_id")
	private Category category;
	
	@Column(name="mobile_no")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="dateTime")
	private String date;
	
	@Column(name="photo")
	private String photo;
	
}
