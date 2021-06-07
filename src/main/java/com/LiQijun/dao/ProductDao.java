package com.LiQijun.dao;

import com.LiQijun.model.Product;
import com.LiQijun.model.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao{
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) {
        int n = 0;
        String sql = "delete from product where ProductId=?";
        PreparedStatement pt = null;
        try {
            pt = con.prepareStatement(sql);
            pt.setInt(1, productId);
            n = pt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (n > 0) {
            return n;
        }
        return 0;
    }

    @Override
    public int update(Product instance, Connection con) {
        int n=0;
        try{
            String sql="update product set "
                    +"ProductName='"+instance.getProductName()
                    +"',ProductDescription='"+instance.getProductDescription()
                    +"',picture='"+instance.getPicture()
                    +"',price='"+instance.getPrice()
                    +"',CategoryId='"+instance.getCategoryId()
                    +"'where ProductId='"+instance.getProductId()+"'";
            PreparedStatement ps= con.prepareStatement(sql);
            n=ps.executeUpdate();
            ps.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) {
        String sql="select * from product where ProductId='"+productId+"'";
        Product product=null;
        try{
        PreparedStatement ps= con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            product = new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setPrice(rs.getDouble("price"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setCategoryId(rs.getInt("CategoryId"));
        }
        ps.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) {
        String sql="select * from product where ProductId='"+categoryId+"'";
        List<Product> productList=new ArrayList<Product>();
        Product product=new Product();
        try{
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPicture(rs.getBinaryStream("picture"));
                product.setPrice(rs.getDouble("price"));
                product.setProductDescription(rs.getString("Description"));
                product.setCategoryId(rs.getInt("CategoryId"));
                productList.add(product);
            }
            ps.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        String sql="select * from product ";
        List<Product> productList=new ArrayList<Product>();
        Product product=new Product();
        try{
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("price"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setCategoryId(rs.getInt("CategoryId"));
                productList.add(product);
            }
            System.out.println("product findAll successful!!");
            ps.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        return null;
    }
    public byte[] getPictureById(Integer productId,Connection con)throws SQLException{
        byte[] imgByte=null;
        String sql="select picture from product where productId=?";
        PreparedStatement pt =con.prepareStatement(sql);
        pt.setInt(1,productId);
        ResultSet rs=pt.executeQuery();
        while (rs.next()) {
            Blob blob=rs.getBlob("picture");
            imgByte=blob.getBytes(1,(int)blob.length());
        }
        return imgByte;
    }
}
