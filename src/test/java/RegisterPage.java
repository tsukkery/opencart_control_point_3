import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    protected static final By FIRSTNAME_FIELD = By.xpath("//input[@name='firstname']");
    protected static final By LASTNAME_FIELD = By.xpath("//input[@name='lastname']");
    protected static final By EMAIL_FIELD = By.xpath("//*[@id='input-email']");
    protected static final By PASSWORD_FIELD = By.xpath("//*[@id='input-password']");
    protected static final By LOGIN_BUTTON = By.xpath("//*[@id='form-login']/div[3]/button");
    protected static final By CONTINUE_BUTTON = By.xpath("//button[text()='Continue']");
    protected static final By AGREE_BUTTON = By.xpath("//input[@name='agree']");

    public RegisterPage(WebDriver driver) {
        super(driver);
        logger.info("Инициализирована страница");
    }

    public void enterFirstname(String firstname) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstname);

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}({})", methodName, firstname);
    }

    public void enterLastname(String lastname) {
        driver.findElement(LASTNAME_FIELD).sendKeys(lastname);

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}({})", methodName, lastname);
    }

    public void enterEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}({})", methodName, email);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}({})", methodName, password);
    }

    public void clickLogin() {
        driver.findElement(LOGIN_BUTTON).click();

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}", methodName);
    }

    public void clickAgree() {
        driver.findElement(AGREE_BUTTON).click();

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}", methodName);
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}", methodName);
    }
}