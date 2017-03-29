﻿
  
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
  apeliidos text,
  email text,
  nacimiento date,
  direccion text,
  nacinalidad text,
  polling integer,
  CONSTRAINT user_pkey PRIMARY KEY (dni),
  CONSTRAINT user_id_key UNIQUE (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
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
  category text,
  user_id integer,
  CONSTRAINT proposal_pkey PRIMARY KEY (id),
  CONSTRAINT proposal_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.proposal
  OWNER TO postgres;

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


