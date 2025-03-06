from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from BasePage import BasePage


class AuthPage(BasePage):
    FIRSTNAME_FIELD = (By.XPATH, "//input[@name='firstname']")
    LASTNAME_FIELD = (By.XPATH, "//input[@name='lastname']")
    EMAIL_FIELD = (By.XPATH, "//*[@id='input-email']")
    PASSWORD_FIELD = (By.XPATH, "//*[@id='input-password']")
    LOGIN_BUTTON = (By.XPATH, "//*[@id='form-login']/div[3]/button")
    CONTINUE_BUTTON = (By.XPATH, "//button[text()='Continue']")
    AGREE_BUTTON = (By.XPATH, "//input[@name='agree']")

    def __init__(self, driver):
        super().__init__(driver)
        self.driver = driver

    def enter_firstname(self, firstname):
        self.driver.find_element(*self.FIRSTNAME_FIELD).send_keys(firstname)

    def enter_lastname(self, lastname):
        self.driver.find_element(*self.LASTNAME_FIELD).send_keys(lastname)

    def enter_email(self, email):
        self.driver.find_element(*self.EMAIL_FIELD).send_keys(email)

    def enter_password(self, password):
        self.driver.find_element(*self.PASSWORD_FIELD).send_keys(password)

    def click_login(self):
        self.driver.find_element(*self.LOGIN_BUTTON).click()

    def click_agree(self):
        self.driver.find_element(*self.AGREE_BUTTON).send_keys(Keys.SPACE)

    def click_continue(self):
        self.driver.find_element(*self.CONTINUE_BUTTON).click()