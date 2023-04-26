insert into admin (name,last_name,phone,email) values ('Jon','snow','525219908','jon@gmail.com');
insert into customer (name,last_name,phone,email) values ('Evelin','miller','892615333','evelin@gmail.com');
insert into hairdresser (name,last_name,phone,email,employee_code) values ('Jim','smith','916243087','jim@gmail.com',48);

insert into _user (username,pwd) values ('jon','jon123');
insert into _user (username,pwd) values ('evelin','evelin123');
insert into _user (username,pwd) values ('jim','jim123');

insert into _user_roles (_user_id,roles) values(1,'ADMIN')
insert into _user_roles (_user_id,roles) values(1,'CUSTOMER')
insert into _user_roles (_user_id,roles) values(1,'EMPLOYEE')
insert into _user_roles (_user_id,roles) values(2,'EMPLOYEE')
insert into _user_roles (_user_id,roles) values(3,'CUSTOMER')

update admin set user_id=1 where id=1;
update hairdresser set user_id=2 where id=1;
update customer set user_id=3 where id=1;