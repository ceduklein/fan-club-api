CREATE TABLE usuarios (
	id bigserial NOT NULL,
	carteirinha_emitida bool NOT NULL,
	carteirinha_enviada bool NOT NULL,
	cpf varchar(255) NOT NULL,
	data_nascimento date NOT NULL,
	email varchar(255) NOT NULL,
	is_admin bool NOT NULL,
	nome varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	telefone varchar(255) NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT uk_7kqluf7wl0oxs7n90fpya03ss UNIQUE (cpf),
	CONSTRAINT usuarios_pkey PRIMARY KEY (id)
);