import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.function.Supplier;

public class BasePage {
    protected WebDriver driver;
    protected static final String BASE_URL = "http://localhost:8081/";
    protected WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void openMainPage() {
        driver.get(BASE_URL);
        logger.info("открыта страница");
    }
}
