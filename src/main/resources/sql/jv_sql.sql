create table user_info (
	id varchar(36) not null,
	name varchar(100) not null,
	lastname varchar(100),
	email varchar(100) not null,
	password varchar(200),
	location varchar(100),
	createdTs timestamp,
	updatedTs timestamp,
	createdBy varchar(36),
	updatedBy varchar(36),
	constraint pk_user_info primary key (id)
);

