package com.deloitte.estore.repo;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.estore.model.Product;

public class ProductRepoImp1 implements ProductRepo{

	
	@Override
	public boolean addProduct(Product product) throws Exception {
		Connection conn=getDbConnection();
		PreparedStatement ps=conn.prepareStatement("insert into product values(?,?,?)");
		ps.setInt(1, product.getProductId());
		ps.setString(2, product.getProductName());
		ps.setFloat(3, product.getPrice());
		if(ps.executeUpdate()>0)
		{
		return true;
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) throws Exception {
		Connection con=getDbConnection();
		PreparedStatement ps1=con.prepareStatement("delete from product where product_id=?");
		ps1.setInt(1, product.getProductId());
		if(ps1.executeUpdate()>0)
		{
		return true;
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) throws Exception{
		Connection con=getDbConnection();
		PreparedStatement ps1=con.prepareStatement("update product set product_name=? where product_id=?");
		ps1.setString(1, product.getProductName());
		ps1.setInt(2, product.getProductId());
		if(ps1.executeUpdate()>0)
		{
		return true;
		}
		return false;
	}

	@Override
	public Product getProductById(int productId) throws Exception{
		Connection con=getDbConnection();
		Product p=new Product();
		Statement s1=con.createStatement();
		ResultSet rs=s1.executeQuery("select * from product where product_id="+productId);
		if(rs.next())
		{
		p.setProductId(productId);
		p.setProductName(rs.getString("product_name"));
		p.setPrice(rs.getFloat("price"));
		return p;
		}
		
		return null;
	}

	@Override
	public List<Product> getAllProducts() throws Exception{
		Connection con=getDbConnection();
		List<Product> prod=new ArrayList<>();
		PreparedStatement s1=con.prepareStatement("select * from product");
		ResultSet rs=s1.executeQuery();
		while(rs.next())
		{
		Product p=new Product();
		p.setProductId(rs.getInt("product_id"));
		p.setProductName(rs.getString("product_name"));
		p.setPrice(rs.getFloat("price"));
		prod.add(p);
		}
		return prod;
	}

	@Override
	public Connection getDbConnection() throws Exception{
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
		return conn;
	}

	
	
}
