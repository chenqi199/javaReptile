package ipfilter;

import IPModel.DatabaseMessage;
import IPModel.IPMessage;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import mainwork.OnlyOneLineGetReptllie;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import threadUtil.GetThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.System.out;

/**
 * Created by paranoid on 17-4-21.
 * 测试此Ip是否有效
 */

public class IPUtils {
    private static Log log = LogFactory.getLog(IPUtils.class);

    public static List<IPMessage> IPIsable(List<IPMessage> ipMessages) throws Exception {



        System.out.println("get共执行" + ipMessages.size() + "个请求");
        ArrayList<String> results = new ArrayList<>();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置线程数最大500,如果超过500为请求500个
        cm.setMaxTotal(ipMessages.size() );
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

//        GetThread[] getThreads = new GetThread[ipMessages.size()];
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> pages = new ArrayList<>();
        for ( int i = 0 ;i<ipMessages.size();i++       ) {
            DatabaseMessage da = new DatabaseMessage();
            da.setIPAddress(ipMessages.get(i).getIPAddress());
            da.setIPPort(ipMessages.get(i).getIPPort());
            pages.add(i, executorService.submit(OnlyOneLineGetReptllie. getGetThread(da, httpclient, i)));
        }
        List<IPMessage> ipMessages1 = new ArrayList<>(ipMessages);
        for (int j = 0; j < pages.size(); j++) {
            try {
               String s =  pages.get(j).get();
               if (s.length()==0){
                   out.println("不可用代理已删除" + ipMessages1.get(j).getIPAddress() + ": " + ipMessages1.get(j).getIPPort());
                   ipMessages.remove(ipMessages1.get(j));
               }else {
                   System.out.println(s.substring(0,s.length()>25?25:s.length()));
               }
            }catch (Exception e){
//                out.println("不可用代理已删除" + ipMessages.get(j).getIPAddress() + ": " + ipMessages.get(j).getIPPort());
//                ipMessages.remove(ipMessages.get(j));
                e.printStackTrace();
            }
        }

//        try {
//
//
//            for (int j = 0; j < ipMessages.size(); j++) {
//
//              getThreads[j]=  getArrayGetThread(ipMessages, httpclient, getThreads, j);
//
//            }
//
//            for (GetThread gt : getThreads) {
//                try {
//                    gt.start();
//                }catch (Exception e){
//                    ipMessages.remove(ipMessages.get((int)gt.getId()));
//                }
//
//            }
//            //设置所有线程执行完毕之后再执行后续代码
//            for (GetThread gt : getThreads) {
//                gt.join();
//            }
//
//
//
//            for (int i = 0; i < ipMessages.size(); i++) {
//
//                try {
//                    results.add(getThreads[i].call());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    out.println("不可用代理已删除" + ipMessages.get(i).getIPAddress() + ": " + ipMessages.get(i).getIPPort());
//                    ipMessages.remove(ipMessages.get(i));
//                    i--;
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }


//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//
//        for (int i = 0; i < ipMessages.size(); i++) {
//            ip = ipMessages.get(i).getIPAddress();
//            port = ipMessages.get(i).getIPPort();
//
//            HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
//            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
//                    setSocketTimeout(3000).build();
//            HttpGet httpGet = new HttpGet("https://www.baidu.com");
//            httpGet.setConfig(config);
//
//            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
//                    "q=0.9,image/webp,*/*;q=0.8");
//            httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
//            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
//            httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" +
//                    "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//
//            try {
//                response = httpClient.execute(httpGet);
//            } catch (IOException e) {
//                out.println("不可用代理已删除" + ipMessages.get(i).getIPAddress() + ": " + ipMessages.get(i).getIPPort());
//                ipMessages.remove(ipMessages.get(i));
//                i--;
//            }
//        }
//
//        try {
//            httpClient.close();
//            response.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        return ipMessages;
    }

    public static GetThread getArrayGetThread(List<IPMessage> ipMessages, CloseableHttpClient httpclient, GetThread[] getThreads, int j) throws InterruptedException {
        String ip;
        String port;

        ip = ipMessages.get(j).getIPAddress();
        port = ipMessages.get(j).getIPPort();

        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(5000).
                setSocketTimeout(5000).build();
        HttpGet httpGet = new HttpGet("http://www.ceeexpo.com/publicvote");
        httpGet.setConfig(config);

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                "q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Host", "www.ceeexpo.com");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" +
                "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

        return new GetThread(httpclient, httpGet, j + 1);



    }
}
