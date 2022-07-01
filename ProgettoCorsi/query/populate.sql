Insert INTO utenti (nome, cognome, username, password, email) VALUES ('Gioele', 'Cufaj', 'JG', 'password', 'jg@gmail.com');
Insert INTO utenti (nome, cognome, username, password, email) VALUES ('Stefano', 'Toneatto', 'TS', 'password', 'ts@libero.com');
Insert INTO utenti (nome, cognome, username, password, email) VALUES ('Matteo', 'DallArco', 'DAM', 'password', 'dam@gmail.com');
Insert INTO utenti (nome, cognome, username, password, email) VALUES ('Andrea', 'Recchia', 'RA', 'password', 'ra@yahoo.com');
Insert INTO utenti (nome, cognome, username, password, email) VALUES ('Mario', 'Rossi', 'RM', 'password', 'rm@gmail.com');

Insert INTO ruolo (idutente, tipo_ruolo) VALUES (1, 'user');
Insert INTO ruolo (idutente, tipo_ruolo) VALUES (2, 'user');
Insert INTO ruolo (idutente, tipo_ruolo) VALUES (3, 'user');
Insert INTO ruolo (idutente, tipo_ruolo) VALUES (4, 'user');
Insert INTO ruolo (idutente, tipo_ruolo) VALUES (5, 'admin');

insert into corsi (nome_corso, numero_pagine, descrizione, autore) values ('cucina', 4, "Preparare la pasta non è mai stato così semplice", 'Chef Tony');
insert into corsi (nome_corso, numero_pagine, descrizione, autore) values ('uncinetto', 4, "Gli amigurumi di zia Betty", 'Betty');
insert into corsi (nome_corso, numero_pagine, descrizione, autore) values ('falegnameria', 5, "I primi passi col compensato", 'Eusebio');

insert into iscritto (id_corso, id_utente) values (1, 1);
insert into iscritto (id_corso, id_utente, pagina_attuale) values (1, 2, 3);
insert into iscritto (id_corso, id_utente) values (2, 2);
insert into iscritto (id_corso, id_utente) values (2, 1);