package com.example.myapplication.ui.lab1_2;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class RssItem {

    @Element(name = "title", required = false)
    private String title;

    @Element
    private String description;

    // Add other necessary fields such as link, pubDate, etc.

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
