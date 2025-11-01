create table if not exists employees
(
    id            int auto_increment primary key,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    email         varchar(255) not null unique,
    department_id int,
    foreign key (department_id) references departments (id) on delete set null
);

create unique index if not exists idx_employee_email on employees (email);
create index if not exists idx_employee_department on employees (department_id);