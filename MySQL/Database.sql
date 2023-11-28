CREATE DATABASE IF NOT EXISTS `bdenzostore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bdenzostore`;

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  `tipocontacto` varchar(45) DEFAULT NULL,
  `historial_compras` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `prendas`;
CREATE TABLE IF NOT EXISTS `prendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `colores` varchar(45) DEFAULT NULL,
  `talla` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `punto_referencia` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `prendas` TEXT NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `valortotal` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkpedidousuario` (`idusuario`),
  CONSTRAINT `fkpedidousuario` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `detalle_pedido_prenda`;
CREATE TABLE IF NOT EXISTS `detalle_pedido_prenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idventa` int(11) NOT NULL,
  `idprenda` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkventa` (`idventa`),
  CONSTRAINT `fkventa` FOREIGN KEY (`idventa`) REFERENCES `ventas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `usuarios` (`id`,`nombre`,`correo`,`contraseña`,`tipocontacto`,`historial_compras`) VALUES (1,'brayan batista','test','123','Cliente','test');
INSERT INTO `usuarios` (`id`,`nombre`,`correo`,`contraseña`,`tipocontacto`,`historial_compras`) VALUES (13,'brayan batista','test02','123','Cliente',NULL);

INSERT INTO `prendas` (`id`,`nombre`,`colores`,`talla`,`descripcion`,`precio`,`cantidad`,`estado`) VALUES (1,'test','','25','test',2000,100,'DISPONIBLE');
INSERT INTO `prendas` (`id`,`nombre`,`colores`,`talla`,`descripcion`,`precio`,`cantidad`,`estado`) VALUES (2,'test2','','25','test2',1000,100,'DISPONIBLE');
INSERT INTO `prendas` (`id`,`nombre`,`colores`,`talla`,`descripcion`,`precio`,`cantidad`,`estado`) VALUES (3,'test3','','25','test3',50,0,'AGOTADA');

DROP VIEW IF EXISTS `obtener_detalles_pedido`;
CREATE
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `obtener_detalles_pedido` AS
    SELECT 
        `vent`.`id` AS `id`,
        `usu`.`id` AS `idusuario`,
        `usu`.`nombre` AS `usuario`,
        `vent`.`prendas` AS `prendas`,
        `vent`.`cantidad` AS `cantidad`,
        `vent`.`valortotal` AS `total`
    FROM `ventas` `vent`
	JOIN `usuarios` `usu` ON (`usu`.`id` = `vent`.`idusuario`)
	ORDER BY `vent`.`id`
