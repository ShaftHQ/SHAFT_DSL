package core.dsl.ui;

import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class CheckBoxList {

    String commonId;
    String commonIdStatus;

    public CheckBoxList(String commonId, String commonIdStatus) {
        this.commonId = commonId;
        this.commonIdStatus = commonIdStatus;
    }

    public List<Boolean> getDisplayedStatus() {
        List<Boolean> months = new ArrayList<>();
        int count = getSize();
        for (int i = 0; i < count; i++) {
            months.add(getElement(i).isSelected());
        }
        return months;
    }

    public List<String> getListText() {
        List<String> months = new ArrayList<>();
        int count = getSize();
        for (int i = 0; i < count; i++) {
            months.add(getElement(i).getText());
        }
        return months;
    }

    public int getSize() {
        return Element.getDriver().findElements(By.xpath("(//*[contains(@id,'" + commonId + "')])")).size();
    }

    public CheckBox getElement(int index) {
        return new CheckBox(By.id(commonId + index), By.id(commonIdStatus + index));
    }

}
