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
      	<div class="">
      		<h5 class="">
      			Historia de: ${nombreMascota}
      		</h5>
      	</div>
      	<div class="col-12 d-flex">
      		<div class="row d-flex justify-content-center col-4">
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