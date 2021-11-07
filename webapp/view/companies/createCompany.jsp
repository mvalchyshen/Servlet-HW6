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
                <form action="/companies" accept-charset="utf-8" method="post" ></br>
                    <div class="title">Create company</div>
                    <div class="subtitle">Let's create the company!</div>
                    <div class="input-container ic1">
                        <input id="company name" class="input" type="text" name="company name" placeholder=" " />
                        <div class="cut"></div>
                        <label for="company name" class="placeholder">Company name</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="number of developers" class="input" type="number" name="number of developers" placeholder=" " />
                        <div class="cut"></div>
                        <label for="number of developers" class="placeholder">Number of developers</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="projects" class="input" type="text" name="projects" placeholder=" " />
                        <div class="cut"></div>
                        <label for="projects" class="placeholder">Enter project Ids, separate by comma</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="customers" class="input" type="text" name="customers" placeholder=" " />
                        <div class="cut"></div>
                        <label for="customers" class="placeholder">Enter customers Ids, separate by comma</label>
                    </div>
                    <button type="submit" class="submit">SUBMIT</button>
                </form>
                </div>
            </div>
        </div>
    </body>
</html>