import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final By CART_PAGE_LINK = By.xpath("//*[@id=\"top\"]/div/div[2]/ul/li[4]/a/span");
    private static final By CART_CONTENT = By.xpath("//*[@id=\"content\"]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() {
        driver.get(BasePage.BASE_URL);
        logger.info("Инициализирована страница");
    }

    public void openCartPage() {
        driver.findElement(CART_PAGE_LINK).click();
        logger.info("открыта страница");
    }

    public String getCartContent() {
        return driver.findElement(CART_CONTENT).getText();
    }


    public void addHtcPhoneToCart(ProductPage productPage) {
        productPage.openMainPage();
        productPage.openPhonesPage();
        productPage.clickProductName(0, null, 3); // Передаем индекс продукта
        productPage.findAndClickElement(productPage.ADD_TO_CART_BUTTON, null, 3);

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}", methodName);
    }
}