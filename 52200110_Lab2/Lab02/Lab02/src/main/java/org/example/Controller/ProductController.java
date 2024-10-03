package org.example.Controller;

import org.example.DAO.ProductDAO;
import org.example.DTO.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductDAO productDAO;


    public ProductController(Connection conn) {
        productDAO = new ProductDAO(conn);
    }

    public boolean selectOfUser(int number) {
        switch (number){
            case 1:
                readAllProduct();
                return false;
            case 2:
                readProductById();
                return false;
            case 3:
                addNewRow();
                return false;
            case 4:
                updateProduct();
                return false;
            case 5:
                deleteProduct();
                return false;
            case 6:
                exiting();
                return true;
        }
        return true;
    }
//    Function
//    1
    public void readAllProduct(){
        List<Product> resultProducts = productDAO.readAll();
        System.out.println("----------------All Products-----------------");
        resultProducts.forEach(product -> System.out.println(product.toString()));
    }

//    2
    public void readProductById(){
        Scanner sc = new Scanner(System.in);
        int queriedID ;
        do {
            System.out.print("Enter id of product: ");
            while(!sc.hasNextInt()) {
                System.out.print("Please enter integer number for id: ");
                sc.next();
            }
            queriedID = sc.nextInt();
        }while(queriedID<0);
        Product resultProduct =productDAO.read(queriedID);
        if(resultProduct==null) {
            System.out.println("\nNot exists product with id = " + queriedID);
        }else {
            System.out.println();
            System.out.println(resultProduct.toString());
        }
    }

//    3
    public void addNewRow() {
//        Require id
        int newId;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter id of product: ");
            while(!sc.hasNextInt()) {
                System.out.print("Please enter integer number for id: ");
                sc.next();
            }
            newId = sc.nextInt();
            if(productDAO.read(newId) != null) {
                System.out.println("Number id is existed!");
                newId = -1;
            }
        }while(newId<0);
        sc.nextLine();


//        Require name
        String newName;
        System.out.print("Enter name for product: ");
        newName = sc.nextLine();
        List<String> productNames = productDAO.getAllNames();
        while(productNames.contains(newName)) {
            System.out.println("This name is existed!");
            System.out.print("Enter another name: ");
            newName = sc.nextLine();
        }

//        Require price
        int newPrice;
        do {
            System.out.print("Enter price for product: ");
            while(!sc.hasNextInt()) {
                System.out.print("Please enter integer for price: ");
                sc.next();
            }
            newPrice = sc.nextInt();
            sc.nextLine();
        }while(newPrice<0);

//        Require color
        String newColor;
        System.out.print("Enter color for product: ");
        newColor = sc.nextLine();

//        Result Product
        int isCreated = productDAO.add(new Product(newId,newName,newPrice,newColor));
        if(isCreated==-1) {
            System.out.println("Addition new product is fail");
        }else{
            System.out.println("Addition new product is successful");
        }
    }

//    4
    public void updateProduct(){
        Scanner sc = new Scanner(System.in);
        int queriedID ;
        do {
            System.out.print("Enter id of product: ");
            while(!sc.hasNextInt()) {
                System.out.print("Please enter integer number for id: ");
                sc.next();
            }
            queriedID = sc.nextInt();
            sc.nextLine();
        }while(queriedID<0);
        Product resultProduct =productDAO.read(queriedID);
        if(resultProduct==null) {
            System.out.println("\nNot exists product with id = " + queriedID);
        }else {
//            Process what user's insertion
            Product oldProduct = productDAO.read(queriedID);

//            Require name
            String newName;
            System.out.print("Enter name for product: ");
            newName = sc.nextLine();
            List<String> productNames = productDAO.getAllNames();
            if(!oldProduct.getName().equals(newName)) {
                while(productNames.contains(newName)) {
                    System.out.println("This name is existed!");
                    System.out.print("Enter another name: ");
                    newName = sc.nextLine();
                    if(newName.equals(oldProduct.getName())) {
                        break;
                    }
                }
            }


            //        Require price
            int newPrice;
            do {
                System.out.print("Enter price for product: ");
                while(!sc.hasNextInt()) {
                    System.out.print("Please enter integer for price: ");
                    sc.next();
                }
                newPrice = sc.nextInt();
                sc.nextLine();
            }while(newPrice<0);

//        Require color
            String newColor;
            System.out.print("Enter color for product: ");
            newColor = sc.nextLine();

            //        Result Update
            Product newProduct = new Product(queriedID,newName,newPrice,newColor);
            boolean idUpdated = productDAO.update(newProduct);
            if(!idUpdated) {
                System.out.println("Updating product is fail");
            }else{
                System.out.println("Updating  product is successful");
                System.out.println(oldProduct.toString() + " --> " + newProduct.toString());
            }
        }



        }

//    5
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        int queriedID ;
        do {
            System.out.print("Enter id of product: ");
            while(!sc.hasNextInt()) {
                System.out.print("Please enter integer number for id: ");
                sc.next();
            }
            queriedID = sc.nextInt();
            sc.nextLine();
        }while(queriedID<0);
        Product resultProduct =productDAO.read(queriedID);
        if(resultProduct==null) {
            System.out.println("\nNot exists product with id = " + queriedID);
        }else {
            if(productDAO.delete(queriedID)) {
                System.out.println("Deleting product has id: " + queriedID + " is successful");
            }else {
                System.out.println("Deleting product has id: " + queriedID + " is fail");
            }
        }
    }


//    6
    public void exiting(){
        System.out.println("Program is exiting...");
    }

}
