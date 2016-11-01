package IreulTools.sql.statements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech0039 on 2016/11/1.
 */
public class Insert implements IInsert{

    private static final Logger LOG = LoggerFactory.getLogger(Insert.class);

    private final String table;
    private final List<String> cols = new ArrayList<String>(50);
    private final List<String> vals = new ArrayList<String>(50);


    public static IInsert into(String table){
        return new Insert(table);
    }

    private Insert(String table){
        this.table = table;
    }

    public IInsert put(Object column, Object value){
        cols.add(column.toString());
        vals.add(value.toString());
        return this;
    }

    @Override
    public String toString() {
        StringBuilder head = new StringBuilder(100);
        head.append("insert into ");
        head.append(table);
        head.append(" (");

        StringBuilder body = new StringBuilder(100);
        body.append(" values (");

        for(int i=0; i<cols.size(); i++){
            head.append(cols.get(i));

            body.append("'");
            body.append(vals.get(i));
            body.append("'");

            if(i==cols.size()-1)
                break;

            head.append(",");
            body.append(",");
        }

        head.append(") ");
        body.append(") ");

        head.append(body);
        return head.toString();
    }
}
