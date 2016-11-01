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
    public void testMain() {

        String insertStr = Insert.into("table1")
                .put("field1","1")
                .put("field2", 2)
                .toString();

        LOG.info(insertStr);
    }
}
