package com.deloitte.estore.application;

import com.deloitte.estore.model.Product;

import com.deloitte.estore.service.ProductService;
import com.deloitte.estore.service.ProductServiceImp;
import java.util.*;
public class DeloitteEStoreApp {

	public static void main(String[] args) {
	
		ProductService service=new ProductServiceImp();
		Product p=new Product();
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("Enter the option");
			int n=sc.nextInt();
			switch(n)
			{
			case 1:System.out.println("Enter the details of product:");
					p.setProductId(sc.nextInt());
					p.setProductName(sc.next());
					p.setPrice(sc.nextFloat());
				if(service.addProduct(p))
					{
					System.out.println("Product added...");
					}
				else
					{
					System.out.println("Not added");
					}
			break;
			case 2:System.out.println("Enter the product id which u want to delete:");
					int d=sc.nextInt();
					p.setProductId(d);
				if(service.deleteProduct(p))
					{
					System.out.println("Product deleted...");
					}
				else
					{
					System.out.println("Not deleted");
					}
			break;
			case 3:System.out.println("Enter the new value of product name");
					p.setProductName(sc.next());
					System.out.println("Enter the product id to be updated");
					p.setProductId(sc.nextInt());
					if(service.updateProduct(p))
					{
					System.out.println("Product updated...");
					}
				else
					{
					System.out.println("Not updated");
					}
					break;
			case 4:System.out.println("Enter the product id");
					int s=sc.nextInt();
					p=service.getProductById(s);
					if(p!=null)
					{
					System.out.println("Product id:"+p.getProductId()+"\nProduct Name:"+p.getProductName()+"\nPrice:"+p.getPrice());
					}
				else
					{
					System.out.println("Not id present");
					}
					break;
			case 5:System.out.println("Details are:");
			    	List<Product> prod=new ArrayList<>();
			    	prod=service.getAllProducts();
			    	prod.forEach(System.out::println);
			    	break;
			default:System.out.println("Invalid option");
					
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
