package by.epam.rafalovich.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

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
  }
}
