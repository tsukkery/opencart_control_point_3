from selenium import webdriver
import pytest
from ProductPage import ProductPage

class TestsKT3:
    @pytest.fixture(scope="session")
    def product_page(self, driver):
        return ProductPage(driver)


    @pytest.mark.parametrize("product_index", [0, 1, 2])
    def test_add_to_wishlist(self, product_page, product_index):
        product_page.open_main_page()
        product_page.click_product_name(product_index)
        product_page.find_and_click_element(product_page.WISHLIST_ON_PRODUCT_PAGE_BUTTON)
        assert product_page.is_login_alert_displayed()

    @pytest.mark.parametrize("expected", [" Success: You have added Canon EOS 5D to your shopping cart! "])
    def test_add_camera(self, product_page, expected):
        product_page.open_main_page()
        product_page.open_cameras_page()
        product_page.click_product_name(product_index=0)
        product_page.open_optional_options()
        product_page.select_red_color()
        product_page.find_and_click_element(product_page.ADD_TO_CART_BUTTON)

        if product_page.is_cart_alert_displayed():
            if product_page.is_this_cart_alert(expected):
                assert True
            else:
                print("Это уведомление не о добавлении в корзину")
                assert False
        else:
            print("Уведомление не появилось")
            assert False

    @pytest.mark.parametrize("expected", [" Success: You have added Samsung Galaxy Tab 10.1 to your shopping cart! "])
    def test_add_tablet(self, product_page, expected):
        product_page.open_main_page()
        product_page.open_tablets_page()
        product_page.click_product_name(product_index=0)
        product_page.find_and_click_element(product_page.ADD_TO_CART_BUTTON)

        if product_page.is_cart_alert_displayed():
            if product_page.is_this_cart_alert(expected):
                assert True
            else:
                print("This is a wrong alert")
                assert False
        else:
            print("There was no alert")
            assert False

    @pytest.mark.parametrize("expected", [" Success: You have added HTC Touch HD to your shopping cart! "])
    def test_add_htc_phone(self, product_page, expected):
        product_page.open_main_page()
        product_page.open_phones_page()
        product_page.click_product_name(product_index=0)
        product_page.find_and_click_element(product_page.ADD_TO_CART_BUTTON)

        if product_page.is_cart_alert_displayed():
            if product_page.is_this_cart_alert(expected):
                assert True
            else:
                print("This is a wrong alert")
                assert False
        else:
            print("There was no alert")
            assert False

    @pytest.mark.parametrize("product_index", [0])
    @pytest.mark.parametrize("name", ["me"])
    @pytest.mark.parametrize("review_text", ["finally some really good stuff"])
    @pytest.mark.parametrize("rating", [5])
    def test_write_review(self, driver, product_page, product_index, name, review_text, rating):
        product_page.open_main_page()
        product_page.click_product_name(product_index)
        product_page.find_and_click_element(product_page.REVIEWS_BUTTON)
        product_page.write_a_review(name, review_text)
        product_page.rate_the_product(rating)
        product_page.find_and_click_element(product_page.CONTINUE_REVIEW_BUTTON)
        actual_reviewer_name = product_page.get_actual_reviewer_name()
        actual_review_text = product_page.get_actual_review_text()
        assert actual_reviewer_name == name and actual_review_text == review_text

@pytest.fixture(scope="session")
def driver():
    driver = webdriver.Firefox()
    driver.maximize_window()
    yield driver
    driver.quit()
