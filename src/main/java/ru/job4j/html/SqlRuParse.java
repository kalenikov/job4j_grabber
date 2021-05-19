package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements rows = doc.select(".postslisttopic");
        for (Element row : rows) {
            Element href = row.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            Element date = row.parent().children().last();
            System.out.println(date.text());
        }
    }
}