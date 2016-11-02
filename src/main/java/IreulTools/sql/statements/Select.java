package IreulTools.sql.statements;

import IreulTools.stringExtension.IJoin;
import IreulTools.stringExtension.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ireullin on 2016/11/1.
 */
public class Select implements ISelect {

    private static final Logger LOG = LoggerFactory.getLogger(Select.class);

    private final IAndConditions wherePart = new AndConditions();
    private final List<String> orderPart = new ArrayList<String>(10);
    private final String table;
    private String columns = "*";
    private String groupBy = "";


    public static ISelect from(String table){
        return new Select(table);
    }

    private Select(String table){
        this.table = table;
    }

    @Override
    public ISelect where(String field, Object val){
        wherePart.put(field, val);
        return this;
    }

    @Override
    public ISelect where(String syntax){
        wherePart.put(syntax);
        return this;
    }

    @Override
    public ISelect columns(String syntax) {
        this.columns = syntax;
        return this;
    }

    @Override
    public ISelect columns(String[] syntax) {
        this.columns = Join.from(syntax).with(",").toString();
        return this;
    }

    @Override
    public ISelect count() {
        this.columns = "count(*)";
        return this;
    }

    @Override
    public ISelect distinct(String column) {
        this.columns = "distinct "+column;
        return this;
    }

    @Override
    public ISelect columns(List<String> syntax) {
        this.columns = Join.from(syntax).with(",").toString();
        return this;
    }

    @Override
    public ISelect orderByDesc(String column) {
        orderPart.add(column+" desc");
        return this;
    }

    @Override
    public ISelect orderByAsc(String column) {
        orderPart.add(column+" asc");
        return this;
    }

    @Override
    public ISelect groupWithCount(String syntax) {
        this.columns = syntax+",count(*)";
        this.groupBy = "group by "+syntax;
        return this;
    }

    @Override
    public ISelect groupWithCount(String[] syntax) {
        String s = Join.from(syntax).with(",").toString();
        return groupWithCount(s);
    }

    @Override
    public ISelect groupWithCount(List<String> syntax) {
        String s = Join.from(syntax).with(",").toString();
        return groupWithCount(s);
    }

    @Override
    public String toString() {

        String orderby = "";
        if(!orderPart.isEmpty()) {
            orderby = "order by "+Join.from(orderPart).with(",").toString();
        }

        String[] buff ={
            "select",columns,
            "from", table,
            "where", wherePart.toString(),
            groupBy,
            orderby
        };

        IJoin main = Join.from(buff).with(" ");
        return main.toString();
    }
}
