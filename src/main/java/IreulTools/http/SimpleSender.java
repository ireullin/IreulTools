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
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by tech0039 on 2016/11/15.
 */
public class SimpleSender implements ISimpleSender {

    private static class FakeX509TrustManager implements X509TrustManager{
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }


    private static class FakeHostnameVerifier implements HostnameVerifier{
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }


    private static final Logger LOG = LoggerFactory.getLogger(SimpleSender.class);
    private static final FakeX509TrustManager fakeX509TrustManager = new FakeX509TrustManager();
    private static final FakeHostnameVerifier fakeHostnameVerifier = new FakeHostnameVerifier();

    private final HttpURLConnection httpcn;

    public static ISimpleSender create(String url) throws Exception{
        return new SimpleSender(url);
    }

    public static String get(String url) throws Exception{
        return create(url).get().getBody();
    }

    public static String post(String url, String data) throws Exception{
        return create(url).post(data).getBody();
    }

    private SimpleSender(String url) throws Exception{
        ignoreSslVerify();
        URL u  = new URL(url);
        this.httpcn = (HttpURLConnection)u.openConnection();
    }

    @Override
    public ISimpleSender setReadTimeout(int timeout){
        this.httpcn.setReadTimeout(timeout);
        return this;
    }

    @Override
    public ISimpleSender setConnectTimeout(int timeout){
        this.httpcn.setConnectTimeout(timeout);
        return this;
    }

    @Override
    public IResponse get() throws Exception{

        httpcn.setRequestMethod("GET");
        String body = extractStream(httpcn.getInputStream());
        Response rsp = new Response(body, httpcn.getResponseCode(), httpcn.getResponseMessage());
        httpcn.disconnect();
        return rsp;
    }

    @Override
    public IResponse post(String data) throws Exception{

        httpcn.setRequestMethod("POST");
        httpcn.setDoOutput(true);

        OutputStream output = httpcn.getOutputStream();
        output.write(data.getBytes("UTF-8"));
        output.flush();

        String body = extractStream(httpcn.getInputStream());
        Response rsp = new Response(body, httpcn.getResponseCode(), httpcn.getResponseMessage());
        httpcn.disconnect();
        return rsp;
    }

    @Override
    public ISimpleSender setHeader(String name, String value) {
        this.httpcn.setRequestProperty(name, value);
        return this;
    }


    private ISimpleSender ignoreSslVerify() throws Exception {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{ fakeX509TrustManager };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        ((HttpsURLConnection)httpcn).setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Install the all-trusting host verifier
        ((HttpsURLConnection)httpcn).setDefaultHostnameVerifier(fakeHostnameVerifier);

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


}
