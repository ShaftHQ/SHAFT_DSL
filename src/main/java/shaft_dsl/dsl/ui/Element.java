package shaft_dsl.dsl.ui;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Element {
    static WebDriver driver;
    final By locator;
    final ElementActions elementActions;

    protected Element(By locator) {
        this.locator = locator;
        elementActions = new ElementActions(driver);
    }

    public static WebDriver getDriver() {
        return Element.driver;
    }

    public static void setDriver(WebDriver driver) {
        Element.driver = driver;
    }

    public void click() {
        elementActions.click(locator);
    }

    public void hover() {
        elementActions.hover(locator);
    }

    public boolean isDisplayed() {
        return elementActions.isElementDisplayed( locator);
    }

    public void shouldBeDisplayed() {
        Validations.assertThat().object(isDisplayed()).isTrue().perform();
    }

    public void shouldExist() {
        Validations.assertThat().element(driver, locator).exists().perform();
    }

    public void shouldExist(String reportMsg) {
        Validations.assertThat().element(driver, locator).exists()
                .withCustomReportMessage(reportMsg).perform();
    }

    public void shouldNotExist() {
        Validations.assertThat().element(driver, locator).doesNotExist().perform();
    }

    public void shouldNotExist(String reportMsg) {
        Validations.assertThat().element(driver, locator).doesNotExist()
                .withCustomReportMessage(reportMsg).perform();
    }
}
