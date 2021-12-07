create database db_oauth2;

create table IF NOT EXISTS oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

insert into oauth_client_details(client_id, resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove)
values('foo',null,'{bcrypt}$2a$10$LAGAF/ml4HqrhMpKpQP2.e6dYF/bIGGosVwya66DTsmPTrrPiXDEu','read,write,profile,email','authorization_code,password,client_credentials,implicit,refresh_token','http://localhost:8080/login/oauth2/code/local','ROLE_USER',36000,50000,null,null);
