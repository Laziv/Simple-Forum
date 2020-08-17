<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <title>${topic.title}</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container my-3">

    <div class="row">
        <div class="col-12">
            <h2 class="h4 text-white bg-info mb-0 p-4 rounded-top">${topic.title}</h2>
            <c:forEach items="${posts.getContent()}" var="post">
                <table class="table table-striped table-bordered table-responsive-lg">
                    <tbody>
                    <tr>
                        <td style="width: 16.66%" class="author-col">
                            <div>by <span class="font-weight-bold">${post.user.username}</span></div>
                        </td>
                        <td class="post-col d-lg-flex justify-content-lg-between">
                            <div><span class="font-weight-bold">Posted:</span> ${post.creationDate}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div><span class="font-weight-bold">Joined:</span><br>${post.user.creationDate}
                            </div>
                            <div><span class="font-weight-bold">Posts:</span><br>${post.user.numberOfPosts}</div>
                        </td>
                        <td>
                                ${post.content}
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:forEach>
        </div>
    </div>

        <c:if test="${posts.hasPrevious()}">
            <td><a href="/section/${topic.sectionId}/topic/${topic.id}/?page=${posts.getPageable().getPageNumber() - 1}&size=5">Previous</a>
            </td>
        </c:if>

        <c:if test="${posts.hasNext()}">
            <td><a href="/section/${topic.sectionId}/topic/${topic.id}/?page=${posts.getPageable().getPageNumber() + 1}&size=5">Next</a></td>
        </c:if>

    <sec:authorize access="isAuthenticated()">
        <springForm:form method="POST" action="/section/${topic.sectionId}/topic/${topic.id}/new-post"
                         modelAttribute="post"
                         class="mb-3">
            <div class="form-group">
                <springForm:textarea path="content" class="form-control" cssErrorClass="form-control is-invalid"
                                     rows="10"
                                     placeholder="Write your comment here."/>
                <springForm:errors path="content" cssClass="has-error"/>
            </div>
            <button type="submit" class="btn btn-primary">Reply</button>
            <button type="reset" class="btn btn-danger">Reset</button>
        </springForm:form>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <div>
            You must <a href="${pageContext.request.contextPath}/login">log in</a> or <a href="${pageContext.request.contextPath}/registration">register </a>to reply here.
        </div>
    </sec:authorize>
</div>
</body>
</html>
