<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<title>Simple vet</title>
	<meta content="" name="description">
	<meta content="" name="keywords">
	<!-- Favicons -->
	<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    <link href="https://scontent.feze11-1.fna.fbcdn.net/v/t1.6435-9/233834089_6163962316979417_1023006618699093216_n.jpg?_nc_cat=103&ccb=1-4&_nc_sid=730e14&_nc_ohc=b3eJxR3yJocAX9QIsg-&_nc_ht=scontent.feze11-1.fna&oh=75715a25a99f358a565b6c0598e14e34&oe=6133A1D5" rel="icon" data-head-react="true">
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	<!-- Vendor CSS Files -->
	<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
	<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
	<link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
	<link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
	<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
	<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
	<!-- Template Main CSS File -->
	<link href="assets/css/style.css" rel="stylesheet">
	<!-- =======================================================
	* Template Name: Medilab - v4.1.0
	* Template URL: https://bootstrapmade.com/medilab-free-medical-bootstrap-theme/
	* Author: BootstrapMade.com
	* License: https://bootstrapmade.com/license/
	======================================================== -->
</head>
<body>
	<!-- CARGA LA TIENDA -->
	<c:choose>
		<c:when test="${tiendaProductos == null}">
			<c:redirect url="Inicio"/>
		</c:when>
	</c:choose>
	<!-- HEADER -->
	<jsp:include page="includes/header.jsp"></jsp:include>
	<!-- ======= Hero Section ======= -->
	<section id="hero" class="d-flex align-items-center">
		<div class="container">
			<h1>Bienvenido a Simple vet</h1>
			<h2>Atención personalizada para tu mascota.</h2>
			<a href="#about" class="btn-get-started scrollto">Comenzar</a>
		</div>
	</section><!-- End Hero -->
	<main id="main">
	<!-- ======= Why Us Section ======= -->
	<section id="why-us" class="why-us">
		<div class="container">
		</div>
	</section><!-- End Why Us Section -->
	<!-- ======= About Section ======= -->
	<section id="about" class="about">
		<div class="container-fluid">
			<br>
			<br>
			<div class="row">
				<br>
				<br>
				<div class="col-xl-5 col-lg-6 video-box d-flex justify-content-center align-items-stretch position-relative">
				</div>
				<div class="col-xl-7 col-lg-6 icon-boxes d-flex flex-column align-items-stretch justify-content-center py-5 px-lg-5">
					<h3>Sobre la Aplicación</h3>
					<p>
						El objetivo de esta aplicación es simplificar la actividad de la recepción y administración de turnos para ambos modelos de usuario, mediante la modalidad a distancia (Online).
					</p>
				</div>
			</div>
		</div>
	</section>
	<!-- ======= Counts Section ======= -->
	<section id="counts" class="counts">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="count-box">
						<i class="fas fa-user-md"></i>
						<span data-purecounter-start="0" data-purecounter-end="3" data-purecounter-duration="1" class="purecounter"></span>
						<p>Especialistas</p>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 mt-5 mt-md-0">
					<div class="count-box">
						<i class="far fa-hospital"></i>
						<span data-purecounter-start="0" data-purecounter-end="1" data-purecounter-duration="1" class="purecounter"></span>
						<p>Departmentos</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Counts Section -->
	<!-- ======= Services Section ======= -->
	<section id="services" class="services">
			<div class="container">
				<div class="section-title">
					<h2>Servicios</h2>
					<p></p>
				</div>
				<div class="row">
					<div class="col-lg-4 col-md-6 d-flex ">
						<div class="icon-box">
							<div class="icon"><i class="fas fa-heartbeat"></i></div>
							<h4>Clínica general</h4>
							<p >Consultas médicas.<br>Desparacitaciones.<br>Vacunaciones.<br>Entre otros.</p>
						</div>
					</div>
					<div class="col-lg-4 col-md-6 d-flex ">
						<div class="icon-box">
							<div class="icon"><i class="fas fa-notes-medical"></i></div>
							<h4>Urgencias</h4>
							<p>Veterinarios a domicilio.<br>Emergencias 24 horas.</p>
						</div>
					</div>
					<div class="col-lg-4 col-md-6 d-flex ">
						<div class="icon-box">
							<div class="icon"><i class="fas fa-hospital-user"></i></div>
							<h4>Interconsultas</h4>
							<p>Derivaciones.<br>Hospitalización.<br>Certificados para viajes.</p>
						</div>
					</div>
				</div>
			</div> 
	</section>
	<!-- ======= Tienda Section ======= -->
	<section id="tienda" class="tienda">
			<div class="container">
				<div class="section-title">
					<h2>Tienda</h2>
					<p>Productos que vendemos</p>
				</div>
				<div class="row">
					<div class="table-responsive">
					    <table class="table table-bordered table-sm table-hover mb-0 text-center">
				            <thead>
				                <tr style="background:#1977cc;color:white" class="text-center">
				                	<td>Img</td>
				                    <td>Nombre</td>
				                    <td>Descripción</td>
				                    <td>Precio</td>
				                    <td>Disponibilidad</td>
				                </tr>
				            </thead>
			           		<tbody>
				                <c:forEach items="${tiendaProductos}" var="prod">
				                	<tr>
				                	<td><img src="images/${prod.getImgLink()}" alt="Imagen del producto" width="150" height="150"></td>
				                 	<td>${prod.getNombre()}</td>
				                 	<td>${prod.getDescripcion()}</td>
				                 	<td>$ ${prod.getPrecio()}</td>
				                 	<c:choose>
									    <c:when test="${prod.getCantidad() > 10}">
									    	<td class="text-primary text-center">Disponible</td>
									    </c:when>    
									    <c:otherwise>
									    	<td class="text-danger text-center">Sin stock</td>
										</c:otherwise> 
									</c:choose>
				                	</tr>
				            	</c:forEach>
				            </tbody>
				        </table>
				    </div>
				</div>
			</div> 
	</section>
	</main><!-- End #main -->
	<!-- ======= Footer ======= -->
	<footer id="footer">
	</footer><!-- End Footer -->
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>
	<script src="assets/vendor/purecounter/purecounter.js"></script>
	<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="js/Clase.js"></script>
	<!-- Template Main JS File -->
  	<script src="assets/js/main.js"></script>
</body>
</html>