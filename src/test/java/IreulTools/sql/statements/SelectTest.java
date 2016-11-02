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
        String[] col ={"id", "tranid","tran_date", "member_id", "itno","it_name"};
        String[] col2 ={"tran_date", "member_id"};

        String cmd = Select.from("transactions")
//                .columns(col)
//                .distinct("member_id,itno")
                .where("tran_date >= '2016-08-01'")
                .where("cp_name", "礦泉水/包裝水")
                .groupWithCount(col2)
//                .orderByAsc("tran_date")
//                .orderByDesc("itno")
                .toString();

        LOG.info("\n"+cmd);
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


//        String cmd = Select.from("table")
//                .whereIn("var1",var1)
//                .whereIn("var2",var2)
//                .toString();
//
//        LOG.info(cmd);
    }

}

