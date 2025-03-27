CREATE TABLE IF NOT EXISTS Item (
    item_id INT NOT NULL,
    name varchar(250) NOT NULL, 
    description varchar(250) NOT NULL,
    location varchar(250) NOT NULL,
    reported_on timestamp NOT NULL,
    found_on timestamp NOT NULL,
    status varchar(15) NOT NULL,
    campus varchar(15) NOT NULL,
    PRIMARY KEY (item_id)
);