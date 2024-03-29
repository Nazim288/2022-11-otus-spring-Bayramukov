CREATE TABLE AUTHOR(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), LAST_NAME VARCHAR(255));

CREATE TABLE GENRE(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255));

CREATE TABLE BOOK(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), AUTHOR_ID BIGINT REFERENCES AUTHOR(ID), GENRE_ID BIGINT REFERENCES GENRE(ID));

CREATE TABLE BOOK_COMMENT(ID BIGINT AUTO_INCREMENT PRIMARY KEY, COMMENT_VALUE VARCHAR(255), BOOK_ID BIGINT REFERENCES BOOK(ID));

