import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.Assert.assertEquals;

public class Main {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.edgedriver().setup();
        this.driver = new EdgeDriver();
        this.driver.navigate().to("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void alert() {
        this.driver.findElement(By.xpath("//*[contains(text(),'Click for JS Alert')]")).click();
        this.driver.switchTo().alert().accept();
    }

    @Test
    public void alertSecond() {
        this.driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
        this.driver.switchTo().alert().dismiss();
    }

    @Test
    public void alertThird() {
        this.driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]")).click();
        Alert alert = this.driver.switchTo().alert();
        String txt = "alert";
        alert.sendKeys(txt);
        alert.accept();
        assertEquals("You entered: " + txt, this.driver.findElement(By.id("result")).getText());
    }

    @After
    public void end() {
        this.driver.close();
    }
}
