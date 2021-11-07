<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="/view/css/style.css"%></style>
    <title>Companies</title>
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
                            <td>Company Name</td>
                            <td>Number of developers</td>
                            <td></td>
                        </tr>
                    </thead>
                    </table>
                </div>
                <div class="tbl-content">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tbody>
                        <c:forEach var="company" items="${companies}">
                         <tr>
                             <td>${company.companyId}</td>
                             <td>${company.companyName}</td>
                             <td>${company.numberOfDevelopers}</td>
                             <td><a href="/companies/update?name=${company.companyName}">
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