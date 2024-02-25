package com.example.myapplication.ui.lab1_2;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class RssFeed {
    @Element(name = "channel")
    private RssChannel channel;

    public RssChannel getChannel() {
        return channel;
    }
}

