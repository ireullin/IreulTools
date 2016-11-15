package IreulTools.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Created by tech0039 on 2016/11/15.
 */
public class SimpleSender implements ISimpleSender {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleSender.class);

    private final HttpURLConnection httpcn;

    public static ISimpleSender create(String url) throws Exception{
        return new SimpleSender(url);
    }


    private SimpleSender(String url) throws Exception{
        URL u  = new URL(url);
        this.httpcn = (HttpURLConnection)u.openConnection();
    }

    @Override
    public String get() throws Exception{

        httpcn.setRequestMethod("GET");
        String rsp = extractStream(httpcn.getInputStream());
        return rsp;
    }


    @Override
    public String post(String data) throws Exception{

        httpcn.setRequestMethod("POST");
        httpcn.setDoInput(true);

        OutputStream output = httpcn.getOutputStream();
        output.write(data.getBytes("UTF-8"));
        output.flush();

        String rsp = extractStream(httpcn.getInputStream());
        return rsp;
    }


    @Override
    public ISimpleSender setHeader(String name, String value) {
        this.httpcn.setRequestProperty(name, value);
        return this;
    }


    public ISimpleSender ignoreSslVerify() throws Exception {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        return this;
    }


    private String extractStream(InputStream stream) throws Exception{

        StringBuilder buff = new StringBuilder(100);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"))){
            while(true){
                String s = reader.readLine();
                if(s==null){
                    break;
                }
                buff.append(s);
            }
        }
        catch (Exception e){
            throw e;
        }
        return buff.toString();
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
