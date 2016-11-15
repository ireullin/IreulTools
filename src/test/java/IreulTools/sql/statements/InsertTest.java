package IreulTools.sql.statements;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ireullin on 2016/11/1.
 */
public class InsertTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(InsertTest.class);

    @Test
    public void testExample1() {

        String insertStr = Insert.into("table1")
                .put("field1","1")
                .put("field2", 2)
                .toString();

        LOG.info(insertStr);
        String answer = "insert into table1 (field1,field2) values ('1','2')";
        assertEquals(answer, insertStr.toString());
    }

    @Test
    public void testExample2() {

        String insertStr = Insert.into("recommendations_20161108_03")
                .put("member_id","ireullin")
                .put("recommendation_list", "[]")
                .put("created_at", "2016-08-04 00:00:00")
                .returning("member_id")
                .toString();

        LOG.info(insertStr);
        String answer = "insert into recommendations_20161108_03 (member_id,recommendation_list,created_at) values ('ireullin','[]','2016-08-04 00:00:00') returning member_id";
        assertEquals(answer, insertStr.toString());
    }
}
