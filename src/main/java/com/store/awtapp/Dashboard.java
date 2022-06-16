package com.store.awtapp;

import static java.lang.Integer.parseInt;
import java.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {
    
    HashMap<String, Integer> roles = new HashMap<>();
    HashMap<Integer, String> rolesNames = new HashMap<>();
    HashMap<String, Integer> companies = new HashMap<>();
    HashMap<String, Integer> users = new HashMap<>();
    HashMap<Integer, String> items = new HashMap<>();
    HashMap<String, Integer> itemsId = new HashMap<>();
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    LocalDateTime now = LocalDateTime.now();  
    
    HashMap<String, Integer> itemsList = new HashMap<>();
    
    int companyId;
    int userId;

    /**
     * Creates new form Dashboard
     * @param idUser
     * @param name
     * @param company
     * @param companyId
     */
    public Dashboard(int idUser, String name, String company, int companyId) {
        
        initComponents();
        
        // Set Names in the right corner of top Nav
        loggedInUser.setText(name);
        userCompany.setText("Company: "+company);
        usersTableTitle.setText("List of users that belongs to " + company);
        itemsTableTitle.setText("List of items in " + company + "'s stock");
        salesTableTitle.setText("List of sales for " + company);
        
        // Changing panes which should be open
        homePagePanel.setVisible(true);
        itemsPanel.setVisible(false);
        usersPanel.setVisible(false);
        companyPanel.setVisible(false);
        
        // Setting up Company Id and user id
        this.companyId = companyId;
        this.userId = idUser;
        
        // Fetching
        fetchRoles();
        fetchUsers();
        fetchCompanies();
        fetchItems();
        fetchSales();
        
    }
    
    public final void fetchUsers(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from users WHERE companyId =" + this.companyId);
            
            String[] columns = {"Name", "Email", "Role"};
            String[][] data = new String[20][3];
            int i = 0;
            while(rs.next()){
                String roleName = rolesNames.get(rs.getInt(1));
                data[i][0] = rs.getString("userFullName");
                data[i][1] = rs.getString("userEmail");
                data[i][2] = roleName;
                users.put(rs.getString(2), rs.getInt(1));
                i++;
            }
            usersTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    public final void fetchRoles(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from roles" );
            
            while(rs.next()){
                selectUserRole.addItem(rs.getString(2));
                roles.put(rs.getString(2), rs.getInt(1));
                rolesNames.put(rs.getInt(1), rs.getString(2));
            }
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    public final void fetchCompanies(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from company" );
            
            while(rs.next()){
                if(this.companyId == rs.getInt(1)){
                    selectUserCompany.addItem(rs.getString(2));
                }
                companies.put(rs.getString(2), rs.getInt(1));
            }
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    public final void fetchItems(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from items WHERE companyId =" + this.companyId);
            
            String[] columns = {"Name", "Price"};
            String[][] data = new String[20][4];
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getString("itemName");
                data[i][1] = rs.getString("itemPrice");
                items.put(rs.getInt(1), rs.getString("itemName"));
                itemsId.put(rs.getString("itemName"), rs.getInt(1));
                selectItemField.addItem(rs.getString("itemName"));
                i++;
            }
            itemsTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }
    
    public final void fetchSales(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select * from sales WHERE companyId =" + this.companyId);
            
            String[] columns = {"ID","Item", "Quantity", "Price/Unit", "Total Price"};
            String[][] data = new String[20][5];
            int i = 0;
            while(rs.next()){
                data[i][0] = rs.getString(1);
                data[i][1] = items.get(rs.getInt(1));
                data[i][2] = rs.getString(5);
                data[i][3] = rs.getString(6);
                data[i][4] = rs.getString(7);
                
                i++;
            }
            salesTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topNavigation = new javax.swing.JPanel();
        goToItemsButton = new javax.swing.JButton();
        goToCompanies = new javax.swing.JButton();
        goToUsers = new javax.swing.JButton();
        goToMain = new javax.swing.JButton();
        loggedInUser = new javax.swing.JLabel();
        userCompany = new javax.swing.JLabel();
        mainPane = new javax.swing.JPanel();
        usersPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        emailField = new javax.swing.JTextField();
        namesField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
        selectUserCompany = new javax.swing.JComboBox<>();
        selectUserRole = new javax.swing.JComboBox<>();
        addNewUserButton = new javax.swing.JButton();
        cancelUser = new javax.swing.JButton();
        updateUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        usersTableTitle = new javax.swing.JLabel();
        companyPanel = new javax.swing.JPanel();
        quantityField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
        totalPriceField = new javax.swing.JTextField();
        unitPriceField = new javax.swing.JTextField();
        selectItemField = new javax.swing.JComboBox<>();
        newSaleButton = new javax.swing.JButton();
        updateCompanyButton = new javax.swing.JButton();
        deleteSaleButton = new javax.swing.JButton();
        cancelComapany = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        salesTableTitle = new javax.swing.JLabel();
        itemsPanel = new javax.swing.JPanel();
        addNewUserButton8 = new javax.swing.JButton();
        deleteItemButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        itemNameField = new javax.swing.JTextField();
        actionsLabel = new javax.swing.JLabel();
        addNewItem = new javax.swing.JButton();
        itemPriceLabel = new javax.swing.JLabel();
        addNewUserButton11 = new javax.swing.JButton();
        itemPriceField = new javax.swing.JTextField();
        itemNameLabel = new javax.swing.JLabel();
        itemsTableTitle = new javax.swing.JLabel();
        homePagePanel = new javax.swing.JPanel();
        welcomeMessage = new javax.swing.JLabel();
        declarationTitle = new javax.swing.JLabel();
        supposedlyImage = new javax.swing.JInternalFrame();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        getStartedButton = new javax.swing.JButton();
        listOfItemsWeHave = new javax.swing.JScrollPane();
        listOne = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        topNavigation.setBackground(new java.awt.Color(0, 100, 0));

        goToItemsButton.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        goToItemsButton.setText("Items");
        goToItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToItemsButtonActionPerformed(evt);
            }
        });

        goToCompanies.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        goToCompanies.setText("Sales");
        goToCompanies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToCompaniesActionPerformed(evt);
            }
        });

        goToUsers.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        goToUsers.setText("Users");
        goToUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToUsersActionPerformed(evt);
            }
        });

        goToMain.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        goToMain.setText("Main");
        goToMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToMainActionPerformed(evt);
            }
        });

        loggedInUser.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        loggedInUser.setForeground(new java.awt.Color(255, 255, 255));

        userCompany.setForeground(new java.awt.Color(245, 255, 255));

        javax.swing.GroupLayout topNavigationLayout = new javax.swing.GroupLayout(topNavigation);
        topNavigation.setLayout(topNavigationLayout);
        topNavigationLayout.setHorizontalGroup(
            topNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topNavigationLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(goToMain, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goToItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goToCompanies, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goToUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1207, Short.MAX_VALUE)
                .addGroup(topNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loggedInUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userCompany, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(28, 28, 28))
        );
        topNavigationLayout.setVerticalGroup(
            topNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topNavigationLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(topNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topNavigationLayout.createSequentialGroup()
                        .addComponent(loggedInUser)
                        .addGap(2, 2, 2)
                        .addComponent(userCompany))
                    .addGroup(topNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(goToCompanies, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(goToUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(goToMain, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(goToItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        mainPane.setLayout(new javax.swing.OverlayLayout(mainPane));

        usersPanel.setBackground(new java.awt.Color(255, 255, 255));

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(usersTable);

        emailField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        namesField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        namesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namesFieldActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        selectUserCompany.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        selectUserRole.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        addNewUserButton.setBackground(new java.awt.Color(0, 100, 0));
        addNewUserButton.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        addNewUserButton.setForeground(new java.awt.Color(255, 255, 255));
        addNewUserButton.setText("Add");
        addNewUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewUserButtonActionPerformed(evt);
            }
        });

        cancelUser.setBackground(new java.awt.Color(255, 0, 51));
        cancelUser.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        cancelUser.setForeground(new java.awt.Color(255, 255, 255));
        cancelUser.setText("Cancel");
        cancelUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelUserActionPerformed(evt);
            }
        });

        updateUserButton.setBackground(new java.awt.Color(0, 100, 0));
        updateUserButton.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        updateUserButton.setForeground(new java.awt.Color(255, 255, 255));
        updateUserButton.setText("Update");
        updateUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteUserButton.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        deleteUserButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteUserButton.setText("Delete");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N
        jLabel1.setText("ACTIONS");

        jLabel2.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel2.setText("User Name");

        jLabel3.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel4.setText("Select a company");

        jLabel7.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel7.setText("Password");

        jLabel8.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel8.setText("Select user's role");

        usersTableTitle.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(usersPanelLayout.createSequentialGroup()
                                    .addComponent(addNewUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(updateUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(passwordField)
                                .addComponent(namesField)
                                .addComponent(selectUserCompany, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(selectUserRole, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailField)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(usersPanelLayout.createSequentialGroup()
                                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(374, 374, 374))
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addComponent(usersTableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 1297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(usersTableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namesField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(2, 2, 2)
                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectUserCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addNewUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        mainPane.add(usersPanel);

        companyPanel.setBackground(new java.awt.Color(255, 255, 255));

        quantityField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        salesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(salesTable);

        totalPriceField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        unitPriceField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        selectItemField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        newSaleButton.setBackground(new java.awt.Color(0, 100, 0));
        newSaleButton.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        newSaleButton.setForeground(new java.awt.Color(255, 255, 255));
        newSaleButton.setText("Add");
        newSaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSaleButtonActionPerformed(evt);
            }
        });

        updateCompanyButton.setBackground(new java.awt.Color(0, 100, 0));
        updateCompanyButton.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        updateCompanyButton.setForeground(new java.awt.Color(255, 255, 255));
        updateCompanyButton.setText("Update");
        updateCompanyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCompanyButtonActionPerformed(evt);
            }
        });

        deleteSaleButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteSaleButton.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        deleteSaleButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteSaleButton.setText("Delete");
        deleteSaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSaleButtonActionPerformed(evt);
            }
        });

        cancelComapany.setBackground(new java.awt.Color(255, 0, 51));
        cancelComapany.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        cancelComapany.setForeground(new java.awt.Color(255, 255, 255));
        cancelComapany.setText("Cancel");
        cancelComapany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelComapanyActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N
        jLabel9.setText("ACTIONS");

        jLabel10.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel10.setText("Quantity");

        jLabel11.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel11.setText("Unit Price");

        jLabel12.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel12.setText("Select an item");

        jLabel13.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel13.setText("Total Price");

        salesTableTitle.setBackground(new java.awt.Color(255, 255, 255));
        salesTableTitle.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N

        javax.swing.GroupLayout companyPanelLayout = new javax.swing.GroupLayout(companyPanel);
        companyPanel.setLayout(companyPanelLayout);
        companyPanelLayout.setHorizontalGroup(
            companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(salesTableTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1297, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(companyPanelLayout.createSequentialGroup()
                        .addGroup(companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(companyPanelLayout.createSequentialGroup()
                                .addComponent(newSaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteSaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelComapany, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(totalPriceField)
                            .addComponent(quantityField)
                            .addComponent(selectItemField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(unitPriceField)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(companyPanelLayout.createSequentialGroup()
                        .addGroup(companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        companyPanelLayout.setVerticalGroup(
            companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(companyPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(salesTableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(companyPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(2, 2, 2)
                        .addComponent(unitPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(4, 4, 4)
                        .addComponent(totalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectItemField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(companyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newSaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelComapany, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteSaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(194, Short.MAX_VALUE))
        );

        mainPane.add(companyPanel);

        itemsPanel.setBackground(new java.awt.Color(255, 255, 255));

        addNewUserButton8.setBackground(new java.awt.Color(0, 100, 0));
        addNewUserButton8.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        addNewUserButton8.setForeground(new java.awt.Color(255, 255, 255));
        addNewUserButton8.setText("Update");
        addNewUserButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewUserButton8ActionPerformed(evt);
            }
        });

        deleteItemButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteItemButton.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        deleteItemButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteItemButton.setText("Delete");
        deleteItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteItemButtonActionPerformed(evt);
            }
        });

        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(itemsTable);

        itemNameField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        actionsLabel.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N
        actionsLabel.setText("ACTIONS");

        addNewItem.setBackground(new java.awt.Color(0, 100, 0));
        addNewItem.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        addNewItem.setForeground(new java.awt.Color(255, 255, 255));
        addNewItem.setText("Add");
        addNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewItemActionPerformed(evt);
            }
        });

        itemPriceLabel.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        itemPriceLabel.setText("Item Price");

        addNewUserButton11.setBackground(new java.awt.Color(255, 0, 51));
        addNewUserButton11.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        addNewUserButton11.setForeground(new java.awt.Color(255, 255, 255));
        addNewUserButton11.setText("Cancel");
        addNewUserButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewUserButton11ActionPerformed(evt);
            }
        });

        itemPriceField.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N

        itemNameLabel.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        itemNameLabel.setText("Item Name");

        itemsTableTitle.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N

        javax.swing.GroupLayout itemsPanelLayout = new javax.swing.GroupLayout(itemsPanel);
        itemsPanel.setLayout(itemsPanelLayout);
        itemsPanelLayout.setHorizontalGroup(
            itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(itemsTableTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1297, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemsPanelLayout.createSequentialGroup()
                        .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(itemsPanelLayout.createSequentialGroup()
                                .addComponent(addNewItem, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addNewUserButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addNewUserButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(itemNameField)
                            .addComponent(itemPriceField)
                            .addComponent(actionsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(itemsPanelLayout.createSequentialGroup()
                        .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemNameLabel)
                            .addComponent(itemPriceLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        itemsPanelLayout.setVerticalGroup(
            itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemsPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(itemsTableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(itemsPanelLayout.createSequentialGroup()
                        .addComponent(actionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(itemNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemPriceLabel)
                        .addGap(2, 2, 2)
                        .addComponent(itemPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(itemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addNewItem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewUserButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewUserButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(194, Short.MAX_VALUE))
        );

        mainPane.add(itemsPanel);

        welcomeMessage.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N
        welcomeMessage.setText("Welcome to MyShop Admin Dashboard!");

        declarationTitle.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        declarationTitle.setText("This is the Admin's dashboard. From here, we will be able to do the following:");

        supposedlyImage.setBackground(new java.awt.Color(255, 255, 255));
        supposedlyImage.setVisible(true);

        jLabel5.setFont(new java.awt.Font("Lato", 1, 48)); // NOI18N
        jLabel5.setText("MySHOP");

        jLabel6.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        jLabel6.setText("Navigate using the buttons in the top Navbar, or click on the button below!");

        getStartedButton.setBackground(new java.awt.Color(0, 100, 0));
        getStartedButton.setFont(new java.awt.Font("Lato", 1, 48)); // NOI18N
        getStartedButton.setForeground(new java.awt.Color(255, 255, 255));
        getStartedButton.setText("GET STARTED!");
        getStartedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getStartedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout supposedlyImageLayout = new javax.swing.GroupLayout(supposedlyImage.getContentPane());
        supposedlyImage.getContentPane().setLayout(supposedlyImageLayout);
        supposedlyImageLayout.setHorizontalGroup(
            supposedlyImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, supposedlyImageLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(40, 40, 40))
            .addGroup(supposedlyImageLayout.createSequentialGroup()
                .addGroup(supposedlyImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(supposedlyImageLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(getStartedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(supposedlyImageLayout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        supposedlyImageLayout.setVerticalGroup(
            supposedlyImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supposedlyImageLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel5)
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addGap(61, 61, 61)
                .addComponent(getStartedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        listOne.setFont(new java.awt.Font("Lato", 0, 24)); // NOI18N
        listOne.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "View Items available in all companies", "Add a new item in any company", "Create a new company", "Create users and assign them to companies", "We will also be able to delete whatever we add." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listOfItemsWeHave.setViewportView(listOne);

        javax.swing.GroupLayout homePagePanelLayout = new javax.swing.GroupLayout(homePagePanel);
        homePagePanel.setLayout(homePagePanelLayout);
        homePagePanelLayout.setHorizontalGroup(
            homePagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listOfItemsWeHave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(welcomeMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                    .addGroup(homePagePanelLayout.createSequentialGroup()
                        .addComponent(declarationTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supposedlyImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        homePagePanelLayout.setVerticalGroup(
            homePagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supposedlyImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(homePagePanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(welcomeMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(declarationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(listOfItemsWeHave, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 181, Short.MAX_VALUE))
        );

        mainPane.add(homePagePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topNavigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1685, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topNavigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(812, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(95, 95, 95)
                    .addComponent(mainPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(97, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goToItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToItemsButtonActionPerformed
        homePagePanel.setVisible(false);
        itemsPanel.setVisible(true);
        usersPanel.setVisible(false);
        companyPanel.setVisible(false);
    }//GEN-LAST:event_goToItemsButtonActionPerformed

    private void getStartedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getStartedButtonActionPerformed
        homePagePanel.setVisible(false);
        itemsPanel.setVisible(true);
        usersPanel.setVisible(false);
        companyPanel.setVisible(false);
    }//GEN-LAST:event_getStartedButtonActionPerformed

    private void goToMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToMainActionPerformed
        homePagePanel.setVisible(true);
        itemsPanel.setVisible(false);
        usersPanel.setVisible(false);
        companyPanel.setVisible(false);
    }//GEN-LAST:event_goToMainActionPerformed

    private void goToCompaniesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToCompaniesActionPerformed
        companyPanel.setVisible(true);
        itemsPanel.setVisible(false);
        homePagePanel.setVisible(false);
        usersPanel.setVisible(false);
    }//GEN-LAST:event_goToCompaniesActionPerformed

    private void goToUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToUsersActionPerformed
        usersPanel.setVisible(true);
        companyPanel.setVisible(false);
        itemsPanel.setVisible(false);
        homePagePanel.setVisible(false);
    }//GEN-LAST:event_goToUsersActionPerformed

    private void addNewUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewUserButtonActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            String query;
            if(namesField.getText() == null || emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Some data is missing!");
            }else{
                query = "INSERT INTO `users` (`userFullName`, `userName`, `userEmail`, `userPassword`,`companyId`, `roleId`, `createdAt`) VALUES ('" + namesField.getText() + "','"+ emailField.getText() +"','"+ emailField.getText() +"','"+ passwordField.getText() +"','"+ this.companyId +"','"+ roles.get(selectUserRole.getSelectedItem()) +"','"+ dtf.format(now) +"')";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, namesField.getText() + " added successfully!");
                fetchUsers();
                namesField.setText("");
                emailField.setText("");
                passwordField.setText("");
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_addNewUserButtonActionPerformed

    private void cancelUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelUserActionPerformed

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
  
        
        int column = 0;
        int row = usersTable.getSelectedRow();
        String name = usersTable.getModel().getValueAt(row, column).toString();
        System.out.println(name);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            String query;
            query = "DELETE FROM `users` WHERE `userFullName` = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            
            ps.executeUpdate();
                    
            JOptionPane.showMessageDialog(this, "User deleted successfully");
            fetchUsers();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void newSaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSaleButtonActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            String query;
            if(quantityField.getText() == null){
                JOptionPane.showMessageDialog(this, "Some data is missing!");
            }else{
                int totalPrice = parseInt(quantityField.getText()) * parseInt(unitPriceField.getText());
                query = "INSERT INTO `sales` (`itemId`, `userId`, `companyId`, `quantity`,`priceUnit`,`totalCost`,`createdAt`) VALUES ('" + itemsId.get(selectItemField.getSelectedItem()) + "','"+ this.userId +"','"+ this.companyId +"','"+ quantityField.getText() +"','"+ unitPriceField.getText() +"','"+ totalPrice +"','"+ dtf.format(now) +"')";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "New sale added successfully!");
                fetchSales();
                itemNameField.setText("");
                itemPriceField.setText("");
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_newSaleButtonActionPerformed

    private void updateCompanyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCompanyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateCompanyButtonActionPerformed

    private void deleteSaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSaleButtonActionPerformed
        int column = 0;
        int row = salesTable.getSelectedRow();
        String name = salesTable.getModel().getValueAt(row, column).toString();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            String query;
            query = "DELETE FROM `items` WHERE `saleId` = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            
            ps.executeUpdate();
                    
            JOptionPane.showMessageDialog(this, "Sale deleted successfully");
            fetchSales();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteSaleButtonActionPerformed

    private void cancelComapanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelComapanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelComapanyActionPerformed

    private void addNewUserButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewUserButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addNewUserButton8ActionPerformed

    private void deleteItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteItemButtonActionPerformed
        int column = 0;
        int row = itemsTable.getSelectedRow();
        String name = itemsTable.getModel().getValueAt(row, column).toString();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            String query;
            query = "DELETE FROM `items` WHERE `itemName` = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            
            ps.executeUpdate();
                    
            JOptionPane.showMessageDialog(this, "Item deleted successfully");
            fetchItems();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteItemButtonActionPerformed

    private void addNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewItemActionPerformed
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "");
            Statement st = conn.createStatement();
            String query;
            if(itemNameField.getText() == null || itemPriceField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Some data is missing!");
            }else{
                query = "INSERT INTO `items` (`itemName`, `itemPrice`, `userId`, `companyId`,`createdAt`) VALUES ('" + itemNameField.getText() + "','"+ itemPriceField.getText() +"','"+ this.userId +"','"+ this.companyId +"','"+ dtf.format(now) +"')";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, itemNameField.getText() + " added successfully!");
                fetchItems();
                itemNameField.setText("");
                itemPriceField.setText("");
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_addNewItemActionPerformed

    private void addNewUserButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewUserButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addNewUserButton11ActionPerformed

    private void namesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namesFieldActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionsLabel;
    private javax.swing.JButton addNewItem;
    private javax.swing.JButton addNewUserButton;
    private javax.swing.JButton addNewUserButton11;
    private javax.swing.JButton addNewUserButton8;
    private javax.swing.JButton cancelComapany;
    private javax.swing.JButton cancelUser;
    private javax.swing.JPanel companyPanel;
    private javax.swing.JLabel declarationTitle;
    private javax.swing.JButton deleteItemButton;
    private javax.swing.JButton deleteSaleButton;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JButton getStartedButton;
    private javax.swing.JButton goToCompanies;
    private javax.swing.JButton goToItemsButton;
    private javax.swing.JButton goToMain;
    private javax.swing.JButton goToUsers;
    private javax.swing.JPanel homePagePanel;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JLabel itemNameLabel;
    private javax.swing.JTextField itemPriceField;
    private javax.swing.JLabel itemPriceLabel;
    private javax.swing.JPanel itemsPanel;
    private javax.swing.JTable itemsTable;
    private javax.swing.JLabel itemsTableTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane listOfItemsWeHave;
    private javax.swing.JList<String> listOne;
    private javax.swing.JLabel loggedInUser;
    private javax.swing.JPanel mainPane;
    private javax.swing.JTextField namesField;
    private javax.swing.JButton newSaleButton;
    private javax.swing.JTextField passwordField;
    private javax.swing.JTextField quantityField;
    private javax.swing.JTable salesTable;
    private javax.swing.JLabel salesTableTitle;
    private javax.swing.JComboBox<String> selectItemField;
    private javax.swing.JComboBox<String> selectUserCompany;
    private javax.swing.JComboBox<String> selectUserRole;
    private javax.swing.JInternalFrame supposedlyImage;
    private javax.swing.JPanel topNavigation;
    private javax.swing.JTextField totalPriceField;
    private javax.swing.JTextField unitPriceField;
    private javax.swing.JButton updateCompanyButton;
    private javax.swing.JButton updateUserButton;
    private javax.swing.JLabel userCompany;
    private javax.swing.JPanel usersPanel;
    private javax.swing.JTable usersTable;
    private javax.swing.JLabel usersTableTitle;
    private javax.swing.JLabel welcomeMessage;
    // End of variables declaration//GEN-END:variables
}
