<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 8/6/19
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add new user</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <style>
            .w3-myfont {
                font-family: "Helvetica";
            }
        </style>
    </head>

    <body class="w3-light-grey w3-myfont">

        <div>
            <div class="w3-container w3-padding">
                <%
                    if (request.getAttribute("userName") != null) {
                        out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");
                    }
                %>
            </div>

            <div class="w3-container">
                <div class="w3-card-4">
                    <div class="w3-container w3-teal w3-padding">
                        <h2 class="w3-center">Add user</h2>
                    </div>
                    <form method="post" class="w3-selection w3-light-grey w3-padding">
                        <label>Name:
                            <input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                        </label>
                        <label>Password:
                            <input type="password" name="pass" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                        </label>
                        <button type="submit" class="w3-btn w3-green w3-hover-light-blue w3-round-large w3-margin-bottom">Submit</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="w3-padding">
            <button class="w3-padding w3-btn w3-green w3-hover-light-blue w3-round-large w3-margin-bottom" onclick="location.href='/'">Back to main</button>
        </div>
    </body>
</html>
