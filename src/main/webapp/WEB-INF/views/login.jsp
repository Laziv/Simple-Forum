<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container my-3">
    <div class="row">
        <div class="col-9">
            <h2 class="h4 text-white bg-info mb-3 p-4 rounded">Log in</h2>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    Invalid username or password.
                </div>
            </c:if>

            <form class="mb-3" action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Log in</button>

            </form>
            <div><a href="${pageContext.request.contextPath}/registration">Sign up</a></div>
        </div>
    </div>
</div>
</body>
</html>
