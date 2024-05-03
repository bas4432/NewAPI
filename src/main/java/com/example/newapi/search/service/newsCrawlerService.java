package com.example.newapi.search.service;


import com.example.newapi.search.domain.NewsArticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class newsCrawlerService {

    public List<NewsArticle> crawlGoogleNews(String keyword) {
        List<NewsArticle> newsArticles = new ArrayList<>();
        String url = "https://www.hkbs.co.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("section ul.type2 li");

            for (Element content : elements) {
                NewsArticle news = NewsArticle.builder()
                        .image(content.select("a img").attr("abs:src")) // 이미지
                        .subject(content.select("h4 a").text())		// 제목
                        .url(content.select("a").attr("abs:href"))		// 링크
                        .build();
                newsArticles.add(news);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsArticles;
    }



}
