package ForFacebook;


import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class FbDataErase {

	public static WebDriver driver = null;
	
	public static void main(String[] args) throws InterruptedException,NoSuchElementException {
		
		System.out.println("Github Ateeb Branch code is running...");
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Dell\\Desktop\\Office Stuff\\SeleniumJava_SocialMediaDataRemoval\\Driver\\chromedriver.exe");
		
		//Create a map to store  preferences 
		Map<String, Object> prefs = new HashMap<String, Object>();

		//add key and value to map as follow to switch off browser notification
		//Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.notifications", 2);

		//Create an instance of ChromeOptions 
		ChromeOptions options = new ChromeOptions();

		// set ExperimentalOption - prefs 
		options.setExperimentalOption("prefs", prefs);

		//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		
		//open the web
		driver.navigate().to("https://www.facebook.com/");
		
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		WebElement Emailid=driver.findElement(By.id("email"));
		Emailid.click();
		Emailid.sendKeys("");//Replace your id here		
		Thread.sleep(2000);
		
		WebElement password=driver.findElement(By.id("pass"));
		password.sendKeys("");//Replace your password here
		Thread.sleep(2000);
		
		//press login button
		WebElement logInBtn=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/form/table/tbody/tr[2]/td[3]/label/input"));
		logInBtn.click();
		Thread.sleep(2000);
		
		//open carret for activity option
		WebElement dropdown_option=driver.findElement(By.id("userNavigationLabel"));
		dropdown_option.click();
		Thread.sleep(2000);
	
		//move on activity option
		WebElement activityBtn= driver.findElement(By.linkText("Activity log"));//By.cssSelector("span._54nh>div")
		Actions activity_action =new Actions(driver);
		activity_action.moveToElement(activityBtn).perform();
		
		//click on activity
		WebElement activity_click=driver.findElement(By.cssSelector("span._54nh>div>div"));
		activity_click.click();
		Thread.sleep(2000);
		
		//click on comments tab		
		WebElement comments_tab=driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[1]/div/div/div/div/div[3]/ul/li[3]/a/div[2]/div"));
		comments_tab.click();
		Thread.sleep(2000);

		int break_count1=0;
		int comment_flag=0;
		int simpleCommentsCounter=0;
		int mentionedCommentsCounter=0;
		Actions hoverAction1 = new Actions(driver);
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		try {
		    long lastHeight1=((Number)je1.executeScript("return document.body.scrollHeight")).longValue();
		    while (true) {
		        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		        Thread.sleep(8000);
		        long newHeight1 = ((Number)je1.executeScript("return document.body.scrollHeight")).longValue();
		        if (newHeight1 == lastHeight1 || break_count1 == 6) {// || break_count1 == 6
		            break;
		        }
		        lastHeight1 = newHeight1;
		        
		        break_count1++;
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

		//To press all the edit buttons on comments page
		List<WebElement> elements1 = driver.findElements(By.linkText("Edit"));
		System.out.println("List size of edit element on comments page- "+elements1.size());
		
		java.util.Iterator<WebElement> iterator1 = elements1.iterator();
        while(iterator1.hasNext()) 
        {
            WebElement element1 = iterator1.next();
            if (element1.isDisplayed()) 
            {
            	hoverAction1.moveToElement(element1).perform();            	
            	JavascriptExecutor executor = (JavascriptExecutor)driver;
            	executor.executeScript("arguments[0].click();", element1);
            	
            	if(driver.findElements(By.linkText("Delete")).size()!=0)
            	{
            		WebElement deleteOption= driver.findElement(By.linkText("Delete"));
            		Actions deleteAction =new Actions(driver);
            		deleteAction.moveToElement(deleteOption).perform();
            		Thread.sleep(1000);
            		JavascriptExecutor executor11 = (JavascriptExecutor)driver;
            		executor11.executeScript("arguments[0].click();", deleteOption);
            		simpleCommentsCounter++;
            		System.out.println(simpleCommentsCounter+" simple comment on this page found.");            		
            		comment_flag=1;
            	}
            	else 
            	{
            		WebElement removeOption= driver.findElement(By.linkText("Report/Remove tag"));
            		Actions removeAction =new Actions(driver);
            		removeAction.moveToElement(removeOption).perform();
            		Thread.sleep(1000);
            
//					Report/Remove tag click feature not available from facebook side.(25/3/20)
//                	JavascriptExecutor executor33 = (JavascriptExecutor)driver;
//                	executor33.executeScript("arguments[0].click();", removeOption);
            		mentionedCommentsCounter++;
                	System.out.println(mentionedCommentsCounter+" mentioned comment on this page found.");
            	}
        }
    } 
        
        if(comment_flag==1) {
        	System.out.println(simpleCommentsCounter+" simple comments found on your timeline and deleted successfully.");
		}else {
        	System.out.println("No comments Found.");
		}

//click on likes and reaction tab		
		WebElement likes_tab=driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[1]/div/div/div/div/div[3]/ul/li[2]/a/div[2]/div"));
		likes_tab.click();
		Thread.sleep(2000);

		int break_count2=0;
		int post_flag=0;
		int postCounter=0;
		Actions hoverAction2 = new Actions(driver);
		JavascriptExecutor je2 = (JavascriptExecutor) driver;
		try {
		    long lastHeight2=((Number)je2.executeScript("return document.body.scrollHeight")).longValue();
		    while (true) {
		        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		        Thread.sleep(8000);
		        long newHeight2 = ((Number)je1.executeScript("return document.body.scrollHeight")).longValue();
		        if (newHeight2 == lastHeight2 || break_count2 == 6) {// || break_count2 == 6
		            break;
		        }
		        lastHeight2 = newHeight2;
		        
		        break_count2++;
//		        System.out.println("break_count2 -"+break_count2);
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		//To press all the edit buttons
		List<WebElement> elements2 = driver.findElements(By.linkText("Edit"));
		System.out.println("List size of edit element on likes & reaction page- "+elements2.size());
        java.util.Iterator<WebElement> iterator2 = elements2.iterator();
        while(iterator2.hasNext()) 
        {
            WebElement element2 = iterator2.next();
            if (element2.isDisplayed()) 
            {
            	hoverAction2.moveToElement(element2).perform();            	
            	JavascriptExecutor executor2 = (JavascriptExecutor)driver;
            	executor2.executeScript("arguments[0].click();", element2);
            	
            	if(driver.findElements(By.linkText("Unlike")).size()!=0)
            	{
            		WebElement unlikeOption= driver.findElement(By.linkText("Unlike"));
	        		Actions unlikeAction =new Actions(driver);
	        		unlikeAction.moveToElement(unlikeOption).perform();
	        		Thread.sleep(1000);
	            	JavascriptExecutor executor22 = (JavascriptExecutor)driver;
 		           	executor22.executeScript("arguments[0].click();", unlikeOption);
	        		postCounter++;
	            	System.out.println(postCounter+" number activity is a Liked type post");

            	}
            	else//driver.findElements(By.linkText("Remove reaction")).size()!=0
            	{
            		WebElement removeOption= driver.findElement(By.linkText("Remove reaction"));
            		Actions removeAction =new Actions(driver);
            		removeAction.moveToElement(removeOption).perform();
            		Thread.sleep(1000);
                	JavascriptExecutor executor33 = (JavascriptExecutor)driver;
                	executor33.executeScript("arguments[0].click();", removeOption);
            		postCounter++;
                	System.out.println(postCounter+" number activity is a Reaction type post");            		
            	}
            	post_flag=1;
            }
        } 
        
		if(post_flag==1) {
        	System.out.println(postCounter+" posts found on your timeline and deleted successfully.");
		}else {
        	System.out.println("No post Found.");
		}
		
		//click for logout option
		WebElement dropdown2=driver.findElement(By.id("userNavigationLabel"));
		dropdown2.click();
		Thread.sleep(2000);
		
		//move on logout option
		WebElement logOut= driver.findElement(By.id("userNavigationLabel"));
		Actions action2 =new Actions(driver);
		action2.moveToElement(logOut).perform();
		
		//click on logout button
		WebElement outClk=driver.findElement(By.linkText("Log Out"));
		outClk.click();
		Thread.sleep(2000);

		driver.close();
		driver.quit();

		System.out.println("Code Finished.");
	}
}