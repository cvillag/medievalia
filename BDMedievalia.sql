-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
-- Text 2
-- Servidor: localhost
-- Tiempo de generación: 11-01-2018 a las 19:21:05
-- Versión del servidor: 5.5.58-0ubuntu0.14.04.1
-- Versión de PHP: 5.5.9-1ubuntu4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `medievaliaBD`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `action`
--

CREATE TABLE IF NOT EXISTS `action` (
  `idAction` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idAction`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=54 ;

--
-- Volcado de datos para la tabla `action`
--

INSERT INTO `action` (`idAction`, `action_name`) VALUES
(0, 'Acción Nula'),
(1, 'Login'),
(2, 'Editar perfil'),
(3, 'Lista de usuarios'),
(4, 'Crear usuario'),
(5, 'Borrar usuario'),
(6, 'Modificar usuario'),
(7, 'Acceso portal'),
(8, 'Logout'),
(9, 'Lista grupos general'),
(10, 'Lista grupos personal'),
(11, 'Detalle de actividad de usuario ajeno'),
(12, 'Detalle grupos director otro'),
(13, 'Detalle grupos director propio'),
(14, 'Detalle grupos profesor otro'),
(15, 'Detalle grupos profesor propio'),
(16, 'Detalle grupo alumnos propio'),
(17, 'Detalle grupo alumnos otro'),
(18, 'Log general'),
(19, 'Seleccionar grupo activo'),
(20, 'Crear nuevo grupo'),
(28, 'Modificar propio usuario'),
(29, 'Modificar propia contraseña de usuario'),
(30, 'Matricular alumno'),
(31, 'Matricular profesor'),
(32, 'Listar usuarios para matricular'),
(33, 'Ventana de lista de participantes de un grupo'),
(34, 'Desmatricular alumno de curso'),
(35, 'Desmatricular profesor de curso'),
(36, 'Ver lista de tipos de objeto'),
(37, 'Validar instancia de objeto'),
(38, 'Ver lista de instancias de un objeto'),
(39, 'Ver detalle de instancia de objeto'),
(40, 'Crear nueva instancia de objeto'),
(41, 'Borrar instancia de objeto validada'),
(42, 'Renombrar instancia de objeto validada'),
(43, 'Modificar instancia de objeto validada'),
(44, 'Validar atributo complejo de instancia'),
(45, 'Renombrar instancia de objeto propia (alumno)'),
(46, 'Borrar instancia de objeto propia (alumno)'),
(47, 'Listado de objetos sin validar por grupo'),
(48, 'Modificar instancia de objeto propio (alumno)'),
(49, 'Listado de objetos (sin) validar por grupo/usuario'),
(50, 'Marcar comentario de objeto como leído'),
(51, 'Marcar comentario de atributo complejo como leído'),
(52, 'Ver estadísticas de objetos creados por tipo'),
(53, 'Ver estadísticas de objetos propios creados /tipo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `AtributoComplejoObjeto`
--

CREATE TABLE IF NOT EXISTS `AtributoComplejoObjeto` (
  `idObjetoPadre` int(11) NOT NULL DEFAULT '0',
  `idObjetoHijo` int(11) NOT NULL DEFAULT '0',
  `NombreAtributo` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `idObjetoRelacion` int(11) DEFAULT NULL,
  `conFecha` int(11) NOT NULL DEFAULT '0',
  `conPaginaDoc` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idObjetoPadre`,`idObjetoHijo`),
  KEY `idObjetoHijo` (`idObjetoHijo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `AtributoComplejoObjeto`:
--   `idObjetoPadre`
--       `ObjetoDOM` -> `idObjeto`
--   `idObjetoHijo`
--       `ObjetoDOM` -> `idObjeto`
--

--
-- Volcado de datos para la tabla `AtributoComplejoObjeto`
--

INSERT INTO `AtributoComplejoObjeto` (`idObjetoPadre`, `idObjetoHijo`, `NombreAtributo`, `idObjetoRelacion`, `conFecha`, `conPaginaDoc`) VALUES
(3, 1, 'Cargos del personaje', 11, 1, 1),
(3, 2, 'Estudios del personaje', 11, 1, 1),
(10, 3, 'Implicados', 11, 0, 1),
(10, 4, 'Localizaciones', 11, 0, 1),
(10, 6, 'Tema asociado', 0, 0, 0),
(10, 7, 'Subtema asociado', 0, 0, 0),
(10, 11, 'Documentación', 11, 0, 1),
(11, 4, 'Lugar asociado', 0, 0, 0),
(11, 9, 'Autor', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `AtributoSencilloObjeto`
--

CREATE TABLE IF NOT EXISTS `AtributoSencilloObjeto` (
  `idAtributo` int(11) NOT NULL DEFAULT '0',
  `idObjeto` int(11) NOT NULL DEFAULT '0',
  `nombreAtributo` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` int(11) DEFAULT NULL,
  `subtipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAtributo`,`idObjeto`),
  KEY `idObjeto` (`idObjeto`),
  KEY `tipo_fk` (`tipo`),
  KEY `subtipo` (`subtipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `AtributoSencilloObjeto`:
--   `idObjeto`
--       `ObjetoDOM` -> `idObjeto`
--   `subtipo`
--       `ObjetoDOM` -> `idObjeto`
--   `tipo`
--       `Tipos` -> `idTipo`
--

--
-- Volcado de datos para la tabla `AtributoSencilloObjeto`
--

INSERT INTO `AtributoSencilloObjeto` (`idAtributo`, `idObjeto`, `nombreAtributo`, `tipo`, `subtipo`) VALUES
(1, 3, 'Fecha de nacimiento', 1, NULL),
(1, 7, 'Tema:', 6, 6),
(1, 10, 'Regesto:', 5, NULL),
(1, 11, 'Está en:', 6, 12),
(2, 3, 'Fecha de fallecimiento', 1, NULL),
(2, 11, 'Fecha de publicación', 1, NULL),
(3, 3, 'Otros', 5, NULL),
(4, 3, 'Lugar de nacimiento', 6, 4),
(5, 3, 'Lugar de fallecimiento', 6, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorization`
--

CREATE TABLE IF NOT EXISTS `authorization` (
  `idRol` int(11) NOT NULL,
  `idAction` int(11) NOT NULL,
  PRIMARY KEY (`idRol`,`idAction`),
  KEY `idAction` (`idAction`),
  KEY `idRol` (`idRol`),
  KEY `idRol_2` (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `authorization`:
--   `idAction`
--       `action` -> `idAction`
--   `idRol`
--       `role` -> `idRol`
--

--
-- Volcado de datos para la tabla `authorization`
--

INSERT INTO `authorization` (`idRol`, `idAction`) VALUES
(1, 1),
(2, 1),
(3, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(2, 7),
(3, 7),
(1, 8),
(2, 8),
(3, 8),
(1, 9),
(1, 10),
(2, 10),
(3, 10),
(1, 11),
(1, 12),
(1, 13),
(2, 13),
(1, 16),
(2, 16),
(3, 16),
(1, 17),
(2, 17),
(1, 18),
(2, 19),
(3, 19),
(2, 20),
(1, 28),
(2, 28),
(3, 28),
(1, 29),
(2, 29),
(3, 29),
(2, 30),
(2, 31),
(2, 32),
(2, 33),
(2, 34),
(2, 35),
(1, 36),
(2, 36),
(3, 36),
(2, 37),
(2, 38),
(3, 38),
(2, 39),
(3, 39),
(2, 40),
(3, 40),
(2, 41),
(2, 42),
(2, 43),
(3, 43),
(2, 44),
(3, 45),
(3, 46),
(2, 47),
(3, 48),
(3, 49),
(3, 50),
(3, 51),
(2, 52),
(3, 53);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `idGroup` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `director` int(11) NOT NULL,
  `description` varchar(250) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idGroup`),
  UNIQUE KEY `name` (`name`),
  KEY `director` (`director`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=3 ;

--
-- RELACIONES PARA LA TABLA `groups`:
--   `director`
--       `users` -> `user_id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaAtributoComplejo`
--

CREATE TABLE IF NOT EXISTS `InstanciaAtributoComplejo` (
  `idObjetoPadre` int(11) NOT NULL DEFAULT '0',
  `idObjetoHijo` int(11) NOT NULL DEFAULT '0',
  `idInstanciaPadre` int(11) NOT NULL DEFAULT '0',
  `idInstanciaHijo` int(11) NOT NULL DEFAULT '0',
  `validado` int(11) DEFAULT NULL,
  `textoValidacion` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `idGrupo` int(11) DEFAULT NULL,
  `creador` int(11) DEFAULT NULL,
  `textoLeido` int(11) NOT NULL DEFAULT '0',
  `idObjetoRelacion` int(11) DEFAULT NULL,
  `idInstanciaRelacion` int(11) DEFAULT NULL,
  `conFecha` int(11) NOT NULL DEFAULT '0',
  `diaI` int(11) DEFAULT NULL,
  `mesI` int(11) DEFAULT NULL,
  `anioI` int(11) DEFAULT NULL,
  `diaF` int(11) DEFAULT NULL,
  `mesF` int(11) DEFAULT NULL,
  `anioF` int(11) DEFAULT NULL,
  `conPaginaDoc` int(11) NOT NULL DEFAULT '0',
  `paginaDoc` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idObjetoPadre`,`idObjetoHijo`,`idInstanciaPadre`,`idInstanciaHijo`),
  KEY `idGrupo` (`idGrupo`),
  KEY `creador` (`creador`),
  KEY `idObjetoPadre` (`idObjetoPadre`,`idInstanciaPadre`),
  KEY `idObjetoHijo` (`idObjetoHijo`,`idInstanciaHijo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `InstanciaAtributoComplejo`:
--   `idGrupo`
--       `groups` -> `idGroup`
--   `creador`
--       `users` -> `user_id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaAtributoDate`
--

CREATE TABLE IF NOT EXISTS `InstanciaAtributoDate` (
  `idInstancia` int(11) NOT NULL DEFAULT '0',
  `idAtributoSencillo` int(11) NOT NULL DEFAULT '0',
  `idObjeto` int(11) NOT NULL DEFAULT '0',
  `dia` int(11) DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `anio` int(11) DEFAULT NULL,
  PRIMARY KEY (`idInstancia`,`idAtributoSencillo`,`idObjeto`),
  KEY `idAtributoSencillo` (`idAtributoSencillo`,`idObjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `InstanciaAtributoDate`:
--   `idInstancia`
--       `InstanciaObjeto` -> `idInstancia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaAtributoDouble`
--

CREATE TABLE IF NOT EXISTS `InstanciaAtributoDouble` (
  `idInstancia` int(11) NOT NULL DEFAULT '0',
  `idAtributoSencillo` int(11) NOT NULL DEFAULT '0',
  `idObjeto` int(11) NOT NULL DEFAULT '0',
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`idInstancia`,`idAtributoSencillo`,`idObjeto`),
  KEY `idAtributoSencillo` (`idAtributoSencillo`,`idObjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `InstanciaAtributoDouble`:
--   `idInstancia`
--       `InstanciaObjeto` -> `idInstancia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaAtributoInt`
--

CREATE TABLE IF NOT EXISTS `InstanciaAtributoInt` (
  `idInstancia` int(11) NOT NULL DEFAULT '0',
  `idAtributoSencillo` int(11) NOT NULL DEFAULT '0',
  `idObjeto` int(11) NOT NULL DEFAULT '0',
  `valor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idInstancia`,`idAtributoSencillo`,`idObjeto`),
  KEY `idAtributoSencillo` (`idAtributoSencillo`,`idObjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `InstanciaAtributoInt`:
--   `idInstancia`
--       `InstanciaObjeto` -> `idInstancia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaAtributoObjeto`
--

CREATE TABLE IF NOT EXISTS `InstanciaAtributoObjeto` (
  `idObjeto` int(11) NOT NULL,
  `idInstancia` int(11) NOT NULL,
  `idAtributoSencillo` int(11) NOT NULL,
  `idObjetoHijo` int(11) NOT NULL,
  `idInstanciaHijo` int(11) NOT NULL,
  PRIMARY KEY (`idObjeto`,`idInstancia`,`idAtributoSencillo`,`idObjetoHijo`,`idInstanciaHijo`),
  KEY `idObjeto` (`idObjeto`,`idAtributoSencillo`),
  KEY `idAtributoSencillo` (`idAtributoSencillo`,`idObjeto`),
  KEY `idObjetoHijo` (`idObjetoHijo`,`idInstanciaHijo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaAtributoString`
--

CREATE TABLE IF NOT EXISTS `InstanciaAtributoString` (
  `idInstancia` int(11) NOT NULL DEFAULT '0',
  `idAtributoSencillo` int(11) NOT NULL DEFAULT '0',
  `idObjeto` int(11) NOT NULL DEFAULT '0',
  `valor` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idInstancia`,`idAtributoSencillo`,`idObjeto`),
  KEY `idAtributoSencillo` (`idAtributoSencillo`,`idObjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `InstanciaAtributoString`:
--   `idInstancia`
--       `InstanciaObjeto` -> `idInstancia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaAtributoText`
--

CREATE TABLE IF NOT EXISTS `InstanciaAtributoText` (
  `idInstancia` int(11) NOT NULL DEFAULT '0',
  `idAtributoSencillo` int(11) NOT NULL DEFAULT '0',
  `idObjeto` int(11) NOT NULL DEFAULT '0',
  `valor` longtext COLLATE utf8_spanish2_ci,
  PRIMARY KEY (`idInstancia`,`idAtributoSencillo`,`idObjeto`),
  KEY `idAtributoSencillo` (`idAtributoSencillo`,`idObjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `InstanciaAtributoText`:
--   `idInstancia`
--       `InstanciaObjeto` -> `idInstancia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `InstanciaObjeto`
--

CREATE TABLE IF NOT EXISTS `InstanciaObjeto` (
  `idInstancia` int(11) NOT NULL DEFAULT '0',
  `idObjeto` int(11) NOT NULL DEFAULT '0',
  `nombreInstancia` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `validado` int(11) DEFAULT NULL,
  `textoValidacion` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `idGrupo` int(11) DEFAULT NULL,
  `creador` int(11) DEFAULT NULL,
  `textoLeido` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idInstancia`,`idObjeto`),
  KEY `idObjeto` (`idObjeto`),
  KEY `idGrupo` (`idGrupo`),
  KEY `creador` (`creador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `InstanciaObjeto`:
--   `idObjeto`
--       `ObjetoDOM` -> `idObjeto`
--   `idGrupo`
--       `groups` -> `idGroup`
--   `creador`
--       `users` -> `user_id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `idUser` int(11) DEFAULT NULL,
  `idAction` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `idLog` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) COLLATE utf8_spanish2_ci NOT NULL,
  `success` int(11) NOT NULL,
  PRIMARY KEY (`idLog`),
  KEY `idAction` (`idAction`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=45463 ;

--
-- RELACIONES PARA LA TABLA `log`:
--   `idAction`
--       `action` -> `idAction`
--   `idUser`
--       `users` -> `user_id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ObjetoDOM`
--

CREATE TABLE IF NOT EXISTS `ObjetoDOM` (
  `idObjeto` int(11) NOT NULL AUTO_INCREMENT,
  `nombreObjeto` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idObjeto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=14 ;

--
-- Volcado de datos para la tabla `ObjetoDOM`
--

INSERT INTO `ObjetoDOM` (`idObjeto`, `nombreObjeto`) VALUES
(1, 'Cargos'),
(2, 'Estudios'),
(3, 'Personajes'),
(4, 'Lugares'),
(6, 'Temas'),
(7, 'Subtemas'),
(9, 'Autores'),
(10, 'Datos'),
(11, 'Documentación'),
(12, 'Archivos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombreRol` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`idRol`, `nombreRol`) VALUES
(0, 'Otro'),
(1, 'Administrador'),
(2, 'Profesor'),
(3, 'Alumno'),
(4, 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `students`
--

CREATE TABLE IF NOT EXISTS `students` (
  `idStudent` int(11) NOT NULL,
  `idGroup` int(11) NOT NULL,
  PRIMARY KEY (`idStudent`,`idGroup`),
  KEY `idGroup` (`idGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `students`:
--   `idGroup`
--       `groups` -> `idGroup`
--   `idStudent`
--       `users` -> `user_id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teachers`
--

CREATE TABLE IF NOT EXISTS `teachers` (
  `idTeacher` int(11) NOT NULL,
  `idGroup` int(11) NOT NULL,
  PRIMARY KEY (`idTeacher`,`idGroup`),
  KEY `idGroup` (`idGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- RELACIONES PARA LA TABLA `teachers`:
--   `idGroup`
--       `groups` -> `idGroup`
--   `idTeacher`
--       `users` -> `user_id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Tipos`
--

CREATE TABLE IF NOT EXISTS `Tipos` (
  `idTipo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `descLarga` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `Tipos`
--

INSERT INTO `Tipos` (`idTipo`, `descripcion`, `descLarga`) VALUES
(1, 'Date', 'Fecha'),
(2, 'Double', 'Número real'),
(3, 'Int', 'Número entero'),
(4, 'String', 'Texto corto'),
(5, 'Text', 'Texto largo'),
(6, 'Objeto', 'Un atributo que únicamente puede tomar un único va');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `user_long_name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `user_pass` varbinary(250) NOT NULL,
  `user_role` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `user_role` (`user_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci AUTO_INCREMENT=6 ;

--
-- RELACIONES PARA LA TABLA `users`:
--   `user_role`
--       `role` -> `idRol`
--

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_long_name`, `user_pass`, `user_role`) VALUES
(0, 'nulo', 'nulo', 'nulo', 0),
(2, 'admin', 'Administrador', '\n=r�tvaA.,ǯ', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `AtributoComplejoObjeto`
--
ALTER TABLE `AtributoComplejoObjeto`
  ADD CONSTRAINT `AtributoComplejoObjeto_ibfk_1` FOREIGN KEY (`idObjetoPadre`) REFERENCES `ObjetoDOM` (`idObjeto`),
  ADD CONSTRAINT `AtributoComplejoObjeto_ibfk_2` FOREIGN KEY (`idObjetoHijo`) REFERENCES `ObjetoDOM` (`idObjeto`);

--
-- Filtros para la tabla `AtributoSencilloObjeto`
--
ALTER TABLE `AtributoSencilloObjeto`
  ADD CONSTRAINT `AtributoSencilloObjeto_ibfk_1` FOREIGN KEY (`idObjeto`) REFERENCES `ObjetoDOM` (`idObjeto`),
  ADD CONSTRAINT `AtributoSencilloObjeto_ibfk_2` FOREIGN KEY (`subtipo`) REFERENCES `ObjetoDOM` (`idObjeto`),
  ADD CONSTRAINT `tipo_fk` FOREIGN KEY (`tipo`) REFERENCES `Tipos` (`idTipo`);

--
-- Filtros para la tabla `authorization`
--
ALTER TABLE `authorization`
  ADD CONSTRAINT `authorization_ibfk_1` FOREIGN KEY (`idAction`) REFERENCES `action` (`idAction`),
  ADD CONSTRAINT `authorization_ibfk_2` FOREIGN KEY (`idRol`) REFERENCES `role` (`idRol`);

--
-- Filtros para la tabla `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`director`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `InstanciaAtributoComplejo`
--
ALTER TABLE `InstanciaAtributoComplejo`
  ADD CONSTRAINT `InstanciaAtributoComplejo_ibfk_1` FOREIGN KEY (`idObjetoPadre`, `idObjetoHijo`) REFERENCES `AtributoComplejoObjeto` (`idObjetoPadre`, `idObjetoHijo`),
  ADD CONSTRAINT `InstanciaAtributoComplejo_ibfk_4` FOREIGN KEY (`idGrupo`) REFERENCES `groups` (`idGroup`),
  ADD CONSTRAINT `InstanciaAtributoComplejo_ibfk_5` FOREIGN KEY (`creador`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `InstanciaAtributoComplejo_ibfk_6` FOREIGN KEY (`idObjetoPadre`, `idInstanciaPadre`) REFERENCES `InstanciaObjeto` (`idObjeto`, `idInstancia`) ON DELETE CASCADE,
  ADD CONSTRAINT `InstanciaAtributoComplejo_ibfk_7` FOREIGN KEY (`idObjetoHijo`, `idInstanciaHijo`) REFERENCES `InstanciaObjeto` (`idObjeto`, `idInstancia`) ON DELETE CASCADE;

--
-- Filtros para la tabla `InstanciaAtributoDate`
--
ALTER TABLE `InstanciaAtributoDate`
  ADD CONSTRAINT `InstanciaAtributoDate_ibfk_2` FOREIGN KEY (`idAtributoSencillo`, `idObjeto`) REFERENCES `AtributoSencilloObjeto` (`idAtributo`, `idObjeto`),
  ADD CONSTRAINT `InstanciaAtributoDate_ibfk_3` FOREIGN KEY (`idInstancia`) REFERENCES `InstanciaObjeto` (`idInstancia`) ON DELETE CASCADE;

--
-- Filtros para la tabla `InstanciaAtributoDouble`
--
ALTER TABLE `InstanciaAtributoDouble`
  ADD CONSTRAINT `InstanciaAtributoDouble_ibfk_2` FOREIGN KEY (`idAtributoSencillo`, `idObjeto`) REFERENCES `AtributoSencilloObjeto` (`idAtributo`, `idObjeto`),
  ADD CONSTRAINT `InstanciaAtributoDouble_ibfk_3` FOREIGN KEY (`idInstancia`) REFERENCES `InstanciaObjeto` (`idInstancia`) ON DELETE CASCADE;

--
-- Filtros para la tabla `InstanciaAtributoInt`
--
ALTER TABLE `InstanciaAtributoInt`
  ADD CONSTRAINT `InstanciaAtributoInt_ibfk_2` FOREIGN KEY (`idAtributoSencillo`, `idObjeto`) REFERENCES `AtributoSencilloObjeto` (`idAtributo`, `idObjeto`),
  ADD CONSTRAINT `InstanciaAtributoInt_ibfk_3` FOREIGN KEY (`idInstancia`) REFERENCES `InstanciaObjeto` (`idInstancia`) ON DELETE CASCADE;

--
-- Filtros para la tabla `InstanciaAtributoObjeto`
--
ALTER TABLE `InstanciaAtributoObjeto`
  ADD CONSTRAINT `InstanciaAtributoObjeto_ibfk_2` FOREIGN KEY (`idAtributoSencillo`, `idObjeto`) REFERENCES `AtributoSencilloObjeto` (`idAtributo`, `idObjeto`),
  ADD CONSTRAINT `InstanciaAtributoObjeto_ibfk_4` FOREIGN KEY (`idObjeto`, `idInstancia`) REFERENCES `InstanciaObjeto` (`idObjeto`, `idInstancia`) ON DELETE CASCADE,
  ADD CONSTRAINT `InstanciaAtributoObjeto_ibfk_5` FOREIGN KEY (`idObjetoHijo`, `idInstanciaHijo`) REFERENCES `InstanciaObjeto` (`idObjeto`, `idInstancia`) ON DELETE CASCADE;

--
-- Filtros para la tabla `InstanciaAtributoString`
--
ALTER TABLE `InstanciaAtributoString`
  ADD CONSTRAINT `InstanciaAtributoString_ibfk_2` FOREIGN KEY (`idAtributoSencillo`, `idObjeto`) REFERENCES `AtributoSencilloObjeto` (`idAtributo`, `idObjeto`),
  ADD CONSTRAINT `InstanciaAtributoString_ibfk_3` FOREIGN KEY (`idInstancia`) REFERENCES `InstanciaObjeto` (`idInstancia`) ON DELETE CASCADE;

--
-- Filtros para la tabla `InstanciaAtributoText`
--
ALTER TABLE `InstanciaAtributoText`
  ADD CONSTRAINT `InstanciaAtributoText_ibfk_2` FOREIGN KEY (`idAtributoSencillo`, `idObjeto`) REFERENCES `AtributoSencilloObjeto` (`idAtributo`, `idObjeto`),
  ADD CONSTRAINT `InstanciaAtributoText_ibfk_3` FOREIGN KEY (`idInstancia`) REFERENCES `InstanciaObjeto` (`idInstancia`) ON DELETE CASCADE;

--
-- Filtros para la tabla `InstanciaObjeto`
--
ALTER TABLE `InstanciaObjeto`
  ADD CONSTRAINT `InstanciaObjeto_ibfk_1` FOREIGN KEY (`idObjeto`) REFERENCES `ObjetoDOM` (`idObjeto`),
  ADD CONSTRAINT `InstanciaObjeto_ibfk_2` FOREIGN KEY (`idGrupo`) REFERENCES `groups` (`idGroup`),
  ADD CONSTRAINT `InstanciaObjeto_ibfk_3` FOREIGN KEY (`creador`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`idAction`) REFERENCES `action` (`idAction`),
  ADD CONSTRAINT `log_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`idGroup`) REFERENCES `groups` (`idGroup`),
  ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`idStudent`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `students_ibfk_3` FOREIGN KEY (`idGroup`) REFERENCES `groups` (`idGroup`);

--
-- Filtros para la tabla `teachers`
--
ALTER TABLE `teachers`
  ADD CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`idGroup`) REFERENCES `groups` (`idGroup`),
  ADD CONSTRAINT `teachers_ibfk_2` FOREIGN KEY (`idTeacher`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`user_role`) REFERENCES `role` (`idRol`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
