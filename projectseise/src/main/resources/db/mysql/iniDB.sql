CREATE DATABASE IF NOT EXISTS Conferences;

ALTER DATABASE Conferences
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON Conferences.* TO pc@localhost IDENTIFIED BY 'pc';

USE Conferences;

CREATE TABLE IF NOT EXISTS evento (
  ID_evento int(10)UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre varchar(40) DEFAULT NULL,
	fecha date DEFAULT NULL
) ENGINE=InnoDB ;

CREATE TABLE IF NOT EXISTS expositor (
  ID_expositor int(10)UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre varchar(40) DEFAULT NULL,
 sexo char(1) DEFAULT NULL,
 correo varchar(40) DEFAULT NULL,
 fono int(10) DEFAULT NULL,
 INDEX(nombre)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS invitado (

 ID_invitado int(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 nombre varchar(40) NOT NULL,
 correo varchar(40) NOT NULL,
 sexo char(1) NOT NULL,
 fono int(11) DEFAULT NULL,
 INDEX(nombre)

) ENGINE=InnoDB ;
CREATE TABLE IF NOT EXISTS boleto (
 ID_boleto int(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 ID_invitado int(10) UNSIGNED NOT NULL,
 ID_evento int(10) UNSIGNED NOT NULL,
 nombre varchar(40) NOT NULL,
 fecha date NOT NULL,
 total int(11) NOT NULL,
 FOREIGN KEY (ID_invitado) REFERENCES invitado (ID_invitado),
 FOREIGN KEY (ID_evento) REFERENCES evento (ID_evento)
) ENGINE=InnoDB;
	
CREATE TABLE IF NOT EXISTS catalogo (
 ID_catalogo int(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 ID_evento int(10) UNSIGNED NOT NULL,
 nombreCat varchar(40) NOT NULL,
 cantidad_Productos int(10) DEFAULT NULL,
 FOREIGN KEY (ID_evento) REFERENCES evento (ID_evento)
) ENGINE=InnoDB ;

CREATE TABLE IF NOT EXISTS ponencia(
	ID_ponencia int(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ID_expositor int(10) UNSIGNED NOT NULL,
	ID_evento int(10) UNSIGNED NOT NULL,
	FOREIGN KEY (ID_evento) REFERENCES evento (ID_evento),	
   	FOREIGN KEY(ID_expositor) REFERENCES expositor(ID_expositor),
  	nombre varchar(40) NOT null,
   	hora_inicio time DEFAULT NULL, 
   	hora_termino time DEFAULT NULL
 )ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS producto (
    	ID_producto int(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
		ID_catalogo int(10) UNSIGNED NOT NULL,
   		FOREIGN KEY(ID_catalogo) REFERENCES catalogo(ID_catalogo),
		nombre varchar(40) NOT null,
		precio_venta int not null,
    		precio_compra int not null	
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(20) NOT NULL ,
  password VARCHAR(20) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  role varchar(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
) engine=InnoDB;