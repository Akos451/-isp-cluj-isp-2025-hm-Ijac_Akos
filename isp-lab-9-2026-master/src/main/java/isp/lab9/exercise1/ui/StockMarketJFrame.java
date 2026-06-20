package isp.lab9.exercise1.ui;

import isp.lab9.exercise1.services.UserPortfolio;
import isp.lab9.exercise1.services.StockMarket;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mihai.hulea
 * @author radu.miron
 */
public class StockMarketJFrame extends JFrame {
    private StockMarket stockMarket;
    private UserPortfolio portfolio;

    /**
     * Creates new form StockMarketJFrame
     */
    public StockMarketJFrame() {
        try {
            stockMarket = new StockMarket();
            stockMarket.refreshMarketData();

            portfolio = new UserPortfolio(new BigDecimal(1000), new TreeMap<>());
        } catch (IOException ex) {
            Logger.getLogger(StockMarketJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        setVisible(true);
    }

    /**
     * Initializes the window with the tabs and main panels.
     */
    private void initComponents() {
        this.setSize(700, 450);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Market", new MarketJPanel(this));
        tabs.addTab("UserPortfolio", new PortfolioJPanel(this));
        tabs.addTab("Buy", new BuyJPanel(this));
        tabs.addTab("Sell", new SellJPanel());

        this.add(tabs);
    }

    public StockMarket getStockMarket() {
        return stockMarket;
    }

    public UserPortfolio getPortfolio() {
        return portfolio;
    }
}