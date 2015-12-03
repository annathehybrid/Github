package com.nshmura.recyclertablayout.demo;


public enum Demo {

    CUSTOM_VIEW01(R.string.title_custom_view01),
    CUSTOM_VIEW02(R.string.title_custom_view02),
    ;

    public int titleResId;

    Demo(int titleResId) {
        this.titleResId = titleResId;
    }
}
