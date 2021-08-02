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
  <link href="assets/css/styleLogin.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: Medilab - v4.1.0
  * Template URL: https://bootstrapmade.com/medilab-free-medical-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
	<!-- SI YA ESTA LOGEADO REDIRECCIONA A INDEX.JSP. -->
	<c:choose>  
		<c:when test="${logeado}">  
			<c:redirect url="index.jsp"/>
		</c:when>
	</c:choose>
  <!-- ======= Top Bar ======= -->
  <div id="topbar" class="d-flex align-items-center fixed-top">
    <div class="container d-flex justify-content-between">
      <div class="contact-info d-flex align-items-center">
        <i class="bi bi-envelope"></i> <a href="simple_vet@gmail.com">simple_vet@gmail.com</a>
        <i class="bi bi-phone"></i> +1532528809
      </div>
      <div class="d-none d-lg-flex social-links align-items-center">
        <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
        <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
        <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
        <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
      </div>
    </div>
  </div>
  <header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

      <h1 class="logo me-auto"><a href="index.jsp">Simple Vet</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

	<nav id="navbar" class="navbar order-last order-lg-0">
		<ul>
	    	<li><a class="nav-link scrollto active" href="index.jsp">Home</a></li>
		  	<li><a class="nav-link scrollto" href="index.jsp#about">Sobre app</a></li>
		  	<li><a class="nav-link scrollto" href="index.jsp#services">Servicios</a></li>
		  	<li><a class="nav-link scrollto" href="index.jsp#departments">Tienda</a></li>
			<li><a class="nav-link scrollto" href="Login?menuLogin=1">Ingresar</a></li>
			<li><a class="nav-link scrollto" href="Login?menuLogin=2">Registrarme</a></li>
	   </ul>
	<i class="bi bi-list mobile-nav-toggle"></i>
	
    </nav><!-- .navbar -->

      <a href="#appointment" class="appointment-btn scrollto"><span class="d-none d-md-inline">Consulta vía </span>WhatsApp</a>

    </div>
  </header><!-- End Header -->

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex align-items-center">
    <div class="container">
      <h1>Bienvenido a Simple vet</h1>
      <h2>Atencion personalizada para tu mascota.</h2>
    </div>
  </section><!-- End Hero -->
  
	<c:choose>
		<c:when test="${param.menuLogin == 1}">
			<jsp:include page="includes/iniciar_sesion.jsp"></jsp:include>
		</c:when>
		<c:when test="${param.menuLogin == 2}">
			<jsp:include page="includes/registrarme.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<div>
			
			</div>
		</c:otherwise>
	</c:choose>
	
	<!-- MENSAJE DE EXITO/ERROR -->
	<div class="text-center mb-3">
		<h5 class='text-danger'>
			<c:out value="${errores}"></c:out>
		</h5>
		<h5 class='text-success'>
			<c:out value="${success}"></c:out>
		</h5>
	</div>
</body>
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
<!-- SCRIPT DE BOTONES REGISTRO/LOGIN -->
<script type="text/javascript">
	function Desactivar(){
	    document.getElementById("fecha").disabled=true;
	    document.getElementById("hora").disabled=true;
	}
	function Activar(){
	document.getElementById("fecha").disabled=false;
	document.getElementById("hora").disabled=false;
	}
	function MostrarIni(){
	document.getElementById("faq").style.display="block";
	document.getElementById("secreg").style.display="none";
	}
	function MostrarReg(){
	document.getElementById("secreg").style.display="block";
	document.getElementById("faq").style.display="none";
	}
</script>

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
</html>