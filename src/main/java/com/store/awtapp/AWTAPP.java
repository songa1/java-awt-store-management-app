/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.store.awtapp;

/**
 *
 * @author CISHAHAYO
 */
public class AWTAPP {
    public String driver = "com.mysql.cj.jdbc.Driver";
    public String url = "jdbc:mysl://localhost:3000/test";
    
    public static void main(String[] args) {
        System.out.println("Hello, Let's open the App!");
        new Login().setVisible(true);
    }
}
