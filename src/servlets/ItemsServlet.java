package servlets;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.FullItem;
import pojo.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by zss on 2015/11/29.
 * post方法处理ajax请求
 * get 方法处理主页面 /items 的加载  和 单个展开 /item
 */
@WebServlet(name = "servlets.ItemsServlet",urlPatterns = {"/items","/item","/more"})
public class ItemsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray jsonArray  = new JSONArray();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        String idStr = request.getParameter("id");
        Connection connection = (Connection)getServletContext().getAttribute("connection");
        if (connection != null  && idStr != null){
            int minid,maxid;
            int id = new Integer(idStr).intValue();
            //还有剩余页
            if (id>=11){
                maxid = id -1;
                minid = maxid - 9;
            }else if (id>1){
                maxid = id -1;
                minid = 1;
            }else {
                minid = 1;
                maxid = -1;
            }

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT title,id FROM items WHERE id<="+maxid+" AND id>="+minid +" ORDER BY id DESC ");
                while (resultSet.next()){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("title",resultSet.getString("title"));
                    jsonObject.put("id",resultSet.getString("id"));
                    jsonArray.add(jsonObject);
                }
            }catch (SQLException e){
                System.out.println("ajax 请求错误");
            }
        }
        out.write(jsonArray.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri =request.getRequestURI();
        request.setCharacterEncoding("utf-8");
        if (uri.endsWith("items")){
            //得到数据库的连接
            Connection connection = (Connection)getServletContext().getAttribute("connection");

            if (connection != null){
                //数据库中id最大值
                int minid=1;
                ArrayList<Item> list = new ArrayList<Item>();
                try{
                    Statement statement = connection.createStatement();
                    int maxId = getSysMaxId(request);
                    if (maxId>10){
                        minid = maxId - 9;
                    }
                    String sql = "SELECT title,id FROM items WHERE id<="+maxId+" AND id>="+minid+" ORDER BY id DESC ";
                    ResultSet resultSet = statement.executeQuery(sql);
                    while(resultSet.next()){
                        Item item = new Item();
                        item.setTitle(resultSet.getString("title"));
                        item.setId(resultSet.getInt("id"));
                        list.add(item);
                    }
                    request.setAttribute("list", list);

                }catch (SQLException e){
                    System.out.println("items 数据库操作失败");
                }
                request.getRequestDispatcher("newitem.jsp").forward(request,response);
            }
        }else if (uri.endsWith("item")) {
            String idStr = request.getParameter("id");
            Connection connection = (Connection)getServletContext().getAttribute("connection");
            if (idStr != null && connection != null) {
                int id = new Integer(idStr).intValue();
                try {
                    FullItem fullItem= new FullItem();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT  title ,uname,tel,start_time,stop_time,content FROM items where id="+id);
                    if (resultSet.next()){
                        fullItem.setTitle(resultSet.getString("title"));
                        fullItem.setUname(resultSet.getString("uname"));
                        fullItem.setTel(resultSet.getString("tel"));
                        fullItem.setStart_time(resultSet.getString("start_time"));
                        fullItem.setStop_time(resultSet.getString("stop_time"));
                        fullItem.setContent(resultSet.getString("content"));
                    }
                    request.setAttribute("list",fullItem);
                }catch (SQLException e){
                    System.out.println("item 数据库操作失败");
                }

            }
            request.getRequestDispatcher("oneItem.jsp").forward(request,response);
        }
    }
    protected int  getSysMaxId(HttpServletRequest request){
        Connection connection = (Connection)getServletContext().getAttribute("connection");
        int maxId=0;
        try{
            Statement statement = connection.createStatement();
            String sql = "select MAX(id) maxid from items";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                maxId = resultSet.getInt("maxid");
            }
        }catch (SQLException e){
            System.out.println("items 数据库操作失败");
            return maxId;
        }
        return maxId;
    }

}
