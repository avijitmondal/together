INSERT INTO users (id, username, email, password, enabled) VALUES ('1', 'user1', 'user1@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true);
INSERT INTO users (id, username, email, password, enabled) VALUES ('2', 'user2', 'user2@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true);
INSERT INTO users (id, username, email, password, enabled) VALUES ('3', 'admin', 'admin@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true);

INSERT INTO authority (id, role) VALUES ('1', 'ROLE_USER');
INSERT INTO authority (id, role) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO authority_users (authority_id, users_id) VALUES ('1', '1');
INSERT INTO authority_users (authority_id, users_id) VALUES ('1', '2');
INSERT INTO authority_users (authority_id, users_id) VALUES ('1', '3');
INSERT INTO authority_users (authority_id, users_id) VALUES ('2', '3');
