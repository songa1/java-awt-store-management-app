/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.store.awtapp;

/**
 *
 * @author CISHAHAYO
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     * @param email
     * @param password
     * @param company
     */
    public Dashboard(String email, String password, String company) {
        initComponents();
        ops(company);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu3 = new java.awt.Menu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        stockInMenu = new javax.swing.JMenu();
        inStockLink = new javax.swing.JMenu();
        outStockLink = new javax.swing.JMenu();
        currentStockLink = new javax.swing.JMenu();
        userName = new javax.swing.JMenu();
        companyName = new javax.swing.JMenu();

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu1.setLabel("File");
        menuBar2.add(menu1);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        file.setText("Items");
        jMenuBar1.add(file);

        stockInMenu.setText("Stock");

        inStockLink.setText("In");
        stockInMenu.add(inStockLink);

        outStockLink.setText("Out");
        stockInMenu.add(outStockLink);

        currentStockLink.setText("Current");
        stockInMenu.add(currentStockLink);

        jMenuBar1.add(stockInMenu);

        userName.setText("About");
        jMenuBar1.add(userName);
        jMenuBar1.add(companyName);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void ops(String c){
        companyName.setText("Company: " + c);
        userName.setText("Username");
    }
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu companyName;
    private javax.swing.JMenu currentStockLink;
    private javax.swing.JMenu file;
    private javax.swing.JMenu inStockLink;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private javax.swing.JMenu outStockLink;
    private javax.swing.JMenu stockInMenu;
    private javax.swing.JMenu userName;
    // End of variables declaration//GEN-END:variables
}
