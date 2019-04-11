<%@ page import="java.util.*" %>
<html>
<head>
    <title>SOMS Portal Http Request Headers</title>
</head>
<body>
    <H1>SOMS Portal - HTTP Request Information</H1>
    <BR>
    <table border="1">
        <% java.util.Enumeration names = request.getHeaderNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
        %>
        <tr>
            <td bgcolor="#CCCCCC"><%= name %>
            </td>
            <td><%= request.getHeader(name) %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <br/>

    <h2>Cookies:</h2>
    <table border="1">
        <%
            Cookie cookies[] = request.getCookies();
            Cookie myCookie;

            for (int i = 0; i < cookies.length; i++) {
                myCookie = cookies[i];
        %>
        <tr>
            <td bgcolor="#CCCCCC"><%= myCookie.getName() %></td>
        </tr>
        <tr>
            <td>
                VALUE: <%= myCookie.getValue() %> <br/>
                MAXAGE: <%= myCookie.getMaxAge() %> <br/>
                PATH: <%= myCookie.getPath() %> <br/>
                DOMAIN: <%= myCookie.getDomain() %> <br/>
                COMMENT: <%= myCookie.getComment() %> <br/>
                SECURE: <%= myCookie.getSecure() %> <br/>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <br/>

    <h2>HTTP Request Attributes:</h2>
    <TABLE border="1">
        <% names = request.getAttributeNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
        %>
        <tr>
            <td bgcolor="#CCCCCC"><%= name %></td>
            <td><%= request.getAttribute(name) %></td>
        </tr>
        <%
            }
        %>
    </TABLE>
</body>
</html>
