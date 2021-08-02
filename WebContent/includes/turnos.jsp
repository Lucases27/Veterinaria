<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- SI YA ESTA LOGEADO REDIRECCIONA A INDEX.JSP. -->
	<c:choose>  
		<c:when test="${!logeado}">  
			<c:redirect url="../index.jsp"/>
		</c:when>
	</c:choose>

<section id="faq" class="faq section-bg">
	<div class="container">
		<div class="section-title">
			<h2>Turnos</h2>
		</div>
  		<form class="row g-5 needs-validation" novalidate method="POST" action="Turnos">
    		<div class="col-md-6 form-check">
      			<input class="form-check-input" type="checkbox" name="consulta" value="consulta" id="consultaCheckbox" >
  				<label class="form-check-label" for="consulta">Consulta</label>
			</div>
			<div class="col-md-6 form-check">
  				<input class="form-check-input" type="checkbox" name="urgencia" value="urgencia" id="urgenciaCheckbox" >
   				<label class="form-check-label" for="urgencia">Urgencia</label>
			</div>
			<div class="col-md-4">
				<label class="form-label">Fecha</label>
				<input type="date" class="form-control" id="fecha" name="fecha">
			</div>
			<div class="col-md-4">
			 	<label  class="form-label">Hora</label>
				<select class="form-select" id="hora" name="hora">
				    <option>09:00</option>
				    <option>10:00</option>
				    <option>11:00</option>
				    <option>12:00</option>
				    <option>17:00</option>
				    <option>18:00</option>
				    <option>19:00</option>
			  	</select>   
			</div>
			<div class="col-md-4">
			 	<label  class="form-label">Sede</label>
			  	<select class="form-select" id="sede" name="sede">
				    <option>Lomas de Zamora</option>
				    <option>Monte Grande</option>
			  	</select>  
			</div>
			<div class="col-12">
			  	<button class="btn btn-primary" onclick="RecogerDatos()" type="submit">Enviar</button>
	    	</div>
	  	</form>
	</div>
</section>
<script src="js/Clase.js"></script>