package isp.lab9.exercise1.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Uses Lombok to get rid of boilerplate code.
 *
 * @author mihai.hulea
 * @author radu.miron
 */
@Data // it creates getters, setters, equals(), hashCode() and toString() (at compile time)
@AllArgsConstructor // it creates the constructor with arguments for all the attributes (at compile time)
public class UserPortfolio extends AbstractTableModel {
    private static String[] columns = new String[]{"Symbol", "Quantity", "Price per unit", "Total price"};

    private BigDecimal cash;

    private Map<String, Integer> shares; // a map of number of shares by stock symbol

    public void refreshPortfolioData() {
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return shares.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int index) {
        return columns[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<String> symbolsList = new ArrayList<>(shares.keySet());
        if (rowIndex >= symbolsList.size()) {
            return "N/A";
        }

        String symbol = symbolsList.get(rowIndex);
        int quantity = shares.get(symbol);

        switch (columnIndex) {
            case 0:
                return symbol;
            case 1:
                return quantity;
            case 2:
                return "Market Dependent";
            case 3:
                return "Market Dependent";
        }
        return "N/A";
    }
}