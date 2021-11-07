<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>PROJECTS</title>
    </head>
    <body>
        <div class="mainDiv">
            <div class="littleHeader">
                <c:import url="/view/header.jsp" />
            </div>
            <div class="bigTableDiv">
                <div class="tbl-header">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <thead>
                            <tr>
                                <th>Project Name</th>
                                <th>Date Of Beginning</th>
                                <th>Stage</th>
                                <th>Coast</th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="tbl-content">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tbody>
                            <c:forEach var="project" items="${projects}">
                             <tr>
                                 <td>${project.projectName}</td>
                                 <td>${project.dateOfBeginning}</td>
                                 <td>${project.stage}</td>
                                 <td>${project.stage}</td>
                                 <td> <a href="/projects/update?name=${project.projectName}">
                                        <button class="update">Update</button>
                                      </a>
                                 </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>