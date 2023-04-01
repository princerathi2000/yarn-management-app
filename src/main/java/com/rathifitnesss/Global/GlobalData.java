package com.rathifitnesss.Global;

import java.util.ArrayList;
import java.util.List;

import com.rathifitnesss.onlineShop.entity.Product;
import com.rathifitnesss.onlineShop.entity.User;

public class GlobalData {

	public static Product loggedProduct=new Product();
	public static List<Product> cart;
	    static 
	    {
			cart=new ArrayList<Product>();
		}
	
}
