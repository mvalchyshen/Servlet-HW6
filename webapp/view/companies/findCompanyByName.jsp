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
        <c:set var="company" value="${company}" />
        <div class="bigTableDiv">
            <div class="tbl-header">
                <table cellpadding="0" cellspacing="0" border="0">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Company Name</td>
                            <td>Number of developers</td>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="tbl-content">
                <table cellpadding="0" cellspacing="0" border="0"><tbody>
                     <tr>
                         <td>${company.companyId}</td>
                         <td>${company.companyName}</td>
                         <td>${company.numberOfDevelopers}</td>
                     </tr>
                </tbody>
                </table>
            </div>
        </div>
    </div>
    </body>
</html>
