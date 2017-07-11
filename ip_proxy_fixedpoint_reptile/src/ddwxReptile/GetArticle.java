package ddwxReptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqi on 2017/7/10 0010 : 下午 6:18.
 *
 * @version : 1.0
 * @description :
 */
public class GetArticle {

    public static void main(String[] args) throws IOException, ParseException {
        List<String> targetUrlsContainer = new ArrayList<>(50);
//        获取二级目录
        List<String> baseUrls = NewReptile.getBaseUrl();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> allPageUrl = new ArrayList<>(100);
//       String url = "http://www.ddweixiao.com/window";
//        getPageUrls(baseUrls, url);
        for (String beseUrl :
                baseUrls) {
            allPageUrl .addAll(getPageUrls(beseUrl)) ;
        }
        List<Article> articles = new ArrayList<>();
        for (String url :
                allPageUrl) {
            articles.addAll(getLiForArticle(format,url));
        }
        for (Article article    :articles
             ) {
            article.setContent(getContentByUrl(NewReptile.ROOTURL+article.getContent()));
        }

        System.out.println(articles.get(3));

    }

    public static String getContentByUrl(String url) throws IOException {
        Document doc;
        doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
        return doc.select("[class=wzzw]").get(2).html();
    }

    public static List<Article> getLiForArticle(SimpleDateFormat format ,String url) throws IOException, ParseException {
        List<Article> articles = new ArrayList<>();
        Document doc;
        doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
        Elements elements = doc.select("[class=wzli]").get(0).select("li");
        for (Element e :
                elements) {
            String conetent = e.select("a").get(0).attr("href");
            String imageUrl =e.select("img").get(0).attr("src");
            String tittle = e.select("a").get(1).attr("title");
            String author = e.select("b").get(0).text();
            String ctime = e.select("b").get(1).text();
          long cTime=  format.parse(ctime).getTime();
            Article article = new Article(tittle,cTime,author,imageUrl,conetent);
            System.out.println(article);
            articles.add(article);
        }
        return articles;
    }

    public static List<String>  getPageUrls( String url) throws IOException {

        Document doc;
        doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
        Elements elements = doc.select("[class=manu]").get(0).select("a");
        List<String> Urls = new ArrayList<>(10);
        Urls.add(url);
        for (int i= 2;i<elements.size()-2;i++){
            Urls.add(url+elements.get(i).select("a").attr("href"));
        }

        return Urls;
    }


}
