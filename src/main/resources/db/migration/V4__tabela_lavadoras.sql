create table lavadoras (
    lavadora_id integer generated always as identity,
    marca varchar(20),
    numero integer unique,
    tempo_lavagem integer,
    estado varchar(12),
    primary key (lavadora_id)
);
