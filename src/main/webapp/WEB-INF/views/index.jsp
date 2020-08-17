<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Forum</title>
</head>
<body>
<style type="text/css">
    .forum-col {
        min-width: 30.8em;
    }

    .last-post-col {
        min-width: 12em;
    }
</style>
<jsp:include page="header.jsp"/>
<div class="container my-3">
    <div class="row">
        <div class="col-12 col-xl-9">
            <h2 class="h4 bg-info mb-0 p-4 rounded-top">Forum category</h2>
            <table class="table table-striped table-bordered table-responsive">
                <thead class="thead-light">
                <tr>
                    <th scope="col" class="forum-col">Forum</th>
                    <th scope="col">Topics</th>
                    <th scope="col">Posts</th>
                    <th scope="col" class="last-post-col">Last post</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sections}" var="section">
                    <tr>
                        <td>
                            <h3 class="h5 mb-0"><a
                                    href="${pageContext.request.contextPath}/section/${section.id}"
                                    class="text-uppercase">${section.name}</a></h3>
                            <p class="mb-0">${section.description}</p>
                        </td>
                        <td>
                            <div>${section.numberOfTopics}</div>
                        </td>
                        <td>
                            <div>${section.numberOfPosts}</div>
                        </td>
                        <td>
                            <c:if test="${not empty section.lastPost}">
                                <div>by <strong>${section.lastPost.user.username}</strong></div>
                                <div>${section.lastPost.creationDate}</div>
                            </c:if>
                            <c:if test="${empty section.lastPost}">
                                <div>no post yet</div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${pageContext.request.contextPath}/section/new" class="btn btn-lg btn-primary">New Section</a>
            </sec:authorize>
        </div>

        <div class="col-12 col-xl-3">
            <aside>
                <div class="row">
                    <div class="col-12 col-sm-6 col-xl-12">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="h4 card-title">Forum statistics</h2>
                                <dl class="row mb-0">
                                    <dt class="col-8">Total forums:</dt>
                                    <dd class="col-4 mb-0">${statistics.numberOfSections}</dd>
                                </dl>
                                <dl class="row mb-0">
                                    <dt class="col-8">Total topics:</dt>
                                    <dd class="col-4 mb-0">${statistics.numberOfTopics}</dd>
                                </dl>
                                <dl class="row mb-0">
                                    <dt class="col-8">Total posts:</dt>
                                    <dd class="col-4 mb-0">${statistics.numberOfPosts}</dd>
                                </dl>
                                <dl class="row mb-0">
                                    <dt class="col-8">Total members:</dt>
                                    <dd class="col-4 mb-0">${statistics.numberOfUsers}</dd>
                                </dl>
                            </div>
                            <div class="card-footer">
                                <div>Newest member:</div>
                                <div><strong>${statistics.newestUser}</strong></div>
                            </div>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
    </div>

    </div>
</div>
</body>
</html>
