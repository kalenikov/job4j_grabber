package ru.job4j.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PageLinksExtractor {
    private static final String attr = "href";

    public static List<String> extract(Document document, String selector, int count) {
        List<String> rsl = new ArrayList<>();
        Elements els = document.select(selector);
        for (Element el : els) {
            rsl.add(el.attr(attr));
            if (rsl.size() == count) {
                break;
            }
        }
        return rsl;
    }
}
