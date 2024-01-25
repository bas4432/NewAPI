<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>

<h2>회원가입</h2>

<form action="/member/signup" method="post">
    <label for="userid">아이디:</label>
    <input type="text" id="userid" name="userId" required><br>

    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="name">이름:</label>
    <input type="text" id="name" name="username"><br>

    <label for="email">이름:</label>
    <input type="text" id="email" name="eamil"><br>
    <!-- 기타 필요한 필드들 추가 -->

    <input type="submit" value="가입">
</form>

</body>
</html>