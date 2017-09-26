package mainwork;

import IPModel.DatabaseMessage;
import IPModel.IPMessage;
import database.DataBaseDemo;
import httpbrowser.HttpResponseDemo;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import threadUtil.GetThread;
import threadUtil.PostThread;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqi on 2017/6/15 0015 : 上午 11:07.
 *
 * @version : 1.0
 * @description :
 */
public class OnlyOneLineGetReptllie {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpCient = HttpClients.createDefault();
//        DatabaseMessage databaseMessage = DataBaseDemo.queryOne(1);

//        GetThread getThread = getGetThread(databaseMessage, httpCient, 1);
//        getThread.start();
//        getThread.join(10000);
//        String result = getThread.call();
//        System.out.println(result.substring(0,300));
////将html解析成DOM结构
//        Document document = Jsoup.parse(result);
//        //提取所需要的数据
//        Elements trs = document.select("button[class = runCode btn_tp]");
//        String value = trs.get(1).attr("val");
//        System.out.println(value);
//
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("nt", true);
//        params.put("user", String.valueOf(Math.random()));
//        params.put("vid", value);
//        params.put("randcode", "9999");
//        params.put("time", String.valueOf(System.currentTimeMillis()));
//        PostThread postThread = postThread(databaseMessage, httpCient, 1, params);
//        postThread.start();
//        postThread.join();
//        String black = postThread.call();
//        System.out.println(black);
    }
    public static GetThread getGetThread(DatabaseMessage ipMessages, CloseableHttpClient httpclient, int j) throws InterruptedException {
        String ip;
        String port;
//        HttpGet get = new HttpGet("https://www.baidu.com");

        ip = ipMessages.getIPAddress();
        port = ipMessages.getIPPort();
        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
         RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(8000).
        setSocketTimeout(1000).build();
//        HttpGet httpGet = new HttpGet("http://www.xicidaili.com/nn/1" );
        HttpGet httpGet = new HttpGet("http://www.ceeexpo.com/publicvote/" );
        httpGet.setConfig(config);

//        httpGet.setConfig(config);
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                "q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "_free_proxy_session=BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJTRkYjMyM" +
                "TU3NGRjMWVhM2JlMDA5Y2IyNzZlZmVlZTYwBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMUhtT0pjcnRT" +
                "bm9CZEllSXNTYkNZZWk2Nnp3NGNDcFFSQVFodzk1dmpLZWM9BjsARg%3D%3D--09d8736fbfb9a8544" +
                "b46eef48bb320c2b40ee721; Hm_lvt_0cf76c77469e965d2957f0553e6ecf59=1492128157,149" +
                "2160558,1492347839,1492764281; Hm_lpvt_0cf76c77469e965d2957f0553e6ecf59=1492764295");
        httpGet.setHeader("Host", "www.ceeexpo.com");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");


        return new GetThread(httpclient, httpGet, j );



    }

public static PostThread postThread(DatabaseMessage ipMessages, CloseableHttpClient httpclient, int j, Map<String,Object> params) throws InterruptedException, UnsupportedEncodingException {
        String ip;
        String port;
        ip = ipMessages.getIPAddress();
        port = ipMessages.getIPPort();
        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
         RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(80000).
        setSocketTimeout(80000).build();
    HttpPost post=new HttpPost("http://www.ceeexpo.com/publicvote/ajax.php");
    post.setConfig(config);

//        httpGet.setConfig(config);
        post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                "q=0.9,image/webp,*/*;q=0.8");
        post.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        post.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        post.setHeader("Cache-Control", "no-cache");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Cookie", "Hm_lvt_8529e0187e7ea2dcc179b78159952f0d=1497507054; PHPSESSID=u1npgna3d94d5f1unbdb22e7q1");
        post.setHeader("Host", "www.ceeexpo.com");
    post.setHeader("Referer", "http://www.ceeexpo.com/publicvote/");

    post.setHeader("Pragma", "no-cache");
        post.setHeader("Upgrade-Insecure-Requests", "1");
        post.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");


        return new PostThread(httpclient, post,params,"urf-8", j + 1);



    }


}
