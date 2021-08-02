<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- SI NO ESTA LOGEADO REDIRECCIONA A INDEX.JSP. -->
	<c:choose>  
		<c:when test="${!logeado}">  
			<c:redirect url="../index.jsp"/>
		</c:when>
	</c:choose>

<section id="faq" class="faq section-bg">
	<div class="container">
    	<div class="section-title">
       		<h2>Mi Cuenta</h2>
      	</div>
      	<div class="col-12 d-flex">
      		<div class="row d-flex justify-content-center col-5">
				<!-- MENSAJE DE EXITO/ERROR AL MODIFICAR -->
				<div class="col-12 form-group mb-1 mt-2">
					<h5 class="text-danger">
						<c:out value="${errores}"></c:out>
					</h5>
					<h5 class="text-success">
						<c:out value="${success}"></c:out>
					</h5>
				</div>
				<!-- FORMULARIO DE AGREGAR DE MASCOTAS  -->
				<form action="MascotasUsuario" method="POST">
					<div class="col-12 form-group mt-3 mb-0">
						<label>Nombre:
							<input class="form-control" type="text" name="nombre" id="nombre"/>
						</label>
					</div>
					<div class="col-12 form-group mb-0">
						<label>Tipo: <input class="form-control" type="text" name="tipo" id="tipo" 
						placeholder="Ej:Chihuahua" min="2" max="20"/></label>
					</div>
					<div class="col-12 form-group mb-0">
						<label>Edad en años: <input class="form-control" type="text" name="edad" id="edad" placeholder="Ej:2"/></label>
					</div>
					<div class="col-12 form-group  mb-0">
						<label>Peso aproximado en Kg: 
							<input class="form-control" type="text" name="peso" id="peso" placeholder="EJ:4"/>
						</label>
					</div>
					<button type="submit" class="btn btn-primary mt-2 mb-3 form-control">Agregar!</button>
				</form>
			</div>
	      	<div class="col-6 ml-n5 mt-n5 p-5">
				<div class="container navbar-light  p-0" style="background-color: #e3f2fd;"> 
				    <div class="table-responsive">
				        <table class="table table-bordered table-sm table-hover mb-0">
				            <thead>
				                <tr style="background:#003325;color:white" class="text-center">
				                    <td>Nombre</td>
				                    <td>Tipo</td>
				                    <td>Edad</td>
				                    <td>Peso</td>
				                </tr>
				            </thead>
			           		<tbody>
				                <c:forEach items="${listaMascotas}" var="mascota">
				                	<tr class= "text-center">
				                 	<td>${mascota.getNombre()}</td>
				                 	<td>${mascota.getTipo()}</td>
				                 	<td>${mascota.getEdad()} Años</td>
				                 	<td>${mascota.getPeso()} Kg</td>
				                 	<td><a class="btn btn-outline-primary btn-sm"  href="Historial?getHistoria=${mascota.getIdMascota()}">Historial</a></td>
									<td><a class="btn btn-outline-danger btn-sm " href="MascotasUsuario?borrarMascota=${mascota.getIdMascota()}">Borrar Mascota</a></td>
				                	</tr>
				            	</c:forEach>
				            </tbody>
				        </table>
				    </div>
				</div>
			</div>
      	</div>
    </div>
</section>