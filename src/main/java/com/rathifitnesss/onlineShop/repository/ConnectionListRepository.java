package com.rathifitnesss.onlineShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rathifitnesss.onlineShop.entity.ConnectionList;


public interface ConnectionListRepository extends JpaRepository<ConnectionList,Integer>{

//	ConnectionList findAllByConnectionId(String connectionId);
}
