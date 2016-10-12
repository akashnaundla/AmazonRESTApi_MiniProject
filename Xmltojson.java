/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Ajit
 */
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author Ajit
 */
public class Xmltojson {
    
    
    
    public  String  convert(StringBuilder str) throws JSONException, IOException {
         String jsonPrettyPrintString = null;
        try {
            String TEST_XML_STRING =str.toString();
            JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
            int PRETTY_PRINT_INDENT_FACTOR=4;
             jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
           // System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
       
        return jsonPrettyPrintString ;
    }
    
}
