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
        var initialAmountFirst = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var initialAmountSecond = dashboardPage.getCardBalance(DataHelper.getSecondCard());
        var depositPage =  dashboardPage.depositMoneyToCardNum(2);
        var dashboardPageAfter = depositPage.transferMoney(AMOUNT, DataHelper.getFirstCard());
        var finalAmountFirst = dashboardPageAfter.getCardBalance(DataHelper.getFirstCard());
        var finalAmountSecond = dashboardPageAfter.getCardBalance(DataHelper.getSecondCard());
        assertEquals(AMOUNT, initialAmountFirst - finalAmountFirst);
        assertEquals(AMOUNT, finalAmountSecond - initialAmountSecond);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var initialAmountFirst = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var initialAmountSecond = dashboardPage.getCardBalance(DataHelper.getSecondCard());
        var depositPage =  dashboardPage.depositMoneyToCardNum(1);
        var dashboardPageAfter = depositPage.transferMoney(AMOUNT, DataHelper.getSecondCard());
        var finalAmountFirst = dashboardPageAfter.getCardBalance(DataHelper.getFirstCard());
        var finalAmountSecond = dashboardPageAfter.getCardBalance(DataHelper.getSecondCard());
        assertEquals(AMOUNT, initialAmountSecond - finalAmountSecond);
        assertEquals(AMOUNT, finalAmountFirst - initialAmountFirst);
    }
}