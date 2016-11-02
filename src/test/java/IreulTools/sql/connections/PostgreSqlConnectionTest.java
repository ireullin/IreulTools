package IreulTools.sql.connections;

import IreulTools.stringExtension.Join;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class PostgreSqlConnectionTest  extends TestCase {


    private static final Logger LOG = LoggerFactory.getLogger(PostgreSqlConnectionTest.class);

    @Test
    public void testMain() {

        try(IConnection cn = PostgreSqlConnection.create("172.17.68.188", "recommendation_dev","postgres","uitox@pgsql2016")) {

            String[] selects = {
                    "select id, comment,model_name, model_ver, created_at ",
                    "from models"
            };
            String cmd = Join.from(selects).toString();

            cn.query(cmd, (i,row) -> {
                LOG.info("{}. {}",i, row.column("id").toFloat(0) );
                return true;
            });


        }
        catch (SQLException e){
            LOG.error("SQLException", e);
        }
        catch (Exception e){
            LOG.error("Exception", e);
        }

    }

}
