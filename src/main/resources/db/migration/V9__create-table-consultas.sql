create table consultas(

    id bigint not null auto_increment,
    veterinario_id bigint not null,
    pet_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_veterinario_id foreign key(veterinario_id) references veterinarios(id),
    constraint fk_consultas_pet_id foreign key(pet_id) references pets(id)

);