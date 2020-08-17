<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>${section.name}</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container my-3">
    <c:if test="${empty topics}">
        <div class="alert alert-primary" role="alert">
            The list of topics is empty
        </div>
    </c:if>
    <c:if test="${not empty topics}">
        <div class="row">
            <div class="col-12">
                <h2 class="h4 text-white bg-info mb-0 p-4 rounded-top">${section.name}</h2>
                <table class="table table-striped table-bordered table-responsive-lg">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col" class="topic-col">Topic</th>
                        <th scope="col" class="created-col">Created</th>
                        <th scope="col">Statistics</th>
                        <th scope="col" class="last-post-col">Last post</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${topics}" var="topic">
                        <tr>
                            <td>
                                <h3 class="h6"><a href="/section/${section.id}/topic/${topic.id}/?page=0&size=5">${topic.title}</a>
                                </h3>
                            </td>
                            <td>
                                <div>by <strong>${topic.user.username}</strong></div>
                                <div>${topic.creationDate}</div>
                            </td>
                            <td>
                                <div>${topic.numberOfReplies} replies</div>
                                <div>${topic.numberOfViews} views</div>
                            </td>
                            <td>
                                <div>by <strong>${topic.lastPost.user.username}</strong></div>
                                <div>${topic.lastPost.creationDate}</div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
    <sec:authorize access="isAuthenticated()">
        <a href="${pageContext.request.contextPath}/section/${section.id}/new-topic" class="btn btn-lg btn-primary">New
            topic</a>
    </sec:authorize>
</div>
</body>
</html>
