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
              <a href="/developers/createDeveloper">CREATE DEVELOPER</a>
              <a href="/developers/enterId">FIND DEVELOPER</a>
              <a href="/developers/enterIdForDelete">DELETE DEVELOPER</a>
              <a href="/developers/list">FIND ALL DEVELOPERS</a>
              <div class="dot"></div>
        </nav>
    </body>
</html>