<%--@elvariable id="firstName" type="String"--%>
<%--@elvariable id="lastName" type="String"--%>
<%--@elvariable id="fullname" type="String"--%>
<%--@elvariable id="desc" type="String"--%>
<%--@elvariable id="email" type="String"--%>
<%--@elvariable id="userId" type="String"--%>
<%--@elvariable id="ipaddress" type="String"--%>
<%--@elvariable id="entitledApps" type="java.util.List<gov.ca.cdcr.somsportal.SOMSApplication>"--%>
<%--@elvariable id="totalAppsAllowed" type="Integer"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript">
        function completeLogoff(link) {
            var r = confirm("Are you sure you want to logoff the SOMS Launch Pad?");
            if (r === true) {
                //document.form.action = link;
                //document.form.submit();
                document.location = link;
            }
        }

        function submitleadsform() {
            document.forms["leadsform"].submit();
        }
    </script>
    <link rel="stylesheet" href="<c:url value="/static/main.css" />"/>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="850" class="pageTable">

    <!-- Row 1 - Banner -->

    <tr>
        <!-- This row spans all columns in the table -->
        <td colspan="4" height="100" width="100%"
            background="<c:url value="/static/images/bg-green-body.png" />">
            <table>
                <tr>
                    <td width="20%" align="left">
                        <a>
                            <!--<img src="images/soms_logo.jpg" />-->
                            <img src="<c:url value="/static/images/soms_logo.jpg" />" alt="SOMS Logo"/>
                        </a>
                    </td>
                    <td width="77%" align="right" valign="top">
                        <h3 class="largetitle">
                            Logged in as:
                            ${firstName}
                            ${lastName}
                        </h3>

                        <br/><br/>

                        <a class="largetitle" href="javascript:completeLogoff('<c:url value="/logout" />')">
                            [Logoff ]
                        </a>
                    </td>
                    <td width="3%"></td>
                </tr>
            </table>
        </td>
    </tr>

    <!-- Row 2, Column 1  - Left Navigation Links and JSP specific info -->

    <tr height="400">
        <!-- Row 2, Column 1 - displays the Navigation links -->
        <td align="LEFT" valign="top" height="700" width="15%"
            background="<c:url value="/static/images/bg-green-body-vertical.png" />">
            <!-- BT FIX: seem to need these spaces so Nav bar stays visible -->
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <!-- Row 2, Column 2 - Empty column to allow space between
         links and JSP info -->
        <td width="5%">
            <!-- zzz2  -->
        </td>
        <!-- Row 2, Column 3 - MAIN Content AREA -->
        <td valign="top" width="75%">
            <table>
                <tr></tr>
                <tr>
                    <td width="200"></td>
                    <td align="center">
                        <h1 class="largetitle">Welcome to SOMS Portal</h1>
                    </td>
                    <td width="200"></td>
                </tr>

                <!--start legal disclaimer-->

                <tr>
                    <td style="TEXT-ALIGN: justify; PADDING-BOTTOM: 10px; FONT-FAMILY: Arial,sans-serif; FONT-SIZE: 12px"
                        colspan="3" align="center">
                        Access to SOMS and all activities
                        conducted within the system are recorded.
                        The content of the system is private and
                        privileged information. Use of the system
                        and the information contained within it
                        are to be used strictly for the
                        fulfillment of job responsibilities and
                        shall not be discussed or shared with
                        other persons except as is necessary for
                        professional reasons.
                    </td>
                </tr>

                <!-- END legal disclaimer -->

                <tr>
                    <td width="100"></td>
                    <td width="400" align="center">

                        <c:choose>
                            <c:when test="${totalAppsAllowed != 0}">
                                <c:set var="s1" scope="page"
                                       value="Below are the applications you have entitlements to:"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="s1" scope="page"
                                       value="You currently do not have any SOMS application entitlement. Please contact your manager if you have any questions."/>
                            </c:otherwise>
                        </c:choose>

                        <h3 class="sectiontitle">
                            ${s1}
                        </h3>
                    </td>
                    <td width="100"></td>
                </tr>
                <tr>
                    <td align="center" colspan="3">

                        <c:forEach items="${entitledApps}" var="app" varStatus="count">

                            <c:set var="className" scope="page" value="${count.index % 3 == 0 ? \"first\" : \"\"}" />

                            <div class="app-icon ${className}">
                                <c:choose>
                                    <c:when test="${app.caption == 'LEADS'}" >

                                        <form id="leadsform" method="post" action="${app.url}" target="_blank">
                                            <input type="hidden" name="FIRSTNM" value="${firstName}"/>
                                            <input type="hidden" name="LASTNM" value="${lastName}"/>
                                            <input type="hidden" name="FULLNM" value="${fullname}"/>
                                            <input type="hidden" name="DESC" value="${desc}"/>
                                            <input type="hidden" name="MAIL" value="${email}"/>
                                            <input type="hidden" name="USERID" value="${userId}"/>
                                            <input type="hidden" name="IPADDRESS" value="${ipaddress}"/>
                                        </form>
                                        <a href="javascript:submitleadsform()" title="${app.caption}">
                                            <img src="<c:url value="/static/images/${app.image}" />" alt="${app.caption}"
                                                 class="soms-app-icon"/>
                                        </a>
                                    </c:when>
                                    <c:when test="${app.caption == 'AQR'}" >
                                        <a href="hca-redirect" title="${app.caption}" target="_blank">
                                            <img src="<c:url value="/static/images/${app.image}" />" alt="${app.caption}"
                                                 class="soms-app-icon"/>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${app.url}" title="${app.caption}" target="_blank">
                                            <img src="<c:url value="/static/images/${app.image}" />" alt="${app.caption}"
                                                 class="soms-app-icon"/>
                                        </a>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </c:forEach>

                    </td>
                </tr>

            </table>
        </td>
        <td width="5%">
            <!-- zzz2  -->
        </td>
        <!-- End Client Information box -->
        <!-- End JSP Specific items -->
    </tr>

    <!-- End Row 2 - Left Navigation Links and JSP specific info -->
</table>

</body>
</html>

