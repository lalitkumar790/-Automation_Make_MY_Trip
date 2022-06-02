import java.time.Duration;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationDemo {
  
	public static void main(String args[]) throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");

    WebDriver driver = new ChromeDriver();

    Dimension newDimension = new Dimension(1920, 1080);
    driver.manage().window().setSize(newDimension);

    driver.get("https://www.makemytrip.com/railways/");
    String url = driver.getCurrentUrl();
    String titleOfPage = driver.getTitle();
    System.out.println("The current url is: " + url + "\n The title of the page is: " + titleOfPage);

    driver.findElement(By.id("fromCity")).click();
    Thread.sleep(2000);
    WebElement fromInput = driver.findElement(By.cssSelector("input[placeholder=\"From\"]"));
    fromInput.click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    Thread.sleep(2000);
    fromInput.sendKeys("DELHI");
    WebElement fromSuggestionListBox = driver.findElement(By.xpath("//*[@id=\"react-autowhatever-1\"]/div/ul"));

    WebElement fromFirstSuggestion = wait.until(
        new Function<WebDriver, WebElement>() {
          public WebElement apply(WebDriver driver) {
            return fromSuggestionListBox.findElement(By.xpath("//*[contains(text(), 'Delhi - All Stations')]"));
          }
        });

    fromFirstSuggestion.click();

    Thread.sleep(2000);

    WebElement toInput = driver.findElement(By.cssSelector("input[placeholder=\"To\"]"));
    toInput.sendKeys("LUCKNOW");

    WebElement toSuggestionListBox = driver.findElement(By.id("react-autowhatever-1"));

    WebElement toFirstSuggestion = wait.until(
        new Function<WebDriver, WebElement>() {
          public WebElement apply(WebDriver driver) {
            return toSuggestionListBox.findElement(By.xpath("//*[contains(text(), 'Lucknow - All Stations')]"));
          }
        });

    toFirstSuggestion.click();

    Thread.sleep(2000);

    try {
      driver.findElement(By.id("departure")).click();
    } catch (Exception e) {
      // selenium was throwing an exception that the element was not clickable, as a
      // way-around the method has been put inside an try-catch block
    }

    driver.findElement(By.cssSelector(".DayPicker-Day[aria-label=\"Fri May 20 2022\"]")).click();

    driver.findElement(By.cssSelector("li[data-cy=\"3A\"]")).click();
    driver.findElement(By.cssSelector("a[data-cy=\"submit\"]")).click();

    driver.wait(300000);
    driver.quit();

  }
}


