package mainwork;

import IPModel.DatabaseMessage;
import IPModel.IPMessage;
import database.DataBaseDemo;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import threadUtil.PostThread;

import java.io.IOException;
import java.util.*;

/**
 * Created by chenqi on 2017/6/14 0014 : 下午 3:15.
 *
 * @version : 1.0
 * @description :
 */
public class VoteMian {


    public static String[] threadPost(List<DatabaseMessage> listIp,List<Map<String,Object>> requestInfo){
        System.out.println("post共执行"+listIp.size()+"个请求");
        String[] results=new String[listIp.size()];
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置线程数最大100,如果超过100为请求个数
        cm.setMaxTotal(listIp.size()>500?500:listIp.size());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        try{
            PostThread[] postThreads=new PostThread[listIp.size()];
//            for (int i = 0; i < listIp.size(); i++) {
            for (int i = 0; i < listIp.size(); i++) {
                Map<String,Object> req=requestInfo.get(0);
                DatabaseMessage ipmessage = listIp.get(i);
//                配置代理
                HttpHost proxy = new HttpHost(ipmessage.getIPAddress(), Integer.parseInt(ipmessage.getIPPort()));
//                System.out.println(ipmessage.getIPAddress()+"---------------------------"+ipmessage.getIPPort());
                RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(8000).
                        setSocketTimeout(8000).build();
                HttpPost post=new HttpPost((String) req.get("url"));
                post.setConfig(config);

                post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                        "q=0.9,image/webp,*/*;q=0.8");
                post.setHeader("Accept-Encoding", "gzip, deflate, sdch");
                post.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
                post.setHeader("X-Requested-With", "XMLHttpRequest");
                post.setHeader("Host", "www.ceeexpo.com");
                post.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit" +
                        "/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                post.setHeader("Origin", "http://www.ceeexpo.com");
                post.setHeader("Referer", "http://www.ceeexpo.com/publicvote/");
                post.setHeader("PHPSESSID", "erqet07598qtp47gg78m56"+i);

                postThreads[i]=new PostThread(httpclient,post, (Map<String, Object>) req.get("params"), (String) req.get("encode"),i+1);
            }
            //执行线程
            for(PostThread pt:postThreads){
                pt.start();
            }
            //设置所有线程执行完毕之后再执行后续代码
            for (PostThread pt : postThreads) {
                pt.join();
            }
            for (int i = 0; i < listIp.size(); i++) {
                results[i]=postThreads[i].call();
            }

        }catch (Exception e){
            e.printStackTrace();
//            log.debug("多线程post方法异常："+e.getMessage());
        }finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(listIp.size()+"个线程的结果为:"+results.length+"个，明细:::"+results);
        return results;
    }

    public static void main(String[] args) {
        List<DatabaseMessage> databaseMessages;
        try {
          databaseMessages = DataBaseDemo.query();


        List<Map<String,Object>> reqInfo = new ArrayList<>();
        Map<String,Object> requestParams = new HashMap<>();
        Map<String ,String > params = new HashMap<>();
        requestParams.put("url","http://www.ceeexpo.com/publicvote/ajax.php");
        params.put("nt","12996417340");
        params.put("user","");
        params.put("vid","EC4448BQMGCwUGAQIAAgBQBVELAQBUAwsLAwsFBAYDAAQF");
        params.put("randcode","9999");
//        params.put("time","1497426840700");

        requestParams.put("params",params);
        reqInfo.add(requestParams);
            String[] strings = threadPost(databaseMessages, reqInfo);
            System.out.println(Arrays.toString(strings));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
