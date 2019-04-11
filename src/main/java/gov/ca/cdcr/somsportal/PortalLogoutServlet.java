package gov.ca.cdcr.somsportal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "PortalLogoutServlet", urlPatterns = "/logout")
public class PortalLogoutServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger();
    private static final String propertiesFileName = "/WEB-INF/resources/somsportal.properties";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Processing logout request.");

        Properties prop = loadPropertiesFile(propertiesFileName);

        // invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // get the property value and print it out
        String logoutLink = prop.getProperty("logout_url");

        response.sendRedirect(logoutLink);
    }


    /**
     * @param filename property file name
     * @return property objects
     */
    private Properties loadPropertiesFile(String filename) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = getServletContext().getResourceAsStream(filename);

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }



}
