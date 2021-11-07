<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="/view/css/style.css"%></style>
    <title>Create company</title>
</head>
<body>
<div class="mainDiv">
    <div>
        <c:import url="/view/header.jsp" />
    </div>
    <div class="shortForm">
        <form action="company" accept-charset="utf-8" method="get" >
            <div class="title">Enter company name</div>
            <div class="subtitle">Enter the name of the searched company!</div>
            <div class="oneInput-container ic1">
                <input id="name" class="oneInput" type="text" name="name" placeholder=" " />
                <div class="oneCut"></div>
                <label for="name" class="onePlaceholder">Type name</label>
            </div>
            <button type="submit" class="submit">SUBMIT</button>
        </form>
        </div>
    </div>
    </body>
</html>