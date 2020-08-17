<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Page not found</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="alert alert-danger" role="alert">
    ${message}
</div>
</body>
</html>
