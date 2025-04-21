import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private static final By FEATURED_PRODUCT = By.cssSelector("#content > div.row .product-thumb");
    private static final By PRODUCT_NAME = By.cssSelector(".description h4 a");
    private static final By PRODUCT_PRICE = By.cssSelector(".description > .price span");
    private static final By CAROUSEL_ITEM = By.cssSelector("#carousel-banner-0");
    private static final By HEADER = By.cssSelector("#top");
    private static final By MONEY_LIST = By.cssSelector("#top > div.row .product-thumb");
    private static final By SEARCH_BUTTON = By.cssSelector("#search button");
    private static final By SEARCH_FIELD = By.xpath("//input[@name='search']");
    private static final By SEARCH_ICON = By.xpath("//i[@class='fa-solid fa-magnifying-glass']");
    private static final By SEARCH_RESULT_HEADER = By.xpath("//*[@id='content']/h1");
    private static final By MACBOOK_IMAGE_LINK = By.xpath("//img[@title='MacBook']");
    private static final By MACBOOK_INVALID_IMAGE_LINK = By.xpath("//img[@title='MacBook Pro Max']");
    private static final By IMAGE_COUNTER = By.xpath("//div[text()='2 of 5']");
    private static final By POPUP_CLOSE_BUTTON = By.xpath("/html/body/div[2]/div/button[2]");
    private static final By CURRENCY_DROPDOWN = By.className("dropdown-toggle");
    private static final By EUR_CURRENCY_LINK = By.xpath("//a[@href='EUR']");
    private static final By CURRENCY_SYMBOL = By.xpath("//strong");
    private static final By DESKTOPS_LINK = By.xpath("//a[@href='" + BasePage.BASE_URL + "/en-gb/catalog/desktops']");
    private static final By PC_LINK = By.xpath("//a[@href='" + BasePage.BASE_URL + "/en-gb/catalog/desktops/pc']");
    private static final By CONTENT_TEXT = By.id("content");

    public MainPage(WebDriver driver) {
        super(driver);
        logger.info("Инициализирована страница");
    }

    public void openMainPage() {
        driver.get(BASE_URL);
        logger.info("открыта страница");
    }

    public void enterSearchText(String text) {
        driver.findElement(SEARCH_FIELD).sendKeys(text);

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}({})", methodName, text);
    }

    public void clickSearchButton() {
        driver.findElement(SEARCH_ICON).click();

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("функция {}", methodName);
    }

    public String getSearchResultHeaderText() {
        return driver.findElement(SEARCH_RESULT_HEADER).getText();
    }

    public String getContentText() {
        return driver.findElement(CONTENT_TEXT).getText();
    }
}
