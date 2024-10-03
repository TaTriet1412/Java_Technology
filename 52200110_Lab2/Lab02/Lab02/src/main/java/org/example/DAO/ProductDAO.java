package org.example.DAO;

import org.example.DTO.Product;
import org.example.Repository.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDAO  implements Repository <Product,Integer> {
    private final Connection conn;
    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer add(Product item) {
        try {
            Statement stmt = conn.createStatement();
            String insertNewProduct =
                    "insert into product(id,name,price,color) values (" +
                            item.getId() + "," +
                            haveStringSign(item.getName()) + "," +
                            item.getPrice() + "," +
                            haveStringSign(item.getColor())+ ")";
            stmt.execute(insertNewProduct);
            return item.getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Product> readAll() {
        List<Product> productsList = new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            String getAllProduct = "select * from product";
            ResultSet rst;
            rst = stmt.executeQuery(getAllProduct);

            while(rst.next()){
                int queriedId = rst.getInt("id");
                String queriedName = rst.getNString("name");
                int queriedPrice = rst.getInt("price");
                String queriedColor = rst.getNString("color");

                productsList.add(new Product(queriedId,queriedName,queriedPrice,queriedColor));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return productsList;
    }

    @Override
    public Product read(Integer id)  {
        try {
            Statement stmt = conn.createStatement();
            String getProduct = "select * from product where id = " + id;
            ResultSet rst;
            rst = stmt.executeQuery(getProduct);

            if(rst.next()){
                int queriedId = rst.getInt("id");
                String queriedName = rst.getNString("name");
                int queriedPrice = rst.getInt("price");
                String queriedColor = rst.getNString("color");

                return (new Product(queriedId,queriedName,queriedPrice,queriedColor));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Product item) {
        try {
            Statement stmt = conn.createStatement();
            String updateProduct =
                    "update product set " +
                            "name = " + haveStringSign(item.getName()) +
                            ", price = " + item.getPrice() +
                            ", color = " + haveStringSign(item.getColor()) +
                            " where id = " + item.getId();
            System.out.println(updateProduct);
            stmt.execute(updateProduct);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            Statement stmt = conn.createStatement();
            String deleteProduct = "delete from product where id = " + id;
            System.out.println(deleteProduct);
            stmt.execute(deleteProduct);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<String> getAllNames(){
        return this.readAll().stream().map((Product::getName)).collect(Collectors.toList());
    }

    public static String haveStringSign(String str){
        return '"' + str + '"';
    }
}
