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
       		<h2>Turnos</h2>
      	</div>
      	<div class="col-12 d-flex">
      		<div class="row d-flex justify-content-center col-3">
				<!-- MENSAJE DE EXITO/ERROR -->
				<div class="col-12 form-group mb-1 mt-2">
					<h5 class="text-danger">
						<c:out value="${errores}"></c:out>
					</h5>
					<h5 class="text-success">
						<c:out value="${success}"></c:out>
					</h5>
				</div>
				<!-- FORMULARIO SOLICITAR TURNO  -->
				<c:choose>  
					<c:when test="${!isAdmin}">  
						<form action="Turnos" method="POST">
							<div class="col-12 form-group mt-3 mb-0">
								<button type="submit" class="btn btn-primary mt-2 mb-3 form-control">Solicitar Turno</button>
							</div>
						</form>
					</c:when>
				</c:choose>
			</div>
	      	<div class="col-9 ml-n5 mt-n5 p-5">
				<div class="container navbar-light  p-0" style="background-color: #e3f2fd;"> 
				    <div class="table-responsive">
				    	<!-- TABLA TURNOS -->
				        <table class="table table-bordered table-sm table-hover mb-0">
				            <thead>
				                <tr style="background:#003325;color:white" class="text-center">
				                    <td>Turno N�</td>
			                    	<c:choose>  
										<c:when test="${isAdmin}">  
											<td>Nombre</td>
				                    		<td>Telefono</td>
										</c:when>
									</c:choose>
				                    <td>Fecha</td>
				                    <td>Dia y Hora</td>
				                    <td>Estado</td>
				                </tr>
				            </thead>
			           		<tbody>
				                <c:forEach items="${listaTurnos}" var="turno">
				                	<tr class= "text-center">
				                 	<td>${turno.getIdTurno()}</td>
				                 	<c:choose>  
										<c:when test="${isAdmin}">  
											<td>${turno.getNombreApellido()}</td>
				                 			<td>${turno.getTelefono()}</td>
										</c:when>
									</c:choose>
				                 	<td>${turno.getFecha()}</td>
				                 	<td>${turno.getDiaYHora()}</td>
				                 	<c:choose>  
										<c:when test="${turno.getVigente() == true}">
											<td class="text-primary">Vigente</td>
											<td><a class="btn btn-outline-danger btn-sm " href="Turnos?borrarTurno=${turno.getIdTurno()}">Borrar Turno</a></td>
										</c:when>
										<c:otherwise>
								            <td class="text-danger">No vigente</td>
								         </c:otherwise>
									</c:choose>
				                	<c:choose>  
										<c:when test="${isAdmin && turno.getVigente()}">
				                 			<td><a class="btn btn-outline-danger btn-sm " href="Turnos?cancelarTurno=${turno.getIdTurno()}">Cancelar Turno</a></td>
										</c:when>
									</c:choose>
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