create table `oauth_client_details` (
                                        `client_id` varchar (768),
                                        `resource_ids` varchar (768),
                                        `client_secret` varchar (768),
                                        `scope` varchar (768),
                                        `authorized_grant_types` varchar (768),
                                        `web_server_redirect_uri` varchar (768),
                                        `authorities` varchar (768),
                                        `access_token_validity` int (11),
                                        `refresh_token_validity` int (11),
                                        `additional_information` varchar (12288),
                                        `autoapprove` varchar (768)
);
insert into `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) values('USER_ADMIN_CLIENT','USER_CLIENT_APP,USER_CLIENT_RESOURCE,USER_ADMIN_RESOURCE,USER_OAUTH2_RESOURCE','415b4c63247f0ab6924bc198f7be156d','ROLE_SAD,ROLE_AD,ROLE_MGR,ROLE_USER','authorization_code,password,refresh_token,implicit,client_credentials','localhost:8080/login','READ,FULL_WRITE','86400','86400','{}','true');
insert into `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) values('USER_ADMIN_GIT_CLIENT','USER_CLIENT_APP,USER_CLIENT_RESOURCE,USER_ADMIN_RESOURCE,USER_OAUTH2_RESOURCE','b6567e8a56337002b6ec182b4e700f3a','ROLE_SAD,ROLE_AD,ROLE_MGR,ROLE_USER','authorization_code,password,refresh_token,implicit,client_credentials',NULL,'READ,FULL_WRITE','86400','86400','{}','true');

