package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.OrderCheckout;
import factories.OrderCheckoutFactory;

import static com.codeborne.selenide.Selenide.$$x;

public class OrderCheckoutPage {

    public OrderCheckout getDataFromCreatedAccount() {
        OrderCheckout orderCheckout = OrderCheckoutFactory.setData();
        ElementsCollection list = $$x("//ul[@id='address_delivery']//li");
        int i = 0;
        for (SelenideElement string : list) {
            if (!string.getText().equals("YOUR DELIVERY ADDRESS")) {
                switch (i) {
                    case 0:
                        String[] fio = string.getText().split(" ");
                        orderCheckout.setGender(fio[0].substring(0, fio[0].length() - 1));
                        orderCheckout.setFirstName(fio[1]);
                        orderCheckout.setLastName(fio[2]);
                        break;
                    case 1:
                        orderCheckout.setCompany(string.getText());
                        break;
                    case 2:
                        orderCheckout.setAddressRequired(string.getText());
                        break;
                    case 3:
                        orderCheckout.setAddress2(string.getText());
                        break;
                    case 4:
                        String[] address = string.getText().split(" ");
                        orderCheckout.setCity(address[0]);
                        orderCheckout.setState(address[1]);
                        orderCheckout.setZipCode(address[2]);
                        break;
                    case 5:
                        orderCheckout.setCountry(string.getText());
                        break;
                    case 6:
                        orderCheckout.setMobileNumber(string.getText());
                        break;
                }
                i++;
            }
        }
        return orderCheckout;
    }
}
