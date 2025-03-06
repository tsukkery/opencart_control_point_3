import pytest
from BasePage import BasePage
from ProductPage import ProductPage
from selenium.webdriver.common.by import By


class CartPage(BasePage):
    CART_PAGE_LINK = (By.XPATH, "//*[@id=\"top\"]/div/div[2]/ul/li[4]/a/span")
    CART_CONTENT = (By.XPATH, "//*[@id=\"content\"]")

    def __init__(self, driver):
        super().__init__(driver)

    @pytest.fixture(scope="session")
    def product_page(self, driver):
        return ProductPage(driver)

    def open_main_page(self):
        self.driver.get(BasePage.BASE_URL)

    def open_cart_page(self):
        self.driver.find_element(*self.CART_PAGE_LINK).click()

    def get_cart_content(self):
        return self.driver.find_element(*self.CART_CONTENT).text

    def add_htc_phone_to_cart(self, product_page):
        product_page.open_main_page()
        product_page.open_phones_page()
        product_page.click_product_name(product_index=0)
        product_page.find_and_click_element(product_page.ADD_TO_CART_BUTTON)