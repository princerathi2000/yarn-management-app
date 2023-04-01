package com.rathifitnesss.onlineShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rathifitnesss.onlineShop.entity.ConnectionList;
import com.rathifitnesss.onlineShop.repository.ConnectionListRepository;
import com.rathifitnesss.onlineShop.service.ConnectionListService;

@Service
public class ConnectionListServiceImpl implements ConnectionListService{

	@Autowired
	private ConnectionListRepository connectionListRepository;

	public ConnectionListServiceImpl(ConnectionListRepository connectionListRepo) {
		super();
		this.connectionListRepository = connectionListRepo;
	}
	
	public List<ConnectionList> getAllConnectionList() {
		return connectionListRepository.findAll();
	}

	public ConnectionList saveConnectionList(ConnectionList connectionList) {
		return connectionListRepository.save(connectionList);
	}

	public ConnectionList getConnectionListById(Integer id) {
		return connectionListRepository.findById(id).get();
	}
	
//	public ConnectionList getConnectionListByConnectionListId(String connectionListId) {
//		return connectionListRepository.findAllByConnectionListId(connectionListId);
//	}

	public ConnectionList updateConnectionList(ConnectionList connectionList) {
		return connectionListRepository.save(connectionList);
	}

	public void deleteConnectionListById(Integer id) {
		connectionListRepository.deleteById(id);	
	}



}
