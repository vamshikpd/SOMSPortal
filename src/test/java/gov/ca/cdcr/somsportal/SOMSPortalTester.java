package gov.ca.cdcr.somsportal;//import static org.mockito.Mockito.*;
import gov.ca.cdcr.somsportal.SOMSApplication;
import gov.ca.cdcr.somsportal.SOMSApplicationHandler;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SOMSPortalTester {

//    private SOMSPortalServlet servlet;
//    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;
    private SOMSApplicationHandler applicationHandler;

    private String propFileName = "testProps.properties";

    @Before
    public void setUp() throws Exception {

        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(servletContext.getResourceAsStream(any())).thenReturn(this.getClass().getClassLoader().getResourceAsStream(propFileName));

//        when(servletContext.getRequestDispatcher("/WEB-INF/jsp/index.jsp")).thenReturn(dispatcher);
        //when(servletContext.getResourceAsStream("somsportal.properties")).thenReturn(new FileInputStream(propFile));
//        servlet = new SOMSPortalServlet() {
//            public ServletContext getServletContext() {
//                return servletContext; // return the mock
//            }
//        };
//
//        request = new MockHttpServletRequest(); //mock(HttpServletRequest.class);
//        response = new MockHttpServletResponse();

        applicationHandler = new SOMSApplicationHandler(servletContext, propFileName);
    }

    @Test
    public void testNoAppsAccess() throws ServletException, IOException {


        List<SOMSApplication> appList = applicationHandler.getEntitledApps("");

        assertEquals(0, appList.size());
    }


    @Test
    public void testnewMDCHeaders() throws ServletException, IOException {

//        String minGroups = "CN=dl_CDCR-Office365-Phase1,OU=Distribution Lists,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-Veritas-Journaling,OU=O365,OU=Apps,OU=Security,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=DL-CDCR-SOMS-eOMIS-Users,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=DL-CDCR SOMS HP Staff,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=APP-CDCR-SOMSeOMIS-CAViewEnhanced,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=APP-CDCR-SOMSeOMIS-CAViewBasic,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-CDCRGeneral-FileAccess-IT,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-VPN-SOMS,OU=VPN,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMSeOMIS-CAParole,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-DataConversion,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-SCMTest,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-SCMTest-Release-1,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMSeOMIS-CAInmSupv,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-Interfaces,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Role-SCM-Developer,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-MEE-Laptop Users EIS,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=EIS 1900 Aerojet,OU=Legacy DLs,OU=Distribution Lists,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=CDCR-SOMS-M,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=Internet-Filtered,OU=Internet,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov";
        String minGroups = "| CN=App-CDCR-OpAdHoc-BIPUBADM,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=App-CDCR-SOMSOpAdHoc-OAHC,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=App-CDCR-SOMSBI-BIHC,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=App-CDCR-SOMSBI-BIPU,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=APP-CDCR-SOMSeOMIS-CAnoSD,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=APP-CDCR-SOMSeOMIS-CAALL,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | ";
        List<SOMSApplication> appList = applicationHandler.getEntitledApps(minGroups);


        assertNotEquals(0, appList.size());

        //Test class property, and its value
        SOMSApplication eOMISApp = new SOMSApplication();
        eOMISApp.setCaption("eOMIS");

        SOMSApplication opadhoc = new SOMSApplication();
        opadhoc.setCaption("OpAdHoc");

        SOMSApplication biPub = new SOMSApplication();
        biPub.setCaption("BIPUB");

        SOMSApplication bi = new SOMSApplication();
        bi.setCaption("BI");

        assertThat(appList, containsInAnyOrder(
                eOMISApp,
                opadhoc,
                bi,
                biPub
        ));

    }

    @Test
    public void testHCAHeaders() throws ServletException, IOException {

//        String minGroups = "CN=dl_CDCR-Office365-Phase1,OU=Distribution Lists,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-Veritas-Journaling,OU=O365,OU=Apps,OU=Security,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=DL-CDCR-SOMS-eOMIS-Users,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=DL-CDCR SOMS HP Staff,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=APP-CDCR-SOMSeOMIS-CAViewEnhanced,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=APP-CDCR-SOMSeOMIS-CAViewBasic,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-CDCRGeneral-FileAccess-IT,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-VPN-SOMS,OU=VPN,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMSeOMIS-CAParole,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-DataConversion,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-SCMTest,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-SCMTest-Release-1,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMSeOMIS-CAInmSupv,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-Interfaces,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Role-SCM-Developer,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-MEE-Laptop Users EIS,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=EIS 1900 Aerojet,OU=Legacy DLs,OU=Distribution Lists,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=CDCR-SOMS-M,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=Internet-Filtered,OU=Internet,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov";
        String minGroups = "CN=App-CDCR-SOMSeOMIS-CAHSCareCpt,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov";
        List<SOMSApplication> appList = applicationHandler.getEntitledApps(minGroups);

        assertNotEquals(0, appList.size());

        //Test class property, and its value
        SOMSApplication HCAApp = new SOMSApplication();
        HCAApp.setCaption("AQR");

        assertThat(appList, hasItem(
                HCAApp
        ));

    }

    @Test
    public void testTwoHeaders() throws ServletException, IOException {

//        String minGroups = "CN=dl_CDCR-Office365-Phase1,OU=Distribution Lists,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-Veritas-Journaling,OU=O365,OU=Apps,OU=Security,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=DL-CDCR-SOMS-eOMIS-Users,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=DL-CDCR SOMS HP Staff,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=APP-CDCR-SOMSeOMIS-CAViewEnhanced,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=APP-CDCR-SOMSeOMIS-CAViewBasic,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-CDCRGeneral-FileAccess-IT,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-VPN-SOMS,OU=VPN,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMSeOMIS-CAParole,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-DataConversion,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-SCMTest,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-SCMTest-Release-1,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMSeOMIS-CAInmSupv,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Project-SCM-Interfaces,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMS-Role-SCM-Developer,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-MEE-Laptop Users EIS,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=EIS 1900 Aerojet,OU=Legacy DLs,OU=Distribution Lists,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=CDCR-SOMS-M,OU=EIS,OU=Units,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=Internet-Filtered,OU=Internet,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov";
        String minGroups = "| CN=App-CDCR-SOMSeOMIS-CAHSCareCpt,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=App-CDCR-SOMSLEADS-LEADS,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov |";
        List<SOMSApplication> appList = applicationHandler.getEntitledApps(minGroups);

        assertNotEquals(0, appList.size());

        SOMSApplication HCAApp = new SOMSApplication();
        HCAApp.setCaption("AQR");

        SOMSApplication leadsApp = new SOMSApplication();
        leadsApp.setCaption("LEADS");

        assertThat(appList, hasItems(
                HCAApp,
                leadsApp
        ));

    }

    @Test
    public void testRedirectServlet() throws Exception {

    }
}
