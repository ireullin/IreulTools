package IreulTools.sql.statements;

import IreulTools.functionalProgramming.ITap;
import IreulTools.jsonBuilder.IJsonMap;
import IreulTools.stringExtension.IJoin;
import IreulTools.stringExtension.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
    private String having = "";
    private String limit = "";
    private String offset = "";


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
    public ISelect where(String field, ISelect subquery){
        StringBuilder sb = new StringBuilder(50);
        sb.append(field)
            .append(" in (")
            .append( subquery.toString() )
            .append(")");

        where(sb.toString());
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
    public ISelect orderDescBy(String column) {
        orderPart.add(column+" desc");
        return this;
    }

    @Override
    public ISelect orderAscBy(String column) {
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
    public ISelect groupWithCount(String syntax, String having) {
        this.having = "having count(*) "+having;
        return groupWithCount(syntax);
    }

    @Override
    public ISelect groupWithCount(String[] syntax, String having) {
        this.having = "having count(*) "+having;
        return groupWithCount(syntax);
    }

    @Override
    public ISelect groupWithCount(List<String> syntax, String having) {
        this.having = "having count(*) "+having;
        return groupWithCount(syntax);
    }

    @Override
    public ISelect limit(int i) {
        this.limit = "limit "+Integer.toString(i);
        return this;
    }

    @Override
    public ISelect offset(int i) {
        this.offset = " offset "+Integer.toString(i);
        return this;
    }

    @Override
    public ISelect tap(ITap<String> debugMsg) {
        debugMsg.put(toString());
        return this;
    }

    @Override
    public String toString() {
        return this.toString(false);
    }

    @Override
    public String toString(boolean doesIndent) {

        String indent = " ";
        if(doesIndent){
            indent = "\n";
        }

        String orderby = "";
        if(!orderPart.isEmpty()) {
            orderby = "order by "+Join.from(orderPart).with(",").toString();
        }

        List<String> raw = Arrays.asList(
                "select "+columns,
                "from "+table,
                "where "+wherePart.toString(doesIndent),
                groupBy,
                having,
                orderby,
                limit + offset
        );

        List<String> buff = raw.stream()
                .filter(word -> !word.trim().isEmpty())
                .collect(Collectors.toList());

        IJoin main = Join.from(buff).with(indent);
        return main.toString().trim();
    }
}
