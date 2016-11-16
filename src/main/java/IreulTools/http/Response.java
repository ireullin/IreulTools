package IreulTools.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tech0039 on 2016/11/16.
 */
public class Response implements IResponse{
    private static final Logger LOG = LoggerFactory.getLogger(Response.class);

    private final String body;
    private final int statusCode;
    private final String message;

    public Response(String body, int statusCode, String message){
        this.body = body;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("StatusCode: ").append(statusCode).append("\n");
        sb.append("Message: ").append(message).append("\n");;
        sb.append("Body: ").append(body);
        return sb.toString();
    }
}
