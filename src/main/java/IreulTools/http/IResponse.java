package IreulTools.http;

/**
 * Created by tech0039 on 2016/11/16.
 */
public interface IResponse {
    public String getBody();
    public int getStatusCode();
    public String getMessage();
}
