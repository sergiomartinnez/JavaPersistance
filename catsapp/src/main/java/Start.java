
import java.io.IOException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sergio Lozano
 */
public class Start {
    public static void main(String[] args) throws IOException{
        int option_menu = -1;
        String[] buttons = {"1. watch cats", "2. exit"};
        
        do{
            
            String option = (String) JOptionPane.showInputDialog(null, "cats java", "Menu principal", JOptionPane.INFORMATION_MESSAGE,
                    null, buttons,buttons[0]);
            
            for(int i=0;i<buttons.length;i++){
                if(option.equals(buttons[i])){
                    option_menu = i;
                }
            }
            
            switch(option_menu){
                case 0:
                    CatService.watchCats();
                    break;
                case 1:
                    Cats cat = new Cats();
                    CatService.watchFavorite(cat.getApikey());  
                default:
                    break;
            }
            
        }while(option_menu !=1);
    }
    
}
