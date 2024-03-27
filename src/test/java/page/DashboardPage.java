package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class DashboardPage {
    private SelenideElement inputAmount = $("[data-test-id=amount] input");
    public SelenideElement firstBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement transferMoney = $("[data-test-id=action-transfer]");
    private SelenideElement inputCard =  $("[data-test-id=from] input");

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
    }

    public int getCardBalance(String id) {
        for (int i = 0; i < cards.size(); i++) {
            SelenideElement card = cards.get(i);
           if(card.getAttribute("data-test-id").equals(id)){
               return extractBalance(card.getText());
           }
        }
        return 0;
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage depositMoneyToCardNum(int amount, int cardNum) {
        $$("[data-test-id=action-deposit]").get(cardNum-1).click();
        inputAmount.setValue(String.valueOf(amount));
        int lastNum = cardNum == 2 ? 1 : 2;
        inputCard.setValue("5559 0000 0000 000"+ lastNum);
        transferMoney.click();
        return new DashboardPage();
    }

}