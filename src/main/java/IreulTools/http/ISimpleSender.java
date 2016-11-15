package IreulTools.http;

import IreulTools.functionalProgramming.ITap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by tech0039 on 2016/11/15.
 */
public interface ISimpleSender {
    public ISimpleSender config(ISimpleSenderParams params);
    public String send() throws Exception;
}
