

INSERT INTO personnel (personnel_id, fonction, nom_personnel, prenom_personnel, active) VALUES ('1', 'ADMIN', 'ADMIN', 'ADMIN', true);

INSERT INTO users (user_id, email, password, username, personnel_id, language, current_ip_adress,active,blocked) VALUES ('1', 'admin@yahoo.com', '$2a$10$1KPYrUcPMtvTn1T/x7IIQOO.SS3LqZ40dZvlBaOgro741nWnyW86u', 'admin', '1', 'fr', null, true,false);

UPDATE users SET language = 'fr' WHERE (user_id = '1');

 INSERT INTO roles(role_id,role_name) VALUES('1','ROLE_ADMIN');

 INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1');



