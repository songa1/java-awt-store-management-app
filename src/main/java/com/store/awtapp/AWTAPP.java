/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.store.awtapp;

import java.sql.*;

/**
 *
 * @author CISHAHAYO
 */
public class AWTAPP {
    
    public static void main(String[] args) {
        System.out.println("Hello, Let's open the App!");
        new Login().setVisible(true);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysl://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from users" );
            
            while(rs.next()){
                System.out.println();
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
