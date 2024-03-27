import com.codeborne.selenide.Condition;
import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    public static final int AMOUNT = 5000;

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.firstBalance.shouldBe(Condition.visible);
        var initialAmount = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        var dashboardPageAfter =  dashboardPage.depositMoneyToCardNum(AMOUNT, 2);
        var finalAmount = dashboardPageAfter.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        assertEquals( AMOUNT, initialAmount - finalAmount);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.firstBalance.shouldBe(Condition.visible);
        var initialAmount = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        var dashboardPageAfter =  dashboardPage.depositMoneyToCardNum(AMOUNT, 1);
        var finalAmount = dashboardPageAfter.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        assertEquals( AMOUNT, initialAmount - finalAmount);
    }
}