package IreulTools.sql.connections;

import IreulTools.collections.ISimpleMap;
import IreulTools.collections.SimpleMap;
import IreulTools.datetime.Datetime;
import IreulTools.datetime.IDatetime;
import IreulTools.sql.connections.Exceptions.InitialFailedException;
import IreulTools.sql.statements.IInsert;
import IreulTools.sql.statements.ISelect;
import IreulTools.sql.statements.Insert;
import IreulTools.sql.statements.Select;
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

    private final String HOST = "172.17.68.188";
    private final String INIT_DB = "recommendation";
    private final String USER = "postgres";
    private final String PASSWORD = "uitox@pgsql2016";

    private ISimpleMap generateOptions(){
        ISimpleMap options = SimpleMap.create()
                .put("host",HOST)
                .put("dbname",INIT_DB)
                .put("user",USER)
                .put("password",PASSWORD);

        return options;
    }

    @Test
    public void testException() {

        ISimpleMap options = SimpleMap.create();
        try(IConnection cn = PostgreSqlConnection.create(options)) {
        }
        catch (Exception e){
            LOG.info(e.getMessage());
        }

        options.put("host", HOST);
        try(IConnection cn = PostgreSqlConnection.create(options)) {
        }
        catch (Exception e){
            LOG.info(e.getMessage());
        }

        options.put("dbname", INIT_DB);
        try(IConnection cn = PostgreSqlConnection.create(options)) {
        }
        catch (Exception e){
            LOG.info(e.getMessage());
        }

        options.put("user", USER);
        try(IConnection cn = PostgreSqlConnection.create(options)) {
        }
        catch (Exception e){
            LOG.info(e.getMessage());
        }

        options.put("password", PASSWORD);
        try(IConnection cn = PostgreSqlConnection.create(options)) {
        }
        catch (Exception e){
            LOG.info(e.getMessage());
        }

        options.put("port", "im string");
        try(IConnection cn = PostgreSqlConnection.create(options)) {
        }
        catch (Exception e){
            LOG.info(e.getMessage());
        }

        options.put("port", 5432);
        try(IConnection cn = PostgreSqlConnection.create(options)) {
            LOG.info(cn.toString());
            LOG.info("success");
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }
    }


    @Test
    public void testSelect() {

        ISimpleMap options = generateOptions();

        try(IConnection cn = PostgreSqlConnection.create(options)) {

            String[] selects = {
                    "select id, comment,model_name, model_ver, created_at ",
                    "from models"
            };
            String cmd = Join.from(selects).toString();
            IDatetime defaultDt = Datetime.now().setBeginOfDay();
            LOG.info("Default is {}", defaultDt.toString());

            cn.query(cmd, (i,row) -> {
                if(i==0){
                    LOG.info("{}", Join.from(row.getColumnNames()).with(",").toString());
                }

                LOG.info("{}. {}",i, row.column("created_at").toDatetime(defaultDt));
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


    @Test
    public void testInsert() {

        ISimpleMap options = generateOptions();

        try {
            String cmd = Insert.into("models")
                    .put("comment", "test")
                    .put("model_name", "test_model")
                    .put("model_ver", "v0000")
                    .put("weights","[1,2,3]")
                    .put("created_at", Datetime.now())
                    .toString();

            LOG.info(cmd);

            IConnection cn = PostgreSqlConnection.create(options);
            cn.exec(cmd, (i,row) -> {
                for(int j=0; j<row.size(); j++){
                    LOG.info("i:{} j:{} data:{}",i,j,row.index(j));
                }
                return true;
            });


            cn.close();
        }
        catch (SQLException e){
            LOG.error("SQLException", e);
        }
        catch (Exception e){
            LOG.error("Exception", e);
        }
    }


    @Test
    public void testRealExample1() {
        ISimpleMap options = SimpleMap.create()
                .put("host","172.17.68.188")
                .put("dbname","recommendation")
                .put("user","postgres")
                .put("password","uitox@pgsql2016");

        String memberId = "01174075-494E-5A87-4BC5-D50D907687C7";

        ISelect select = Select.from("recommendation_for_users")
                .where("member_id", memberId)
//                .where("ws_seq",wsSeq)
                .orderDescBy("created_at")
                .limit(1);

        LOG.debug(select.toString(true));

        final StringBuilder recList = new StringBuilder(50);
        try (IConnection cn = PostgreSqlConnection.create(options)) {
            cn.query(select.toString(), (i, row) -> {
                String list = row.column("recommendation_list").toString();
                LOG.info(list);
                recList.append(list);
                return false;
            });
        }
        catch (Exception e) {
            LOG.error("Exception", e);
        }
        LOG.info(recList.toString());
    }
}
