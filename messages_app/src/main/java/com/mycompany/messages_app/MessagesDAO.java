/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.messages_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sergio Lozano
 */
public class MessagesDAO {
    
    public static void createMessageDB(Messages message){
        Conectiontest db_connect = new Conectiontest();
        try(Connection conectiondao = db_connect.get_conection()){
            PreparedStatement ps=null;
            try{
                String query="INSERT INTO messages (message, author_message) VALUES (?,?)";
                ps=conectiondao.prepareStatement(query);
                ps.setString(1, message.getMessage());
                ps.setString(2, message.getAuthor_message());
                ps.executeUpdate();
                System.out.println("message created");
                
            }catch(SQLException ex){
                System.out.println(ex);
            }   
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static void readMessagesDB(){
        Conectiontest db_connect = new Conectiontest();
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        
        try(Connection conectiondao = db_connect.get_conection()){
            String query="SELECT * FROM messages";
            ps=conectiondao.prepareStatement(query);
            rs=ps.executeQuery();
            
            while(rs.next()){
                System.out.println("ID: "+rs.getInt("id_message"));
                System.out.println("Message: "+rs.getString("message"));
                System.out.println("Author: "+rs.getString("author_message"));
                System.out.println("date: "+rs.getString("Date_message"));
                System.out.println("");
            }
                    
        }catch(SQLException e){
            System.out.println("Could not recover the messages");
            System.out.println(e);
        }
    }
    
    public static void deleteMessageDB(int id_message){
        Conectiontest db_connect = new Conectiontest();
        try(Connection conectiondao = db_connect.get_conection()){
        PreparedStatement ps=null;
            
            try {
                String query="DELETE FROM `messages` WHERE id_message = ?";
                ps=conectiondao.prepareStatement(query);
                ps.setInt(1, id_message);
                ps.executeUpdate();
                System.out.println("the message was deleted");
                
            }catch(SQLException e){
                System.out.println(e);
                System.out.println("could not delete the message");
            }
        
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static void updateMessageDB(Messages message){
        Conectiontest db_connect = new Conectiontest();
        
        try(Connection conectiondao = db_connect.get_conection()){
        PreparedStatement ps=null;
        
            try{
                String query="UPDATE messages SET message = ? WHERE id_message = ?";
                 ps=conectiondao.prepareStatement(query);
                 ps.setString(1, message.getMessage());
                 ps.setInt(2,message.getId_message());
                 ps.executeUpdate();
                 System.out.println("The message was updated succesfully");
            }catch(SQLException ex){
                System.out.println(ex);
                System.out.println("Could not update the message");
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
}
