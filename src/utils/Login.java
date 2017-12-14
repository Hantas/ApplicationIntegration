package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 10742 on 2017/11/24.
 */
public class Login {
    private String json = JSONUtils.getJSON(Constant.FILE_PATH);
    //判断所属数据库
    public Boolean checkLogin(String id, String password){
        int len = Constant.DB_NAMES.length;
        for (int i = 0; i < len; i++){
            try {
                Connection conn = JDBCUtils.getConnection(Constant.DB_NAMES[i]);
                Statement st = conn.createStatement();
                String dbName = JSONUtils.getMatchName(json, Constant.CONFIG, Constant.DB_NAMES[i]);//数据库名
                String table = JSONUtils.getMatchName(json, Constant.STUDENT, dbName);//学生表
                String idColumn = JSONUtils.getMatchName(json, Constant.ID, dbName);//学生表id列
                String pword = JSONUtils.getMatchName(json, Constant.PASSWORD, dbName);//学生表密码列
                String sql = "select " + pword + " from " + table + " where " + idColumn + "=" + id;
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()){
                    if (rs.getString(pword).equals(password)){
                        JDBCUtils.free(rs, st, conn);
                        Constant.DB_NAME = dbName;
                        return true;
                    }
                }else {
                    JDBCUtils.free(rs, st, conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
