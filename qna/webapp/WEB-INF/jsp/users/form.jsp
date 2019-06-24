<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>

<%@ include file="../commons/_header.jspf"%>
</head>
<body>
	<%@ include file="../commons/_top.jspf"%>
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>회원가입</h1>
				</div>
				
				<c:choose>
					<c:when test="${empty user.userId}">
						<c:set var="method" value="post" />
					</c:when>
					<c:otherwise>
						<c:set var="method" value="put" />
					</c:otherwise>
				</c:choose>		
				
				<form:form modelAttribute="user" cssClass="form-horizontal" action="/users" method="${method}">
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
						<c:choose>
							<c:when test="${empty user.userId}">
								<form:input path="userId" />
								<form:errors path="userId" cssClass="error" />
							</c:when>
							<c:otherwise>
								${user.userId}
								<form:hidden path="userId"/>
							</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<form:password path="password" />
							<form:errors path="password" cssClass="error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<form:input path="name" />
							<form:errors path="name" cssClass="error" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<form:input path="email" />
							<form:errors path="email" cssClass="error" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">회원가입</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>