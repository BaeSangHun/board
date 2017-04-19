<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BOARD VIEW(모델2 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</head>
<body>
<div class="container">
    <h1>BOARD VIEW(모델2 방식)</h1>
     <table class="table">
         <tbody>
             <tr>
                <td>board_no :</td>
                <td>${board.board_no}</td>
               </tr>
            <tr>
                   <td>board_title :</td>
                   <td>${board.board_title}</td>
            </tr>
            <tr>
                   <td>board_content :</td>
                   <td>${board.board_content}</td>
            </tr>
            <tr>
                   <td>board_user :</td>
                   <td>${board.board_user}</td>
            </tr>
            <tr>
                   <td>board_date :</td>
                   <td>${board.board_date}</td>
            </tr>
        </tbody>
    </table>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/BoardModify?boardNo=${board.board_no}">수정</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/RemoveForm?boardNo=${board.board_no}">삭제</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/list">글목록</a>
</div>
</body>
</html>
