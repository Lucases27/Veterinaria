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
       		<h2>Mi Cuenta</h2>
      	</div>
      	<div class="">
      		<h5 class="">
      			Historia de: ${nombreMascota}
      		</h5>
      	</div>
      	<div class="col-12 d-flex">
      		<div class="row d-flex justify-content-center col-4">
				<!-- MENSAJE DE EXITO/ERROR AL MODIFICAR -->
				<div class="col-12 form-group mb-1 mt-2">
					<h5 class="text-danger">
						<c:out value="${errores}"></c:out>
					</h5>
					<h5 class="text-success">
						<c:out value="${success}"></c:out>
					</h5>
				</div>
				<c:choose>
					<c:when test="${param.agregar > 0}">
						<!-- FORMULARIO AGREGAR HISTORIA-->
						<c:set var = "idMascota"  value = "${idMascotaHistorial}"/>
						<form action="Historial" method="POST">
							<div class="form-group mt-2">
								<label>Patología: <input class="form-control" type="text" name="patologia" placeholder="Patología"/></label>
							</div>
							<div class="form-group mt-2">
								<label>Vacunas: <input class="form-control" type="text" name="vacunas" placeholder="Vacunas"/></label>
							</div>
							<div class="form-group">
								<label>Descripción<input class="form-control" type="text" name="descripcion" placeholder="Descripción"/></label>
							</div>
							<input type="hidden" value="${idMascota}" name="agregarHistoria"/>
							<button type="submit" class="btn btn-primary form-control mb-4">Agregar Historia</button>
						</form>
					</c:when>
				</c:choose>
			</div>
			
	      	<div class="col-9 ml-n5 mt-n5 p-5">
				<div class="container navbar-light  p-0" style="background-color: #e3f2fd;"> 
				    <div class="table-responsive">
					    <table class="table table-bordered table-sm table-hover mb-0 text-center">
				            <thead>
				                <tr style="background:#003325;color:white" class="text-center">
				                    <td>Fecha</td>
				                    <td>Patología</td>
				                    <td>Vacunas</td>
				                    <td>Descripción</td>
				                </tr>
				            </thead>
			           		<tbody>
				                <c:forEach items="${historial}" var="historia">
				                	<tr>
				                 	<td>${historia.getFecha()}</td>
				                 	<td>${historia.getPatologia()}</td>
				                 	<td>${historia.getVacunas()}</td>
				                 	<td>${historia.getDescripcion()}</td>
				                 	<td><a class="btn btn-outline-primary btn-sm"  href="Historial?eliminar=${historia.getIdHistoria()}">Eliminar</a></td>
				                	</tr>
				            	</c:forEach>
				            </tbody>
				        </table>
				    </div>
				</div>
		        <div class="text-right mt-4 p-0">
					<a class="btn btn-outline-danger btn-sm" href="Historial?agregar=${idMascotaHistorial}">Nueva Historia</a>
				</div>
			</div>
      	</div>
      	<div>
      		<a href="MascotasAdmin?userId=${idUsuarioHistorial}" class="btn btn-primary btn-sm">Volver</a>
      	</div>
    </div>
</section>