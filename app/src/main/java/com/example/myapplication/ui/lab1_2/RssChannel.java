package com.example.myapplication.ui.lab1_2;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class RssChannel {

    @ElementList(inline = true, name = "item")
    private List<RssItem> items;

    public List<RssItem> getItems() {
        return items;
    }
}
