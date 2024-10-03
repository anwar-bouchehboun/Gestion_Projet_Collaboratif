<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> 

<% request.setAttribute("title", "members");
request.setAttribute("contentPage", "../content/member.jsp"); %>

<jsp:include page="/views/layout/layout.jsp" />