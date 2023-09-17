package core.dsl.ui;

import org.openqa.selenium.By;

public class ComboTextBox extends TextBox {
    public Label label;
    public Label error;
    public ComboTextBox(By locator, By label, By error) {
        super(locator);
        this.label = new Label(label);
        this.error= new Label(error);
    }
}
