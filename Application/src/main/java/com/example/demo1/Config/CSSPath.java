package com.example.demo1.Config;

import java.net.URL;

import com.example.demo1.BinApplication;

public enum CSSPath {
    SCROLLBAR("scrollbar.css"),
    POP_UP("notificationPopUp.css"),
    CHART("chartStyle.css");

    private final String cssPath;

    CSSPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public URL getCssPath() {
        return BinApplication.class.getResource(cssPath);
    }
}
