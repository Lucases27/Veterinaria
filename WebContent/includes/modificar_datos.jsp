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
		<div class="col-4">
			<div class="container navbar-light" style="background-color: #e3f2fd;"> 
				<div>
					<h5>Modificar Datos</h5>				
				</div>
				<div class="row d-flex justify-content-center">
					<!-- MENSAJE DE EXITO/ERROR AL MODIFICAR -->
					<div class="col-12 form-group mb-1 mt-2">
						<h5 class="text-danger">
							<c:out value="${errores}"></c:out>
						</h5>
						<h5 class="text-success">
							<c:out value="${success}"></c:out>
						</h5>
					</div>
					<!-- FORMULARIO DE MODIFICACIÓN DE DATOS DE USUARIO -->
					<form action="ModificarDatos" method="POST">
						<div class="col-12 form-group mt-3 mb-0">
							<label>Nombres:
								<input readonly class="form-control" type="text" name="nombreApellido" 
									id="nombreApellido" value="<%out.print(session.getAttribute("nombreApellido"));%>"/>
							</label>
						</div>
						<div class="col-12 form-group mb-0">
							<label>Email: <input class="form-control" type="email" name="email" id="email" placeholder="Email" min="8" max="60"
								value="<%out.print(session.getAttribute("email"));%>"/></label>
						</div>
						<div class="col-12 form-group mb-0">
							<label>Telefono: <input class="form-control" type="text" name="telefono" id="telefono" placeholder="1111111111" min="8" max="20"
								value="<%out.print(session.getAttribute("telefono"));%>"/></label>
						</div>
						<div class="col-12 form-group  mb-0">
							<label>Contraseña Actual: 
								<input class="form-control" type="password" name="pass" id="pass" placeholder="Contraseña Actual" 
								required min="4" max="30" value="<%out.print(session.getAttribute("pass"));%>"/>
							</label>
						</div>
						<div class="col-12 form-group mb-0">
							<label>Nueva Contraseña: 
								<input class="form-control " type="password" name="pass1" id="pass1" placeholder="Nueva Contraseña" required min="4" max="30"/>
							</label>
						</div>
						<div class="col-12 form-group mb-0">
							<label>Repetir Contraseña: 
								<input class="form-control " type="password" name="pass2" id="pass2" placeholder="Repetir Contraseña" required min="4" max="30"/>
							</label>
						</div>
						<button type="submit" class="btn btn-primary mt-2 mb-3 form-control">Modificar!</button>
					</form>
				</div>
			</div>
		</div>
		<div class="col-4">
			<div class="container" style="background-color: #e3f2fd;">
				<h5></h5>
			</div>
		</div>
    </div>
</section>