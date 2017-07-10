package ddwxReptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqi on 2017/7/10 0010 : 下午 6:18.
 *
 * @version : 1.0
 * @description :
 */
public class GetArticle {
    public static void main(String[] args) throws IOException {
        List<String> targetUrlsContainer = new ArrayList<>(50);
//        获取二级目录
        List<String> baseUrls = NewReptile.getBaseUrl();

//        List<String> allPageUrl = new ArrayList<>(100);
////       String url = "http://www.ddweixiao.com/window";
////        getPageUrls(baseUrls, url);
//        for (String beseUrl :
//                baseUrls) {
//            allPageUrl = getPageUrls(allPageUrl,beseUrl);
//        }
//        System.out.println(allPageUrl);




    }

    public static List<String>  getPageUrls(List<String> baseUrls, String url) throws IOException {
        Document doc;
        doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
        Elements elements = doc.select("[class=manu]").get(0).select("a");
        List<String> Urls = new ArrayList<>(10);
        Urls.add(url);
        for (int i= 2;i<elements.size()-2;i++){
            Urls.add(url+elements.get(i).select("a").attr("href"));
        }
       baseUrls.addAll(Urls);
        return baseUrls;
    }


}
