package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://www.sql.ru/forum/job-offers";
        Document main = Jsoup.connect(url).get();
        List<String> pageLinks = PageLinksExtractor.extract(main, ".sort_options tr td a", 2);
        for (String pageLink : pageLinks) {
            Document page = Jsoup.connect(pageLink).get();
            Elements headers = page.select(".postslisttopic");
            for (Element header : headers) {
                if (header.text().toLowerCase().startsWith("важно")) {
                    continue;
                }
                String headerText = header.child(0).text();
                String headerLink = header.child(0).attr("href");
                String headerDate = header.parent().children().last().text();
                System.out.printf("%s %s %s%n", headerDate, headerText, headerLink);
            }
        }
    }
}
