package threadUtil;

/**
 * Created by chenqi on 2017/6/13 0013 : 上午 11:55.
 *
 * @version : 1.0
 * @description :
 */

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.Callable;

/**
 * A thread that performs a GET.
 */
public class GetThread extends Thread implements Callable<String> {

    private static Log log = LogFactory.getLog(GetThread.class);

    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpget;
    private final int id;
    private String result = null;

    public GetThread(CloseableHttpClient httpClient, HttpGet httpget, int id) {
        this.httpClient = httpClient;
        this.context = new BasicHttpContext();
        this.httpget = httpget;
        this.id = id;
    }

    /**
     * Executes the GetMethod and prints some status information.
     */
    @Override
    public void run() {
        try {
//            System.out.println(id + " - about to get something from " + httpget.getURI());
            CloseableHttpResponse response = httpClient.execute(httpget, context);
            try {
//                System.out.println(id + " - get executed");
                // get the response body as an array of bytes
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    System.out.println("result============="+result.substring(0,10));
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            System.out.println(id + " - error: " + e);
        }
    }

    @Override
    public String call() throws Exception {
        return result;
    }
}



