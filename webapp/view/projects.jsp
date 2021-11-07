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
              <a href="/projects/createProject">CREATE PROJECT</a>
              <a href="/projects/enterName">FIND PROJECT</a>
              <a href="/projects/enterNameForDelete">DELETE PROJECT</a>
              <a href="/projects/list">FIND ALL PROJECTS</a>
              <div class="dot"></div>
        </nav>
    </body>
</html>