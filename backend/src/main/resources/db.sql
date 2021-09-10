drop table if exists users cascade;
drop table if exists problems cascade;
drop table if exists problems_answer cascade ;
drop table if exists problems_comments cascade ;
drop table if exists problems_testcase cascade ;
drop table if exists problem_likes cascade ;
drop table if exists answer_likes cascade ;

create table users(
                      id serial primary key ,
                      name varchar(40) unique ,
                      password varchar(40)
);

create table problems(
                         id serial primary key ,
                         title varchar(40),
                         promoter_id int references users(id) on delete cascade ,
                         description text,
                         default_generator text
);

create table problems_testcase(
                                  id serial primary key ,
                                  problem_id int references problems(id) on delete cascade ,
                                  input text,
                                  output text
);

create table problems_comments(
                                  id serial primary key ,
                                  problem_id int references problems(id) on delete cascade ,
                                  user_id int references users(id) on delete cascade ,
                                  replying_id int references users(id) on delete cascade null,
                                  content text
);

create table problems_answer(
                                id serial primary key ,
                                problem_id int references problems(id) on delete cascade ,
                                user_id int references users(id) on delete cascade ,
                                content text

);

create table problem_likes(
                              id serial primary key ,
                              problem_id int references problems(id) on delete cascade ,
                              user_id int references users(id) on delete cascade ,
                              is_like boolean
);

create table answer_likes(
                             id serial primary key ,
                             answer_id int references problems_answer(id) on delete cascade ,
                             user_id int references users(id) on delete cascade ,
                             is_like boolean
);