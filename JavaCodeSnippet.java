import java.io.*;  
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/*
 * This class shows how to make a simple authenticated call to the
 * Amazon Product Advertising API.
 *
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class JavaCodeSnippet {

    /*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static final String AWS_ACCESS_KEY_ID = "";

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "";

    /*
     * Use the end-point according to the region you are interested in.
     */
    private static final String ENDPOINT = "webservices.amazon.in";
	
	private static String result1;

    public static void main(String[] args) throws IOException, ProtocolException, JSONException{

        /*
         * Set up the signed requests helper.
         */
     
        //***********************************
		BufferedReader crunchifyBuffer = null;
		String json1="{\"ArrayResponse\":[";
		
		
		int looop=1;
		try {
			String crunchifyLine;
			crunchifyBuffer = new BufferedReader(new FileReader("input.csv"));
			int i;
			Scanner sc=new Scanner(System.in);
			int rec=0;
			char choice;
			
			while ((crunchifyLine = crunchifyBuffer.readLine()) != null) {
			
							i=0;
							System.out.println("Records fetched ="+rec);
					System.out.println("enter 'y' you want to call next 10 records otherwise 'n'     :");
					
					choice=sc.next().charAt(0);
					
					
					if(choice=='y')
					{
						rec=rec+10;
						if(looop!=1)
						{json1=json1.concat(",");
						
						
						
						}
						StringBuffer sb = new StringBuffer("");
						do{
							if(i==9)
							{
								sb.append(crunchifyLine);
							}
							else
							{
								sb.append(crunchifyLine+",");
							}
							i++;
						}while((crunchifyLine = crunchifyBuffer.readLine()) != null & i<10);
					
					
					
					
					
					SignedRequestsHelper helper;

        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String requestUrl = null;
//        Scanner sc= new Scanner(System.in);
//        System.out.println("Enter Your query :\n");
//        String q=sc.next();

        Map<String, String> params = new HashMap<String, String>();

         params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemLookup");
        params.put("AWSAccessKeyId", "");
        params.put("AssociateTag", "-21");
        params.put("SearchIndex", "All");
        params.put("ResponseGroup", "ItemAttributes,SalesRank");
        params.put("IdType","ISBN");
		params.put("Availability","Available");
		params.put("ItemId",sb.toString());
		params.put("Version","2015-10-01");
		
		
        requestUrl = helper.sign(params);

        System.out.println("Signed URL: \"" + requestUrl + "\"");
        Xmlretrive j1=new Xmlretrive();
        StringBuilder result;
        result=j1.ReadXml(requestUrl);
		
		
		
		if(looop!=1)
		{
		
		result1=result1+"<value>"+result.toString().substring(22,result.toString().length())+"</value>";

		}
		
		else{
		result1="<Responses type=\"array\">";
		result1=result1+"<value>"+result.toString().substring(22,result.toString().length())+"</value>";
		}
		
		
						
		
		
		Xmltojson j2=new Xmltojson();
         json1=json1.concat(j2.convert(result));
       looop=looop+1;
	
					}
				else if(choice =='n'){
							json1=json1.concat("]}");
							 result1+="</Responses>";
							
					try{  
						 FileOutputStream fout=new FileOutputStream(new File("data.json"),true);  
						 
						 byte b[]=json1.getBytes();//converting string into byte array  
						 fout.write(b);  
						 fout.close();  
						 
						 
						  
		 FileOutputStream fout2=new FileOutputStream(new File("dataXML.xml"),true);  
						 byte b1[]=result1.getBytes();
						 fout2.write(b1);  
						 fout2.close(); 
						 
						 System.out.println("success...");  
						 
						 
						
						}catch(Exception e){System.out.println(e);}				
										
				
					break;
				
				
				}	else{System.out.println("Invalid choice");
				break;}
				
				
				
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	    
   
    
    

    }

}