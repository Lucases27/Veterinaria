SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `historias` (
  `IdHistoria` int(11) NOT NULL,
  `IdMascota` int(11) NOT NULL,
  `Patologia` varchar(255) NOT NULL,
  `Vacunas` varchar(255) NOT NULL,
  `Descripcion` varchar(255) NOT NULL,
  `Fecha` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `historias` VALUES(1, 4, 'esta enfermito', 'Ninguna.', 'Lo trajeron hoy y lo diagnosticamos.', '2021-07-03 23:34:21');
INSERT INTO `historias` VALUES(2, 4, 'esta curado', 'todas', 'se curo el perrito', '2021-07-03 23:48:21');
INSERT INTO `historias` VALUES(3, 22, 'Ninguna', 'Todas', 'Esta re lindo', '2021-07-04 00:09:07');
INSERT INTO `historias` VALUES(4, 21, 'ninguna', 'todas', 'es una minina muy linda', '2021-08-05 01:17:21');

CREATE TABLE `mascotas` (
  `IdMascota` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Tipo` varchar(100) NOT NULL,
  `Edad` decimal(10,2) NOT NULL,
  `Peso` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `mascotas` VALUES(4, 2, 'lucho', 'perro', '2.00', '232.44');
INSERT INTO `mascotas` VALUES(5, 2, 'mimi', 'gato', '2.00', '3.00');
INSERT INTO `mascotas` VALUES(21, 2, 'minina', 'gatita', '2.00', '3.00');
INSERT INTO `mascotas` VALUES(22, 1, 'tito', 'perrito', '3.00', '45.00');
INSERT INTO `mascotas` VALUES(23, 12, 'mascotita', 'chiquitita', '2.00', '2.00');
INSERT INTO `mascotas` VALUES(24, 13, 'pechorcho', 'gato gordo', '5.00', '9.00');
INSERT INTO `mascotas` VALUES(27, 1, 'mimi', 'gata', '2.00', '3.90');

CREATE TABLE `productos` (
  `IdProducto` int(11) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Descripcion` varchar(255) NOT NULL,
  `ImgLink` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `productos` VALUES(1, 'Pipeta', '555.00', 50, 'pipeta para pulgas (Por Unidad)', 'Pipeta.jpg');
INSERT INTO `productos` VALUES(5, 'alimento', '200.00', 10, 'Balanceado (Por KG)', 'alimento.png');
INSERT INTO `productos` VALUES(8, 'alimento2', '1500.00', 88, 'Balanceado (POR BOLSA)', 'alimento2.jpg');
INSERT INTO `productos` VALUES(14, 'alimento de gato', '500.00', 3, 'juguete para gato', 'alimento de gato.jpg');
INSERT INTO `productos` VALUES(15, 'Pecera chica', '40.00', 32, 'pecera chiquita', 'Pecera chica.jpg');
INSERT INTO `productos` VALUES(16, 'pecera mediana', '400.00', 5, 'pecera mediana', 'pecera mediana.jpg');
INSERT INTO `productos` VALUES(17, 'alimento para gato2', '80.00', 500, 'alimento gato', 'alimento para gato2.jpg');
INSERT INTO `productos` VALUES(18, 'pecera grande', '450.00', 55, 'pecera grande', 'pecera grande.jpg');

CREATE TABLE `turnos` (
  `IdTurno` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL,
  `NombreApellido` varchar(100) NOT NULL,
  `NombreMascota` varchar(100) DEFAULT NULL,
  `TipoMascota` varchar(100) DEFAULT NULL,
  `Telefono` varchar(100) NOT NULL,
  `EsConsulta` tinyint(1) DEFAULT NULL,
  `EsUrgencia` tinyint(1) DEFAULT NULL,
  `Fecha` datetime NOT NULL,
  `Hora` datetime DEFAULT NULL,
  `Vigente` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `turnos` VALUES(4, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 00:00:00', NULL, 1);
INSERT INTO `turnos` VALUES(5, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 00:00:00', NULL, 1);
INSERT INTO `turnos` VALUES(6, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 00:00:00', NULL, 1);
INSERT INTO `turnos` VALUES(7, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 10:00:00', NULL, 1);
INSERT INTO `turnos` VALUES(8, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 11:00:00', NULL, 1);
INSERT INTO `turnos` VALUES(9, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 12:00:00', NULL, 0);
INSERT INTO `turnos` VALUES(10, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 17:00:00', NULL, 0);
INSERT INTO `turnos` VALUES(11, 11, 'admin', NULL, NULL, '2147483647', NULL, NULL, '1999-01-01 18:00:00', NULL, 0);
INSERT INTO `turnos` VALUES(50, 1, 'lucas saleme', NULL, NULL, '1126306411', NULL, NULL, '2021-07-30 18:00:00', NULL, 0);
INSERT INTO `turnos` VALUES(100, 13, 'agosto', NULL, NULL, '123123123', NULL, NULL, '2021-07-30 18:00:00', NULL, 0);
INSERT INTO `turnos` VALUES(124, 13, 'agosto', NULL, NULL, '123123123', NULL, NULL, '2021-08-05 09:00:00', NULL, 0);
INSERT INTO `turnos` VALUES(126, 1, 'lucas saleme', NULL, NULL, '1126306411', NULL, NULL, '2021-08-09 09:00:00', NULL, 0);
INSERT INTO `turnos` VALUES(131, 13, 'agosto', NULL, NULL, '123123123', NULL, NULL, '2021-08-09 09:00:00', NULL, 1);
INSERT INTO `turnos` VALUES(142, 1, 'lucas saleme', NULL, NULL, '1126306411', NULL, NULL, '2021-08-09 10:00:00', NULL, 1);

CREATE TABLE `usuarios` (
  `IdUsuario` int(11) NOT NULL,
  `Usuario` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `NombreApellido` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  `IsAdmin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `usuarios` VALUES(1, 'lucas', '1234', 'lucas saleme', 'lucasemiliano21@hotmail.com', '1126306411', 0);
INSERT INTO `usuarios` VALUES(2, 'Lucasemiliano21@hotmail.com', '	\r\nLucasemiliano21@hotmail.com', 'Lucas Emiliano', 'Lucasemiliano21@hotmail.com', '1126306411', 0);
INSERT INTO `usuarios` VALUES(3, 'Taniasosa@hotmail.com', 'TANIASOSA', 'TANIASOSA', 'Taniasosa@hotmail.com', '123123123', 0);
INSERT INTO `usuarios` VALUES(4, 'Alma.cs310@gmail.com', 'Lucas Emiliano', 'Lucas2', 'Alma.cs310@gmail.com', '1126306411', 0);
INSERT INTO `usuarios` VALUES(5, 'Lucasemiliano19@live.com.ar', 'lucas77', 'Lucas77', 'Lucasemiliano19@live.com.ar', '1126306411', 0);
INSERT INTO `usuarios` VALUES(6, 'Alma.cs12312@gmail.com', 'asdasdasd1', 'Lucas12312', 'Alma.cs12312@gmail.com', '123123123', 0);
INSERT INTO `usuarios` VALUES(7, 'Usuario2@usuario2.com', 'usuario2', 'usuario2', 'Usuario2@usuario2.com', '123123123', 0);
INSERT INTO `usuarios` VALUES(8, 'Lucasemiliano2122@hotmail.com', '123123123', 'Lucas Emiliano2', 'Lucasemiliano2122@hotmail.com', '1126306411', 0);
INSERT INTO `usuarios` VALUES(9, 'Usuario3@usuario3.com', 'Usuario3', 'Lucas Emiliano', 'Usuario3@usuario3.com', '1126306411', 0);
INSERT INTO `usuarios` VALUES(10, 'Juancarlos@juanca.com', '12345', 'Juan Carlos', 'Juancarlos@juanca.com', '2147483647', 0);
INSERT INTO `usuarios` VALUES(11, 'Admin@admin.com', '12345', 'admin', 'Admin@admin.com', '2147483647', 1);
INSERT INTO `usuarios` VALUES(12, 'Lucas.emiliano-_-@admin.com', 'admin', 'Lucas Emiliano2', 'Lucas.emiliano-_-@admin.com', '1126306411', 0);
INSERT INTO `usuarios` VALUES(13, 'Agosto@agosto.com', 'agosto', 'agosto', 'Agosto@agosto.com', '123123123', 0);


ALTER TABLE `historias`
  ADD PRIMARY KEY (`IdHistoria`);

ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`IdMascota`);

ALTER TABLE `productos`
  ADD PRIMARY KEY (`IdProducto`);

ALTER TABLE `turnos`
  ADD PRIMARY KEY (`IdTurno`);

ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`IdUsuario`);


ALTER TABLE `historias`
  MODIFY `IdHistoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

ALTER TABLE `mascotas`
  MODIFY `IdMascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

ALTER TABLE `productos`
  MODIFY `IdProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

ALTER TABLE `turnos`
  MODIFY `IdTurno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

ALTER TABLE `usuarios`
  MODIFY `IdUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
