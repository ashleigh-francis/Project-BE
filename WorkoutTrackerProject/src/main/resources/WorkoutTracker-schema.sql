drop table if exists workout_tracker CASCADE;

create table workout_tracker

(

id integer PRIMARY KEY AUTO_INCREMENT,
day_of_week varChar(255),
hours_of_exercise integer not null,
type_of_exercise varChar (255),
goal varChar(255)

);