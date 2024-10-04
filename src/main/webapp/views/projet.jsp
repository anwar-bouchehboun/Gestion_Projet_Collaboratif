<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> 

<% request.setAttribute("title", "projets");
request.setAttribute("contentPage", "../content/projet.jsp"); %>

<jsp:include page="/views/layout/layout.jsp" />