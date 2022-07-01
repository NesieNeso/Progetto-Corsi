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

create table corsi (
	id_corso int(11) auto_increment primary key,
    nome_corso varchar(32) not null unique,
    numero_pagine int(11) not null,
    descrizione text,
    autore varchar(32)
);

create table iscritto (
	id_corso int(11) not null,
    id_utente int(11) not null,
    pagina_attuale int(11) not null default 0,
    primary key(id_corso, id_utente),
    foreign key (id_corso) references corsi(id_corso),
    foreign key (id_utente) references utenti(idUtente)
);
