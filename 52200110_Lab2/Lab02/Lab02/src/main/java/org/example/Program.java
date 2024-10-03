package org.example;


import org.example.Controller.ProductController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Program
{
    public static void main( String[] args ) {
        Connection conn;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/product";
            conn = DriverManager.getConnection(url,"root","");
            System.out.println("Success Connection");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

//        Controller
        ProductController controller = new ProductController(conn);

//        User Interaction
        while(true){
            hintTable();

            Scanner sc = new Scanner(System.in);
            int numInteraction;
            System.out.print("Your choise: ");

            do {
                System.out.print("Enter a number from 1 to 6: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Please enter a integer number: ");
                    sc.next();
                }
                numInteraction = sc.nextInt();

            } while (numInteraction < 1 || numInteraction > 6);
            if(controller.selectOfUser(numInteraction)){
                break;
            }
        }





        
    }

    public static void hintTable() {
        System.out.println("\n1. Read all products");
        System.out.println("2. Read detail of a product by id");
        System.out.println("3. Add a new product");
        System.out.println("4. Update a product");
        System.out.println("5. Delete a product by id");
        System.out.println("6. Exit");
    }


}
