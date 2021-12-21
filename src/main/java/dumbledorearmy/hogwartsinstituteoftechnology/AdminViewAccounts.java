package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


//Admin界面，有两个列表，两个按钮，编辑和删除人
//明天复习下傻逼sql语言
//todo: 整一个老师的表，学生的表，我是傻逼



@WebServlet(name = "AdminViewAccounts", value = "/AdminViewAccounts")
public class AdminViewAccounts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //第一步，打印出4个按钮，分别对应加人和删人，老师学生；支持批量加人和删人
        //第二步，两个表格作为列表打印出来，账号 + 密码
        //这个页面就是Admin登陆完进，Tang跳转进来

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            Connection con = Provider.GetConn();
            Statement stmt = con.createStatement();
            String x = "select * from teachers";

            ResultSet rs = stmt.executeQuery(x);

            //咱们左边放老师，右边放学生

            out.println("<a href = \"AddStudentAcc.jsp\"><button type=\"button\">Add Students</button></a>");
            out.println("<a href = \"AddTeacherAcc.jsp\"><button type=\"button\">Add Teachers</button></a>");
            out.println("<a href = \"DelStu.jsp\"><button type=\"button\">Delete Students</button></a>");
            out.println("<a href = \"DelTea.jsp\"><button type=\"button\">Delete Teachers</button></a>");


            out.println("<table>\n" +
                    "        <th>\n" +
                    "            <td>Account</td>\n" +
                    "            <td>Password</td>\n" +
                    "        </th>");
            while (rs.next()){
                out.println("<tr>\n" +
                        "            <td>" + rs.getString("Firstname") + "</td>\n" +
                        "            <td>" + rs.getString("Lastname") + "</td>\n" +
                        "            <td>" + rs.getString("email") + "</td>\n" +
                        "            <td>" + rs.getString("password") + "</td>\n" +
                        "        </tr>");

            }
            out.println("</table>");
            x = "select * from students";
            rs = stmt.executeQuery(x);
            out.println("<table>\n" +
                    "        <th>\n" +
                    "            <td>Account</td>\n" +
                    "            <td>Password</td>\n" +
                    "        </th>");
            while (rs.next()){
                out.println("<tr>\n" +
                        "            <td>" + rs.getString("Firstname") + "</td>\n" +
                        "            <td>" + rs.getString("Lastname") + "</td>\n" +
                        "            <td>" + rs.getString("email") + "</td>\n" +
                        "            <td>" + rs.getString("password") + "</td>\n" +
                        "            <td>" + rs.getString("grade") + "</td>\n" +
                        "            <td>" + rs.getString("class") + "</td>\n" +
                        "        </tr>");
            }

            out.println("</table>");




        } catch (Exception exe) {
        System.out.println("Exception caught" + exe);
    }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
