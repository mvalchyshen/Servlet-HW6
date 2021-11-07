<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>Developers</title>
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
                            <td>ID</td>
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
                <table cellpadding="0" cellspacing="0" border="0"><tbody>
                    <c:forEach var="developer" items="${developers}">
                         <tr>
                             <td>${developer.developerId}</td>
                             <td>${developer.firstName}</td>
                             <td>${developer.lastName}</td>
                             <td>${developer.gender}</td>
                             <td>${developer.age}</td>
                             <td>${developer.experienceInYears}</td>
                             <td>${developer.salary}</td>
                             <td>${developer.developerEmail}</td>
                             <td> <a href="/developers/update?email=${developer.developerEmail}">
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