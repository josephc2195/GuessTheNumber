drop database if exists BullsAndCows;
create database BullsAndCows;

use BullsAndCows;

create table game(

	gameid int auto_increment primary key,
    answer int not null,
    finnished boolean not null,
    attempts int not null
    
);

insert into game(answer, finnished, attempts)
values(1234, false, 0);

insert into game(answer, finnished, attempts)
values(1434, false, 0);

select * from game;