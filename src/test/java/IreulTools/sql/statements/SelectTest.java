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

    /**
     * 測試 where, or, order & columns
     */
    @Test
    public void testExample1() {

        String[] columns ={"id", "tranid","tran_date", "member_id", "itno","it_name"};

        ISelect query = Select.from("transactions")
                .columns(columns)
                .where("tran_date >= '2016-08-01'")
                .where("member_id","0006E4EA-7B77-78FE-EBB2-B69F564574B0")
                .where("(cp_name = '充氣泳池' or cp_name = '濕紙巾')")
                .orderByAsc("tran_date")
                .orderByDesc("member_id");


        LOG.info("\n{}", query.toString(true));
    }


    /**
     * 測試 distinct & subquery
     */
    @Test
    public void testExample2() {

        ISelect subquery = Select.from("transactions")
                .distinct("member_id")
                .where("cp_name","男錶");

        LOG.info("\n{}", subquery.toString(true));


        ISelect mainquery = Select.from("transactions")
                .where("member_id",subquery);

        LOG.info("\n{}", mainquery.toString(true));
    }

    /**
     * 測試 group & having
     */
    @Test
    public void testExample3() {

        String[] columns ={"tran_date", "member_id"};

        ISelect query = Select.from("transactions")
                .where("tran_date >= '2016-08-01'")
                .where("cp_name", "礦泉水/包裝水")
                .groupWithCount(columns, "> 2")
                .orderByAsc("tran_date")
                .orderByDesc("member_id");

        LOG.info("\n{}", query.toString(true));
    }


    /**
     * 測試 tap, in, offset & limit
     */
    @Test
    public void testExample4() {

        List<String> c2 = new ArrayList<>();
        c2.add("紙尿褲/濕紙巾");
        c2.add("手機周邊/配件");
        c2.add("紙尿褲/濕紙巾");
        c2.add("兒童玩具");

        ISelect query = Select.from("transactions")
                .where("member_id","0006E4EA-7B77-78FE-EBB2-B69F564574B0")
                .where("c2_name", c2)
                .orderByDesc("id")
                .tap(debugmsg -> LOG.info(debugmsg))
                .limit(10)
                .offset(4);

        LOG.info("\n{}", query.toString(true));
    }
}

