/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.messages_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sergio Lozano
 */
public class Conectiontest {
    
    public Connection get_conection(){
        Connection conection = null;
        try{
            conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/messagesapp", "root", "");
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return conection;
    }
    
}
