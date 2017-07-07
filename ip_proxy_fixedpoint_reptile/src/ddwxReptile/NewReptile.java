package ddwxReptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqi on 2017/7/7 0007 : 上午 10:24.
 *
 * @version : 1.0
 * @description :
 */
public class NewReptile {
    public static final String ROOTURL = "http://www.ddweixiao.com";

    public static void main(String[] args) {
        Document doc = null;
        List<String> targetUrlsContainer = new ArrayList<>(50);
        try {
//             获取二级目录
            List<String> baseUrls = getBaseUrl();

//获取三级目录
            List<String> targetUrls = getTargetUrls(baseUrls, targetUrlsContainer);
            System.out.println(targetUrls);
//拿到所有三级目录下列表首节点的url
            List<String> firstUrlsOnTarget = getFirstUrlsOnTarget(targetUrls);
            System.out.println(firstUrlsOnTarget);
//递归遍历所有的首节点，取出内容
            List<DdwxHtmlBean> ddwxHtmlBeans = new ArrayList<>(200);
//            DdwxHtml ddwxHtml = new DdwxHtml();
            for (String firstUrl :
                    firstUrlsOnTarget) {
                getDoc(ddwxHtmlBeans, firstUrl);
            }
            System.out.println(ddwxHtmlBeans.size());
            System.out.println("__________________________________________________________________");
            for (DdwxHtmlBean s :
                    ddwxHtmlBeans   ) {
                System.out.println(s.getTittle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description : 获取三级路径下列表的第一个节点的链接，
     * @param tagetUrls 所有的三级路径
     * @return java.util.List<java.lang.String>
     * @throws
     */
    public static List<String> getFirstUrlsOnTarget(List<String> tagetUrls) throws IOException {

        List<String> firstUrls = new ArrayList<>(50);
        Document doc;
        for (String targetUrl :
                tagetUrls) {
            doc = Jsoup.connect(ROOTURL + targetUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
            String firstUrl = doc.select("[class=wzli]").select("li").get(0).select("a").attr("href");
            firstUrls.add(firstUrl);
        }
        return firstUrls;
    }
    /**
     * @Description : 从二级路径获取三级路径
     * @param baseUrls 二级路径
     * @param container 三级路径容器
     * @return java.util.List<java.lang.String>
     * @throws IOException
     */
    public static List<String> getTargetUrls(List<String> baseUrls, List<String> container) throws IOException {

        Document doc;
        for (String baseUrl :
                baseUrls) {
            System.out.println(baseUrl);
            doc = Jsoup.connect(baseUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
            Elements elements = doc.select("[class=xz]");

            for (Element aTarget :
                    elements) {
                container.add(aTarget.select("a").attr("href"));
//                container.add(baseUrl);
            }
        }
        return container;
    }

    /**
     * @Description : 从根路径获取二级路径
     * @return java.util.List<java.lang.String>
     * @throws

     */
    public static List<String> getBaseUrl() throws IOException {

        Document doc;
        doc = Jsoup.connect(ROOTURL).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
        Elements elements = doc.select("[class=s_nav]").get(0).select("li");
        List<String> baseUrls = new ArrayList<>(10);
        for (Element aTarget :
                elements) {
            baseUrls.add(aTarget.select("a").attr("href"));
        }
        return baseUrls;
    }
    /**
     * @Description : 递归抓取页面内容，没有上一张的链接就返回
     * @param beans 容器
     * @param url 目标url
     * @return java.util.List<ddwxReptile.DdwxHtmlBean>
     * @throws
     */
    public  static List<DdwxHtmlBean> getDoc(List<DdwxHtmlBean> beans, String url) {
        Document doc = null;
        String tittle = "", textBody = "", beforeA = "";
        try {
            doc = Jsoup.connect(ROOTURL + url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
            tittle = doc.select("h3[class=biaoti]").get(0).text();
            textBody = doc.select("[class=wzzw]").get(2).html();
            Element span = doc.select("[class= fenye1]").select("span").get(0);
            Elements aTarget = span.select("a");
            if (aTarget.size()>0)
                beforeA = aTarget.get(0).attr("href");
            else
                beforeA="";
            DdwxHtmlBean ddwxHtml = new DdwxHtmlBean(tittle, textBody, beforeA);
            beans.add(ddwxHtml);
            if (aTarget.size()>0) { //上一章的链接为空时结束递归
                getDoc(beans,  beforeA);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beans;
    }

}
