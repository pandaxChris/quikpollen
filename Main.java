package chri.pollengrabber;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\cli78\\OneDrive\\Desktop\\msedgedriver.exe");
		String location = "Hicksville, NY";

		EdgeOptions edge = new EdgeOptions();
		edge.addArguments("--headless=new");
		WebDriver driver = new EdgeDriver(edge);
		driver.get("https://weather.com");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("LocationSearch_input")));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		search.sendKeys(location);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> listboxButtons = driver.findElements(By.xpath("//button[contains(@id,'LocationSearch_listbox-')]"));

		listboxButtons.get(0).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//Go get pollen index
		WebElement pollenLink = driver.findElement(By.xpath("//a[contains(@href, '/forecast/allergy')]" ));
		driver.get(pollenLink.getAttribute("href"));

		//Get tree pollen
		List<WebElement> pollenContainer = driver.findElements(By.xpath("//div[contains(@class, 'PollenBreakdown--breakdownContainer--')]"));
		System.out.println("Pollen results for: " + location);
		System.out.println("========================================");
		for(WebElement l: pollenContainer) {
			System.out.println(l.getText());
			System.out.println("========================================");
		}
		driver.quit();
	}
}