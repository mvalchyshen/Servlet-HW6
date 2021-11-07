<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>Update project</title>
    </head>
    <body>
        <div class="mainDiv">
            <div>
                <c:import url="/view/header.jsp" />
            </div>
            <div>
                <div class="form">
                    <form action="/projects" accept-charset="utf-8" method="post" >
                        <input type="hidden" name="id" value='${project.projectId}' />
                          <div class="title">Update project</div>
                          <div class="subtitle">Let's update your project!</div>
                          <div class="input-container ic1">
                            <input id="name" class="input" type="text" name="name" value="<c:out value='${project.projectName}' />" />
                            <div class="cut"></div>
                            <label for="name" class="placeholder">Project name</label>
                          </div>
                          <div class="input-container ic2">
                            <input id="stage" class="input" type="text" name="stage" value="<c:out value='${project.stage}' />" />
                            <div class="cut"></div>
                            <label for="stage" class="placeholder">Stage</label>
                          </div>
                          <div class="input-container ic2">
                            <input id="period" class="input" type="number" name="period" value="<c:out value='${project.timePeriod}' />" />
                            <div class="cut cut-short"></div>
                              <label for="period" class="placeholder">Period</label>
                          </div>
                          <div class="input-container ic2">
                              <input id="coast" class="input" type="number" name="coast" value="<c:out value='${project.coast}' />" />
                              <div class="cut cut-short"></div>
                              <label for="coast" class="placeholder">Coast</label>
                          </div>
                          <div class="input-container ic2">
                                <input id="number of developers" class="input" type="number" name="number of developers" value="<c:out value='${project.numberOfDevelopers}' />" />
                                <div class="cut cut-short"></div>
                              <label for="number of developers" class="placeholder">Number of developers</label>
                          </div>
                          <div class="input-container ic2">
                                <input id="date" class="input" type="date" name="start date" value="<c:out value='${project.dateOfBeginning}' />" />
                                <div class="cut cut-short"></div>
                              <label for="date" class="placeholder">Start date</label>
                          </div>
                          <div class="input-container ic2">
                              <input id="developers" class="input" type="text" name="developers" value='${developersList}' />
                                <div class="cut cut-short"></div>
                              <label for="developers" class="placeholder">enter developer Ids, separate by comma</label>
                          </div>
                          <div class="input-container ic2">
                              <input id="companies" class="input" type="text" name="companies" value='${companies}' />
                                <div class="cut cut-short"></div>
                              <label for="companies" class="placeholder">enter company Ids, separate by comma</label>
                          </div>
                          <div class="input-container ic2">
                              <input id="customers" class="input" type="text" name="customers" value='${customers}' />
                                <div class="cut cut-short"></div>
                              <label for="customers" class="placeholder">enter customer Ids, separate by comma</label>
                          </div>
                          <button type="submit" class="submit">UPDATE</button>
                     </form>
                </div>
            </div>
        </div>
    </body>
</html>
