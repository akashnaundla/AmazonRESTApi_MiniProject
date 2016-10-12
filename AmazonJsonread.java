/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ajit
 */
public class AmazonJsonread {
    
    
    
   public  ArrayList<String> url=new ArrayList<>(); 
   public  ArrayList<String> title=new ArrayList<>(); 
   public  ArrayList<String>purl=new ArrayList<>(); 
   public  ArrayList<String>formatedprice =new ArrayList<>(); 
   
    public void jsonread(String op) throws JSONException{
        String result=op;
        
        System.out.println(result);
      try{  
      JSONObject obj1=new JSONObject(result);
      JSONObject obj2=obj1.getJSONObject("ItemSearchResponse");
      JSONObject items=obj2.getJSONObject("Items");
        int totalresult=items.getInt("TotalResults");
          if(totalresult!=0){
        
              JSONArray item=items.getJSONArray("Item");
             for(int i=0;i<10;i++){
                 
                 JSONObject obj3=item.getJSONObject(i);
        JSONObject mediumimage=obj3.getJSONObject("MediumImage");
                String  url1=mediumimage.getString("URL");
                   url.add(url1);
                   String producturl1=obj3.getString("DetailPageURL");
                   purl.add(producturl1);
                 JSONObject itemattribute=obj3.getJSONObject("ItemAttributes");
                    String title1=itemattribute.getString("Title");
                     title.add(title1);
               //  String color1=itemattribute.getString("Color");
              // color.add(color1);
                      //  JSONObject listprice=itemattribute.getJSONObject("ListPrice");
                        // String price1=listprice.getString("FormattedPrice");
        //formatedprice.add(price1);
                       
                      JSONObject offersummary=obj3.getJSONObject("OfferSummary");
                       JSONObject lowestprice=offersummary.getJSONObject("LowestNewPrice");
                          String price=lowestprice.getString("FormattedPrice");
                          formatedprice.add(price);
                         
                    
           }
                
//             for(int i=0;i<10;i++){
//                 System.out.println("Title ="+title.get(i));
//                System.out.println("Product Url ="+purl.get(i));
//                 System.out.println("Price ="+formatedprice.get(i));
//                System.out.println("Image Url :"+url.get(i));
//                 
//    }
          }else{
              System.out.println("No product found");
          }               
      }catch(Exception e)  {
          System.err.println(e);
      } 
              }
    
    
}
