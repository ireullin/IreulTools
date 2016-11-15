package IreulTools.http;

import IreulTools.functionalProgramming.ITap;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tech0039 on 2016/11/15.
 */
public class SimpleSender implements ISimpleSender {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleSender.class);

    private final HttpRequestBase httpRequestBase;
    private final HttpClientBuilder httpClient;


    public static ISimpleSender createWithGet(String url) throws Exception{
        HttpGet httpGet = new HttpGet(url);
        return new SimpleSender(httpGet);
    }


    public static ISimpleSender createWithPost(String url, String data) throws Exception{
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(data));
        return new SimpleSender(post);
    }


    private SimpleSender(HttpRequestBase request){
        this.httpRequestBase = request;
        this.httpClient = HttpClientBuilder.create();
    }


    @Override
    public ISimpleSender config(ISimpleSenderParams params) {
        params.set(this.httpClient, this.httpRequestBase);
        return this;
    }

    @Override
    public String send() throws Exception{
        HttpClient client = this.httpClient.build();
        HttpResponse rsp = client.execute(this.httpRequestBase);
        String body = EntityUtils.toString(rsp.getEntity());
        return body;
    }


    /*public void send(ITap<HttpResponse> response) throws Exception{
        HttpClient client = this.httpClient.build();
        HttpResponse rsp = client.execute(this.httpRequestBase);
        response.put(rsp);
    }*/


//    @Override
//    public String withGet() throws Exception{
//        HttpGet httpGet = new HttpGet(url);
//        headers.each((k,v) -> {
//            httpGet.setHeader(k,v.toString());
//            return true;
//        });
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpResponse rsp = client.execute(httpGet);
//        String body = EntityUtils.toString(rsp.getEntity());
//
//        rsp.
//        return body;
//    }
//
//    @Override
//    public String withPost(String data) throws Exception{
//        HttpEntity reqEntity = new StringEntity(data);
//
//        HttpPost httpPost = new HttpPost(url);
//        headers.each((k,v) -> {
//            httpPost.setHeader(k,v.toString());
//            return true;
//        });
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpResponse rsp = client.execute(httpPost);
//        String body = EntityUtils.toString(rsp.getEntity());
//        return body;
//    }
}
