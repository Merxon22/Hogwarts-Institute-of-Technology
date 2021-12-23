package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = null;
        Cookie[] cookies = null;


        cookies = request.getCookies();


        if( cookies != null ) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];

                if((cookie.getName( )).compareTo("email") == 0 ) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        RequestDispatcher rd  = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }


}
