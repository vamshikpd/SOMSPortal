<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
    <style type="text/css">
        a:hover {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 10pt;
            color: #990000;
            text-decoration: underline
        }

        a:link {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 10pt;
            color: #000066;
            text-decoration: none
        }

        a:visited {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 10pt;
            color: #31654A;
            text-decoration: none
        }
    </style>

    <script type="text/javascript">
        function delCookie(name, path, domain) {
            var today = new Date();
            var deleteDate = new Date(today.getTime() - 48 * 60 * 60 * 1000); // minus 2 days
            var cookie = name + "="
                + ((path === null) ? "" : "; path=" + path)
                + ((domain === null) ? "" : "; domain=" + domain)
                + "; expires=" + deleteDate;
            document.cookie = cookie;
            //confirm("delete: "+ name + " -- " + path + " -- " + domain);
        }

        function delOblixCookie() {
            // set focus to ok button
            var isNetscape = (document.layers);
            if (isNetscape === false || navigator.appVersion.charAt(0) >= 5) {
                for (var i = 0; i < document.links.length; i++) {
                    if (document.links[i].href === "javascript:top.close()") {
                        document.links[i].focus();
                        break;
                    }
                }
            }

            // in case cookieDomain is configured
            // delete same cookie to all of subdomain
            var subdomain;
            var domain = String(document.domain);
            var index = domain.indexOf(".");

            var cookieList = ['MRHSession', 'LastMRH_Session', 'F5_ST', 'BIGipServerELB-Portal-App-pool', 'JSESSIONID'];
            for (var j = 0; j < cookieList.length; ++j) {
                delCookie(cookieList[j], '/', domain);
            }

            while (index > 0) {
                subdomain = domain.substring(index, domain.length);
                if (subdomain.indexOf(".", 1) > 0) {
                    for (var k = 0; k < cookieList.length; ++k) {
                        delCookie(cookieList[k], '/', domain);
                    }
                }
                domain = subdomain;
                index = domain.indexOf(".", 1);
            }
        }
    </script>
</HEAD>

<BODY onload="delOblixCookie();">
<TABLE cellpadding="0" cellspacing="0" width="850" class="pageTable">
    <!-- Row 1 - Banner -->
    <TR>
        <!-- This row spans all columns in the table -->
        <TD COLSPAN="4" height="80" width="100%" align="center" valign="bottom">
            <a href="<c:url value="/" />"><h4>[ Login ]</h4></a>
        </td>
        </TD>
    </TR>

    <!-- Row 2, Column 1  - Left Navigation Links and JSP specific info -->
    <TR height="200">
        <!-- Row 2, Column 1 - displays the Navigation links -->
        <TD align="LEFT" valign="top" height="200" width="10%">
            <!-- BT FIX: seem to need these spaces so Nav bar stays visible -->
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </TD>

        <!-- Row 2, Column 2 - MAIN Content AREA -->
        <TD valign="top" align="center" width="75%">
            <br> <br>
            <h3> You have logged off the SOMS Launch Pad. </h3>
            <h3> Please click the link above to login again. </h3>
        </TD>

        <TD width="5%">
        </TD>
    </TR>
    <!-- End Row 2 - Left Navigation Links and JSP specific info -->

</TABLE>
<!-- End Main Table -->

</BODY>
</HTML>


