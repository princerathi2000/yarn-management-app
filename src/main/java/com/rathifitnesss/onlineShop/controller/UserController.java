package com.rathifitnesss.onlineShop.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.rathifitnesss.Global.GlobalData;
import com.rathifitnesss.onlineShop.entity.ConnectionList;
import com.rathifitnesss.onlineShop.entity.User;
import com.rathifitnesss.onlineShop.service.CategoryService;
import com.rathifitnesss.onlineShop.service.ConnectionListService;
import com.rathifitnesss.onlineShop.service.UserService;

@CrossOrigin(origins= {"http://localhost:4200/"})
@Controller
public class UserController {

	private UserService userService;
	 @Autowired
	 CategoryService categoryService;
	 @Autowired
	 ConnectionListService connectionListService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	// handler method to handle list user and return mode and view
	@GetMapping("/user")
	public String listUsers(Model model) {
		model.addAttribute("user", userService.getAllUserByUserId(""+GlobalData.loggedProduct.getUserId()));
		return "user";
	}
	
	//to display the main frame
		// handler method to handle list product and return mode and view
	@GetMapping("/shop")
	public String displayProducts(Model model) {
		List<List<User>> userList=new ArrayList<>();
		List<ConnectionList> conList=connectionListService.getAllConnectionList();
		List<String> parent=new ArrayList<>();
		List<User> newUser=new ArrayList<User>();
		
		//stored all connectionList data in conList
		for(ConnectionList cl:conList)
		{
			System.out.println("if("+GlobalData.loggedProduct.getUserId()+"=="+cl.getChild()+")");
			
			//logged in user id
			String temp=""+GlobalData.loggedProduct.getUserId();
			
			//comparing logged useId with connection List child
			if(temp.equals(cl.getChild()))
			{
				//if logged user = child then saving their parent id
				parent.add(cl.getParent());
			}
		}
		
		//all the parent id is stored in parent 
		for(String p:parent)
		{
			//getting all the users in form of List
			userList.add(userService.getAllUserByUserId(p));
		}
		
		//breaking list and initilizing single single user in newUser
		for(List<User> u:userList)
		{
			for(User user:u)
			{
				newUser.add(user);
			}
		}
		
		model.addAttribute("cartCount",GlobalData.cart.size());
//		model.addAttribute("user", userService.getAllUser());
		model.addAttribute("user", newUser);
		model.addAttribute("category", categoryService.getAllCategory());
		return "shop";
	}
	
	@GetMapping("/consumerShop")
	public String displayProductsConsumer(Model model) {
		List<List<User>> userList=new ArrayList<>();
		List<ConnectionList> conList=connectionListService.getAllConnectionList();
		List<String> broker=new ArrayList<>();
		List<String> parent=new ArrayList<>();
		List<User> newUser=new ArrayList<User>();
		
		//stored all connectionList data in conList
		for(ConnectionList cl:conList)
		{
//			System.out.println("if("+GlobalData.loggedProduct.getUserId()+"=="+cl.getChild()+")");
			
			//logged in user id
			String temp=""+GlobalData.loggedProduct.getUserId();
			
			//comparing logged useId with connection List child
			if(temp.equals(cl.getChild()))
			{
				//if logged user = child then saving their parent id
				broker.add(cl.getParent());
			}
		}
		System.out.println("broker");
		System.out.println(broker);
		
		int tempNum=0;
		//stored all connectionList data in conList
		for(ConnectionList cl:conList)
		{
//			System.out.println("if("+GlobalData.loggedProduct.getUserId()+"=="+cl.getChild()+")");
					
			//logged in user id
			String temp=""+GlobalData.loggedProduct.getUserId();
					
			for(String u:broker)
			{
				if(u.equals(cl.getChild()))
				{
					//if logged user = child then saving their parent id
					parent.add(cl.getParent());
				}
			}
				//comparing logged useId with connection List child
				
				tempNum++;
		}
		System.out.println("parent");
		System.out.println(parent);
		
		//all the parent id is stored in parent 
		for(String p:parent)
		{
			//getting all the users in form of List
			userList.add(userService.getAllUserByUserId(p));
		}
		System.out.println("userlist");
		System.out.println(userList);
		
		//breaking list and initilizing single single user in newUser
		for(List<User> u:userList)
		{
			for(User user:u)
			{
				newUser.add(user);
			}
		}
		System.out.println("new user");
		System.out.println(newUser);
		
		model.addAttribute("cartCount",GlobalData.cart.size());
//		model.addAttribute("user", userService.getAllUser());
		model.addAttribute("user", newUser);
		model.addAttribute("category", categoryService.getAllCategory());
		return "shop";
	}
	
	@GetMapping("/user/new")
	public String createUserForm(Model model) {
		
		// create user object to hold user form data
		User user = new User();
		model.addAttribute("user", user);
		return "create_user";
		
	}
	
	@PostMapping("/user")
	public String saveUser(@ModelAttribute("user") User user) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		user.setUpdateTime(sdf.format(date));
		user.setOwnerId(""+GlobalData.loggedProduct.getUserId());
		userService.saveUser(user);
		return "redirect:/user";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUserForm(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "edit_user";
	}

	@PostMapping("/user/{id}")
	public String updateUser(@PathVariable Integer id,
			@ModelAttribute("user") User user,
			Model model) {
		

		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		// get user from database by id
		User existingUser = userService.getUserById(id);
		existingUser.setId(id);
		existingUser.setYarnId(user.getYarnId());
		existingUser.setName(user.getName());
		existingUser.setCount(user.getCount());
		existingUser.setCategory(user.getCategory());
		existingUser.setRate(user.getRate());
		existingUser.setQuantity(user.getQuantity());
		existingUser.setUpdateTime(sdf.format(date));
		
		// save updated user object
		userService.updateUser(existingUser);
		return "redirect:/user";		
	}
	
	// handler method to handle delete user request
	
	@GetMapping("/user/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userService.deleteUserById(id);
		return "redirect:/user";
	}
	
	
}
