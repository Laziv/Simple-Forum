<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new topic</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container my-3">
    <div class="row">
        <div class="col-12">
            <h2 class="h4 text-white bg-info mb-3 p-4 rounded">Create new topic</h2>
            <springForm:form method="POST" modelAttribute="topic" class="mb-3">
                <div class="form-group">
                    <label for="title">Title</label>
                    <springForm:input path="title" class="form-control" cssErrorClass="form-control is-invalid"/>
                    <springForm:errors path="title" cssClass="has-error"/>
                </div>

                <div class="form-group">
                    <label for="content">Comment</label>
                    <springForm:textarea path="content" class="form-control" cssErrorClass="form-control is-invalid" rows="3"/>
                    <springForm:errors path="content" cssClass="has-error"/>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Create topic</button>
            </springForm:form>
        </div>
    </div>
</div>
</body>
</html>
