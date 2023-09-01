package com.shaft.dsl.gui;

import org.openqa.selenium.By;

@SuppressWarnings("unused")
public class Link extends Label {
    public Link(By locator) {
        super(locator);
    }

    public void click() {
        elementActions.click(locator);
    }
}
