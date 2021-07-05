CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY auto_increment,
    username VARCHAR(50) UNIQUE,
    email VARCHAR(50) UNIQUE,
    password VARCHAR(256),
    enabled BOOL,
    activation_key VARCHAR(50) DEFAULT NULL,
    reset_password_key VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS authority (
    id BIGINT PRIMARY KEY auto_increment,
    role enum('ROLE_USER','ROLE_ADMIN') DEFAULT 'ROLE_USER'
);

CREATE TABLE IF NOT EXISTS authority_users (
    users_id BIGINT not null,
    authority_id BIGINT not null,
    FOREIGN KEY (users_id) REFERENCES users (id),
    FOREIGN KEY (authority_id) REFERENCES authority (id),
    UNIQUE INDEX user_authority_idx_1 (users_id, authority_id)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
    token_id VARCHAR(256) DEFAULT NULL,
    token BLOB,
    authentication_id VARCHAR(256) DEFAULT NULL,
    user_name VARCHAR(256) DEFAULT NULL,
    client_id VARCHAR(256) DEFAULT NULL,
    authentication BLOB,
    refresh_token VARCHAR(256) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
    token_id VARCHAR(256) DEFAULT NULL,
    token BLOB,
    authentication BLOB
);
