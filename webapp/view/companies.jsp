<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
    </head>
    <body>
        <c:import url="/view/header.jsp" />
        <nav class="navMenuSeparatePage">
              <a href="/">HOME</a>
              <a href="/companies/createCompany">CREATE COMPANY</a>
              <a href="/companies/enterName">FIND COMPANY</a>
              <a href="/companies/enterIdForDelete">DELETE COMPANY</a>
              <a href="/companies/list">FIND ALL COMPANIES</a>
              <div class="dot"></div>
        </nav>
    </body>
</html>