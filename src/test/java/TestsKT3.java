import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

class TestsKT3 {

    private static WebDriver driver;
    private static ProductPage productPage;

    @BeforeAll
    static void setUp() {
        // Инициализация драйвера
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
    }

    @AfterAll
    static void tearDown() {
        // Закрытие драйвера после всех тестов
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @Story("Добавление в вишлист")
    @Description("Проверка добавления товара в список желаний")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление товара в вишлист")
    void testAddToWishlist(int productIndex) {
        productPage.openMainPage();
        productPage.clickProductName(productIndex, null, 3);
        productPage.findAndClickElement(ProductPage.WISHLIST_ON_PRODUCT_PAGE_BUTTON, null, 3);
        assertTrue(productPage.isLoginAlertDisplayed());
    }

    @ParameterizedTest
    @ValueSource(strings = {" Success: You have added Canon EOS 5D to your shopping cart! "})
    void testAddCamera(String expected) throws InterruptedException {
        productPage.openMainPage();
        productPage.openCamerasPage();
        productPage.clickProductName(0, null, 3);
        productPage.openOptionalOptions();
        productPage.selectRedColor();
        productPage.findAndClickElement(ProductPage.ADD_TO_CART_BUTTON, null, 3);

        if (productPage.isCartAlertDisplayed()) {
            if (productPage.isThisCartAlert(expected)) {
                assertTrue(true);
            } else {
                System.out.println("This is a wrong alert");
                assertTrue(false);
            }
        } else {
            System.out.println("There was no alert");
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" Success: You have added Samsung Galaxy Tab 10.1 to your shopping cart! "})
    void testAddTablet(String expected) {
        productPage.openMainPage();
        productPage.openTabletsPage();
        productPage.clickProductName(0, null, 3);
        productPage.findAndClickElement(ProductPage.ADD_TO_CART_BUTTON, null, 3);

        if (productPage.isCartAlertDisplayed()) {
            if (productPage.isThisCartAlert(expected)) {
                assertTrue(true);
            } else {
                System.out.println("This is a wrong alert");
                assertTrue(false);
            }
        } else {
            System.out.println("There was no alert");
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" Success: You have added HTC Touch HD to your shopping cart! "})
    void testAddHtcPhone(String expected) {
        productPage.openMainPage();
        productPage.openPhonesPage();
        productPage.clickProductName(0, null, 3);
        productPage.findAndClickElement(ProductPage.ADD_TO_CART_BUTTON, null, 3);

        if (productPage.isCartAlertDisplayed()) {
            if (productPage.isThisCartAlert(expected)) {
                assertTrue(true);
            } else {
                System.out.println("This is a wrong alert");
                assertTrue(false);
            }
        } else {
            System.out.println("There was no alert");
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0, me, hello, 5"
    })
    void testWriteReview(int productIndex, String name, String reviewText, int rating) throws InterruptedException {
        productPage.openMainPage();
        productPage.clickProductName(productIndex, null, 3);
        productPage.findAndClickElement(ProductPage.REVIEWS_BUTTON, null, 3);
        productPage.writeAReview(name, reviewText);
        productPage.rateTheProduct(rating);
        productPage.findAndClickElement(ProductPage.CONTINUE_REVIEW_BUTTON, null, 3);

        String actualReviewerName = productPage.getActualReviewerName();
        String actualReviewText = productPage.getActualReviewText();

        assertEquals(name, actualReviewerName);
        assertEquals(reviewText, actualReviewText);
    }
}