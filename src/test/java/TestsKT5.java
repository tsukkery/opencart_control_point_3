import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;

public class TestsKT5 {
    private static WebDriver driver;
    private static AdminPage adminPage;

    @BeforeSuite
    static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        adminPage = new AdminPage(driver);
    }

    @Test(dataProvider = "categoryData")
    public void testAddDevicesCategory(String categoryName, String seoName) throws InterruptedException {
        adminPage.openAdminPage();
        adminPage.login();
        Thread.sleep(1000);
        adminPage.login();
        adminPage.openCategoriesPage();
        adminPage.addNewCategory("Devices", "devices");
        adminPage.openStartPage();
        Thread.sleep(1000);
        adminPage.openCategoriesPage();
        adminPage.clickSecondPageCategoriesTable();
        Thread.sleep(1000);
        String devicesCategory = adminPage.devicesCategoryGetText();
        assertEquals(devicesCategory, "Devices\nEnabled");
    }

    @DataProvider(name = "categoryData")
    public Object[][] provideCategoryData() {
        return new Object[][] {
                {"Devices", "devices"}
        };
    }

    @Test(dataProvider = "productData")
    public void testAddProduct(String name, String metaTag, String model, String seoUrl) throws InterruptedException {
        adminPage.openAdminPage();
        adminPage.login();
        Thread.sleep(1000);
        adminPage.login();
        adminPage.openProductsPage();
        adminPage.clickAddButton();
        adminPage.enterNameAndMetaTag(name, metaTag);
        adminPage.enterModel(model);
        adminPage.selectCategory("Devices");
        adminPage.productEnterSeoUrl(seoUrl);
        adminPage.clickSaveButton();
        String actual = adminPage.getAlertText();
        assertTrue(actual.equals("Success: You have modified products!") ||
                actual.equals("Warning: Please check the form carefully for errors!"));
    }

    @DataProvider(name = "productData")
    public Object[][] provideProductData() {
        return new Object[][] {
                {"Keyboard 1", "Keyboard 1", "Keyboard 1", "keyboard-1"},
                {"Keyboard 2", "Keyboard 2", "Keyboard 2", "keyboard-2"},
                {"Mouse 1", "Mouse 1", "Mouse 1", "mouse-1"},
                {"Mouse 2", "Mouse 2", "Mouse 2", "mouse-2"}
        };
    }

    @Test(dataProvider = "searchData")
    public void testSearchAddedProducts(String searchText) {
        adminPage.openMainPage();
        adminPage.search(searchText);
        assertEquals(adminPage.getSearchResultHeaderText(), "Search - " + searchText);
    }

    @DataProvider(name = "searchData")
    public Object[][] provideSearchData() {
        return new Object[][] {
                {"Keyboard 1"},
                {"Keyboard 2"}
        };
    }

    @Test
    public void testDeleteProducts() throws InterruptedException {
        adminPage.openAdminPage();
        adminPage.login();
        Thread.sleep(1000);
        adminPage.login();
        adminPage.openProductsPage();
        adminPage.clickSecondPageProductsTable();
        List<String> productNames = Arrays.asList("Keyboard 1", "Mouse 1");
        adminPage.deleteProductsByName(
                productNames,
                AdminPage.TABLE_PRODUCT_ROW,
                AdminPage.DELETE_CHECKBOX,
                AdminPage.DELETE_PRODUCT_BUTTON,
                AdminPage.TABLE_PRODUCT_NAME,
                null
        );
        String actual = adminPage.getAlertText();
        assertEquals(actual, "Success: You have modified products!");
    }

    @Test(dataProvider = "searchDeletedData")
    public void searchDeletedProducts(String searchText) {
        adminPage.openMainPage();
        adminPage.search(searchText);
        assertEquals(adminPage.getSearchResultHeaderText(), "Search - " + searchText);
    }

    @DataProvider(name = "searchDeletedData")
    public Object[][] provideSearchDeletedData() {
        return new Object[][] {
                {"Keyboard 2"},
                {"Mouse 2"}
        };
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}