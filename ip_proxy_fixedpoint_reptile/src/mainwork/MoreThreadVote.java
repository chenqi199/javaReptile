package mainwork;

import IPModel.DatabaseMessage;
import database.DataBaseDemo;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import threadUtil.GetThread;
import threadUtil.PostThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述：
 * Created by  chen_q_i@163.com on 2017/9/25 : 18:35.
 *
 * @version : 1.0
 */
public class MoreThreadVote {
    public static void main(String[] args) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置线程数最大500,如果超过500为请求500个
        cm.setMaxTotal(1000);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        List<DatabaseMessage> databaseMessages;
        List<Future<String>> pages = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            databaseMessages = DataBaseDemo.queryOne(40);
            int i = 0;
            for (DatabaseMessage da : databaseMessages) {

                GetThread getThread = OnlyOneLineGetReptllie.getGetThread(da, httpclient, i++);
                pages.add(executorService.submit(getThread));
//                getThread.start();
//                getThread.join(10000);
//                String result = getThread.call();


//                Map<String, Object> params = new HashMap<>();
//                params.put("nt", true);
//                params.put("user", String.valueOf(Math.random()));
//                params.put("vid", value);
//                params.put("randcode", "9999");
//                params.put("time", String.valueOf(System.currentTimeMillis()));
//                PostThread postThread =OnlyOneLineGetReptllie. postThread(da, httpCient, 1, params);
//                postThread.start();
//                postThread.join();
//                String black = postThread.call();
//                System.out.println(black);
            }

            for (Future<String> callback : pages) {

                String result = callback.get();
                if (result != null && result.length() > 0) {


                    System.out.println(result.substring(0, result.length() > 800 ? 800 : result.length()));
//将html解析成DOM结构
                    Document document = Jsoup.parse(result);
//                    System.out.println(document.toString());
                    //提取所需要的数据
//                    Elements trs = document.select("button[class = runCode btn_tp]");
//                    String value = trs.get(1).attr("val");
//                    System.out.println(value);
                    Elements elements = document.select("div[class=pp-main-box]").select("ul[id=con_one_4]");
                    List<Element> a = elements.stream().filter(element -> {

                                System.out.println(element);
                                return element.select("div[class=bottom]").select("a").get(0).text().equals("电微校（北京）教育科技有限公司");
                            }
                    ).collect(Collectors.toList());


                    System.out.println("=============================================================================");
                    a.forEach(System.out::println);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
