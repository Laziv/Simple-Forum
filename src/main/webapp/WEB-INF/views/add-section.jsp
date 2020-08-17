<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new section</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container my-3">
    <div class="row">
        <div class="col-12">
            <h2 class="h4 text-white bg-info mb-3 p-4 rounded">Create new section</h2>
            <springForm:form method="POST" modelAttribute="section" class="mb-3">
                <div class="form-group">
                    <label for="name">Name</label>
                    <springForm:input path="name" class="form-control" cssErrorClass="form-control is-invalid"/>
                    <springForm:errors path="name" cssClass="has-error"/>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <springForm:textarea path="description" class="form-control" cssErrorClass="form-control is-invalid" rows="3"/>
                    <springForm:errors path="description" cssClass="has-error"/>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Create section</button>
            </springForm:form>
        </div>
    </div>
</div>
</body>
</html>
