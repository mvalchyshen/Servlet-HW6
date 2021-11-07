<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>Create project</title>
    </head>
    <body>
        <div class="mainDiv">
            <div>
                <c:import url="/view/header.jsp" />
            </div>
            <div>
                <div class="form">
                    <form action="/projects" accept-charset="utf-8" method="post" >
                      <div class="title">Create project</div>
                      <div class="subtitle">Let's create your project!</div>
                      <div class="input-container ic1">
                        <input id="name" class="input" type="text" name="name" placeholder=" " />
                        <div class="cut"></div>
                        <label for="name" class="placeholder">Project name</label>
                      </div>
                      <div class="input-container ic2">
                        <input id="stage" class="input" type="text" name="stage" placeholder=" " />
                        <div class="cut"></div>
                        <label for="stage" class="placeholder">Stage</label>
                      </div>
                      <div class="input-container ic2">
                        <input id="period" class="input" type="number" name="period" placeholder=" " />
                        <div class="cut"></div>
                          <label for="period" class="placeholder">Period</label>
                      </div>
                      <div class="input-container ic2">
                          <input id="coast" class="input" type="number" name="coast" placeholder=" " />
                          <div class="cut"></div>
                          <label for="coast" class="placeholder">Coast</label>
                      </div>
                      <div class="input-container ic2">
                            <input id="number of developers" class="input" type="number" name="number of developers" placeholder=" " />
                            <div class="cut"></div>
                            <label for="number of developers" class="placeholder">Number of developers</label>
                      </div>
                      <div class="input-container ic2">
                            <input id="date" class="input" type="date" name="start date" placeholder=" " />
                            <div class="cut"></div>
                            <label for="date" class="placeholder">Start date</label>
                      </div>
                      <div class="input-container ic2">
                            <input id="developers" class="input" type="text" name="developers" placeholder=" " />
                            <div class="cut"></div>
                            <label for="developers" class="placeholder">enter developer Ids, separate by comma</label>
                      </div>
                      <div class="input-container ic2">
                            <input id="companies" class="input" type="text" name="companies" placeholder=" " />
                            <div class="cut"></div>
                            <label for="companies" class="placeholder">enter company Ids, separate by comma</label>
                      </div>
                      <div class="input-container ic2">
                            <input id="customers" class="input" type="text" name="customers" placeholder=" " />
                            <div class="cut"></div>
                            <label for="customers" class="placeholder">enter customer Ids, separate by comma</label>
                      </div>
                      <button type="submit" class="submit">SUBMIT</button>
                     </form>
                </div>
            </div>
        </div>
    </body>
</html>