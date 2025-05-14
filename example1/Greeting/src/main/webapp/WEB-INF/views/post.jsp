<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>게시판</title>
</head>
<body>
<h1>게시판</h1>

<form action="/addPost" method="post">
    <label>제목:</label>
    <input type="text" name="title" required>
    <br>
    <label>내용:</label>
    <textarea name="content" required></textarea>
    <br>
    <button type="submit">게시글 추가</button>
</form>

<h2>게시글 목록</h2>
<ul>
    <c:forEach var="post" items="${posts}">
        <li><strong>${post.title}</strong>: ${post.content}</li>
    </c:forEach>
</ul>
</body>
</html>
