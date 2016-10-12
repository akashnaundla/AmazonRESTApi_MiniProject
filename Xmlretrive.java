/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Ajit
 */
public class Xmlretrive {
    
    
      public StringBuilder ReadXml(String signedurl) {
        StringBuilder result = new StringBuilder();
        try{
         String url =signedurl ;
       
      //  System.out.println(url);
    String encodedURL=java.net.URLEncoder.encode(url,"UTF-8");
      // System.out.println(encodedURL);
       
        
	
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		
                
                

		int responsecode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responsecode);
               
              
             System.out.println("start fetching xml");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()))) {
            String inputline;
            while ((inputline = in.readLine()) != null) {
                
                result.append(inputline);
               
               
			}
            System.out.println("I finished retriving xml ");
            in.close();
             
           
        }
                
        
        }catch(Exception e){
            System.out.println(e);
        }   
                
                
        
        

                
            
              
              
			
  return result; 
     
        
    }
    
    
    
}
