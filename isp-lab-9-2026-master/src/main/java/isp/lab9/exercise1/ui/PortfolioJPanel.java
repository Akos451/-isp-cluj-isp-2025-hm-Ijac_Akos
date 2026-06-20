package isp.lab9.exercise1.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Implement portfolio panel; for each owned stock add symbol, quantity, price per unit, total price of the position
 * It looks similar to the 'Market' panel.
 */
public class PortfolioJPanel extends JPanel {
    private StockMarketJFrame mainFrame;
    private JLabel fundsLabel;

    public PortfolioJPanel(StockMarketJFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fundsLabel = new JLabel("Available Funds: " + mainFrame.getPortfolio().getCash().toPlainString() + " $");
        fundsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(fundsLabel);
        add(headerPanel, BorderLayout.NORTH);

        JTable jTablePortfolio = new JTable();
        jTablePortfolio.setModel(mainFrame.getPortfolio());
        JScrollPane portfolioScrollablePane = new JScrollPane(jTablePortfolio);
        add(portfolioScrollablePane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh Portfolio");
        refreshButton.addActionListener(e -> {
            fundsLabel.setText("Available Funds: " + mainFrame.getPortfolio().getCash().toPlainString() + " $");
            mainFrame.getPortfolio().refreshPortfolioData();
        });
        add(refreshButton, BorderLayout.SOUTH);
    }
}