<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- SI YA ESTA LOGEADO REDIRECCIONA A INDEX.JSP. -->
	<c:choose>  
		<c:when test="${!isAdmin}">  
			<c:redirect url="../index.jsp"/>
		</c:when>
	</c:choose>

<section id="faq" class="faq section-bg">
	<div class="container">
    	<div class="section-title">
       		<h2>Panel de veterinario</h2>
      	</div>
      	<div>
      		<a href="UsuariosAdmin">Administrar usuarios</a>
      		<a href="TiendaAdmin">Administrar tienda</a>
      	</div>
    </div>
</section>