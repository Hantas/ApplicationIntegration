package servlet;

import utils.Constant;
import utils.DBHandleUtils;
import utils.JDBCUtils;
import utils.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 10742 on 2017/11/25.
 */
public class PersonalMsg extends HttpServlet {

    private HttpSession session = null;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        try {
            out.print(DBHandleUtils.getPersonalMsg((String)session.getAttribute("AdminID")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println();
    }

}
