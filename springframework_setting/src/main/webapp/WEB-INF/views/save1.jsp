<!-- 게시판용입니다. けいじばんようです -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save1</title>
</head>
<body>
<form action="/board/save1" method="post">
    <input type="text" name="boardWriter" value="${loginEmail}"readonly>
    <input type="text" name="boardPass" placeholder="비밀번호">
    <input type="text" name="boardTitle" placeholder="제목">
    <textarea name="boardContents" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea>
    <input type="submit" value="작성">
</form>
</body>
</html>