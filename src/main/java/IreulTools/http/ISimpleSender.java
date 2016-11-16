package IreulTools.http;

/**
 * Created by tech0039 on 2016/11/15.
 */
public interface ISimpleSender {
    public IResponse get()throws Exception;
    public IResponse post(String data) throws Exception;
    public ISimpleSender setHeader(String name, String value);
    public ISimpleSender setReadTimeout(int timeout);
    public ISimpleSender setConnectTimeout(int timeout);
}
