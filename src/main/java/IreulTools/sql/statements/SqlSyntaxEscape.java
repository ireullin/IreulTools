package IreulTools.sql.statements;

/**
 * Created by tech0039 on 2016/11/17.
 */
public class SqlSyntaxEscape {
    static public String escape(String s){
        return s.replace("'","''");
    }
}
