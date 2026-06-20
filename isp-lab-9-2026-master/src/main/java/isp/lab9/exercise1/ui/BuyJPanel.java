package isp.lab9.exercise1.ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyJPanel extends JPanel {
    private StockMarketJFrame mainFrame;

    public BuyJPanel(StockMarketJFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents(){
        setLayout(new GridLayout(2, 2));

        JPanel buyPanel = new JPanel();
        buyPanel.setLayout(new GridLayout(10, 2));

        JLabel availableFundsLabel = new JLabel("Available funds:");
        JTextField availableFundsTextField = new JTextField(mainFrame.getPortfolio().getCash().toPlainString() + " $");
        availableFundsTextField.setEditable(false);

        JLabel symbolLabel = new JLabel("Symbol:");
        JComboBox<String> symbolComboBox = new JComboBox<>();
        symbolComboBox.setModel(new DefaultComboBoxModel(mainFrame.getStockMarket().getSymbols()));

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();

        JLabel costLabel = new JLabel("Total cost:");
        JTextField costTextField = new JTextField();
        costTextField.setEditable(false);

        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> {
            try {
                String symbol = (String) symbolComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityTextField.getText());
                BigDecimal stockPrice = mainFrame.getStockMarket().getStockPrice(symbol);
                BigDecimal totalCost = stockPrice.multiply(new BigDecimal(quantity));

                if (mainFrame.getPortfolio().getCash().compareTo(totalCost) >= 0) {
                    BigDecimal remainingFunds = mainFrame.getPortfolio().getCash().subtract(totalCost);
                    mainFrame.getPortfolio().setCash(remainingFunds);

                    Map<String, Integer> shares = mainFrame.getPortfolio().getShares();
                    shares.put(symbol, shares.getOrDefault(symbol, 0) + quantity);

                    availableFundsTextField.setText(remainingFunds.toPlainString() + " $");
                    mainFrame.getPortfolio().refreshPortfolioData();

                    JOptionPane.showMessageDialog(this, "Successfully bought " + quantity + " shares of " + symbol);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity format!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Network error fetching stock price!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton costButton = new JButton("Get cost");
        costButton.addActionListener(e ->
                calculateTotalCostActionPerformed(symbolComboBox, quantityTextField, costTextField));

        buyPanel.add(availableFundsLabel);
        buyPanel.add(availableFundsTextField);
        buyPanel.add(new JPanel());
        buyPanel.add(new JPanel());
        buyPanel.add(symbolLabel);
        buyPanel.add(symbolComboBox);
        buyPanel.add(new JPanel());
        buyPanel.add(new JPanel());
        buyPanel.add(quantityLabel);
        buyPanel.add(quantityTextField);
        buyPanel.add(new JPanel());
        buyPanel.add(new JPanel());
        buyPanel.add(costLabel);
        buyPanel.add(costTextField);
        buyPanel.add(new JPanel());
        buyPanel.add(new JPanel());
        buyPanel.add(costButton);
        buyPanel.add(buyButton);
        add(buyPanel);
        add(new JPanel());
        add(new JPanel());
        add(new JPanel());
    }

    private void calculateTotalCostActionPerformed(JComboBox<String> symbolComboBox,
                                                   JTextField quantityTextField,
                                                   JTextField totalCostTextField) {
        try {
            String symbol = (String) symbolComboBox.getSelectedItem();
            BigDecimal stockPrice = mainFrame.getStockMarket().getStockPrice(symbol);

            try {
                int quantity = Integer.parseInt(quantityTextField.getText());
                DecimalFormat formatter = new DecimalFormat("#,##0.##");
                totalCostTextField.setText(
                        formatter.format(stockPrice.multiply(new BigDecimal(quantity))));
            } catch (NumberFormatException e) {
                totalCostTextField.setText("Invalid quantity value!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StockMarketJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}