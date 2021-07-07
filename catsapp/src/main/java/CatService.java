
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
public class CatService {
    
    public static void watchCats() throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();

        Response response = client.newCall(request).execute();
        
        String theJson = response.body().string();
        
        theJson = theJson.substring(1, theJson.length());
        theJson = theJson.substring(0, theJson.length()-1);
        
        Gson gson = new Gson();
        Cats cats = gson.fromJson(theJson, Cats.class);
        
        Image image = null;
        try{
            URL url = new URL(cats.getUrl());
            
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());

            
            
            ImageIcon fondoCat = new ImageIcon(bufferedImage);
            
            if(fondoCat.getIconWidth() > 800){
                Image fondo = fondoCat.getImage();
                Image modified = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoCat = new ImageIcon(modified);
            }
            
            String menu = "Options: \n"
                    + "1. see another picture\n"
                    + "2. favorite\n"
                    + "3. back\n";
            String[] buttons = {"see another picture", "favorite", "back"};
            String id_cat = cats.getId();
            String option = (String) JOptionPane.showInputDialog(null, menu, id_cat, JOptionPane.INFORMATION_MESSAGE, fondoCat, buttons, buttons[0]);
            
            int selection = -1;
            for(int i=0; i<buttons.length;i++){
                if(option.equals(buttons[1])){
                    selection = 1;
                }
            }
            
            switch (selection){
                case 0 :
                    watchCats();
                    break;
                case 1:
                    favoriteCat(cats);
                    break;
                default:
                    break;
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    
    public static void favoriteCat(Cats Cat) {
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\""+Cat.getId()+"\"\n}");
            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites")
              .post(body)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", Cat.getApikey())
              .build();
            Response response = client.newCall(request).execute();            
                  
        }catch(IOException e){
            System.out.println(e);
        }
  
    }
    
    public static void watchFavorite(String apikey) throws IOException{
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", apikey)
                .build();
        Response response = client.newCall(request).execute();
        String theJson = response.body().string();
        
        
        Gson gson = new Gson();
        
        Catsfav[] catsArray = gson.fromJson(theJson, Catsfav[].class);
        
        if(catsArray.length > 0){
            int min = 1;
            int max = catsArray.length;
            int aleatory = (int) (Math.random() * ((max-min)+1)) + min;
            int index = aleatory-1;
            
            Catsfav catfav = catsArray[index];
            
               Image image = null;
               try{
                   URL url = new URL(catfav.image.getURL());

                   HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                   httpcon.addRequestProperty("User-Agent", "");
                   BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());



                   ImageIcon fondoCat = new ImageIcon(bufferedImage);

                   if(fondoCat.getIconWidth() > 800){
                       Image fondo = fondoCat.getImage();
                       Image modified = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                       fondoCat = new ImageIcon(modified);
                   }

                   String menu = "Options: \n"
                           + "1. see another picture\n"
                           + "2. Delete favorite\n"
                           + "3. back\n";
                   String[] buttons = {"see another picture", "Delete favorite", "back"};
                   String id_cat = catfav.getId();
                   String option = (String) JOptionPane.showInputDialog(null, menu, id_cat, JOptionPane.INFORMATION_MESSAGE, fondoCat, buttons, buttons[0]);

                   int selection = -1;
                   for(int i=0; i<buttons.length;i++){
                       if(option.equals(buttons[1])){
                           selection = 1;
                       }
                   }

                   switch (selection){
                       case 0 :
                           watchFavorite(apikey);
                           break;
                       case 1:
                           deleteFavorite(catfav);
                           break;
                       default:
                           break;
                   }

               }catch(IOException e){
                   System.out.println(e);
               }
    
            
        }
    }
    
    public static void deleteFavorite(Catsfav catfav){
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites/"+catfav.getId()+"")
              .delete(null)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", catfav.getApikey())
              .build();

            Response response = client.newCall(request).execute();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}

