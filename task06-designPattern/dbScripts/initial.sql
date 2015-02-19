DROP TABLE IF EXISTS ticket_place_map;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tickets;

create table tickets (
	ticket_id VARCHAR(2) NOT NULL,
	session_name VARCHAR(255) NOT NULL,
	ticket_cost DECIMAL,
	session_time_start time NOT NULL,
	session_time_end time NOT NULL,
	CONSTRAINT pk_tickets PRIMARY KEY (ticket_id)
) WITH (
  OIDS=FALSE
);

create table ticket_place_map (
	ticket_id VARCHAR(2) NOT NULL,
	place_number integer NOT NULL,
	status VARCHAR(20) NOT NULL default 'OPEN', 
	CONSTRAINT pk_tickets_place_map PRIMARY KEY (ticket_id, place_number),
	CONSTRAINT fk_ticket_place_map FOREIGN KEY (ticket_id)
      REFERENCES tickets (ticket_id)
) WITH (
  OIDS=FALSE
);

create table users (
	user_id VARCHAR(2) NOT NULL,
	user_name VARCHAR(128),
	balance DECIMAL
)
