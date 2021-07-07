/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.messages_app;

/**
 *
 * @author Sergio Lozano
 */
public class Messages {
    int id_message;
    String message;
    String author_message;
    String date_message;

    public Messages(){
        
    }

    public Messages(String message, String author_message, String date_message) {
        this.message = message;
        this.author_message = author_message;
        this.date_message = date_message;
    }
    

    
    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor_message() {
        return author_message;
    }

    public void setAuthor_message(String author_message) {
        this.author_message = author_message;
    }

    public String getDate_message() {
        return date_message;
    }

    public void setDate_message(String date_message) {
        this.date_message = date_message;
    }
    
    
    
}
