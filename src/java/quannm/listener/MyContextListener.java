/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quannm.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ADMIN
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //kích hoạt khi app bắt đầu được deploy
        //nạp toàn bộ tập tin siteMaps vào bên trong bộ nhớ
        //siteMaps nằm trong bộ nhớ container để mọi người có thể truy cập mọi lúc
        //siteMaps nằm trong context inis
        //1. Get context scope
        //3. cahche into attribute
        ServletContext context = sce.getServletContext();
        //2. load siteMaps
        String filePath = context.getInitParameter("SITEMAPS_FILE_PATH");
        //đọc file
        //Container hỗ trợ thành 1 hashMap không cần đọc line by line
        Properties siteMaps = new Properties();
        InputStream is = null;
        try {
            is = context.getResourceAsStream(filePath);
            //load tập tin properties vào tập tin IOputStream theo dạng hashMap
            siteMaps.load(is);
            //cache into sitemaps
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("MyContextListener_ IOException: " + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    context.log("MyContextListener_ IOException: " + ex.getMessage());
                }
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //kích hoạt trước khi undeploy
    }
}
