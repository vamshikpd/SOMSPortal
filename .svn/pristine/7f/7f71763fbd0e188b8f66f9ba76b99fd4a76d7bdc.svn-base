package gov.ca.cdcr.somsportal;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static gov.ca.cdcr.somsportal.SOMSPortalUtils.extractAppSpecificGroups;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HCARedirectTest {

    private SOMSApplicationHandler applicationHandler;
    private SOMSApplication hcaApp;

    private String propFileName = "testProps.properties";

    @Before
    public void setUp() throws Exception {

        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(servletContext.getResourceAsStream(any())).thenReturn(this.getClass().getClassLoader().getResourceAsStream(propFileName));


        applicationHandler = new SOMSApplicationHandler(servletContext, propFileName);

        hcaApp = applicationHandler.getAppList().get("AQR");
    }

    @Test
    public void testNoHCAGroupsExist() throws ServletException, IOException {

        String groupslist = extractAppSpecificGroups("", hcaApp.getEntitledGroups());
        assertEquals(0, groupslist.length());

        String oneHCAGroup = "CN=App-CDCR-SOMSeOMIS-CAHSCareCpt,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov";
        groupslist = extractAppSpecificGroups(oneHCAGroup, hcaApp.getEntitledGroups());
        assertNotEquals(0, groupslist.length());
        assertEquals(oneHCAGroup, groupslist);

        String twoHCAGroups = "| CN=App-CDCR-SOMSeOMIS-CAHSCareCpt,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=App-CDCR-SOMSeOMIS-CASysAdm,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov |";
        groupslist = extractAppSpecificGroups(twoHCAGroups, hcaApp.getEntitledGroups());
        assertEquals("CN=App-CDCR-SOMSeOMIS-CAHSCareCpt,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov:CN=App-CDCR-SOMSeOMIS-CASysAdm,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov", groupslist);

        String noHCAGroups = "|CN=App-CDCR-SOMSeOMIS-RptAdmin,OU=Groups,OU=eOMIS,OU=SOMS,DC=soms,DC=temp,DC=ca,DC=gov | CN=App-CDCR-SOMSeOMIS-RADtool7,OU=Groups,OU=eOMIS,OU=SOMS,DC=soms,DC=temp,DC=ca,DC=gov";
        groupslist = extractAppSpecificGroups(noHCAGroups, hcaApp.getEntitledGroups());
        assertEquals(0, groupslist.length());

        String mixedGroups = "|CN=App-CDCR-SOMSeOMIS-RptAdmin,OU=Groups,OU=eOMIS,OU=SOMS,DC=soms,DC=temp,DC=ca,DC=gov | CN=App-CDCR-SOMSeOMIS-CASysAdm,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov | CN=App-CDCR-SOMSeOMIS-RADtool7,OU=Groups,OU=eOMIS,OU=SOMS,DC=soms,DC=temp,DC=ca,DC=gov";
        groupslist = extractAppSpecificGroups(mixedGroups, hcaApp.getEntitledGroups());
        assertEquals("CN=App-CDCR-SOMSeOMIS-CASysAdm,OU=SOMS,OU=Apps,OU=CDCR,DC=accounts,DC=cdcr,DC=ca,DC=gov", groupslist);

    }
}
