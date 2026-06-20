package isp.lab9.exercise1;

import isp.lab9.exercise1.services.UserPortfolio;
import isp.lab9.exercise1.ui.LoginJFrame;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Core test verification suite validating structural model integrity
 * and portfolio business computations for Lab 9.
 */
public class StockApplicationTest {

    private UserPortfolio testPortfolio;

    @Before
    public void setUp() {
        // Initialize sample wallet with $1,000 baseline cash
        testPortfolio = new UserPortfolio(new BigDecimal("1000.00"), new TreeMap<>());
    }

    @Test
    public void testAuthenticationDatabaseMock() {
        // Ensure credential records are loaded properly into Login configuration maps
        assertNotNull("Static accounts ledger database should initialize", LoginJFrame.accounts);

        // Explicitly check baseline template profile
        LoginJFrame loginInstance = new LoginJFrame();
        assertTrue("System must register user1 account properties", LoginJFrame.accounts.containsKey("user1"));
        assertEquals("password1", LoginJFrame.accounts.get("user1"));

        // Clean up window visibility from background thread memory
        loginInstance.dispose();
    }

    @Test
    public void testPortfolioInitialBalanceAndAssetDeposit() {
        // Confirm baseline financial properties
        assertEquals(0, testPortfolio.getRowCount());
        assertEquals(new BigDecimal("1000.00"), testPortfolio.getCash());

        // Perform mock transactional buy actions into structural maps
        testPortfolio.getShares().put("MSFT", 15);
        testPortfolio.getShares().put("AAPL", 5);

        // TreeMap automatically sorts keys alphabetically: AAPL will be row 0, MSFT will be row 1
        assertEquals(2, testPortfolio.getRowCount());
        assertEquals("AAPL", testPortfolio.getValueAt(0, 0));
        assertEquals(5, testPortfolio.getValueAt(0, 1));
        assertEquals("MSFT", testPortfolio.getValueAt(1, 0));
        assertEquals(15, testPortfolio.getValueAt(1, 1));
    }

    @Test
    public void testCashBalanceLiquidationDeductions() {
        BigDecimal purchaseCost = new BigDecimal("245.50");

        // Compute transaction balance allocation adjustment
        BigDecimal remainingCash = testPortfolio.getCash().subtract(purchaseCost);
        testPortfolio.setCash(remainingCash);

        assertEquals(new BigDecimal("754.50"), testPortfolio.getCash());
        assertTrue("Wallet status balance remaining must stay positive", testPortfolio.getCash().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    public void testGridModelColumnLayoutSchema() {
        // Assert visual display formatting properties bounds remain stable
        assertEquals(4, testPortfolio.getColumnCount());
        assertEquals("Symbol", testPortfolio.getColumnName(0));
        assertEquals("Quantity", testPortfolio.getColumnName(1));
        assertEquals("Price per unit", testPortfolio.getColumnName(2));
        assertEquals("Total price", testPortfolio.getColumnName(3));
    }
}