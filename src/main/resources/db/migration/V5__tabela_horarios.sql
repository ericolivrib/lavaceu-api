create table horarios (
    horario_id integer generated always as identity,
    periodo varchar(5),
    hora timestamp,
    primary key (horario_id)
);