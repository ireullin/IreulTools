package IreulTools.sql.statements;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech0039 on 2016/11/2.
 */
public class AndConditionsTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(AndConditionsTest.class);

    @Test
    public void testExample1() {

        List<String> var1 = new ArrayList<>();
        var1.add("a");
        var1.add("b");
        var1.add("c");

        IAndConditions cons1 = new AndConditions();
        cons1.put("fields", var1);
        LOG.info(cons1.toString());
        assertEquals("fields in ('a','b','c')", cons1.toString());

        List<Integer> var2 = new ArrayList<>();
        var2.add(1);
        var2.add(2);
        var2.add(3);

        IAndConditions cons2 = new AndConditions();
        cons2.put("fields", var2);
        LOG.info(cons2.toString());
        assertEquals("fields in (1,2,3)", cons2.toString());
    }

    @Test
    public void testExample2() {

        String[] var1 = {"a","b","c"};
        IAndConditions cons1 = new AndConditions();
        cons1.put("fields", var1);
        LOG.info(cons1.toString());
        assertEquals("fields in ('a','b','c')", cons1.toString());

        Integer[] var2 = {1,2,3};
        IAndConditions cons2 = new AndConditions();
        cons2.put("fields", var2);
        LOG.info(cons2.toString());
        assertEquals("fields in (1,2,3)", cons2.toString());

        try {
            int[] var3 = {1, 2, 3};
            IAndConditions cons3 = new AndConditions();
            cons3.put("fields", var3);
            LOG.info(cons3.toString());
            assertEquals(true,false);
        }
        catch (Exception e){
            LOG.info(e.getMessage());
            assertEquals(true,true);
        }
    }

    @Test
    public void testExample3() {

        String[] var = {"a","b","c"};

        IAndConditions cons = new AndConditions();
        String rc = cons
                .put("range", var)
                .put("a",2)
                .put("b","3")
                .put("c > 4")
                .toString();

        LOG.info(rc);
        assertEquals("range in ('a','b','c') and a = 2 and b = '3' and c > 4", cons.toString());

    }
}
