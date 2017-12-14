package utils;

import java.sql.*;

/**
 * Created by 10742 on 2017/11/25.
 */
public class DBHandleUtils {

    private static Connection conn = null;
    private static Statement st = null;
    private static Statement st2 = null;
    private static ResultSet rs = null;
    private static ResultSet rs2 = null;
    private static String sql = null;
    private static String json = JSONUtils.getJSON(Constant.FILE_PATH);
    //个人信息
    public static String getPersonalMsg(String adminID) throws SQLException {

        StringBuilder sb = new StringBuilder();
        String tableName = JSONUtils.getMatchName(json, Constant.STUDENT, Constant.DB_NAME);//学生表
        String idColumn = JSONUtils.getMatchName(json, Constant.ID, Constant.DB_NAME);//id列
        String qq = JSONUtils.getMatchName(json, Constant.QQ, Constant.DB_NAME);//qq
        String tel = JSONUtils.getMatchName(json, Constant.TEL, Constant.DB_NAME);//联系方式
        String province = JSONUtils.getMatchName(json, Constant.PROVINCE, Constant.DB_NAME);//籍贯

        String cou_stu = JSONUtils.getMatchName(json, Constant.STUDENT_COURSE, Constant.DB_NAME);//课程-学生表
        String cou_stu_id = JSONUtils.getMatchName(json, Constant.STUDENT_COURSE_ID, Constant.DB_NAME);//课程学生表-学生id列
        String cou_stu_cid = JSONUtils.getMatchName(json, Constant.STUDENT_COURSE_CID, Constant.DB_NAME);//课程学生表-课程id列
        String int_stu = JSONUtils.getMatchName(json, Constant.STUDENT_INTERESTS, Constant.DB_NAME);//兴趣学生表
        String int_stu_id = JSONUtils.getMatchName(json, Constant.STUDENT_INTERESTS_ID, Constant.DB_NAME);//兴趣学生表-学生id列
        String int_stu_iid = JSONUtils.getMatchName(json, Constant.STUDENT_INTERESTS_IID, Constant.DB_NAME);//兴趣学生表-兴趣id列
        String cha_stu = JSONUtils.getMatchName(json, Constant.STUDENT_CHARACTER, Constant.DB_NAME);//性格学生表

        String course = JSONUtils.getMatchName(json, Constant.COURSE, Constant.DB_NAME);//课程表
        String course_id = JSONUtils.getMatchName(json, Constant.COURSE_ID, Constant.DB_NAME);//课程表id列
        String course_name = JSONUtils.getMatchName(json, Constant.COURSE_NAME, Constant.DB_NAME);//课程表课程名列

        String interests = JSONUtils.getMatchName(json, Constant.INTERESTS, Constant.DB_NAME);//兴趣表
        String interest = JSONUtils.getMatchName(json, Constant.INTEREST_NAME, Constant.DB_NAME);//兴趣表兴趣名列
        String interest_id = JSONUtils.getMatchName(json, Constant.INTEREST_ID, Constant.DB_NAME);//兴趣表兴趣id列

        String character = JSONUtils.getMatchName(json, Constant.CHARACTER, Constant.DB_NAME);//性格表
        String character_name = JSONUtils.getMatchName(json, Constant.CHARACTER_NAME, Constant.DB_NAME);//性格表性格名列
        String character_id = JSONUtils.getMatchName(json, Constant.CHARACTER_ID, Constant.DB_NAME);//性格表性格id列

        conn = JDBCUtils.getConnection(Constant.DB_NAME);
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        sql = "select * from " + tableName + " where " + idColumn + "=";

//        sb.append("<table><thead><tr>");
//        if (!idColumn.equals("")) sb.append("<th>学号</th>");
//        if (!qq.equals("")) sb.append("<th>QQ号</th>");
//        if (!province.equals("")) sb.append("<th>籍贯</th>");
//        if (!tel.equals("")) sb.append("<th>联系方式</th>");
//        if(!course.equals("")) sb.append("<th>已选课程</th>");
//        if (!interests.equals("")) sb.append("<th>兴趣爱好</th>");
//        if (!character.equals("")) sb.append("<th>性格</th>");
//        sb.append("</thead>");

        sb.append("<table><thead><tr><th>学号</th><th>QQ号</th><th>籍贯</th><th>联系方式</th><th>已选课程</th><th>兴趣爱好</th><th>性格</th></thead>");
        sb.append("<tbody>");
        rs = st.executeQuery(sql + adminID);
        while (rs.next()) {
            sb.append("<tr>");
            addToTable(sb, rs, idColumn);
            addToTable(sb, rs, qq);
            addToTable(sb, rs, province);
            addToTable(sb, rs, tel);
//            addToTable2(sb, rs, idColumn);
//            addToTable2(sb, rs, qq);
//            addToTable2(sb, rs, province);
//            addToTable2(sb, rs, tel);

            sql = "SELECT " + course_name + " FROM " + course + " where " + course_id + " IN (SELECT " + cou_stu_cid + " FROM " + cou_stu + " WHERE " + cou_stu_id + "=\'" + adminID + "\')";
            rs2 = st2.executeQuery(sql);
            sb.append("<td>");
            while (rs2.next()) {
                sb.append(" " + rs2.getString(course_name));
            }
            sb.append("</td>");

            sql = "SELECT " + interest + " FROM " + interests + " where " + interest_id;
            if (int_stu.equals(""))
                sql += "=" + adminID;
            else {
                sql += " IN (SELECT " + int_stu_iid + " FROM " + int_stu + " WHERE " + int_stu_id + "=\'" + adminID + "\')";
            }
            rs2 = st2.executeQuery(sql);
            sb.append("<td>");
            while (rs2.next()) {
                sb.append(" " + rs2.getString(interest));
            }
            sb.append("</td>");

            if (cha_stu.equals(""))
                sb.append("<td></td></tr>");
//            if (!cha_stu.equals("")){}
            else {
                sql = "SELECT " + character_name + " FROM " + character + " where " + character_id + " IN (SELECT " + character_id + " FROM " + cha_stu + " WHERE " + idColumn + "=\'" + adminID + "\')";
                rs2 = st2.executeQuery(sql);
                sb.append("<td>");
                while (rs2.next()) {
                    sb.append(" " + rs2.getString(character_name));
                }
                sb.append("</td></tr>");
            }
        }
        sb.append("</tbody>");
        sb.append("</table>");
        JDBCUtils.free(rs, st, conn);
        JDBCUtils.free(rs2, st2, conn);
        return sb.toString();
    }
    //班级信息
    public static String getClassInfo() throws SQLException {
        StringBuilder sb = new StringBuilder();
        String tableName = JSONUtils.getMatchName(json, Constant.STUDENT, Constant.DB_NAME);//学生表
        String name = JSONUtils.getMatchName(json, Constant.NAME, Constant.DB_NAME);//学生表名称列
        String sex = JSONUtils.getMatchName(json, Constant.SEX, Constant.DB_NAME);//学生表性别列
        String idColumn = JSONUtils.getMatchName(json, Constant.ID, Constant.DB_NAME);//学生表id列

        conn = JDBCUtils.getConnection(Constant.DB_NAME);
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        sql = "select * from " + tableName;
        rs = st.executeQuery(sql);

//        sb.append("<table><thead><tr>");
//        if (!name.equals("")) sb.append("<th>姓名</th>");
//        if (!sex.equals("")) sb.append("<th>性别</th>");
//        if (!idColumn.equals("")) sb.append("<th>学号</th>");
//        sb.append("</thead>");

        sb.append("<table><thead><tr><th>姓名</th><th>性别</th><th>学号</th></thead>");
        sb.append("<tbody>");
        while (rs.next()) {

            sb.append("<tr>");
            addToTable(sb, rs, name);
            addToTable(sb, rs, sex);
            addToTable(sb, rs, idColumn);
//            addToTable2(sb, rs, name);
//            addToTable2(sb, rs, sex);
//            addToTable2(sb, rs, idColumn);
            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");
        JDBCUtils.free(rs, st, conn);
        return sb.toString();
    }
    //课程信息
    public static String openedCourse() throws SQLException {
        StringBuilder sb = new StringBuilder();

        String tableName = JSONUtils.getMatchName(json, Constant.COURSE, Constant.DB_NAME);//课程表
        String course_day = JSONUtils.getMatchName(json, Constant.COURSE_DAY, Constant.DB_NAME);//课程表日期列
        String course_name = JSONUtils.getMatchName(json, Constant.COURSE_NAME, Constant.DB_NAME);//课程表课程名列

        conn = JDBCUtils.getConnection(Constant.DB_NAME);
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        sql = "select * from " + tableName;
        rs = st.executeQuery(sql);

//        sb.append("<table><thead><tr>");
//        if (!course_name.equals("")) sb.append("<th>课程名</th>");
//        if (!course_day.equals("")) sb.append("<th>上课日期</th>");
//        sb.append("</thead>");
        rs.last();
        System.out.println(rs.getRow());
//        sb.append("<table><thead><tr><th>课程名</th><th>上课日期</th></thead>");
//        sb.append("<tbody>");
//        while (rs.next()) {
//            sb.append("<tr>");
//            addToTable(sb, rs, course_name);
//            addToTable(sb, rs, course_day);
////            addToTable2(sb, rs, course_name);
////            addToTable2(sb, rs, course_day);
//            sb.append("</tr>");
//        }
//        sb.append("</tbody>");
//        sb.append("</table>");
//        JDBCUtils.free(rs, st, conn);
        return sb.toString();
    }
    //判断表中有没有该列,如果有往表格中添加数据
    public static void addToTable(StringBuilder sb, ResultSet rs, String columnName) throws SQLException {
        if (columnName.equals("")) {
            sb.append("<td></td>");
        } else {
            sb.append("<td>" + rs.getString(columnName) + "</td>");
        }
    }
    //情况2
    public static void addToTable2(StringBuilder sb, ResultSet rs, String columnName) throws SQLException {
        if (!columnName.equals("")) {
            sb.append("<td>" + rs.getString(columnName) + "</td>");
        }
    }
}
