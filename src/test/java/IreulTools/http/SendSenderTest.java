package IreulTools.http;

import IreulTools.jsonBuilder.JsonMap;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tech0039 on 2016/11/15.
 */
public class SendSenderTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(SendSenderTest.class);

    @Test
    public void testSimplestExample(){
        try{
            String url = "http://172.17.68.67:9442/marketplace";
            IResponse rsp = SimpleSender.create(url).get();
            LOG.info(rsp.toString());
        }
        catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void testHttps(){
        try{
            String url = "https://beta-api-solr-market.uitox-cloud.com:9443/marketplace";
            IResponse rsp = SimpleSender.create(url).get();
            LOG.info(rsp.toString());
        }
        catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void testPost(){
        try{
            String data = JsonMap.create()
                    .put("zone","a")
                    .put("country","TW")
                    .put("lang","zh-tw")
                    .put("q","*")
                    .toString();

            String url = "https://beta-api-solr-market.uitox-cloud.com:9443/marketplace/www";

            LOG.info(data);
//            IResponse rsp = SimpleSender.create(url).post(data);
            String rsp = SimpleSender.post(url, data);
            LOG.info(rsp);
        }
        catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }
}
