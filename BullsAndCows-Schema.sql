drop database if exists BullsAndCows;
create database BullsAndCows;

use BullsAndCows;

create table game(

	gameid int auto_increment primary key,
    answer int not null,
    finnished boolean not null,
    attempts int not null
    
);

create table `round`(
	
    roundid int auto_increment,
    gameid int not null,
    roundNumber int not null,
    roundtime timestamp,
    result varchar(20),
    
    constraint pk_round
		primary key(roundid, gameid),
        
     constraint fk_round_game
		foreign key(gameid)
		references game(gameid)	
);    
    
    


insert into game(answer, finnished, attempts)
values(1234, false, 0);

insert into game(answer, finnished, attempts)
values(1434, false, 0);

select * from game;