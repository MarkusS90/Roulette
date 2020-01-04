import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RB {

	public static void main(String[] args) throws AWTException {
		//Initialisierung
		int faktor = 1;
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Markus\\Downloads\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.bet-at-home.com/de/casino/category/roulette/game/european-roulette");
	    Robot bot = new Robot();
	    bot.delay(32000);
		
		//Einloggen
		
		//Schnellspiel
		clickAt(211, 703, 1000);
		clickAt(215, 665, 1000);
		clickAt(360, 589, 1000);
		
		//10 Cent setzen
		clickAt(396, 674, 1000);
		clickAt(396, 674, 1000);
		clickAt(432, 670, 1000);
		
		//Schleife
		while(true){
			setzen(faktor);
			if(getGewinn(driver).contains("00")) {
				faktor++;
			}else{
				faktor = 1;
			}
		}
	}
	
	
	
	public static void clickAt(int x, int y, int delay) throws AWTException{
	    Robot bot = new Robot();
	    bot.delay(delay);
	    bot.mouseMove(x, y);    
	    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public static void setzen(int faktor) throws AWTException{
	    Robot bot = new Robot();
	    bot.delay(3000);
		int anz_click = 0; 
		if(faktor == 1) anz_click = 1;
		if(faktor == 2) anz_click = 2;
		if(faktor == 3) anz_click = 4;
		if(faktor == 4) anz_click = 8;
		if(faktor == 5) anz_click = 16;
		if(faktor == 6) anz_click = 32;
		if(faktor == 7) anz_click = 64;
		if(faktor == 8) anz_click = 128;
		if(faktor == 9) anz_click = 256;
		if(faktor == 10) anz_click = 512;
		if(faktor > 10) System.exit(0);
		
		//Schleife
		for(int x = 0; x < anz_click; x++) {
			clickAt(555, 631, 1000);
		}
		
		clickAt(802, 671, 1000);
		
	}
	
	public static String getGewinn(WebDriver driver) throws AWTException{
		String g;
	    Robot bot = new Robot();
	    bot.delay(2000);
        WebElement frame= driver.findElement(By.id("netentgame"));
        driver.switchTo().frame(frame);
        g = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[3]/div/div[1]/span[3]/div/span[2]")).getText();
        driver.switchTo().defaultContent();
		return g;
	}
}
