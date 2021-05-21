package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.job4j.entity.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;


public class PostExtractor {
    public static void main(String[] args) throws IOException {
        String url = "https://www.sql.ru/forum/1335775/nuzhen-senior-devops-s-angliyskim-3800-4800-v-mesyac-udalenno";
        Document main = Jsoup.connect(url).get();
        System.out.println(extract(main));
    }

    public static Post extract(Document doc) {
        String name = doc.select(".messageHeader").first().text();
        String text = doc.select(".msgTable td:nth-child(2)").first().text();
        String date = doc.select(".msgTable .msgFooter").first().ownText().substring(0, 16);
        String link = doc.select("link[rel=canonical]").first().attr("href");
        return new Post(name,
                text,
                link,
                new SqlRuDateTimeParser().parse(date));
    }
}
