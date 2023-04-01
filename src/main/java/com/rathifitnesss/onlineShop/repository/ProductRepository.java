package com.rathifitnesss.onlineShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rathifitnesss.onlineShop.entity.Product;
import com.rathifitnesss.onlineShop.entity.User;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	List<Product> findAllByCategory_Id(int id);
	List<Product> findAllByName(String name);
	
	@Query(value="select * from product where category_id=?1",nativeQuery = true)
	List<Product> getAllProductByCategoryId(int id);
	
	Product findAllByEmail(String email);
	Product findAllByUserId(int userId);
	List<Product> findBynameLike(String name);
}
