package IreulTools.sql.connections;

import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/2.
 */
public class PostgreSqlConnectionException extends SQLException {
    public PostgreSqlConnectionException(String msg){
        super(msg);
    }
}
