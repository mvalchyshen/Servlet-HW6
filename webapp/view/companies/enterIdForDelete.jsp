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
    <div class="shortForm">
        <form action="deleteCompany" accept-charset="utf-8" method="delete" >
            <div class="title">Enter company id</div>
            <div class="subtitle">Enter the company id for delete!</div>
            <div class="oneInput-container ic1">
                <input id="id" class="oneInput" type="text" name="id" placeholder=" " />
                <div class="oneCut"></div>
                <label for="id" class="onePlaceholder">Type id</label>
            </div>
            <button type="submit" class="submit">SUBMIT</button>
        </form>
    </div>
</div>
</body>
</html><body>