package listeners;

import com.sun.xml.internal.ws.api.policy.PolicyResolver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/11/29.
 */
@WebListener()
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            ServletContext context = servletContextEvent.getServletContext();
            String db_url = context.getInitParameter("db_url");
            String db_user = context.getInitParameter("db_user");
            String db_password = context.getInitParameter("db_password");
            Connection connection = DriverManager.getConnection(db_url, db_user, db_password);
            if (connection != null){
                context.setAttribute("connection",connection);
                System.out.println("数据库连接成功");
            }
        }catch (Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try{
            ServletContext  context = servletContextEvent.getServletContext();
            Connection connection = (Connection)context.getAttribute("connection");
            connection.close();
            System.out.println("数据库已关闭");
        }catch (SQLException e){
            System.out.println("数据库关闭异常");
            e.printStackTrace();
        }
    }
}
