/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.messages_app;

import java.sql.Connection;
import java.util.Scanner;

/**
 *
 * @author Sergio Lozano
 */
public class Start {
    
    public static void main(String[] args) {
        
        
        Scanner sc = new Scanner(System.in);
        
        int option=0;
        do{
            System.out.println("--------------------");
            System.out.println("Message app");
            System.out.println("1. create message");
            System.out.println("2. list messages");
            System.out.println("3. delete message");
            System.out.println("4. edit message");
            System.out.println("5. exit");
            
            option = sc.nextInt();
            
            switch (option){
                case 1:
                    MessagesService.createMessage();
                    break;
                case 2:
                    MessagesService.listMessages();
                    break;
                case 3:
                    MessagesService.deleteMessage();
                    break;
                case 4:
                    MessagesService.editMessage();
                    break;
                default:
                    break;
            }
        }while(option !=5);
        

        }
    }
    

