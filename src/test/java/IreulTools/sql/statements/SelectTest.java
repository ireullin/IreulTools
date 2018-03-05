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
                .orderAscBy("tran_date")
                .orderDescBy("member_id");


        LOG.info("\n{}", query.toString(true));
        String answer = "select id,tranid,tran_date,member_id,itno,it_name from transactions where tran_date >= '2016-08-01' and member_id = '0006E4EA-7B77-78FE-EBB2-B69F564574B0' and (cp_name = '充氣泳池' or cp_name = '濕紙巾') order by tran_date asc,member_id desc";
        assertEquals(answer, query.toString());
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
        assertEquals(subquery.toString(), "select distinct member_id from transactions where cp_name = '男錶'");

        ISelect mainquery = Select.from("transactions")
                .where("member_id",subquery);

        LOG.info("\n{}", mainquery.toString(true));
        assertEquals(mainquery.toString(), "select * from transactions where member_id in (select distinct member_id from transactions where cp_name = '男錶')");
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
                .orderAscBy("tran_date")
                .orderDescBy("member_id");

        LOG.info("\n{}", query.toString(true));
        String answer = "select tran_date,member_id,count(*) from transactions where tran_date >= '2016-08-01' and cp_name = '礦泉水/包裝水' group by tran_date,member_id having count(*) > 2 order by tran_date asc,member_id desc";
        assertEquals(answer, query.toString());
    }


    /**
     * 測試 tap, in, offset & limit
     */
    @Test
    public void testExample4() throws Exception{

        List<String> c2 = new ArrayList<>();
        c2.add("紙尿褲/濕紙巾");
        c2.add("手機周邊/配件");
        c2.add("紙尿褲/濕紙巾");
        c2.add("兒童玩具");

        ISelect query = Select.from("transactions")
                .where("member_id","0006E4EA-7B77-78FE-EBB2-B69F564574B0")
                .where("c2_name", c2)
                .orderDescBy("id")
                .tap(debugmsg -> LOG.info(debugmsg))
                .limit(10)
                .offset(4);

        LOG.info("\n{}", query.toString(true));
        String answer = "select * from transactions where member_id = '0006E4EA-7B77-78FE-EBB2-B69F564574B0' and c2_name in ('紙尿褲/濕紙巾','手機周邊/配件','紙尿褲/濕紙巾','兒童玩具') order by id desc limit 10 offset 4";
        assertEquals(answer, query.toString());
    }

    /**
     * 測試 沒有where的狀態
     */
    @Test
    public void testExample5() {
        ISelect select = Select
                .from("transactions")
                .columns("tran_date")
                .orderDescBy("tran_date")
                .limit(1);

        LOG.info("\n{}", select.toString(true));
        String answer = "select tran_date from transactions order by tran_date desc limit 1";
        assertEquals(answer, select.toString());
    }
}

