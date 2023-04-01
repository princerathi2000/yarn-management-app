package com.rathifitnesss.onlineShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rathifitnesss.onlineShop.entity.Product;
import com.rathifitnesss.onlineShop.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	List<User> findAllByownerId(String ownerId);

}
