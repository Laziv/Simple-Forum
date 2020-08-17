<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container my-3">
    <div class="row">
        <div class="col-9">
            <h2 class="h4 text-white bg-info mb-3 p-4 rounded">Sign up</h2>
            <springForm:form class="mb-3" method="post" modelAttribute="user">
                <div class="form-group">
                    <label for="username">Username</label>
                    <springForm:input  path="username" class="form-control" cssErrorClass="form-control is-invalid"/>
                    <springForm:errors path="username" cssClass="has-error"/>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <springForm:input  path="email" class="form-control" cssErrorClass="form-control is-invalid"/>
                    <springForm:errors path="email" cssClass="has-error"/>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <springForm:input type="password"  path="password" class="form-control" cssErrorClass="form-control is-invalid"/>
                    <springForm:errors path="password" cssClass="has-error"/>
                </div>

                 <div class="form-group">
                    <label for="password">Password Confirm</label>
                    <springForm:input type="password"  path="passwordConfirm" class="form-control" cssErrorClass="form-control is-invalid"/>
                    <springForm:errors path="passwordConfirm" cssClass="has-error"/>
                </div>

                <div class="form-group">
                    <label for="birthday">Birthday</label>
                    <springForm:input  path="birthday" type="date" class="form-control" cssErrorClass="form-control is-invalid"/>
                    <springForm:errors path="birthday" cssClass="has-error"/>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Sign up</button>
            </springForm:form>
            <div><a href="${pageContext.request.contextPath}/login">Log in</a></div>
        </div>
    </div>
</div>
</body>
</html>
