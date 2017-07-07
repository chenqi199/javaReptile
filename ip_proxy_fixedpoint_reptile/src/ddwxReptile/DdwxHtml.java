//package ddwxReptile;
//
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static javax.management.Query.attr;
//
///**
// * Created by chenqi on 2017/7/6 0006 : 下午 4:39.
// *
// * @version : 1.0
// * @description :
// */
//public class DdwxHtml {
//    static int i = 0;
//
//    public static void main(String[] args) {
//        Document doc = null;
//        String url = "http://www.ddweixiao.com/window/guowaijiaoyu/254.html";
//        String baseUrl = "http://www.ddweixiao.com";
//        List<DdwxHtmlBean> ddwxHtmlBeans = new ArrayList<>(50);
//        List<DdwxHtmlBean> teacherPark = new ArrayList<>(50);
//        List<DdwxHtmlBean> childRears = new ArrayList<>(30);
//        List<DdwxHtmlBean> guanlianli = new ArrayList<>(30);
//        List<DdwxHtmlBean> yuanzhangxiaozilang = new ArrayList<>(30);
//        List<DdwxHtmlBean> guowaijiaoyu = new ArrayList<>(30);
//        List<DdwxHtmlBean> huanjingchuangshe = new ArrayList<>(30);
//
////        String beforeA = getDoc(ddwxHtmlBeans,baseUrl,url);
//        DdwxHtml ddwxHtml = new DdwxHtml();
//        List<DdwxHtmlBean> teacherManger = ddwxHtml.getDoc(ddwxHtmlBeans, baseUrl, "/window/guowaijiaoyu/254.html");
//        List<DdwxHtmlBean> teacherParkDoc = ddwxHtml.getDoc(teacherPark, baseUrl, "/garden/lwtj/251.html");
//        List<DdwxHtmlBean> childRearing = ddwxHtml.getDoc(childRears, baseUrl, "/Family/jiatingjiaoyu/194.html");
//
//        List<DdwxHtmlBean> glalBeans = ddwxHtml.getDoc(guanlianli, baseUrl, "/window/glal/252.html");
//        List<DdwxHtmlBean> yzxzlBeans = ddwxHtml.getDoc(yuanzhangxiaozilang, baseUrl, "/window/yzxzh/258.html");
//        List<DdwxHtmlBean> gwjyBeans= ddwxHtml.getDoc(guowaijiaoyu, baseUrl, "/window/guowaijiaoyu/254.html");
//        List<DdwxHtmlBean> hjcsbeans = ddwxHtml.getDoc(huanjingchuangshe, baseUrl, "/window/huanjingchuangshe/239.html");
//
////        System.out.println(doc.toString());
//        for (DdwxHtmlBean db : childRearing
//                ) {
//            System.out.println("*******************************************************************************");
//            System.out.println(db.getTextBody());
//        }
//        System.out.println("teacherManger--------------"+teacherManger.size());
//        System.out.println("teacherParkDoc----------"+teacherParkDoc.size());
//        System.out.println("childRearing----------"+childRearing.size());
//
//
//        System.out.println("glalBeans----------"+glalBeans.size());
//        System.out.println("yzxzlBeans----------"+yzxzlBeans.size());
//        System.out.println("gwjyBeans----------"+gwjyBeans.size());
//        System.out.println("hjcsbeans----------"+hjcsbeans.size());
//    }
//
////    public   List<DdwxHtmlBean> getDoc(List<DdwxHtmlBean> beans, String baseUrl, String url) {
//////        System.out.println(baseUrl+url);
////        i++;
////        Document doc = null;
////        String tittle = "", textBody = "", beforeA = "";
////        try {
////            doc = Jsoup.connect(baseUrl + url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
////
////
////            tittle = doc.select("h3[class=biaoti]").get(0).text();
////            textBody = doc.select("[class=wzzw]").get(2).html();
////            Element span = doc.select("[class= fenye1]").select("span").get(0);
////            Elements aTarget = span.select("a");
//////            System.out.println("--------------------------"+aTarget.size());
//////            System.out.println(i);
////            if (aTarget.size()>0)
////                beforeA = aTarget.get(0).attr("href");
////            else
////                beforeA="";
////            DdwxHtmlBean ddwxHtml = new DdwxHtmlBean(tittle, textBody, beforeA);
////            beans.add(ddwxHtml);
////            if (aTarget.size()>0) {
////                    getDoc(beans, baseUrl, beforeA);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////
////        return beans;
////    }
//}
