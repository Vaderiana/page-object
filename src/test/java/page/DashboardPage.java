package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class DashboardPage {
    public SelenideElement firstBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        firstBalance.shouldBe(Condition.visible);
    }

    public int getCardBalance(DataHelper.CardInfo card) {
        var text = cards.findBy(Condition.text(card.getCardNum().substring(15))).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DepositPage depositMoneyToCardNum(int cardNum) {
        $$("[data-test-id=action-deposit]").get(cardNum-1).click();
        return new DepositPage();
    }

}