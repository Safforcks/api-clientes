create table clientes(
    id              uuid                primary key,
    nome            varchar(150)        not null,
    email           varchar(100)        not null,
    cpf             varchar(11)         not null,
    telefone        varchar(15)         not null,
    datacadastro    timestamp           default current_timestamp,
    ativo           int                 default 1
);