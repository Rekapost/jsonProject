package jsonproject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DDTestUsingJson 
{
	WebDriver driver;
	@BeforeClass
	void setup()
	{ 
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	}
	@AfterClass
	void tearDown()
	{
		driver.close();
	}
	@Test(dataProvider="dp")
	void login(String data)
	{
		String users[]=data.split(",");
		driver.get("https://admin-demo.nopcommerce.com/");
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(users[0]);
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(users[1]);
		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		String act_title=driver.getTitle();
		String exp_title="Your store. Login";
		AssertJUnit.assertEquals(act_title,exp_title);		
	}
	@DataProvider(name="dp")
	String[] readJson() throws IOException, ParseException
	{
		JSONParser jsonparser=new JSONParser();
		FileReader reader=new FileReader(".\\jsonfiles\\testdata.json");
		Object obj=jsonparser.parse(reader);
		JSONObject userloginsJsonobj=(JSONObject)obj;
		JSONArray userloginsArray=(JSONArray)userloginsJsonobj.get("userlogins");
		String arr[]=new String[userloginsArray.size()];
		for (int i=0; i<userloginsArray.size();i++)
		{
			 JSONObject users=(JSONObject) userloginsArray.get(i);
			 String user=(String)users.get("username");
			 String pwd=(String)users.get("password");
			 arr[i]=user+"," +pwd ;
		}
		return arr;
		
	}
	
}
