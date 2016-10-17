CREATE SEQUENCE seq_usuario
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE usuario (
    id integer DEFAULT nextval('seq_usuario') NOT NULL,
    email character varying(255),
    nome character varying(255),
    senha character varying(255),
    nascimento date
);


ALTER TABLE usuario
    ADD CONSTRAINT email_unique UNIQUE (email);

ALTER TABLE usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);

