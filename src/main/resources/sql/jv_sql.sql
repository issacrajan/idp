
create table user_info (
	id varchar(36) not null,
	name varchar(100) not null,
	lastname varchar(100),
	email varchar(100) not null,
	password varchar(200),
	location varchar(100),
	created_Ts timestamp,
	updated_Ts timestamp,
	created_By varchar(36),
	updated_By varchar(36),
	constraint pk_user_info primary key (id)
);


CREATE TABLE oauth2_registered_client (
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(200) DEFAULT NULL,
    client_secret_expires_at timestamp DEFAULT NULL,
    client_name varchar(200) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(1000) NOT NULL,
    redirect_uris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
    created_Ts timestamp,
	updated_Ts timestamp,
	created_By varchar(36),
	updated_By varchar(36),
    constraint pk_oauth2_registered_client PRIMARY KEY (id)
);

CREATE TABLE oauth2_authorization_consent (
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorities varchar(1000) NOT NULL,
    constraint pk_oauth2_authorization_consent PRIMARY KEY (registered_client_id, principal_name)
);


/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
*/
CREATE TABLE oauth2_authorization (
    id varchar(100) NOT NULL,
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorization_grant_type varchar(100) NOT NULL,
    authorized_scopes varchar(1000) DEFAULT NULL,
    attributes text DEFAULT NULL,
    state varchar(500) DEFAULT NULL,
    authorization_code_value text DEFAULT NULL,
    authorization_code_issued_at timestamp DEFAULT NULL,
    authorization_code_expires_at timestamp DEFAULT NULL,
    authorization_code_metadata text DEFAULT NULL,
    access_token_value text DEFAULT NULL,
    access_token_issued_at timestamp DEFAULT NULL,
    access_token_expires_at timestamp DEFAULT NULL,
    access_token_metadata text DEFAULT NULL,
    access_token_type varchar(100) DEFAULT NULL,
    access_token_scopes varchar(1000) DEFAULT NULL,
    oidc_id_token_value text DEFAULT NULL,
    oidc_id_token_issued_at timestamp DEFAULT NULL,
    oidc_id_token_expires_at timestamp DEFAULT NULL,
    oidc_id_token_metadata text DEFAULT NULL,
    refresh_token_value text DEFAULT NULL,
    refresh_token_issued_at timestamp DEFAULT NULL,
    refresh_token_expires_at timestamp DEFAULT NULL,
    refresh_token_metadata text DEFAULT NULL,
    PRIMARY KEY (id)
);



create table client_idp_info (
	client_id varchar(200) not null,
	client_secret varchar(500),
	idp_url varchar(200),
	domain_name varchar(200),
	login_method varchar(10),
	redirect_uri varchar(200),
	idp_provider varchar(20),
	well_known_endpoints_url varchar(200),
	constraint pk_client_idp_info primary key  (client_id)
);

insert into client_idp_info (
client_Id, client_secret, idp_url, domain_name, login_method, 
redirect_uri, idp_provider)
values ('client1', 
	
'http://localhost:8080/oauth2/authorize',
	   'localhost', 'openid',
	   'http://localhost:9090/auth/callback',
	   'spring');
	   
	   

