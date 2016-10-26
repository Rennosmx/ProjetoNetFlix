CREATE SEQUENCE seq_usuario
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE usuario (
    id integer DEFAULT nextval('seq_usuario') PRIMARY KEY NOT NULL,
    email character varying(255) UNIQUE NOT NULL,
    nome character varying(255),
    senha character varying(255),
    nascimento date,
    is_admin boolean DEFAULT FALSE NOT NULL
);

CREATE SEQUENCE seq_filme
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE TABLE filme (
    id integer DEFAULT nextval('seq_filme') PRIMARY KEY NOT NULL,
    titulo character varying(255),
    ano integer,
    genero character varying(255),
    duracao integer,
    ator_principal character varying(255),
    idade integer,
    diretor text,
    descricao text,
    arquivo_capa character varying(255),
    arquivo_midia character varying(255) 
);
