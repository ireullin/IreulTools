package IreulTools.http.configurations;

import IreulTools.http.ISimpleSenderParams;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by tech0039 on 2016/11/15.
 */
public class JsonUtf8 implements ISimpleSenderParams{
    @Override
    public void set(HttpClientBuilder client, HttpRequestBase request) {
        request.setHeader("Content-Type", "application/json; charset=utf-8");
    }
}
