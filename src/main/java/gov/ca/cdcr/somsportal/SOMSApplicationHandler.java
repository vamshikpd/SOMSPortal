package gov.ca.cdcr.somsportal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.util.Arrays.asList;

public class SOMSApplicationHandler {

    private Map<String, SOMSApplication> appList;
    private static final int COMMON_CHARS_IN_GROUP = 16;
    private ServletContext servletContext;
    private String propertiesFileName;


    public SOMSApplicationHandler(ServletContext servletContext, String propertiesFileName) {
        this.servletContext = servletContext;
        this.propertiesFileName = propertiesFileName;
        this.appList = loadApplications();
    }

    public List<SOMSApplication> getEntitledApps(String groups) {

//        System.out.println(appList);
//        System.out.println("");

        Set<SOMSApplication> allowedApps = new HashSet<>();

        // ====================================================================================================
        // Parse the groups String looking for delimiters between groups, etc.  For each group, add the APP
        // associated with that group into the appsAllowed[] table (1 is allowed, 0 is not) for later.
        // ====================================================================================================
        StringTokenizer tokens = new StringTokenizer(groups,"|");
        while(tokens.hasMoreTokens())
        {
            String token = tokens.nextToken().trim();

            // if this is a CN element
            if (token.indexOf("CN",0) > -1 )
            {
//                System.out.println("LINE=" + token);
                int cnIndex = token.indexOf("CN=");
                if (cnIndex > -1){
                    int cnTokenLen = token.indexOf(",", cnIndex) - cnIndex;
                    token = token.substring(cnIndex, cnTokenLen);
                }


                System.out.println("TOKEN=" + token);

                //Add AQR (CR 503 )app logic to check the CRUD groups for access
                //Per CR all users with the following groups will have access to AQR app
                //App-CDCR-SOMSeOMIS-CACorrOff
                //App-CDCR-SOMSeOMIS-CACorrSgt
                //App-CDCR-SOMSeOMIS-CACorrLt
                //App-CDCR-SOMSeOMIS-CAHSCareCpt
                //App-CDCR-SOMSeOMIS-CAHSRunRpts
                //App-CDCR-SOMSeOMIS-CAHCRunReports
                //App-CDCR-SOMSeOMIS-CASysAdm
                //App-CDCR-SOMSeOMIS-CAInstMgmt

                // NOTE! The first COMMONCHARSINGROUP chars of the SOMS group
                // are consistent, So, use name from the AD Group itself to tell us.
                // IE:  CN=App-CDCR-SOMSeOMIS-CASysAdm
                //      Get 'eOMIS' from the above AD Group, THEN Compare to hashGroupApp
                if (token.length() > COMMON_CHARS_IN_GROUP) {
                    // NOTE! At this point, token should contain something like this:
                    //       eOMIS-CASysAdm
                    token=token.substring(COMMON_CHARS_IN_GROUP, token.length());
                }

                System.out.println("::token=" + token );
                // Check Captiva group:
                // App-CDCR-SOMSERMS-ScanOperators-Training, App-CDCR-SOMSERMS-eScan-Operators
                //Code Added by rajesh for new BI publisher Roles : for all the four roles its mapped to same url and icon as hashGroupAppBIPUB1


                String subToken = "";
                if (token.contains("-")) {
                    // NOTE! At this point, subToken should contain something like this:
                    //       eOMIS
                    subToken = token.substring(0, token.indexOf("-"));
                }


                System.out.println("SubTOKEN=" + subToken);
                System.out.println("");

                for (Map.Entry<String, SOMSApplication> appEntry : appList.entrySet()) {
                    boolean appAllowed = false;

                    SOMSApplication app = appEntry.getValue();

                    // shortcut to check whether app has correct URL
                    if (app.getUrl().length() < 2) {
                        continue;
                    }

//                    System.out.println("     APP: " + app.getCaption());
//                    System.out.println("          ent: " + app.getEntitledGroups());

                    List<String> entitlementGroups = app.getEntitledGroups();
                    if (entitlementGroups != null && entitlementGroups.size() > 0) {
                        for (String entitlement : entitlementGroups) {
                            if (token.equalsIgnoreCase(entitlement)) {
                                appAllowed = true;
                            }
                        }
                    } else {
                        if (subToken.equalsIgnoreCase(app.getCaption())) {
                            appAllowed = true;
                        }
                    }

                    if (appAllowed) {
                        allowedApps.add(app);
                    }
                }
            }
        }

        List<SOMSApplication> allowedAppsList =  new ArrayList<>(allowedApps);
        Collections.sort(allowedAppsList);

        return allowedAppsList;
    }

    /**
     * Reads the list of SOMS applications from property file and returns a map.
     * @return Map: map of SOMSApplication instances keyed by "token" (CN group name)
     */
    private Map<String, SOMSApplication> loadApplications() {
        Map<String, SOMSApplication> appList = new HashMap<>();

        Properties prop = loadPropertiesFile(propertiesFileName);
//        Properties prop = loadPropertiesFile(propertiesInputStream);

        /*Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            System.out.println("Key : " + key + ", Value : " + value);
        }*/

        // get the property value and print it out
        int appCount = Integer.parseInt(prop.getProperty("apps_count"));
        for (int i = 1; i <= appCount; ++i) {
            SOMSApplication app = new SOMSApplication();
            String keyPrefix = "app" + i + ".";
            String token = prop.getProperty(keyPrefix + "token").trim();
            String strEntitlements = prop.getProperty("app" + i + ".entitlements");

            app.setCaption(token);

            if (strEntitlements != null && !strEntitlements.trim().equals("")) {
                List<String> entitlements = asList(strEntitlements.trim().split(","));
                app.setEntitledGroups(entitlements);
            }

            app.setImage(prop.getProperty(keyPrefix + "image"));
            app.setUrl(prop.getProperty(keyPrefix + "url"));
            app.setIndex(i);

            appList.put(token, app);
//            System.out.println(app);

        }

        return appList;


    }

    /**
     * @param filename property file name
     * @return property objects
     */
    private Properties loadPropertiesFile(String filename) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = servletContext.getResourceAsStream(filename);

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

    public Map<String, SOMSApplication> getAppList() {
        return appList;
    }
}
