package com.rathifitnesss.onlineShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rathifitnesss.Global.GlobalData;
import com.rathifitnesss.onlineShop.service.ConnectionListService;
import com.rathifitnesss.onlineShop.entity.ConnectionList;

@CrossOrigin(origins= {"http://localhost:4200/","http://localhost:64523/"})
@RestController
//@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/connectionList")
//@Controller
public class ConnectionListController {

	@Autowired
	private ConnectionListService connectionListService;

	public ConnectionListController(ConnectionListService connectionListService) {
		super();
		this.connectionListService = connectionListService;
	}
	
	// handler method to handle list connectionList and return mode and view
	@GetMapping("/connectionLists")
	public List<ConnectionList> listConnectionLists(Model model) {
		model.addAttribute("connectionList", connectionListService.getAllConnectionList());
		return connectionListService.getAllConnectionList();
	}
	
	@GetMapping("/connectionLists/new")
	public String createConnectionListForm(Model model) {
		
		// create connectionList object to hold connectionList form data
		ConnectionList connectionList = new ConnectionList();
		model.addAttribute("connectionList", connectionList);
		return "create_connectionList";
		
	}
	
//	@PostMapping("/connectionList")
//	public ConnectionList saveConnectionList(@RequestBody ConnectionList connectionList) {
//		System.out.println(connectionList);
//
//		return connectionListService.saveConnectionList(connectionList);
////		return "redirect:/connectionList";
//	}
	
	//save connectionList according to teacher
    @PostMapping(value = "/connectionLists", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addConnectionList(@RequestBody ConnectionList connectionList){
		ConnectionList resp=connectionListService.saveConnectionList(connectionList);
		if(resp!=null)
			return new ResponseEntity<Object>(resp,HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Error while inserting a data",
					HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/connectionList/edit/{id}")
	public String editConnectionListForm(@PathVariable Integer id, Model model) {
		model.addAttribute("connectionList", connectionListService.getConnectionListById(id));
		return "edit_connectionList";
	}

	@PostMapping("/connectionList/{id}")
	public String updateConnectionList(@PathVariable Integer id,
			@ModelAttribute("connectionList") ConnectionList connectionList,
			Model model) {
		
		// get connectionList from database by id
		ConnectionList existingConnectionList = connectionListService.getConnectionListById(id);
		existingConnectionList.setConnectionId(id);
		existingConnectionList.setParent(connectionList.getParent());
		existingConnectionList.setChild(connectionList.getChild());
		existingConnectionList.setAccessibility(connectionList.getAccessibility());
		
		// save updated connectionList object
		connectionListService.updateConnectionList(existingConnectionList);
		return "redirect:/connectionList";		
	}
	
	// handler method to handle delete connectionList request
	
	@GetMapping("/connectionList/{id}")
	public String deleteConnectionList(@PathVariable Integer id) {
		connectionListService.deleteConnectionListById(id);
		return "redirect:/connectionList";
	}
	
//	//to view product
//	@GetMapping("/connectionLists/{connectionListId}")
//	public ConnectionList viewProduct(@PathVariable String connectionListId, Model model) {
//		model.addAttribute("product", connectionListService.getConnectionListByConnectionListId(connectionListId));
////		return "viewProduct";
//		return connectionListService.getConnectionListByConnectionListId(connectionListId);
//	}
}
