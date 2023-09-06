package shaft_dsl.dsl.ui;


import org.openqa.selenium.By;

public class ConfirmationPopup {
    Button confirm,cancel;

    public ConfirmationPopup(By confirmLocator, By cancelLocator) {
        this.confirm = new Button(confirmLocator);
        this.cancel = new Button(cancelLocator);
    }

    public void confirm()
    {confirm.click();}
    public void cancel()
    {cancel.click();}

}
