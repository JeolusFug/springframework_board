<!-- 게시판용입니다. けいじばんようです -->
<%--@elvariable id="board" type="mapper"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>detail.jsp</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <td>${board.id}</td>
    </tr>
    <tr>
        <th>writer</th>
        <td>${board.boardWriter}</td>
    </tr>
    <tr>
        <th>date</th>
        <td>${board.boardCreatedTime}</td>
    </tr>
    <tr>
        <th>hits</th>
        <td>${board.boardHits}</td>
    </tr>
    <tr>
        <th>title</th>
        <td>${board.boardTitle}</td>
    </tr>
    <tr>
        <th>contents</th>
        <td>${board.boardContents}</td>
    </tr>
</table>
<button onclick="updateFn()">수정</button>
<button onclick="deleteFn()">삭제</button></br>
<!-- 페이징 기능이 있는 목록으로 돌아가기 -->
<!-- ページングきのうがあるもくろくにもどる -->
<button onclick="listFn()">페이징 있는 목록</button>
<!-- 페이징 기능이 없는 목록으로 돌아가기 -->
<!-- ページングきのうがないもくろくにもどる -->
<button onclick="listnonpFn()">페이징 없는 목록</button>

<div>
    <input type="text" id="commentWriter" value='${loginEmail}'readonly>
    <input type="text" id="commentContents" placeholder="내용">
    <button id="comment-writer-btn" onclick="commentWrite()">댓글작성</button>
</div>

<div id="comment-list">
    <table>
        <tr>
            <!-- <th>댓글번호</th> -->
            <th>작성자</th>
            <th>내용</th>
            <th>작성시간</th>
        </tr>
        <c:forEach items="${commentList}" var="comment">
            <!-- 반복 작업이 필요하기 때문에 forEach 구문을 사용 -->
            <!--　くりかえしさぎょうがひつようなため、forEachこうぶんをしようします -->
            <tr>
                <!-- <td>${comment.id}</td> -->
                <td>${comment.commentWriter}</td>
                <td>${comment.commentContents}</td>
                <td>${comment.commentCreatedTime}</td>
                <!-- <td><button id="comment-delete-btn" onclick="commentdelete()">댓글삭제</button></td> -->
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>
    const listFn = () => {
        const page = '${page}';
        location.href="/board/paging?page=" + page;
    }
    const updateFn = () => {
        const id = '${board.id}';
        location.href="/board/update?id=" + id;
    }
    const deleteFn = () => {
        const id = '${board.id}';
        location.href="/board/delete?id=" + id;
    }
    const listnonpFn = () => {
        const id = '${board.id}';
        location.href="/board/"
    }

    const commentWrite = () => {
        const writer = document.getElementById("commentWriter").value;
        const contents = document.getElementById("commentContents").value;
        const board = '${board.id}';
        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                commentWriter: writer,
                commentContents: contents,
                boardId: board
            },
            dataType: "json",
            success: function (commentList) {
                console.log("작성성공");
                console.log(commentList);
                let output = "<table>";
                output += "<tr>";
                // output += "<th>댓글번호</th>";
                output += "<th>작성자</th>";
                output += "<th>내용</th>";
                output += "<th>작성시간</th></tr>";
                for(let i in commentList) {
                    // i 가 인덱스 번호로 접근
                    //　iがインデックスばんごうにアクセス
                    output += "<tr>";
                    // output += "<td>"+commentList[i].id+"</td>";
                    output += "<td>" + commentList[i].commentWriter + "</td>";
                    output += "<td>" + commentList[i].commentContents + "</td>";
                    output += "<td>" + commentList[i].commentCreatedTime + "</td>";
                    // output += '{<td><button id="comment-delete-btn" onClick="commentdelete()">댓글삭제</button></td>}';
                    output += "</tr>";
                }
                output += "</table>";
                document.getElementById('comment-list').innerHTML = output;
                document.getElementById('commentWriter').value='${loginEmail}';
                document.getElementById('commentContents').value='';
            },
            error: function () {
                console.log("실패");
            }
        });
    }
/*
    const commentdelete = () => {
        const board = '${board.id}';$.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                boardId: board
            },
            dataType: "json",
            success: function (commentList) {
                console.log("삭제성공");
            },
            error: function () {
                console.log("실패");
            }
        });
    }
    */
</script>
</html>