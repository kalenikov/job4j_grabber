package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.entity.Post;
import ru.job4j.grabber.Parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final static Logger log = LoggerFactory.getLogger(SqlRuParse.class.getName());

    public static void main(String[] args) throws Exception {

        String main = "https://www.sql.ru/forum/job-offers";
        Parse parse = new SqlRuParse();
//
//        PageExtractor pageExtractor = new PageExtractor();
//        List<String> pageLinks = pageExtractor.extractLinks(get(main), ".sort_options tr td a", 4);
//        pageLinks.forEach(System.out::println);
//
//        PostExtractor postExtractor = new PostExtractor();
//        List<String> postLinks = postExtractor.extractLinks(get(main), ".postslisttopic");
//        postLinks.forEach(System.out::println);
//
//        String postLink = "https://www.sql.ru/forum/1336267/front-end-full-stack-developer-udalenno-amerikanskaya-kompaniya";
//        System.out.println(parse.detail(postLink));

        List<Post> posts = parse.list(main);
        posts.forEach(System.out::println);

    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        PageExtractor pe = new PageExtractor();
        PostExtractor postExtractor = new PostExtractor();
        List<String> pageLinks = pe.extractLinks(get(link), ".sort_options tr td a", 1);
        pageLinks.add(0, link);
        for (String pageLink : pageLinks) {
            log.debug("parse page link {}", pageLink);
            Document page = get(pageLink);
            List<String> postLinks = postExtractor.extractLinks(page, ".postslisttopic");
            for (String postLink : postLinks) {
                log.debug("get post link {}", postLink);
                posts.add(detail(postLink));
            }
        }
        return posts;
    }

    @Override
    public Post detail(String link) {
        PostExtractor postExtractor = new PostExtractor();
        return postExtractor.extract(get(link));
    }

    private static Document get(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}