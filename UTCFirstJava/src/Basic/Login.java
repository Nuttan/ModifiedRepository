
package Basic;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class Login {
	
	static WebDriver driver = null;
	static Actions actions = null;
	static WebElement ProtocolDropdown = null;

		private static void ScreenShot(String scrnshtName) throws InterruptedException
	{
			
	                Thread.sleep(3000);                                                       
	                File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	                // Now you can do whatever you need to do with it, for example copy somewhere
	                try {
	                            FileUtils.copyFile(screenShot, new File("D:\\CST-AutomationScreenshots\\"+scrnshtName+".png"));
	                    } 
	                catch (IOException e) 
	                {
	                                                                               
	                     e.printStackTrace();
	                }
	}
		
		
		
	@Test(alwaysRun=true, priority=0)
	public static void LoginAutomatic() throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
	    driver=new ChromeDriver();	    		
        driver.get("http://localhost:8081/#/login");
        driver.manage().window().maximize();
  	    Thread.sleep(4000);
	    
	    System.out.println(driver.getTitle()+ "      Running in Progress ....");
    
    
		//Explicit wait for input fields to be visible
		WebElement UsrnameField = (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
		UsrnameField.sendKeys("devutc@utc.com");
	    driver.findElement(By.id("input-pwd")).sendKeys("Welcome@123");;
	    
	    driver.findElement(By.xpath("//div/div[2]/form[1]/button/div/span")).click();
	    
	}
	@Test(alwaysRun=true, priority=2)
	public static void ConnectivityScreen() throws InterruptedException
	{
		//Explicit wait for Connectivity Icon on HomeScreen
		WebElement ConnectivityIcononHomeScreen = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='square col-md-4 connectivity']")));
		ConnectivityIcononHomeScreen.click();
	}
	@Test(alwaysRun=true, priority=3)
	public static void NativeBacnetLogin() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(50000,TimeUnit.MILLISECONDS);
		//Explicit wait for ProtocolDropdown
		ProtocolDropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='dropdownMenu1' and contains(text(),'select')]//span")));
		ProtocolDropdown.click();
		
		WebElement ProtocolValue = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Native BACnet")));
		ProtocolValue.click();
		//driver.findElement(By.linkText("Native BACnet")).click();
		
		
		driver.findElement(By.xpath("//button[@id='dropdownMenu2']")).click();
		//Explicit wait for Selection of available devices
		WebElement Available_devices_drpdown = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("19XRPIC5")));
		Available_devices_drpdown.click();
		//To click connect
		driver.findElement(By.xpath("//button[@class='btn btn-success connectbtn ng-binding']")).click();
		
		Thread.sleep(3000);
		
	}
	@Test(alwaysRun=true, priority=4)
	// below is for reading device connection info
	public static void ReadDeviceInfo() throws InterruptedException
	{
		Thread.sleep(15);
		String Deviceinfo = driver.findElement(By.xpath("//div/ng-include/div/div/div/ul")).getText();
		System.out.println("\n" + "\n" +"We are currently Connected to below device" + "\n" + "\n" +Deviceinfo);
		
	}
	
	@Test(alwaysRun=true, priority=6)// below is for navigating the Chiller Overview and Related Tabs
	public static void ChillerOverviewRefresh() throws InterruptedException
	{
		Thread.sleep(5000);
		List <WebElement> li = driver.findElements(By.tagName("a"));
		System.out.println("\n" + "\n" + "No. of links Before going to Overview Screen  "+li.size());
		driver.findElement(By.xpath("//*[@id='side']/div/div[2]/ul/li[3]/a[2]/img")).click();
		Thread.sleep(5000);
		//To Click on Refresh
		driver.findElement(By.xpath("//a[@title='Refresh']")).click();
		
		System.out.println("Clicked on Refresh !!"); 
		Thread.sleep(5000);
	}
	
	@Test(alwaysRun=true, priority=6)
	public static void TraceRecording() throws InterruptedException
    {
		WebElement myDynamicElement = null;
                    
		driver.manage().timeouts().implicitlyWait(80000,TimeUnit.MILLISECONDS);
        
        myDynamicElement = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated
                                         (By.xpath("//i[@class='material-icons' and contains(text(),'fiber')]")));                       
         myDynamicElement.click();
         
         System.out.println("Clicked on record button!!"); 
         
     
         
         Thread.sleep(5000);
         driver.manage().timeouts().implicitlyWait(50000,TimeUnit.MILLISECONDS);
      
//                       myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated
//                                                       (By.xpath("//*[@id='traceconfiguration']/div/div/div[1]/div[2]/ul[1]/li[2]" + "/div/div[2]/table/tbody/tr[2]/td[3]/input")));
         
         
         
         myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated
          (By.xpath("//input[@ng-disabled='noIncrementMinutes()']")));

                       String test=myDynamicElement.getAttribute("value");
                       System.out.println(test);
                       int foo = Integer.parseInt(test);
                       
                       if (foo >= 0 & foo <= 9){
                     	  ++foo;
                     	  
                     	  String check= "o".concat(Integer.toString(foo));
                     	  foo = Integer.parseInt(check);
                     	  
                       }
                       else  
                       foo++;
                       String fa=Integer.toString(foo);
                       myDynamicElement.clear();
                       myDynamicElement.sendKeys(fa);
                       
                       myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated
                               (By.xpath("//button[contains(text(),'Save & Record')]")));                              
                       myDynamicElement.click();
                   

    }

	
	@Test(alwaysRun=true, priority=9)
	public static void DisconnectDevice() throws InterruptedException
	{
		List <WebElement> li = driver.findElements(By.tagName("a"));
		System.out.println("\n" +"\n" + "No. of links Before going to Overview Screen  "+li.size());
		/*Below is to print the Link name on Overview Screen
		int length = li.size();
		for(int i=0; i<=length; i++)
		{
			System.out.println(li);
		}
		*/
		driver.findElement(By.xpath("//*[@id='side']/div/div[2]/ul/li[1]/a/img")).click();
		Thread.sleep(2000);
		//To Click on Disconnect
		driver.findElement(By.xpath("//button[@class='btn btn-success disConnectbtn ng-binding']")).click();
		System.out.println("\n" + "\n" +"Device Disconnected"); 
	}
	
	@Test(alwaysRun=true, priority=10)
	public static void ReportsOffline() throws InterruptedException
	{
	      
		Thread.sleep(5000);
	  //method used to click on User Profile button.
	    driver.findElement(By.xpath("//*[@id='top']/div/div[1]/div/div[2]/ul/li[2]/div[1]/span[2]/button/span")).click();
	     
	   //To select user temperature Preference.
	     driver.findElement(By.xpath("//*[@id='btnTemperature']")).click();
	     Thread.sleep(5000);
	   ////To select temperature as 'Fahrenheit'.
	     driver.findElement(By.linkText("Fahrenheit")).click();
	   //waiting thread for 5 second.
	     Thread.sleep(5000); 
	   //Clicking on save.
	    driver.findElement(By.xpath("//*[@id='mydiv']/div[2]/div[11]/div[1]/button")).click(); 
	     Thread.sleep(5000); 
	     driver.navigate().refresh();
	     
	     Thread.sleep(4000);
	     List <WebElement> li = driver.findElements(By.tagName("a"));
	     
	      //below is to click on Reports icon in the side bar
	     WebElement ReportsIcon = (new WebDriverWait(driver, 10)).
	                 until(ExpectedConditions.elementToBeClickable(By.xpath
	                             ("//*[@id='side']/div/div[2]/ul/li[5]/a[1]")));
	     //Clicking on Reports Tab
	     ReportsIcon.click();
	     //driver.findElement(By.xpath("//*[@id='side']/div/div[2]/ul/li[5]/a[1]/img")).click();
	     
	      //SelectDevice from the list
	     WebElement selectDevice = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//button[@class='device btn btn-default "
	                             + "dropdown-toggle second-div-btn']")));
	     selectDevice.click();
	     //driver.findElement(By.xpath("//*[@id='dropdownMenu11']")).click();
	     
	      WebElement Device = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("19XRPIC5")));
	     Device.click();
	   //Select Report
	     WebElement selectReport = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//button[@class='btn btn-default dropdown-toggle second-div-btn']")));
	     selectReport.click();
	     WebElement Report = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("ReportGroup")));
	     Report.click();
	   //MouseOver
	     
//	     WebElement element = driver.findElement(By.cssSelector(".path.highcharts-color-2:nth-child(6)"));
//	     Actions mousemove = new Actions(driver);
//	     mousemove.moveToElement(element).perform();
	     Thread.sleep(6000);
	     ScreenShot("Fahrenheit");
	     
	     //Do the above thing for Celcius
	   //method used to click on User Profile button.
	     driver.findElement(By.xpath("//*[@id='top']/div/div[1]/div/div[2]/ul/li[2]/div[1]/span[2]/button/span")).click();
	      Thread.sleep(5000);
	    //To select user temperature Preference.
	      driver.findElement(By.xpath("//*[@id='btnTemperature']")).click();
	      Thread.sleep(5000);
	    ////To select temperature as 'Fahrenheit'.
	      driver.findElement(By.linkText("Celsius")).click();
	    //waiting thread for 5 second.
	      Thread.sleep(5000); 
	    //Clicking on save.
	     driver.findElement(By.xpath("//*[@id='mydiv']/div[2]/div[11]/div[1]/button")).click(); 
	      Thread.sleep(5000); 
	      driver.navigate().refresh();
	      
	      Thread.sleep(4000);
	      //List <WebElement> li = driver.findElements(By.tagName("a"));
	      
	      //below is to click on Reports icon in the side bar
	      WebElement ReportsIcon1 = (new WebDriverWait(driver, 10)).
	                  until(ExpectedConditions.elementToBeClickable(By.xpath
	                              ("//*[@id='side']/div/div[2]/ul/li[5]/a[1]")));
	      //Clicking on Reports Tab
	      ReportsIcon1.click();
	      //driver.findElement(By.xpath("//*[@id='side']/div/div[2]/ul/li[5]/a[1]/img")).click();
	      
	      //SelectDevice from the list
	      WebElement selectDevice1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(
	                  By.xpath("//button[@class='device btn btn-default "
	                              + "dropdown-toggle second-div-btn']")));
	      selectDevice1.click();
	      //driver.findElement(By.xpath("//*[@id='dropdownMenu11']")).click();
	      
	      WebElement Device1 = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("TestDevice19XR")));
	      Device1.click();
	    //Select Report
	      WebElement selectReport1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(
	                  By.xpath("//button[@class='btn btn-default dropdown-toggle second-div-btn']")));
	      selectReport1.click();
	      WebElement Report1 = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("ReportGroup")));
	      Report1.click();
	    //MouseOver
	      
//	      WebElement element = driver.findElement(By.cssSelector(".path.highcharts-color-2:nth-child(6)"));
//	      Actions mousemove = new Actions(driver);
//	      mousemove.moveToElement(element).perform();
	      Thread.sleep(6000);
	      ScreenShot("Celcius");
	}
	

	
	@Test(alwaysRun=true, priority=10, enabled=false)
	public static void UpdateProfile()throws InterruptedException
    {
      Thread.sleep(5000); //waiting thread for 5 second.
          driver.findElement(By.xpath("//*[@id='top']/div/div[1]/div/div[2]/ul/li[2]/div[1]/span[2]/button/span")).click();//method used to click on User Profile button.
      
      Thread.sleep(5000);
      driver.findElement(By.xpath("//*[@id='btnFontsize']")).click();//To select user font Preference.
      
      Thread.sleep(5000);
      driver.findElement(By.linkText("Medium")).click();// To select  font size as 'Medium'.
                        
      Thread.sleep(5000);
      driver.findElement(By.xpath("//*[@id='btnLanguage']")).click();//To select user language Preference.
      
      Thread.sleep(5000);
      driver.findElement(By.linkText("Chinese")).click();// To select language as 'Chinese'.
      
      Thread.sleep(5000);
      driver.findElement(By.xpath("//*[@id='btnTemperature']")).click();//To select user temperature Preference.
      //
      Thread.sleep(5000);
      driver.findElement(By.linkText("Celsius")).click();// To select temperature as 'Celsius'.

      Thread.sleep(5000); //waiting thread for 5 second.
          driver.findElement(By.xpath("//*[@id='mydiv']/div[2]/div[11]/div[1]/button")).click(); //Clicking on save.
      
      Thread.sleep(5000); //waiting thread for 5 second.
      
      String changedLanngString = driver.findElement(By.xpath("//*[@id='mydiv']/div[2]/div[3]/div[1]/label")).getText(); //Clicking on save.
      System.out.println("Printing prefered language"+" "+ changedLanngString);
      ////*[@id="btnFontsize"]
    }
	@Test(alwaysRun=true, priority=1)
	public static void HomeScreenNoconnectioToDevice() throws InterruptedException
    {
	      String getText = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/div/div[1]/div[2]/div[1]/div[2]/div[1]"))).getText();
	      System.out.println("Device not COnnected now hence - Chiller Info Tab is unavailable"+" "+ getText);
	      
	      
	      String getText1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/div/div/div[2]/div[2]/div[3]/div[1]"))).getText();
	      System.out.println("Device not COnnected now hence -  Alarms and Alerts Tab is unavailable"+" "+getText1);

    }
	@Test(alwaysRun=true, priority=11)
	public static void Logout() throws InterruptedException

    {
      Thread.sleep(3000);
          driver.findElement(By.xpath("//*[@id='top']/div/div[1]/div/div[2]/ul/li[2]/div[1]/span[2]/button/span")).click();
      Thread.sleep(2000);
          driver.findElement(By.xpath("//*[@id='mydiv']/div[2]/div[11]/div[2]/button")).click();      
          driver.close();
    }
	@Test(alwaysRun=true, priority=12)
	public static void AboutScreen() throws InterruptedException
    {
		Thread.sleep(3000);
		Login.LoginAutomatic();
		Thread.sleep(6000);
		WebElement AboutScreeninfo =  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='top']/div/div[1]/div/div[2]/ul/li[1]/div[1]/a")));    
		//driver.findElement(By.xpath("//*[@id='top']/div/div[1]/div/div[2]/ul/li[1]/div[1]/a")).click();
		AboutScreeninfo.click();
		Thread.sleep(6000);
		driver.close();
      
    }
	@Test(alwaysRun=true, priority=5)
	public static void iconswhenChillerConnected() throws InterruptedException

{				
				WebElement myDynamicElement = null;
                //going to home screen
                myDynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//*[@id='side']/div/div[1]/ul/li[1]/a/img")));                               
                myDynamicElement.click();
                System.out.println("1. Home Screen");
                
                //going to overview screen
                Thread.sleep(2000);
                myDynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//div[@class='square col-md-4 chillerinfo']/img")));                          
                myDynamicElement.click();
                System.out.println("2. Chiller Overview Screen");
                
                Thread.sleep(6000);
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//*[@id='side']/div/div[1]/ul/li[1]/a/img")));
                myDynamicElement.click();
                System.out.println("3. Home Screen");
                
                Thread.sleep(2000);
                //going to manage mapping screen.
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//div[@class='square col-md-4 settings']/img")));                           
                myDynamicElement.click();
                System.out.println("4. Manage mapping screen");
               
                Thread.sleep(2000);
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//*[@id='side']/div/div[1]/ul/li[1]/a/img")));
                myDynamicElement.click();
                System.out.println("5. Home Screen");
                
                driver.navigate().refresh();
                Thread.sleep(3000);
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//div[@class='square col-md-4 reports ng-scope']/img")));                           
                myDynamicElement.click();
                System.out.println("6. Report Screen");
                
                Thread.sleep(2000);
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//*[@id='side']/div/div[1]/ul/li[1]/a/img")));
                myDynamicElement.click();
                System.out.println("7. Home Screen");
                
                Thread.sleep(3000);
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//div[@class='square col-md-4 checklist']/img")));
                                 //Wrong X-Path Not working Byxpath("//*[@id='content']/div/div/div[2]/div[2]/div[2]/div/a")));                           
                myDynamicElement.click();
                System.out.println("8. CheckList Screen");
                
                Thread.sleep(2000);
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//*[@id='side']/div/div[1]/ul/li[1]/a/img")));
                myDynamicElement.click();
                System.out.println("9. Home Screen");
                
                Thread.sleep(2000);
                myDynamicElement = (new WebDriverWait(driver, 50)).until(ExpectedConditions.
                		elementToBeClickable(By.xpath("//*[@id='content']/div/div/div[2]/div[2]/div[3]/div/a")));                           
                myDynamicElement.click();
                System.out.println("10. Alarms and Alerts Screen");
                
}
	@Test(alwaysRun=true, priority=8)
	public static void Alarms()throws InterruptedException
	{
//	    driver.findElement(By.xpath("//*[@id='side']/div/div[2]/ul/li[4]")).click();
	      List <WebElement> li = driver.findElements(By.tagName("a"));
	      System.out.println("  Now Executing Alarms/Alerts....");
	      //below is to click on Alarms/Alerts icon in the side bar
	      WebElement AlarmsIcon = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='side']/div/div[2]/ul/li[4]/a[2]")));
	      AlarmsIcon.click();
	      Thread.sleep(2000);
	      driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/div[1]/ul/li[1]/a")).click();
	      //Calling Screenshot method to take the screen shot of current Tab
	      ScreenShot("CurrentAlarms");
	      
	      //Click on Historical Tab
	      driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/div[1]/ul/li[2]/a")).click();
	      //Calling Screenshot method to take the screen shot of Historical Tab
	      ScreenShot("HistoricalAlarms");
	      System.out.println("  Finished Alarms/Alerts....");
	}


	@Test(alwaysRun=true, priority=7)
	public static void TabNavigation() throws InterruptedException
{
                String[] myStringArray = {"Overview","Compressor","Heat Exchanger","Power VFD","Start Up"}; 
                ScreenShot(myStringArray[0]);
                for(int i=1; i<5; i++)
                {
                	Thread.sleep(5000);
                	driver.findElement(By.linkText(myStringArray[i])).click();
                	ScreenShot(myStringArray[i]);
                	
                }
}

	@Test(alwaysRun=true, priority=7)
	public static void TrendReportsAddNewConfigurationAddDatapoints() throws InterruptedException 
    {
          driver.findElement(By.xpath("//*[@id='side']/div/div[2]/ul/li[5]")).click();
      Thread.sleep(3000);
      driver.findElement(By.linkText("Trend Configuration")).click();
      Thread.sleep(3000);
      driver.findElement(By.className("new-config")).click();
      Thread.sleep(3000);
      driver.findElement(By.xpath("//*[@id='trendReport']/div[6]/ng-include/div/div[1]/div/div[1]/ul/li[2]/input")).sendKeys("Configuration 1");
      Thread.sleep(3000);
      driver.findElement(By.xpath("//*[@id='trendReport']/div[6]/ng-include/div/div[1]/div/div[3]/ul/li[2]/div/div[2]/table/tbody/tr[1]/td[3]/a")).click();
      Thread.sleep(3000);
      for(int i = 1; i <= 4; i++)
      {
      driver.findElement(By.xpath("//*[@id='trendReport']/div[6]/ng-include/div/div[3]/div/div[3]/div[1]/button")).click();
      Thread.sleep(3000);
      driver.findElement(By.xpath("//*[@id='display-text']")).sendKeys("t");
      Thread.sleep(3000);
      actions = new Actions(driver);
      actions.sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER).build().perform();
      Thread.sleep(3000);
      driver.findElement(By.xpath("//*[@id='insert-point-config']/div/div/div[2]/div[2]/div[2]/input")).sendKeys("10");
      Thread.sleep(2000);
      driver.findElement(By.xpath("//*[@id='insert-point-config']/div/div/div[3]/ul/li/a")).click();
      Thread.sleep(2000);
      }
      driver.findElement(By.xpath("//*[@id='trendReport']/div[6]/ng-include/div/div[3]/div/div[4]/ul/li[2]/button")).click();
      Thread.sleep(2000);
      
    }


}


