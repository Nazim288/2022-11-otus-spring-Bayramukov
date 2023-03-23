insert into author (`name`, last_name) values ('Fedor', 'Dostoevsky');
insert into genre (`name`) values ('Detective');
insert into book (`name`, author_id, genre_id ) values ('book-1', 1, 1);
insert into book_comment (`comment_value`, book_id) values ('хороршая книга, советую!', 1);
insert into book_comment (`comment_value`, book_id) values ('super!!!!', 1);
insert into book_comment (`comment_value`, book_id) values ('cool!!!!', 1);
