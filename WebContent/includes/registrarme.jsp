<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- SI YA ESTA LOGEADO REDIRECCIONA A INDEX.JSP. -->
	<c:choose>  
		<c:when test="${logeado}">  
			<c:redirect url="index.jsp"/>
		</c:when>
	</c:choose>

<section id="secreg" class="mt-5">
  <div class="container">
    <div class="section-title">
      <h2 id="h2">Registro</h2>
    </div>
    
    <form method="POST" id="Registro" class="row g-5 d-flex" action="Registro">
    	<div class="col-4"></div>
    	<div class = "form-group col-4 align-items-center justify-content-center">
    		<div class="col-md-12">
		        <label class="form-label">Nombre y apellido</label>
		        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombres y apellidos">
	      	</div>
  			<div class="col-md-12">
		        <label  class="form-label">Contraseña</label>
		        <input type="password" class="form-control" id="pass" name="pass" placeholder="Contraseña">
		        <input type="button" class="btn btn-outline-dark btn-sm" id="verPasswordBtn" value ="Ver contraseña">
		   	</div>
	      	<div class="col-md-12">
		        <label class="form-label">Email</label>
		    	<input type="text" class="form-control" id="email" name="email" placeholder="TuEmail@ejemplo.com">
	      	</div>
		    <div class="col-md-12">
		        <label  class="form-label">Telefono</label>
		        <input type="text" class="form-control" id="telefono" name="telefono" placeholder="1111111111"> 
		    </div>
		    <div class="col-12 text-center mb-n5 mt-3">
		    	<button class="btn btn-primary center-block" type="submit" name="register">Enviar</button>
			</div>
    	</div>    	
			<div class="font-italic col-12 text-center">
				<h6>¿Ya tenes cuenta? <a href="Login?menuLogin=1">Inicia sesión.</a></h6>
			</div>
    	<div class="col-4"></div>
    </form>
  </div>
	
</section>