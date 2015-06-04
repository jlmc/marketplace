insert into user(id,email, password, username, OPTLOCK)
values( 1, 'admin@gmail.com', 'admin', 'xine Admin', 0);

insert into user_permission(user_id, permission_id)
VALUES (1,1), (1,2), (1,3), (1,4);
