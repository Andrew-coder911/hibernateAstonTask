-- SEQUENCE: public.users_id_seq

-- DROP SEQUENCE IF EXISTS public.users_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.users_id_seq
    OWNER TO testuser;
-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    age integer,
    created_at timestamp without time zone NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
)