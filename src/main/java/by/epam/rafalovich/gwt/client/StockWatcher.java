package by.epam.rafalovich.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcher implements EntryPoint {

  private VerticalPanel mainPanel = new VerticalPanel();
  private FlexTable stocksFlexTable = new FlexTable();
  private HorizontalPanel addPanel = new HorizontalPanel();
  private TextBox newSymbolTextBox = new TextBox();
  private Button addStockButton = new Button("Add");
  private Label lastUpdatedLabel = new Label();
  private ArrayList<String> stocks = new ArrayList<>();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    stocksFlexTable.setText(0, 0, "symbol");
    stocksFlexTable.setText(0, 1, "price");
    stocksFlexTable.setText(0, 2, "change");
    stocksFlexTable.setText(0, 3, "remove");

    addPanel.add(newSymbolTextBox);
    addPanel.add(addStockButton);

    mainPanel.add(stocksFlexTable);
    mainPanel.add(addPanel);
    mainPanel.add(lastUpdatedLabel);

    RootPanel.get("stockList").add(mainPanel);

    // Move cursor focus to the input box.
    newSymbolTextBox.setFocus(true);

    // Listen for mouse events on the Add button.
    addStockButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        addStock();
      }
    });

    // Listen for keyboard events in the input box.
    newSymbolTextBox.addKeyDownHandler(new KeyDownHandler() {
      public void onKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          addStock();
        }
      }
    });

  }

  private void addStock() {

    final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
    newSymbolTextBox.setFocus(true);

    // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
    if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
      Window.alert("'" + symbol + "' is not a valid symbol.");
      newSymbolTextBox.selectAll();
      return;
    }

    newSymbolTextBox.setText("");

    // Don't add the stock if it's already in the table.
    if (stocks.contains(symbol))
      return;

    // Add the stock to the table.
    int row = stocksFlexTable.getRowCount();
    stocks.add(symbol);
    stocksFlexTable.setText(row, 0, symbol);

    Button removeStockButton = new Button("x");
    removeStockButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int removedIndex = stocks.indexOf(symbol);
        stocks.remove(removedIndex);
        stocksFlexTable.removeRow(removedIndex + 1);
      }
    });
    stocksFlexTable.setWidget(row, 3, removeStockButton);
  }


}
