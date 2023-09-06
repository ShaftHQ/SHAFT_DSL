package shaft_dsl.dsl.ui;

import com.shaft.validation.Validations;
import org.openqa.selenium.By;

public class TextBox extends Button {

    public TextBox(By locator) {
        super(locator);
    }

    public void type(String input) {
        elementActions.type(locator, input);
    }

    public String getPlaceholder() {
        return elementActions.getAttribute(locator, "placeholder");
    }

    public void shouldHavePlaceholderText(String placeholderText) {
        Validations.assertThat().object(getPlaceholder()).isEqualTo(placeholderText).perform();
    }
}
