CREATE TABLE `utenti` (
  `idUtente` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(35) DEFAULT NULL,
  `nome` varchar(35) DEFAULT NULL,
  `cognome` varchar(35) DEFAULT NULL,
  `password` varchar(556) DEFAULT NULL,
  `email` varchar(35) DEFAULT NULL unique,
  PRIMARY KEY (`idUtente`)
);

CREATE TABLE ruolo (
  idutente int(11) NOT NULL AUTO_INCREMENT,
  tipo_ruolo varchar(5) not null default "user",
  PRIMARY KEY(idutente),
  FOREIGN KEY (idutente) REFERENCES utenti(idUtente)
);


