package core.dsl.ui;

import org.openqa.selenium.By;

public class Filter extends Button {
    public SearchField searchField;
    public Label noResultFound;
    public CheckBox selectAll;
    public CheckBoxList options;

    public Filter(By locator, SearchField searchField, CheckBox selectAll, CheckBoxList options, Label noResultLabel) {
        super(locator);
        this.searchField = searchField;
        this.noResultFound = noResultLabel;
        this.selectAll = selectAll;
        this.options = options;
    }
    public String getSelectedItemsCount() {
        return getText().split(" ")[1];
    }
}
