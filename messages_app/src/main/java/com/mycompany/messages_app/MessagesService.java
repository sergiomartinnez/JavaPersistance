/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.messages_app;

import java.util.Scanner;

/**
 *
 * @author Sergio Lozano
 */
public class MessagesService {
    public static void createMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your message");
        String message = sc.nextLine();
        
        System.out.println("Your name");
        String name = sc.nextLine();
        
        Messages register = new Messages();
        register.setMessage(message);
        register.setAuthor_message(name);
        MessagesDAO.createMessageDB(register);
    }
    
    public static void listMessages(){
        MessagesDAO.readMessagesDB();
    }
    public static void deleteMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the ID of the message to be deleted");
        int id_message = sc.nextInt();
        MessagesDAO.deleteMessageDB(id_message);
    }
    
    public static void editMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("write your new message");
        String message = sc.nextLine();
        
        System.out.println("Select the ID of the message to be Updated");
        int id_message = sc.nextInt();
        Messages updating = new Messages();
        updating.setId_message(id_message);
        updating.setMessage(message);
        MessagesDAO.updateMessageDB(updating);
    }
    
}
