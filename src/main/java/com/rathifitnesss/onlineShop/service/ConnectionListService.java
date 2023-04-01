package com.rathifitnesss.onlineShop.service;

import java.util.List;

import com.rathifitnesss.onlineShop.entity.ConnectionList;

public interface ConnectionListService {

	List<ConnectionList> getAllConnectionList();
	ConnectionList saveConnectionList(ConnectionList yarn);
	ConnectionList getConnectionListById(Integer id);
	ConnectionList updateConnectionList(ConnectionList yarn);
	void deleteConnectionListById(Integer id);
}
