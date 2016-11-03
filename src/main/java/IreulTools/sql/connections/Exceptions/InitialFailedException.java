package IreulTools.sql.connections.Exceptions;

import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/2.
 */
public class InitialFailedException extends SQLException {
    public InitialFailedException(String msg){
        super(msg);
    }
}
