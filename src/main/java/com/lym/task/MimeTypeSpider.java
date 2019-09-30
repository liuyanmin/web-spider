package com.lym.task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 爬取 mime-type 列表，单个html页面爬取，不涉及到分页
 * url： https://developer.cdn.mozilla.net/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types
 *
 * Created by liuyanmin on 2019/9/30.
 */
public class MimeTypeSpider {

    public static void main(String[] args) throws IOException {
        String url = "https://developer.cdn.mozilla.net/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Complete_list_of_MIME_types";
        crawler(url);
    }

    private static void crawler(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements trElements = document.body().select("table.standard-table").get(0).selectFirst("tbody").select("tr");
        for (Element trElement : trElements) {
            Elements tdElements = trElement.select("td");
            String code = "", mime="";
            for (int i = 0; i < tdElements.size(); i++) {
                Element td = tdElements.get(i);
                if (i == 0) {
                    code = td.selectFirst("code").text().substring(1);
                }
                if (i == 2) {
                    mime = td.selectFirst("code").text();
                }
            }
            System.out.println(code + "=" + mime);
        }
    }

}
