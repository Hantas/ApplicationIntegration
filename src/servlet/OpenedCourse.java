package servlet;

import utils.DBHandleUtils;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by 10742 on 2017/11/24.
 */
public class OpenedCourse extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        try {
            out.print(DBHandleUtils.openedCourse());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println();
    }

}
