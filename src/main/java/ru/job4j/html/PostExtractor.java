package ru.job4j.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.entity.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PostExtractor {

    public Post extract(Document doc) {
        Objects.requireNonNull(doc);
        String name = doc.select(".messageHeader").first().text()
                .replaceAll(" \\[new]","");
        String text = doc.select(".msgTable td:nth-child(2)").first().text();
        String date = doc.select(".msgTable .msgFooter").first()
                .ownText()
                .substring(0, 16)
                .replaceAll("\\[]", "")
                .trim();
        String link = doc.select("link[rel=canonical]").first().attr("href");
        return new Post(name,
                text,
                link,
                new SqlRuDateTimeParser().parse(date));
    }

    public List<String> extractLinks(Document doc, String selector) {
        Objects.requireNonNull(doc);
        List<String> rsl = new ArrayList<>();
        Elements els = doc.select(selector);
        for (Element el : els) {
            if (el.text().toLowerCase().startsWith("важно")) {
                continue;
            }
            rsl.add(el.child(0).attr("href"));
        }
        return rsl;
    }
}