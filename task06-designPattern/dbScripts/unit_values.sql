INSERT into tickets (ticket_id, session_name, ticket_cost, session_time_start, session_time_end) VALUES ('t1','Interstellar', 20, '9:30:00', '12:30:00');
INSERT into tickets (ticket_id, session_name, ticket_cost, session_time_start, session_time_end) VALUES ('t2','The Mask', 10 ,'12:30:00', '13:45:00');
INSERT into tickets (ticket_id, session_name, ticket_cost, session_time_start, session_time_end) VALUES ('t3','The Ring', 12, '14:00:00', '15:30:00');
INSERT into tickets (ticket_id, session_name, ticket_cost, session_time_start, session_time_end) VALUES ('t4','Hulk', 10, '16:00:00', '17:30:00');
INSERT into tickets (ticket_id, session_name, ticket_cost, session_time_start, session_time_end) VALUES ('t5','The Batman', 7, '18:00:00', '19:30:00');

INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',1);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',2);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',3);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',4);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',5);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',6);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',10);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',12);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',18);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',19);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t1',20);

INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t2',11);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t2',12);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t2',13);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t2',14);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t2',15);

INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',20);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',21);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',22);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',23);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',24);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',25);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',27);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t3',28);

INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t4',31);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t4',32);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t4',37);
INSERT into ticket_place_map (ticket_id, place_number) VALUES ('t4',39);

INSERT into users (user_id, user_name, balance) VALUES ('1', 'Alex', 400);
INSERT into users (user_id, user_name, balance) VALUES ('2', 'Michael', 200);
INSERT into users (user_id, user_name, balance) VALUES ('0', 'Cinema', 0);


