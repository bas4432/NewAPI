<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<c:set var="searchResultsDTO" value='${searchResultsDTO}'/>--%>

<%--<div>${searchResultsDTO.lastBuildDate} 서치 타이틀</div>--%>
<%--<div>Total: ${searchResultsDTO.total}</div>--%>

<%--<ul>--%>
<%--    <c:forEach var="searchItem" items="${searchResultsDTO.items}">--%>
<%--        <li>Title: ${searchItem.title}</li>--%>
<%--        <li>Link: ${searchItem.link}</li>--%>
<%--        <li>Description: ${searchItem.description}</li>--%>
<%--        <li>Blogger Name: ${searchItem.bloggerName}</li>--%>
<%--        <li>Post Date: ${searchItem.postDate}</li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>

<button type="button" value="등록하기"><a href="/mypage/keyword/add">등록</a></button>

</body>
</html>