package com.store.awtapp;

import java.sql.*;
import java.util.HashMap;
import java.util.*;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    
    HashMap<String, Integer> companies = new HashMap<>();

    public Login() {
        initComponents();
        setValues();
    }
    
    public final void setValues(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from company" );
            int i = 1;
            String[] companie = new String[5];
            while(rs.next()){
                companySelect.addItem(rs.getString(2));
                companie[i] = rs.getString(2);
                companies.put( rs.getString(2), rs.getInt(1) );
                i++;
            }
            System.out.println(Arrays.toString(companie));
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        welcomeMsg = new java.awt.Label();
        loginForm = new java.awt.Panel();
        companySelectLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        emailFieldLabel = new javax.swing.JLabel();
        emailFieldLabel1 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        companySelect = new javax.swing.JComboBox<>();
        passwordField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("login"); // NOI18N

        panel1.setBackground(new java.awt.Color(0, 100, 0));

        welcomeMsg.setFont(new java.awt.Font("Lato", 0, 48)); // NOI18N
        welcomeMsg.setForeground(new java.awt.Color(255, 255, 255));
        welcomeMsg.setText("MY SHOP");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(welcomeMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(welcomeMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        welcomeMsg.getAccessibleContext().setAccessibleDescription("");

        companySelectLabel.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        companySelectLabel.setText("Select your company");

        emailField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        emailFieldLabel.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        emailFieldLabel.setText("Email Address");

        emailFieldLabel1.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        emailFieldLabel1.setText("Password");

        loginButton.setBackground(new java.awt.Color(0, 100, 0));
        loginButton.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        companySelect.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        passwordField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        javax.swing.GroupLayout loginFormLayout = new javax.swing.GroupLayout(loginForm);
        loginForm.setLayout(loginFormLayout);
        loginFormLayout.setHorizontalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFormLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(emailFieldLabel1)
                        .addComponent(emailFieldLabel)
                        .addComponent(companySelectLabel)
                        .addComponent(emailField)
                        .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                        .addComponent(companySelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        loginFormLayout.setVerticalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(companySelectLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(companySelect, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emailFieldLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(emailFieldLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from users" );
            String message = "";
            while(rs.next()){
                if(companies.get(companySelect.getSelectedItem()) != rs.getInt(5)){
                    message = "Select the right company!";
                }else{
                    if(emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
                        message = "Credentials can not be empty!";
                    }else if(emailField.getText().equals(rs.getString(3)) && passwordField.getText().equals(rs.getString(4))){
                        message = "Welcome " + rs.getString("userName") + "!";
                        new Dashboard(rs.getInt(1), rs.getString(7), (String) companySelect.getSelectedItem(), rs.getInt(5)).setVisible(true);
                        emailField.setText("");
                        passwordField.setText("");
                        setVisible(false);
                    }else{
                        message = "Email or Password provided is wrong!";
                        System.out.println("Failed");
                    }    
                }
            }
            JOptionPane.showMessageDialog(this,message);
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
               
    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> companySelect;
    private javax.swing.JLabel companySelectLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailFieldLabel;
    private javax.swing.JLabel emailFieldLabel1;
    private javax.swing.JButton loginButton;
    private java.awt.Panel loginForm;
    private java.awt.Panel panel1;
    private javax.swing.JTextField passwordField;
    private java.awt.Label welcomeMsg;
    // End of variables declaration//GEN-END:variables
}
