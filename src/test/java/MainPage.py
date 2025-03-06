from BasePage import BasePage
from selenium.webdriver.common.by import By

class MainPage(BasePage):
    FEATURED_PRODUCT = (By.CSS_SELECTOR, "#content > div.row .product-thumb")
    PRODUCT_NAME = (By.CSS_SELECTOR, ".description h4 a")
    PRODUCT_PRICE = (By.CSS_SELECTOR, ".description > .price span")
    CAROUSEL_ITEM = (By.CSS_SELECTOR, "#carousel-banner-0")
    HEADER = (By.CSS_SELECTOR, "#top")
    MONEY_LIST = (By.CSS_SELECTOR, "#top > div.row .product-thumb")
    SEARCH_BUTTON = (By.CSS_SELECTOR, "#search button")
    SEARCH_FIELD = (By.XPATH, "//input[@name='search']")
    SEARCH_ICON = (By.XPATH, "//i[@class='fa-solid fa-magnifying-glass']")
    SEARCH_RESULT_HEADER = (By.XPATH, "//*[@id='content']/h1")
    MACBOOK_IMAGE_LINK = (By.XPATH, "//img[@title='MacBook']")
    MACBOOK_INVALID_IMAGE_LINK = (By.XPATH, "//img[@title='MacBook Pro Max']")
    IMAGE_COUNTER = (By.XPATH, "//div[text()='2 of 5']")
    POPUP_CLOSE_BUTTON = (By.XPATH, "/html/body/div[2]/div/button[2]")
    CURRENCY_DROPDOWN = (By.CLASS_NAME, "dropdown-toggle")
    EUR_CURRENCY_LINK = (By.XPATH, "//a[@href='EUR']")
    CURRENCY_SYMBOL = (By.XPATH, "//strong")
    DESKTOPS_LINK = (By.XPATH, f"//a[@href='{BasePage.BASE_URL}/en-gb/catalog/desktops']")
    PC_LINK = (By.XPATH, f"//a[@href='{BasePage.BASE_URL}/en-gb/catalog/desktops/pc']")
    CONTENT_TEXT = (By.ID, "content")

    def __init__(self, driver):
        super().__init__(driver)
        self.driver = driver

    def open_main_page(self):
        self.driver.get(self.BASE_URL)

    def enter_search_text(self, text):
        self.driver.find_element(*self.SEARCH_FIELD).send_keys(text)

    def click_search_button(self):
        self.driver.find_element(*self.SEARCH_ICON).click()

    def get_search_result_header_text(self):
        return self.driver.find_element(*self.SEARCH_RESULT_HEADER).text

    def get_content_text(self):
        return self.driver.find_element(*self.CONTENT_TEXT).text
