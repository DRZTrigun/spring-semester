<%@ page import="geek.persist.persistUser.User" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<table>
    <tr>
        <th>Id</th>
        <th>Username</th>
    </tr>
    <% for (User user : (List<User>) request.getAttribute("users")) { %>
    <tr>
        <td><%= user.getId() %></td>
        <td>
            <a href="<%= application.getContextPath() + "/user/" + user.getId() %>"><%= user.getUsername() %></a>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>