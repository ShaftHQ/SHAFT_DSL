package shaft_dsl.dsl.ui;

import org.openqa.selenium.By;

public class CheckBox extends RadioButton {
    public CheckBox(By buttonLocator, By statusLocator) {
        super(buttonLocator, statusLocator);
    }
    public void unselect() {
        if ((isSelected())) {
            click();
        }
    }
}
