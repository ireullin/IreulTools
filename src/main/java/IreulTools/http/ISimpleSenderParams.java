package IreulTools.http;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by tech0039 on 2016/11/15.
 */
public interface ISimpleSenderParams {
    public void set(HttpClientBuilder client, HttpRequestBase request);
}
