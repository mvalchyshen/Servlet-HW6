<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>YOUR SELECTED DEVELOPER IS</title>
    </head>
    <body>
        <c:set var="developer" value="${developer}" />
        <div class="mainDiv">
            <div>
                <c:import url="/view/header.jsp" />
            </div>
            <div class="tableDiv">
                <div class="tbl-header">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <thead>
                            <tr>
                                <td>First Name</td>
                                <td>Last Name</td>
                                <td>Gender</td>
                                <td>Age</td>
                                <td>Experience</td>
                                <td>Salary</td>
                                <td>Email</td>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="tbl-content">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tbody>
                             <tr>
                                 <td>${developer.firstName}</td>
                                 <td>${developer.lastName}</td>
                                 <td>${developer.gender}</td>
                                 <td>${developer.age}</td>
                                 <td>${developer.experienceInYears}</td>
                                 <td>${developer.salary}</td>
                                 <td>${developer.developerEmail}</td>
                             </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
