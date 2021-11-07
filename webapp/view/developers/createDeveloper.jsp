<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>Create developer</title>
    </head>
    <body>
    <div class="mainDiv">
        <div>
            <c:import url="/view/header.jsp" />
        </div>
        <div>
            <div class="form">
                <form action="/developers" accept-charset="utf-8" method="post" ></br>
                    <div class="title">Create developer</div>
                    <div class="subtitle">Let's create the developer!</div>
                    <div class="input-container ic1">
                        <input id="email" class="input" type="text" name="email" placeholder=" " />
                        <div class="cut"></div>
                        <label for="email" class="placeholder">Developer's email</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="first name" class="input" type="text" name="first name" placeholder=" " />
                        <div class="cut"></div>
                        <label for="first name" class="placeholder">First name</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="last name" class="input" type="text" name="last name" placeholder=" " />
                        <div class="cut"></div>
                        <label for="last name" class="placeholder">Last name</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="gender" class="input" type="text" name="gender" placeholder=" " />
                        <div class="cut"></div>
                        <label for="gender" class="placeholder">Gender</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="age" class="input" type="number" name="age" placeholder=" " />
                        <div class="cut"></div>
                        <label for="age" class="placeholder">Age</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="experience" class="input" type="number" name="experience" placeholder=" " />
                        <div class="cut"></div>
                        <label for="experience" class="placeholder">Experience</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="salary" class="input" type="number" name="salary" placeholder=" " />
                        <div class="cut"></div>
                        <label for="salary" class="placeholder">Salary</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="projects" class="input" type="text" name="projects" placeholder=" " />
                        <div class="cut"></div>
                        <label for="projects" class="placeholder">Enter project Ids, separate by comma</label>
                    </div>
                    <div class="input-container ic2">
                        <input id="company" class="input" type="text" name="company" placeholder=" " />
                        <div class="cut"></div>
                        <label for="company" class="placeholder">Enter company Id</label>
                    </div>
                    <div class="input-container ic2">
                        <select id="stack" class="input" name="stack">
                            <option value="JAVA">Java</option>
                            <option value="CPLUS">C++</option>
                            <option value="CSHARP">C#</option>
                            <option value="JS">JS</option>
                        </select>
                        <div class="cut"></div>
                    </div>
                    <div class="input-container ic2">
                        <select id="level" class="input" name="level">
                            <option value="JUNIOR">Junior</option>
                            <option value="MIDDLE">Middle</option>
                            <option value="SENIOR">Senior</option>
                        </select>
                        <div class="cut"></div>
                    </div>
                    <button type="submit" class="submit">SUBMIT</button>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>