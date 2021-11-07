<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>YOUR SELECTED PROJECT IS</title>
    </head>
    <body>
        <c:set var="project" value="${project}" />
        <div class="mainDiv">
            <div>
                <c:import url="/view/header.jsp" />
            </div>
            <div class="tableDiv">
                <div class="tbl-header">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <thead>
                            <tr>
                                <th>Project Name</th>
                                <th>Date Of Beginning</th>
                                <th>Stage</th>
                                <th>Coast</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="tbl-content">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tbody>
                             <tr>
                                 <td>${project.projectName}</td>
                                 <td>${project.dateOfBeginning}</td>
                                 <td>${project.stage}</td>
                                 <td>${project.stage}</td>
                             </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
