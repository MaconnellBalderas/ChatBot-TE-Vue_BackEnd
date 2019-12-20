START TRANSACTION;

DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS curriculum;
DROP TABLE IF EXISTS pathway;
DROP TABLE IF EXISTS quotes;

CREATE TABLE job (
id SERIAL PRIMARY KEY NOT NULL,
title VARCHAR(255) NOT NULL

);

CREATE TABLE curriculum (
id SERIAL PRIMARY KEY NOT NULL,
topic VARCHAR(255) NOT NULL,
description VARCHAR (1024) NOT NULL,
link VARCHAR (1024)

);

CREATE TABLE pathway (
id SERIAL PRIMARY KEY NOT NULL,
topic VARCHAR(255) NOT NULL,
description VARCHAR (1024) NOT NULL,
link VARCHAR (1024) NOT NULL
);

CREATE TABLE quotes (
id SERIAL PRIMARY KEY NOT NULL,
title VARCHAR(1024) NOT NULL

);

INSERT INTO quotes
VALUES(DEFAULT, 'Be Sure To Drink Your Ovaltine');

INSERT INTO quotes
VALUES(DEFAULT, 'Be yourself; everyone else is already taken.');

INSERT INTO quotes
VALUES(DEFAULT, 'So many books, so little time.');

INSERT INTO quotes
VALUES(DEFAULT, 'A room without books is like a body without a soul.');

INSERT INTO quotes
VALUES(DEFAULT, 'You only live once, but if you do it right, once is enough.');

INSERT INTO jobs
VALUES(DEFAULT, 'Fireman');

INSERT INTO jobs
VALUES(DEFAULT, 'Firewoman');

INSERT INTO jobs
VALUES(DEFAULT, 'Accountant');

INSERT INTO jobs
VALUES(DEFAULT, 'Software Developer');

INSERT INTO jobs
VALUES(DEFAULT, 'Nurse');

INSERT INTO jobs
VALUES(DEFAULT, 'Doctor');

INSERT INTO jobs
VALUES(DEFAULT, 'Beekeeper');

INSERT INTO jobs
VALUES(DEFAULT, 'Police Officer');

INSERT INTO jobs
VALUES(DEFAULT, 'Farmer');

INSERT INTO pathway
values (default, 'resume', 'A résumé or resume is a document used and created by a person to present their background, skills, and accomplishments.', '<a href=''https://www.themuse.com/advice/43-resume-tips-that-will-help-you-get-hired''> Resume </a>');

UPDATE pathway
SET link = '<a href=''https://www.thebalancecareers.com/elevator-speech-examples-and-writing-tips-2061976''> Elevator Pitch </a>'
WHERE id = 4;

SELECT * FROM pathway;

INSERT INTO pathway
values (default, 'cover letter', 'Fine-tune your cover letter.', '<a href=''https://www.thebalancecareers.com/elevator-speech-examples-and-writing-tips-2061976''> Cover Letter </a>');

INSERT INTO pathway
values (default, 'elevator pitch', 'An elevator speech is a great way to gain confidence in introducing yourself to hiring managers and company representatives.', '<a href=''https://www.thebalancecareers.com/elevator-speech-examples-and-writing-tips-2061976''> Resume </a>');

INSERT INTO pathway
values (default, 'profile picture', 'The image should be clear and clean.', '<a href=''https://www.thebalancecareers.com/elevator-speech-examples-and-writing-tips-2061976''> Profile Picture </a>');

INSERT INTO curriculum
VALUES (DEFAULT , 'Method', 'A method in object-oriented programming is a procedure associated with a class.', '<a href=''https://study.com/academy/lesson/oop-object-oriented-programming-objects-classes-interfaces.html''> Method </a>');

INSERT INTO curriculum
VALUES (DEFAULT , 'Array', 'Java array is an object which contains elements of a similar data type.', '<a href=''https://www.javatpoint.com/array-in-java''> Array </a>');

INSERT INTO curriculum
VALUES (DEFAULT , 'MVC', 'The Model-View-Controller (MVC) framework is an architectural pattern that separates an application into three main logical components Model, View, and Controller.', '<a href=''https://www.guru99.com/mvc-tutorial.html''> MVC </a>');

INSERT INTO curriculum
VALUES (DEFAULT , 'For Loop', 'The Java for loop is a control flow statement that iterates a part of the programs multiple times.', '<a href=''https://www.javatpoint.com/java-for-loop''> For Loop </a>');

INSERT INTO curriculum
VALUES (DEFAULT , 'Boolean', 'A Boolean expression is a Java expression that returns a Boolean value: true or false.', '<a href=''https://www.w3schools.com/java/java_booleans.asp''> Boolean </a>');

INSERT INTO curriculum
VALUES (DEFAULT , 'Object Oriented Programming', 'Object-oriented programming (OOP) is a programming language model in which programs are organized around data, or objects, rather than functions and logic.', '<a href=''https://searchapparchitecture.techtarget.com/definition/object-oriented-programming-OOP''> Object Oriented Programming </a>');

INSERT INTO curriculum
VALUES (DEFAULT , 'Encapsulation', 'In object-oriented programming, encapsulation refers to the bundling of data with the methods that operate on that data, or the restricting of direct access to some of an object/''s components.', '<a href=''https://en.wikipedia.org/wiki/Encapsulation_(computer_programming)''> Encapsulation </a>');
 
 
SELECT * FROM curriculum;
SELECT * FROM pathway;
 
COMMIT;