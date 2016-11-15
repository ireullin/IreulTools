package IreulTools.http.configurations;

import IreulTools.http.ISimpleSenderParams;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

/**
 * Created by tech0039 on 2016/11/15.
 */
public class IgnoringSslCertificate implements ISimpleSenderParams {

    private static final Logger LOG = LoggerFactory.getLogger(IgnoringSslCertificate.class);

    @Override
    public void set(HttpClientBuilder client, HttpRequestBase request) {
        try {

//            TrustManager trustAllCerts = new TrustManager[]{ new X509TrustManager() {
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            }
//            };
//
//            // Install the all-trusting trust manager
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//
//
//
//
//
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//            // Create all-trusting host name verifier
//            HostnameVerifier allHostsValid = new HostnameVerifier() {
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            };
//
//            // Install the all-trusting host verifier
//            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);







            /*
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            client.setSSLSocketFactory(sslsf);
            */
        }
        catch(Exception e){
            LOG.warn("IgnoringSslCertificate built failed", e);
        }
    }
}
