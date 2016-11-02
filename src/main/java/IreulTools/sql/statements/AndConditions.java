package IreulTools.sql.statements;

import IreulTools.stringExtension.IJoin;
import IreulTools.stringExtension.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by tech0039 on 2016/11/2.
 */
public class AndConditions implements IAndConditions {

    private static final Logger LOG = LoggerFactory.getLogger(AndConditions.class);

    private final List<String> cons = new ArrayList<String>(20);

    private String appendNumSyntax(String field, String val){
        return (new StringBuilder(50))
                .append(field)
                .append(" in (")
                .append(val)
                .append(")")
                .toString();
    }


    private String appendStringSyntax(String field, String val){
        return (new StringBuilder(50))
                .append(field)
                .append(" in ('")
                .append(val)
                .append("')")
                .toString();
    }

    public IAndConditions put(String field, Object val){
        if(val instanceof List){
            return handleList(field, (List)val);
        }
        else if(val.getClass().isArray()){
            return handleArray(field, (Object[])val);
        }
        else{
            return handleNonList(field, val);
        }
    }

    public IAndConditions handleArray(String field, Object[] val){

        IJoin join = Join.from(val);
        if(val[0] instanceof Integer) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else if(val[0] instanceof Long) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else if(val[0] instanceof Double) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else if(val[0] instanceof Float) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else {
            String buff = appendStringSyntax(field,join.with("','").toString());
            cons.add(buff);
        }

        return this;
    }

    private IAndConditions handleList(String field, List<Object> val){
        LOG.debug("{}", val.get(0).getClass().toString());
        IJoin join = Join.from(val);
        if(val.get(0) instanceof Integer) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else if(val.get(0) instanceof Long) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else if(val.get(0) instanceof Double) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else if(val.get(0) instanceof Float) {
            String buff = appendNumSyntax(field,join.with(",").toString());
            cons.add(buff);
        }
        else {
            String tmp = join.with("','").toString();
            String buff = appendStringSyntax(field,tmp);
            cons.add(buff);
        }

        return this;
    }

    private IAndConditions handleNonList(String field, Object val){

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
        cons.add(sb.toString());
        return this;
    }

    @Override
    public IAndConditions put(String syntax) {
        cons.add(syntax);
        return this;
    }

    @Override
    public String toString() {
        return Join.from(cons).with(" and ").toString();
    }
}
