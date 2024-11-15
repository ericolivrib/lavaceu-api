create table usuarios
(
    usuario_id  integer generated always as identity,
    nome        varchar(50),
    senha       varchar(60),
    telefone    varchar(13),
    matricula   varchar(10),
    apartamento integer,
    primary key (usuario_id)
);

create table acessos
(
    acesso_id integer generated always as identity,
    nome      varchar(8),
    primary key (acesso_id)
);

create table acessos_usuarios
(
    usuario_id    integer,
    acesso_id     integer,
    ultimo_acesso timestamp,
    primary key (usuario_id, acesso_id),
    foreign key (usuario_id) references usuarios (usuario_id),
    foreign key (acesso_id) references acessos (acesso_id)
);
