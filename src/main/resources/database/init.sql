CREATE DATABASE shortUrl;

CREATE TABLE public.url_mapping
(
    id SERIAL NOT NULL,
    short_url VARCHAR(256),
    original_url VARCHAR(256)
)