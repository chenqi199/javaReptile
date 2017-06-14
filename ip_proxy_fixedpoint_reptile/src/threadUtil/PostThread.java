package threadUtil;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 实现Callable回调接口
 */
public class PostThread extends Thread implements Callable<String> {

    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpPost httppost;
    private final int id;
    private String result = null;
    public PostThread(CloseableHttpClient httpClient, HttpPost httppost, Map<String, Object> params, String encode, int id) throws UnsupportedEncodingException {
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(60000)
                .setConnectTimeout(60000)
                .setSocketTimeout(60000)
                .build();
        httppost.setConfig(requestConfig);
        List<NameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(params.size());
            params.put("time",System.currentTimeMillis());
            for (Map.Entry<String, Object> entry : params.entrySet()) {

                Object value = entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value
                            .toString()));
                }
            }
        }
        if (pairs != null && pairs.size() > 0) {
            encode =  "UTF-8" ;
            httppost.setEntity(new UrlEncodedFormEntity(pairs, encode));
        }
        this.httpClient = httpClient;
        this.context = new BasicHttpContext();
        this.httppost = httppost;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            CloseableHttpResponse response = httpClient.execute(httppost, context);
            try {
                // get the response body as an array of bytes
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result= EntityUtils.toString(entity);
                    System.out.println(id+"：：：执行结果：：："+result);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
//            log.error(id + " - error: " + e);
            System.out.println(e);
        }
    }

    @Override
    public String call() throws Exception {
        return result;
    }
}
