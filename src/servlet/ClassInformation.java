package servlet;

import utils.DBHandleUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by 10742 on 2017/11/25.
 */
public class ClassInformation extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        try {
            out.print(DBHandleUtils.getClassInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println();
    }

}
