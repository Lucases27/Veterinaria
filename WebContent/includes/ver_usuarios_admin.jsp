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
       		<h2>Mi Cuenta</h2>
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
				<!-- FORM BUSCAR USUARIO -->
				<div class="col-12 form-group mb-1 mt-2">
					<form class="" action="UsuariosAdmin" method="POST">
			        	<div class="col-12">
			          		<label class="form-label">Buscar usuario</label>
				          	<input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Nombre"> 
				        </div>
				        <div class="col-12 text-center">
			          		<button class="btn btn-primary btn-sm" type="submit">Enviar</button>
				        </div>
	      			</form>
				</div>
			</div>
	      	<div class="col-9 ml-n5 mt-n5 p-5">
				<div class="container navbar-light  p-0" style="background-color: #e3f2fd;"> 
				    <div class="table-responsive">
					    <table class="table table-bordered table-sm table-hover mb-0">
				            <thead>
				                <tr style="background:#003325;color:white" class="text-center">
				                    <td>Nombre</td>
				                    <td>Email</td>
				                    <td>Telefono</td>
				                    <td>Tipo Usuario</td>
				                </tr>
				            </thead>
			           		<tbody>
				                <c:forEach items="${listaUsuarios}" var="user">
				                	<tr>
				                 	<td>${user.getNombreApellido()}</td>
				                 	<td>${user.getEmail()}</td>
				                 	<td>${user.getTelefono()}</td>
				                 	<c:choose>
									    <c:when test="${user.isAdmin()}">
									    	<td class="text-primary text-center">Administrador</td>
									    	<td><a class="btn btn-outline-primary btn-sm"  href="UsuariosAdmin?quitarAdmin=${user.getIdUsuario()}">Quitar Admin</a></td>
									    </c:when>    
									    <c:otherwise>
									    	<td class="text-info text-center">Cliente</td>
									    	<td><a class="btn btn-outline-danger btn-sm " href="UsuariosAdmin?agregarAdmin=${user.getIdUsuario()}">Agregar Admin</a></td>
									    </c:otherwise>
									</c:choose>
				                 	<td><a class="btn btn-outline-primary btn-sm"  href="MascotasAdmin?userId=${user.getIdUsuario()}">Ver Mascotas</a></td>
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