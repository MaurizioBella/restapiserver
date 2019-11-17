Simple Rest API Server with postgresql deployed on Heroku
# restapiserver
get the greeting from https://spring.io/guides/gs/rest-service/ 

https://restapipostgresql.herokuapp.com/expenses/2



{
"description":"test2",
"type":"restaurant",
"date":"2013-06-01",
amount:1145.21
}

CREATE SEQUENCE table_name_id_seq;
 
drop table public.expenses;
CREATE TABLE public.expenses (
	id integer NOT NULL DEFAULT nextval('table_name_id_seq'),
	description varchar(5000) NULL,
	"date" date NULL,
	"type" varchar(50) NULL,
	amount numeric(6,2) NULL,
	CONSTRAINT expenses_pkey PRIMARY KEY (id)
);
select * from public.expenses;
insert into public.expenses (description, type, date, amount)values ('test','restaurant','2013-06-01',1145.21);


