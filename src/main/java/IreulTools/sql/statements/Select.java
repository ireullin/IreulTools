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

    private final List<String> wherePart = new ArrayList<String>(10);
    private final List<String> orderPart = new ArrayList<String>(10);
    private final String table;


    public static ISelect from(String table){
        return new Select(table);
    }

    private Select(String table){
        this.table = table;
    }

    @Override
    public ISelect whereIn(String field, List<? extends Object> val) {
        IJoin join = Join.from(val);
        if(val.get(0) instanceof Integer) {
            join.with(",");
            wherePart.add(String.format("{} in ({})", field, join.toString()));
        }
        else if(val.get(0) instanceof Long) {
            join.with(",");
            wherePart.add(String.format("{} in ({})", field, join.toString()));
        }
        else if(val.get(0) instanceof Double) {
            join.with(",");
            wherePart.add(String.format("{} in ({})", field, join.toString()));
        }
        else if(val.get(0) instanceof Float) {
            join.with(",");
            wherePart.add(String.format("{} in ({})", field, join.toString()));
        }
        else {
            join.with("','");
            wherePart.add(String.format("{} in ('{}')", field, join.toString()));
        }

        return this;
    }

    @Override
    public ISelect whereIn(String field, Object[] vals) {
        return this;
    }

    @Override
    public ISelect where(String field, Object val){

        StringBuilder sb = new StringBuilder(20);
        if(val instanceof Integer) {
            sb = sb.append(field).append(" = ").append(val);
        }
        else if(val instanceof Long) {
            sb = sb.append(field).append(" = ").append(val);
        }
        else if(val instanceof Double) {
            sb = sb.append(field).append(" = ").append(val);
        }
        else if(val instanceof Float) {
            sb = sb.append(field).append(" = ").append(val);
        }
        else {
            sb = sb.append(field).append(" = '").append(val).append("'");
        }
        wherePart.add(sb.toString());
        return this;
    }

    @Override
    public ISelect where(String syntax){
        wherePart.add(syntax);
        return this;
    }

    @Override
    public String toString() {

        String[] buff ={
            "select *",
            "from", table,
            "where", Join.from(wherePart).with(" and ").toString()
        };





        return Join.from(buff).with(" ").toString();
    }
}
