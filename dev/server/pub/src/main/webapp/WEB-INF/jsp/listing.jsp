<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/css/jumbotron-narrow.css">
    <link rel="stylesheet" type="text/css" href="/css/home.css">
    <link rel="stylesheet" type="text/css" href="/css/jquery.growl.css"/>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/js/jquery.growl.js" type="text/javascript"></script>
</head>
<body>
<form action="http://localhost:8080/portal/mng" method="POST" />
<table>
<tr>
<th>Header</th>
<th>Update Availability</th>
</tr>
<tr>
<%
String[] journalsList = (String[]) request.getSession().getAttribute("journalsList");
for (int i = 0; i < journalsList.length; i++) {
%>
   <td>$<%= journalsList[i]%></td>
   <td>
      <input type="checkbox" name="available" value="available<%=i%>" />
   </td>
<% } %>
</tr>
<tr>
   <td colspan="2"><input type="submit" value="Submit Changes" /></td>
</tr>
</table>
</form>
</body>
</html>
