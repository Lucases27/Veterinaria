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
				<c:choose>
					<c:when test="${param.modificar >0}">
						<!-- FORMULARIO EDITAR PRODUCTO -->
						<form action="TiendaAdmin" method="POST">
							<div class="form-group mt-2">
								<label>Id Producto:<input class="form-control" readonly type="text" name="idProducto" 
										placeholder="${modificarProducto.getIdProducto()}"/></label>
							</div>
							<div class="form-group">
								<label>Nombre: <input class="form-control" type="text" name="nombre" placeholder="Nombre" 
										value="${modificarProducto.getNombre()}"/></label>
							</div>
							<div class="form-group">
								<label>Descripción: <input class="form-control" type="text" name="descripcion" placeholder="Nombre" 
										value="${modificarProducto.getDescripcion()}"/></label>
							</div>
							<div class="form-group">
								<label>Precio<input class="form-control" type="text" name="precio" placeholder="Precio" 
										value="${modificarProducto.getPrecio()}"/></label>
							</div>
							<div class="form-group">
								<label>Cantidad:<input class="form-control" type="text" name="cantidad" placeholder="Cantidad" 
										value="${modificarProducto.getCantidad()}"/></label>
							</div>
							<input type="hidden" value="${modificarProducto.getIdProducto()}" name="modificarProducto">
							<button type="submit" class="btn btn-primary form-control mb-4">Modificar</button>
						</form>
					</c:when>
					<c:when test="${param.agregar == 1}">
						<!-- FORMULARIO AGREGAR PRODUCTO-->
						<form action="TiendaAdmin" method="POST">
							<div class="form-group mt-2">
								<label>Nombre: <input class="form-control" type="text" name="nombre" placeholder="Nombre del producto"/></label>
							</div>
							<div class="form-group mt-2">
								<label>Descripción: <input class="form-control" type="text" name="descripcion" placeholder="Descripción del producto"/></label>
							</div>
							<div class="form-group">
								<label>Precio<input class="form-control" type="text" name="precio" placeholder="Precio del producto"/></label>
							</div>
							<div class="form-group">
								<label>Cantidad:<input class="form-control" type="text" name="cantidad" placeholder="Cantidad disponible"/></label>
							</div>
							<input type="hidden" value="1" name="agregarProducto">
							<button type="submit" class="btn btn-primary form-control mb-4">Agregar Producto</button>
						</form>
					</c:when>
				</c:choose>
			</div>
	      	<div class="col-9 ml-n5 mt-n5 p-5">
				<div class="container navbar-light  p-0" style="background-color: #e3f2fd;"> 
				    <div class="table-responsive">
					    <table class="table table-bordered table-sm table-hover mb-0">
				            <thead>
				                <tr style="background:#003325;color:white" class="text-center">
				                    <td>Id</td>
				                    <td>Nombre</td>
				                    <td>Descripción</td>
				                    <td>Precio</td>
				                    <td>Cantidad</td>
				                </tr>
				            </thead>
			           		<tbody>
				                <c:forEach items="${listaProductos}" var="prod">
				                	<tr>
				                 	<td>${prod.getIdProducto()}</td>
				                 	<td>${prod.getNombre()}</td>
				                 	<td>${prod.getDescripcion()}</td>
				                 	<td>${prod.getPrecio()}</td>
				                 	<td>${prod.getCantidad()}</td>
				                 	
				                 	<c:set var="id" value="${prod.getIdProducto()}"/>
				                 	<c:set var="nombre" value="${prod.getNombre()}"/>
				                 	<c:set var="descripcion" value="${prod.getDescripcion()}"/>
				                 	<c:set var="precio" value="${prod.getPrecio()}"/>
				                 	<c:set var="cantidad" value="${prod.getCantidad()}"/>
									<td class="text-center">
										<a class="btn btn-outline-primary btn-sm" href="TiendaAdmin?modificar=${id}">Modificar</a>
										<a class="btn btn-outline-primary btn-sm" href="TiendaAdmin?eliminar=${id}">Eliminar</a>
									</td>
				                	</tr>
				            	</c:forEach>
				            </tbody>
				        </table>
				    </div>
				</div>
			    <div class="text-right mt-4 p-0">
					<a class="btn btn-outline-danger btn-sm" href="TiendaAdmin?agregar=1">Agregar Producto</a>
				</div>
			</div>
      	</div>
    </div>
</section>