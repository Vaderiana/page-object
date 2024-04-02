package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class DepositPage {

    private SelenideElement inputAmount = $("[data-test-id=amount] input");
    private SelenideElement transferMoney = $("[data-test-id=action-transfer]");
    private SelenideElement inputCard =  $("[data-test-id=from] input");

    public DepositPage() {
        inputCard.shouldBe(Condition.visible);
    }

    public DashboardPage transferMoney(int amount, DataHelper.CardInfo card) {
        inputAmount.setValue(String.valueOf(amount));
        inputCard.setValue(card.getCardNum());
        transferMoney.click();
        return new DashboardPage();
    }
}