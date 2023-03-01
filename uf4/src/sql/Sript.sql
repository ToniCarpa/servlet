CREATE DATABASE m06uf4servlets;
USE m06uf4servlets;

CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usuari` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` text,
  `message` text,
  `image` blob,
  `likes` int DEFAULT NULL,
  `dat` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_usuari` (`id_usuari`),
  CONSTRAINT `fk_post_usuari` FOREIGN KEY (`id_usuari`) REFERENCES `usuari` (`id`)
);

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuari` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `linkedin` varchar(255) DEFAULT NULL,
  `gitlab` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuari` (`usuari`)
);

insert into usuario values (1,'nomPrueba', 'tituloPrueba','http://google.com','Esto es una prueba','null');