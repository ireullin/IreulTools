package IreulTools.sql.statements;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ireullin on 2016/11/1.
 */
public class SelectTest  extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(SelectTest.class);

    @Test
    public void testExample1() {
        String cmd = Select.from("table")
                .where("a",1)
                .where("b", "2")
                .where("c", 3.5)
                .where("d >= 6")
                .toString();

        LOG.info(cmd);
    }

    @Test
    public void testExample2() {
        List<String> var1 = new ArrayList<>();
        var1.add("a");
        var1.add("b");
        var1.add("c");

        List<Integer> var2 = new ArrayList<>();
        var2.add(1);
        var2.add(2);
        var2.add(3);


        String cmd = Select.from("table")
                .whereIn("var1",var1)
                .whereIn("var2",var2)
                .toString();

        LOG.info(cmd);
    }

}

