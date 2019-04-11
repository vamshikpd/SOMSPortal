package gov.ca.cdcr.somsportal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static gov.ca.cdcr.somsportal.SOMSPortalUtils.getEntitledAppsList;


@WebServlet(
        name = "SOMSPortal",
        urlPatterns = {"/"}
)
public class SOMSPortalServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("===========================================");
        log.debug("SOMS Portal -- start processing request.");

        String userId = request.getHeader("UserID");

        log.debug("USERID: " + userId);

        logRequestCookies(request);
        logRequestHeaders(request);

        List<SOMSApplication> entitledApps = getEntitledAppsList(request, getServletContext());

        // jsp attributes
        request.setAttribute("firstName", request.getHeader("Name.First"));
        request.setAttribute("lastName", request.getHeader("Name.Last"));
        request.setAttribute("entitledApps", entitledApps);
        request.setAttribute("totalAppsAllowed", entitledApps.size());

        // LEADS integration jsp attrs

        request.setAttribute("email", request.getHeader("Email"));
        request.setAttribute("desc", "Leave Blank");
        request.setAttribute("fullname", "Leave Blank");
        request.setAttribute("ipaddress", request.getHeader("X-Forwarded-For"));
        request.setAttribute("userId", userId);

        if (entitledApps.size() == 0) {
            log.debug("There are no entitled apps for user");
        }

        // set cookies
        setResponseCookies(request, response);

        log.debug("Rendering JSP portal page for user: " + userId);

        // render JSP
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    private void logRequestCookies(HttpServletRequest request) {
        if (!log.isDebugEnabled()) {
            return;
        }

        log.debug("=== REQUEST COOKIES: ===");
        Cookie[] cookies = request.getCookies();
        StringBuilder sb = new StringBuilder();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                sb.append(cookie.getName());
                sb.append(" ==> ");
                sb.append(cookie.getValue());
                sb.append(System.getProperty("line.separator"));
            }
        }
        log.debug(sb.toString());
    }

    private void logRequestHeaders(HttpServletRequest request) {
        if (!log.isDebugEnabled()) {
            return;
        }

        log.debug("=== REQUEST HEADERS: ===");
        Enumeration<String> headers = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();
        while (headers.hasMoreElements()) {
            String headerName = (String) headers.nextElement();
            String headerValue = request.getHeader(headerName);
            sb.append(headerName);
            sb.append(" ==> ");
            sb.append(headerValue);
            sb.append(System.getProperty("line.separator"));
        }
        log.debug(sb.toString());
    }

    private void setResponseCookies(HttpServletRequest request, HttpServletResponse response) {

        String firstname = request.getHeader("Name.First");
        String lastname = request.getHeader("Name.Last");

        //The following additional variables are for LEADS integration
        String userid = request.getHeader("UserID");

        //the following variables are for AQR integration
        String empid = request.getHeader("EmployeeID");
        String inst = request.getHeader("Institution");
        String groupslist = request.getHeader("Groups");

        //add cookies
        Cookie cCookie = new Cookie("EmployeeID", empid);
        cCookie.setDomain(".cdcr.ca.gov");
        cCookie.setPath("/");
        response.addCookie(cCookie);

        Cookie iCookie = new Cookie("Institution", inst);
        iCookie.setDomain(".cdcr.ca.gov");
        iCookie.setPath("/");
        response.addCookie(iCookie);


        // EA - groups cookie was moved to HCARedirectServlet where it populates
        // only requires groups into the cookie. The original GROUPS from HTTP header
        // may be too large to fit into a cookie.
        // ---------------------------------------------------------------------------
        //Cookie gCookie = new Cookie("Groups", groupslist);
        //gCookie.setDomain(".cdcr.ca.gov");
        //gCookie.setPath("/");
        //response.addCookie(gCookie);
        // ---------------------------------------------------------------------------

        Cookie fnCookie = new Cookie("Name.First", firstname);
        fnCookie.setDomain(".cdcr.ca.gov");
        fnCookie.setPath("/");
        response.addCookie(fnCookie);

        Cookie lnCookie = new Cookie("Name.Last", lastname);
        lnCookie.setDomain(".cdcr.ca.gov");
        lnCookie.setPath("/");
        response.addCookie(lnCookie);

        Cookie uiCookie = new Cookie("UserID", userid);
        uiCookie.setDomain(".cdcr.ca.gov");
        uiCookie.setPath("/");
        response.addCookie(uiCookie);


    }
}

