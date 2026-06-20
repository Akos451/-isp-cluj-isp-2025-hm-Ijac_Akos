package isp.lab9.exercise1.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Implement sell panel - it looks similar to the 'Buy' panel
 */
public class SellJPanel extends JPanel {
    public SellJPanel() {
        setLayout(new BorderLayout());
        JLabel placeholderLabel = new JLabel("Sell feature panel ready for implementation.", SwingConstants.CENTER);
        add(placeholderLabel, BorderLayout.CENTER);
    }
}