package jsonproject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJsonFile {

	public static void main(String[] args) throws IOException, ParseException
	   {
		// TODO Auto-generated method stub
		FileReader reader=new FileReader(".\\jsonfiles\\employee.json");
		JSONParser jsonparser=new JSONParser();
		Object obj=jsonparser.parse(reader);
		JSONObject empjsonobj=(JSONObject)obj;
		String fname=(String)empjsonobj.get("firstName");
		String lname=(String)empjsonobj.get("lastName");
		System.out.println("First name:" + fname);
		System.out.println("Last name:" + lname);
		JSONArray array=(JSONArray)empjsonobj.get("address");
		for (int i=0; i<array.size();i++)
		    {
			JSONObject address=(JSONObject) array.get(i);
			String street=(String)address.get("street");
			String city=(String)address.get("city");
			String state=(String)address.get("state");
			
			System.out.println("Adress of " +i+" is...");
			System.out.println("Street :" + street);
			System.out.println("Street :" + city);
			System.out.println("Street :" + state);
		    }
		     
		
		}
 
	}

