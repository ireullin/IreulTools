package IreulTools.http;

import IreulTools.http.configurations.IgnoringSslCertificate;
import IreulTools.http.configurations.JsonUtf8;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tech0039 on 2016/11/15.
 */
public class SendHttpTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(SendHttpTest.class);


    @Test
    public void testSimplestExample(){
        try{
//            String url = "http://172.17.0.8:8080/solr/a-zh-tw-show-main/select?q=TOP_SI_SEQ%3AA10246&start=0&rows=10&fl=CP_NAME&wt=json";
            String url = "http://www.google.com.tw";
            String rsp = SimpleSender.createWithGet(url).send();
            LOG.info(rsp);
        }
        catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void testHttps(){
        try{
            String url = "https://api-solr-market.uitox-cloud.com:9443/marketplace";
            String rsp = SimpleSender.createWithGet(url)
//                    .config(new IgnoringSslCertificate())
                    .send();

            LOG.info(rsp);
        }
        catch (Exception e){
            LOG.error(e.getMessage(), e);
        }

    }

}
