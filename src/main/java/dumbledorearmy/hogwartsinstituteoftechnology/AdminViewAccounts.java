package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;



//Admin界面，有两个列表，两个按钮，编辑和删除人
//明天复习下傻逼sql语言
//todo: 整一个老师的表，学生的表，我是傻逼



@WebServlet(name = "AdminViewAccounts", value = "/AdminViewAccounts")
public class AdminViewAccounts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //第一步，打印出两个按钮，分别对应加人和删人；支持批量加人和删人
        //第二步，两个表格作为列表打印出来，账号 + 密码



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
