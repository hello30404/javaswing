package com.project.GUI;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.project.BUS.*;
import com.project.DAO.*;
import com.project.DTO.*;


public class ListUser extends javax.swing.JPanel {
     DefaultTableModel dtm;
    public UserService userservice;
    public ListUser() {
        initComponents();
        userservice = new UserService();
        dtm = new DefaultTableModel()
        {
            public boolean isCellEditable(int row, int column)
            {

                return false;
            }
        };
        jTable1.setModel(dtm);
        dtm.addColumn("Id");
        
        dtm.addColumn("Name");
        dtm.addColumn("Age");
        List<User> users = null;
        try {
            users = userservice.getAllUser();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(User user : users)
        {
            dtm.addRow(new Object[] {user.getId(), user.getName(), user.getAge()});
        }
        this.setBackground(new Color(242, 242, 242));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        addUser = new javax.swing.JButton();
        deleteUser = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1085, 786));
        setPreferredSize(new java.awt.Dimension(1085, 786));

        addUser.setText("AddUser");
        addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserActionPerformed(evt);
            }
        });

        deleteUser.setText("Delete");
        deleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserActionPerformed(evt);
            }
        });

        Edit.setText("EditUser");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        searchButton.setText("searchUser");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        refresh.setText("refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(addUser)
                .addGap(66, 66, 66)
                .addComponent(deleteUser)
                .addGap(84, 84, 84)
                .addComponent(Edit)
                .addGap(109, 109, 109)
                .addComponent(searchButton)
                .addGap(85, 85, 85)
                .addComponent(refresh)
                .addContainerGap(307, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addUser)
                            .addComponent(deleteUser)
                            .addComponent(Edit)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton)
                            .addComponent(refresh))))
                .addGap(128, 128, 128)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {                                        
        dtm.setRowCount(0);
        List<User> users = null;
        try {
            users = userservice.getAllUser();
        } catch (Exception ex) {
            Logger.getLogger(ListUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(User user : users)
        {
            dtm.addRow(new Object[] {user.getId(), user.getName(), user.getAge()});
        }
    }                                       

    private void addUserActionPerformed(java.awt.event.ActionEvent evt) {                                        
        new AddUser().setVisible(true);
        
    }                                       

    private void deleteUserActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int selectedRow = jTable1.getSelectedRow();
        if(selectedRow == -1)
        {
            JOptionPane.showMessageDialog(this, "Please choose value which you want to delete?");
        }
        else 
        {   
            int choice = JOptionPane.showConfirmDialog(this, "Do you really want to delete it?");
            if(choice == JOptionPane.YES_OPTION)
            {
                int userId = Integer.parseInt(String.valueOf(jTable1.getValueAt(selectedRow, 0)));
                try {
                    userservice.deleteUser(userId);
                } catch (Exception ex) {
                    Logger.getLogger(ListUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                dtm.setRowCount(0);
                List<User> users = null;
                try {
                    users = userservice.getAllUser();
                } catch (Exception ex) {
                    Logger.getLogger(ListUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(User user : users)
                {
                    dtm.addRow(new Object[] {user.getId(), user.getName(), user.getAge()});
                }
            }   
        }
    }                                          

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {                                     
        int selectedRow = jTable1.getSelectedRow();
        if(selectedRow == -1)
        {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trường muốn update");
        }
        else 
        {
            int userId = Integer.parseInt(String.valueOf(jTable1.getValueAt(selectedRow, 0)));
            try {
                new EditUser(userId).setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }                                    

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        new SearchUser().setVisible(true);
    }                                            
    public void loadUser() {                                        
        dtm.setRowCount(0);
        List<User> users = null;
        try {
            users = userservice.getAllUser();
        } catch (Exception ex) {
            Logger.getLogger(ListUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(User user : users)
        {
            dtm.addRow(new Object[] {user.getId(), user.getName(), user.getAge()});
        }
    }      

    // Variables declaration - do not modify                     
    private javax.swing.JButton Edit;
    private javax.swing.JButton addUser;
    private javax.swing.JButton deleteUser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refresh;
    private javax.swing.JButton searchButton;
    // End of variables declaration                   
}
