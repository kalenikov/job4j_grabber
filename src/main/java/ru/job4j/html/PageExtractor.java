package ru.job4j.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PageExtractor {

    public List<String> extractLinks(Document doc, String selector, int count) {
        Objects.requireNonNull(doc);
        List<String> rsl = new ArrayList<>();
        Elements els = doc.select(selector);
        for (Element el : els) {
            rsl.add(el.attr("href"));
            if (rsl.size() == count) {
                break;
            }
        }
        return rsl;
    }
}
