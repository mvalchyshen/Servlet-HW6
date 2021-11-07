<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="/view/css/style.css"%></style>
    <title>THE SELECTED PROJECT IS DELETED</title>
</head>
<body>
<div class="mainDiv">
    <div>
        <c:import url="/view/header.jsp" />
    </div>
    <div class="textDiv">
        <c:set var="id" value="${id}" />
        <a> The company ${id} is deleted</a>
    </div>
</div>
</body>
</html>