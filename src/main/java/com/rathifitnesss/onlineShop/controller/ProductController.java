package com.rathifitnesss.onlineShop.controller;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rathifitnesss.Global.GlobalData;
import com.rathifitnesss.onlineShop.entity.ConnectionList;
import com.rathifitnesss.onlineShop.entity.Product;
import com.rathifitnesss.onlineShop.service.CategoryService;
import com.rathifitnesss.onlineShop.service.ConnectionListService;
import com.rathifitnesss.onlineShop.service.ProductService;

@CrossOrigin(origins= {"http://localhost:63465/"})
@Controller
//@RestController
//@RequestMapping
public class ProductController {
	
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/Photos";

	 @Autowired
	 ProductService productService;
	 @Autowired
	 CategoryService categoryService;
	 @Autowired
	 ConnectionListService connectionListService;
	
	
	// handler method to handle list product and return mode and view
	@GetMapping("/admin/products")
	public String listProducts(Model model) {
		model.addAttribute("product", productService.getAllProduct());
		model.addAttribute("category", categoryService.getAllCategory());
		return "login";
//		return productService.getAllProduct();
	}
	
	// handler method to handle list product and return mode and view
		@GetMapping("/admin/productsByCategory")
		public String listProductsByCategory(Model model) {
			Product newProduct=new Product();
			List<Product> passProduct=new ArrayList<>();
			List<Product> list=productService.getAllProduct();
			List<ConnectionList> conList=connectionListService.getAllConnectionList();
			
				//getting connection list -> child = broker id / parent = supplier id
				for(ConnectionList cl:conList)
				{
					System.out.println("if("+GlobalData.loggedProduct.getUserId()+"=="+cl.getParent()+")");
					
					//logged in user id
					String temp=""+GlobalData.loggedProduct.getUserId();
					if(temp.equals(cl.getParent()))
					{
						System.out.println("Product is getting added");
							passProduct.add(productService.getAllProductByUserId(Integer.parseInt(cl.getChild())));	
					}
				}
			
			model.addAttribute("product",passProduct);
			model.addAttribute("category", categoryService.getAllCategory());
			return "product";
//			return productService.getAllProduct();
		}

		@GetMapping("/admin/productsByCategorySupplier")
		public String listProductsByCategorySupplier(Model model) {
			Product newProduct=new Product();
			List<Product> passProduct=new ArrayList<>();
			List<Product> list=productService.getAllProduct();
			List<Product> seperateParent=new ArrayList<>();
			List<ConnectionList> conList=connectionListService.getAllConnectionList();
			
				//getting connection list -> child = broker id / parent = supplier id
				for(ConnectionList cl:conList)
				{
					System.out.println("if("+GlobalData.loggedProduct.getUserId()+"=="+cl.getChild()+")");
					
					//logged in user id
					String temp=""+GlobalData.loggedProduct.getUserId();
					if(temp.equals(cl.getChild()))
					{
//						System.out.println("Product is getting added");
							passProduct.add(productService.getAllProductByUserId(Integer.parseInt(cl.getParent())));
//						seperateParent.add(productService.getAllProductByUserId(Integer.parseInt(cl.getParent())));	
						
					}
				}
//				for(Product sep:seperateParent)
//				{
//					//to check the product are supplier
//					if(sep.getCategory().getName().equals("SUPPLIER"))
//					{
//						passProduct.add(sep);
//					}
//				}
			
			model.addAttribute("product",passProduct);
			model.addAttribute("category", categoryService.getAllCategory());
			return "product";
//			return productService.getAllProduct();
		}

		
		
//	//to display the main frame
//	// handler method to handle list product and return mode and view
//		@GetMapping("/shop")
//		public String displayProducts(Model model) {
//			model.addAttribute("cartCount",GlobalData.cart.size());
//			model.addAttribute("product", productService.getAllProduct());
//			model.addAttribute("category", categoryService.getAllCategory());
//			return "shop";
//		}
		
		//short by category
		@GetMapping("/shop/category/{id}")
		public String shopByCategory(Model model,@PathVariable int id) {
			model.addAttribute("cartCount",GlobalData.cart.size());
			model.addAttribute("product", productService.getAllProductByCategory(id));
			model.addAttribute("category", categoryService.getAllCategory());
			return "shop";
		}
		
		//to view product
		@GetMapping("/shop/viewproduct/{id}")
		public String viewProduct(@PathVariable Integer id, Model model) {
			model.addAttribute("cartCount",GlobalData.cart.size());
			model.addAttribute("product", productService.getProductById(id));
			return "viewProduct";
		}		
	
	@GetMapping("/admin/product/new")
	public String createProductForm(Model model) {
		
		// create product object to hold product form data
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("category", categoryService.getAllCategory());
		return "create_product";
	}
	
	@GetMapping("/product/new")
	public String addNewProduct(Model model) {
		
		// create product object to hold product form data
		Product product = new Product();
		if(GlobalData.loggedProduct.getCategory().getId()==0)
			model.addAttribute("product", productService.getAllProductByCategory(1));
		else if(GlobalData.loggedProduct.getCategory().getId()==1)
			model.addAttribute("product", productService.getAllProductByCategory(2));
		model.addAttribute("category", categoryService.getAllCategory());
		return "search_product";
	}
	
	@GetMapping("/product/newBroker")
	public String addNewProductBroker(Model model) {
		System.out.println("inside new Broker");
		// create product object to hold product form data
		Product product = new Product();
		 if(GlobalData.loggedProduct.getCategory().getId()==1)
			model.addAttribute("product", productService.getAllProductByCategory(0));
		model.addAttribute("category", categoryService.getAllCategory());
		return "search_product";
	}
	
//	@PostMapping("/admin/product")
//	public String saveProduct(@ModelAttribute("product") Product product) {
//		productService.saveProduct(product);
//		return "redirect:/admin/products";
//	}
	
	@PostMapping("/admin/products")
	public String saveProduct(@ModelAttribute("product") Product product,
								@RequestParam("productImage")MultipartFile file,
								@RequestParam("imgName")String imgName) throws IOException {
		
		System.out.println("inside admin/products");
		Product newProduct=new Product();
//		newProduct.setId(product.getCategory().getId());
		
		//geneterate usere id
		Random rand = new Random(); 
		int num = rand.nextInt(9000000) + 1000000; 
		newProduct.setUserId(num);
		System.out.println(product.getMobile());
		
		newProduct.setCategory(categoryService.getCategoryById(product.getCategory().getId()));
		newProduct.setName(product.getName());
		newProduct.setFirmName(product.getFirmName());
		newProduct.setMobile(product.getMobile());
		newProduct.setEmail(product.getEmail());
		newProduct.setPassword(product.getPassword());
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		newProduct.setDate(sdf.format(date));
		
		String imageUUID;
		imgName="Blank";
		
		if(!file.isEmpty())
		{
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
			Files.write(fileNameAndPath,file.getBytes());
		}
		else
		{
			imageUUID=imgName;
		}
		
		product.setPhoto(imageUUID);
		productService.saveProduct(newProduct);
		
		return "redirect:/admin/products";
	}
	
	
	//got to edit page 
	@GetMapping("/admin/product/edit/{id}")
	public String editProductForm(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		model.addAttribute("category", categoryService.getAllCategory());
		return "edit_product";
	}
	
	@GetMapping("/admin/product/add/{id}")
	public String addProductForm(@PathVariable Integer id, Model model) {
		String adminPage="";
		ConnectionList list=new ConnectionList();
		list.setParent(""+GlobalData.loggedProduct.getUserId());
		list.setChild(""+id);
		System.out.println(GlobalData.loggedProduct);
		connectionListService.saveConnectionList(list);
		
		if(GlobalData.loggedProduct.getCategory().getName().equals("SUPPLIER"))
			adminPage="adminhomeSupplier";
		else if(GlobalData.loggedProduct.getCategory().getName().equals("BROKER"))
			adminPage="adminhomeBroker";
		else if(GlobalData.loggedProduct.getCategory().getName().equals("CONSUMER"))
			adminPage="adminhomeConsumer";
		
		return adminPage;
	}

	//handel to update the product
	@PostMapping("/admin/product/{id}")
	public String updateProduct(@PathVariable Integer id,
			@ModelAttribute("product") Product product,
			Model model) {
		
		// get product from database by id
		Product existingProduct = productService.getProductById(id);
//		existingProduct.setId(id);
//		existingProduct.setCategory(product.getCategory());
//		existingProduct.setName(product.getName());
//		existingProduct.setDescription(product.getDescription());
//		existingProduct.setPrice(product.getPrice());
//		existingProduct.setPhoto(product.getPhoto());
		
		existingProduct.setCategory(categoryService.getCategoryById(product.getCategory().getId()));
		existingProduct.setName(product.getName());
		existingProduct.setFirmName(product.getFirmName());
		existingProduct.setMobile(product.getMobile());
		existingProduct.setEmail(product.getEmail());
		existingProduct.setPassword(product.getPassword());
		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		existingProduct.setDate(sdf.format(date));

		existingProduct.setPhoto(product.getPhoto());
		
		// save updated product object
		productService.updateProduct(existingProduct);
		return "redirect:/admin/products";		
	}
	
	// handler method to handle delete product request
	@GetMapping("/admin/product/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}
	
}
