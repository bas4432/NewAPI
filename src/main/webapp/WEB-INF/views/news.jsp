<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>News Articles</title>
</head>
<body>
<h1>News Articles</h1>
<ul>
    <c:forEach items="${newsArticles}" var="article">
        <li><a href="${article.url}">${article.image}</a></li>
        <li><a href="${article.url}">${article.subject}</a></li>
    </c:forEach>

    <table class= "table">
        <tr>
            <th>이미지</th>
            <th>제목</th>
        </tr>
        <tr> new
            <c:forEach items="${newsArticles}" var="article">
                <td><a href="${article.url}">${article.image}</a></td>
                <td><a href="${article.url}">${article.subject}</a></td>
            </c:forEach>
        </tr>
    </table>
</div>
</ul>
</body>
</html>