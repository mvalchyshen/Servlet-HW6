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
    <div>
        <div class="form">
            <form action="/companies/update" accept-charset="utf-8" method="post" ></br>
                <div class="title">Update company</div>
                <div class="subtitle">Let's update the company!</div>
                <input type="hidden" name="id" value='${company.companyId}' />
                <div class="input-container ic1">
                    <input id="company name" class="input" type="text" name="company name" value="<c:out value='${company.companyName}' />" />
                    <div class="cut"></div>
                    <label for="company name" class="placeholder">Company name</label>
                </div>
                <div class="input-container ic2">
                    <input id="number of developers" class="input" type="number" name="number of developers" value="<c:out value='${company.numberOfDevelopers}' />" />
                    <div class="cut"></div>
                    <label for="number of developers" class="placeholder">Number of developers</label>
                </div>
                <div class="input-container ic2">
                    <input id="projects" class="input" type="text" name="projects" value='${projects}' />
                    <div class="cut"></div>
                    <label for="projects" class="placeholder">Enter project Ids, separate by comma</label>
                </div>
                <div class="input-container ic2">
                    <input id="customers" class="input" type="text" name="customers" value='${customers}' />
                    <div class="cut"></div>
                    <label for="customers" class="placeholder">Enter customers Ids, separate by comma</label>
                </div>
                <button type="submit" class="submit">UPDATE</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>