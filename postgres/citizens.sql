-- Sequence: public.user_id_seq

-- DROP SEQUENCE public.user_id_seq;

CREATE SEQUENCE public.user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.user_id_seq
  OWNER TO postgres;

  
-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  dni text NOT NULL,
  nombre text,
  apellidos text,
  password text,
  email text,
  nacimiento date,
  direccion text,
  nacionalidad text,
  polling integer,
  CONSTRAINT user_pkey PRIMARY KEY (dni),
  CONSTRAINT user_id_key UNIQUE (id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE public."user"
  OWNER TO postgres;

-- Sequence: public.category_id_seq

-- DROP SEQUENCE public.category_id_seq;

CREATE SEQUENCE public.category_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.category_id_seq
  OWNER TO postgres;

-- Table: public.category

-- DROP TABLE public.category;

CREATE TABLE public.category
(
  id integer NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  name text,
  CONSTRAINT category_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.category
  OWNER TO postgres;


  

  
  
  
-- Sequence: public.proposal_id_seq

-- DROP SEQUENCE public.proposal_id_seq;

CREATE SEQUENCE public.proposal_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.proposal_id_seq
  OWNER TO postgres;
  
  
-- Table: public.proposal

-- DROP TABLE public.proposal;

CREATE TABLE public.proposal
(
  id integer NOT NULL DEFAULT nextval('proposal_id_seq'::regclass),
  content text,
  votes integer,
  user_id integer,
  category_id integer,
  CONSTRAINT proposal_pkey PRIMARY KEY (id),
  CONSTRAINT "foreignKey_Category" FOREIGN KEY (category_id)
      REFERENCES public.category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT proposal_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.proposal
  OWNER TO postgres;

-- Index: public."fki_foreignKey_Category"

-- DROP INDEX public."fki_foreignKey_Category";

CREATE INDEX "fki_foreignKey_Category"
  ON public.proposal
  USING btree
  (category_id);



  -- Sequence: public.commentary_id_seq

-- DROP SEQUENCE public.commentary_id_seq;

CREATE SEQUENCE public.commentary_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.commentary_id_seq
  OWNER TO postgres;

CREATE TABLE public.commentary
(
  id integer NOT NULL DEFAULT nextval('commentary_id_seq'::regclass),
  content text,
  votes integer,
  fecha date,
  user_id integer,
  proposal_id integer,
  CONSTRAINT commentary_pkey PRIMARY KEY (id),
  CONSTRAINT commentary_proposal_id_fkey FOREIGN KEY (proposal_id)
      REFERENCES public.proposal (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT commentary_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.commentary
  OWNER TO postgres;

  
-- Table: public.words

-- DROP TABLE public.words;

CREATE TABLE public.words
(
  word text,
  id integer NOT NULL DEFAULT nextval('words_id_seq'::regclass),
  CONSTRAINT words_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.words
  OWNER TO postgres;


INSERT INTO public.user(
            id, dni, nombre, apellidos, email, password, nacimiento, direccion, polling, 
            nacionalidad)
    VALUES (1, '12345678A', 'Pepe', 'Calleja', 'calleja@email.com', 'password1234', 
      '1950-03-25', 'Oviedo', 2, 'Español');

INSERT INTO public.user(
            id, dni, nombre, apellidos, email, password, nacimiento, direccion, polling, 
            nacionalidad)
    VALUES (2, '87654321A', 'User', 'User', 'user@email.com', 'pass', 
      '1953-06-21', 'Oviedo', 1, 'Español');

INSERT INTO public.category(
            id, name)
    VALUES (1, 'General');

INSERT INTO public.proposal(
            id, content, votes, user_id, category_id)
    VALUES (1, 'Hacer un parque', 0, 1, 1);

INSERT INTO public.proposal(
            id, content, votes, user_id, category_id)
    VALUES (2, 'Hacer un monumento', 0, 1, 1);

INSERT INTO public.commentary(
            id, content, votes, fecha, user_id, proposal_id)
    VALUES (1, 'Buena idea el parque', 0, '2017-04-02', 2, 1);



