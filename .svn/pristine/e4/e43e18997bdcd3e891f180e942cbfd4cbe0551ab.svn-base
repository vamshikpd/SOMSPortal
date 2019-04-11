package gov.ca.cdcr.somsportal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import static gov.ca.cdcr.somsportal.SOMSPortalUtils.extractAppSpecificGroups;
import static gov.ca.cdcr.somsportal.SOMSPortalUtils.getEntitledAppsList;

@WebServlet(name = "HCARedirectServlet", urlPatterns = "/hca-redirect")
public class HCARedirectServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger();
    private static final String propertiesFileName = "/WEB-INF/resources/somsportal.properties";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("Processing HCA Redirect request.");

        Properties prop = loadPropertiesFile(propertiesFileName);


        List<SOMSApplication> entitledApps = getEntitledAppsList(request, getServletContext());

        SOMSApplication hcaApp = entitledApps
                .stream()
                .filter(a -> a.getCaption().equalsIgnoreCase("AQR"))
                .findFirst()
                .orElse(null);

        if (hcaApp != null) {

            // set HCA cookies
            setHCAResponseCookies(request, response, hcaApp);

            response.sendRedirect(hcaApp.getUrl());

        } else {

            outputUnauthorizedMessage(response);

        }
    }

    private void setHCAResponseCookies(HttpServletRequest request, HttpServletResponse response, SOMSApplication hcaApp) {
        String firstname = request.getHeader("Name.First");
        String lastname = request.getHeader("Name.Last");

        //The following additional variables are for LEADS integration
        String userid = request.getHeader("UserID");

        //the following variables are for AQR integration
        String empid = request.getHeader("EmployeeID");
        String inst = request.getHeader("Institution");
        String groupslist = request.getHeader("Groups");
        groupslist = extractAppSpecificGroups(groupslist, hcaApp.getEntitledGroups());


        //add cookies
        Cookie uiCookie = new Cookie("UserID", userid);
        uiCookie.setDomain(".cdcr.ca.gov");
        uiCookie.setPath("/");
        response.addCookie(uiCookie);

        Cookie cCookie = new Cookie("EmployeeID", empid);
        cCookie.setDomain(".cdcr.ca.gov");
        cCookie.setPath("/");
        response.addCookie(cCookie);

        Cookie iCookie = new Cookie("Institution", inst);
        iCookie.setDomain(".cdcr.ca.gov");
        iCookie.setPath("/");
        response.addCookie(iCookie);

        Cookie fnCookie = new Cookie("Name.First", firstname);
        fnCookie.setDomain(".cdcr.ca.gov");
        fnCookie.setPath("/");
        response.addCookie(fnCookie);

        Cookie lnCookie = new Cookie("Name.Last", lastname);
        lnCookie.setDomain(".cdcr.ca.gov");
        lnCookie.setPath("/");
        response.addCookie(lnCookie);

        Cookie gCookie = new Cookie("Groups", groupslist);
        gCookie.setDomain(".cdcr.ca.gov");
        gCookie.setPath("/");
        response.addCookie(gCookie);

    }



    private void outputUnauthorizedMessage(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>SOMS Portal</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<center style=\"text-align:center; margin:0 auto;\">");
        out.println("<h2>You are not authorized to access HCA Application</h2> <br/>");
        out.println("<a href=\"index.jsp\"> <h4>[ Return back to SOMS Portal ]</h4> </a>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
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