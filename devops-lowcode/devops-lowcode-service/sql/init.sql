-- Table: public.lcp_datasource_info

-- DROP TABLE IF EXISTS public.lcp_datasource_info;

CREATE TABLE IF NOT EXISTS public.lcp_datasource_info
(
    id bigint NOT NULL,
    name character varying(20) COLLATE pg_catalog."default",
    host character varying(20) COLLATE pg_catalog."default",
    port character varying COLLATE pg_catalog."default",
    type smallint,
    username character varying(100) COLLATE pg_catalog."default",
    password character varying(100) COLLATE pg_catalog."default",
    create_user_id bigint,
    update_user_id bigint,
    delete_flag boolean,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    database character varying(100) COLLATE pg_catalog."default"
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lcp_datasource_info
    OWNER to postgres;

COMMENT ON TABLE public.lcp_datasource_info
    IS '数据源信息';

COMMENT ON COLUMN public.lcp_datasource_info.id
    IS '主键';

COMMENT ON COLUMN public.lcp_datasource_info.database
    IS '库名';