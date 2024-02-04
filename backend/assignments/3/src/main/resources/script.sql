-- Create User Table
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP,
    is_deleted BOOL DEFAULT false,
    modified_at TIMESTAMP,
    email_id VARCHAR(255) NOT NULL
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
);

-- Create House Table
CREATE TABLE IF NOT EXISTS house (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP,
    is_deleted BOOL DEFAULT false,
    modified_at TIMESTAMP,
    address VARCHAR(255) NOT NULL,
    house_name VARCHAR(100) NOT NULL
);

-- Create HouseUser Table
CREATE TABLE IF NOT EXISTS house_owner (
    house_owner_id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP,
    is_deleted BOOL DEFAULT false,
    modified_at TIMESTAMP,
    role VARCHAR(10) NOT NULL,
    house_id VARCHAR(255),
    user_id VARCHAR(255),
    FOREIGN KEY (house_id) REFERENCES house(id),
    FOREIGN KEY (user_id) REFERENCES users(username)
);

-- Create Inventory Table
CREATE TABLE IF NOT EXISTS inventory (
    kickston_id VARCHAR(6) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP,
    is_deleted BOOL DEFAULT false,
    modified_at TIMESTAMP,
    device_password VARCHAR(255) NOT NULL,
    device_username VARCHAR(255) NOT NULL,
    manufacture_date_time VARCHAR(255) NOT NULL,
    manufacture_factory_place VARCHAR(255) NOT NULL
);

-- Create Device Table
CREATE TABLE IF NOT EXISTS device (
    kickston_id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP,
    is_deleted BOOL DEFAULT false,
    modified_at TIMESTAMP,
    device_password VARCHAR(255) NOT NULL,
    device_username VARCHAR(255) NOT NULL,
    room_id VARCHAR(255),
    houseId VARCHAR(255),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    FOREIGN KEY (house_id) REFERENCES house(id)
);

-- Create Room Table
CREATE TABLE IF NOT EXISTS room (
    room_id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT NOW(),
    deleted_at TIMESTAMP,
    is_deleted BOOL DEFAULT false,
    modified_at TIMESTAMP,
    room_name VARCHAR(255) NOT NULL,
    house_id VARCHAR(255),
    FOREIGN KEY (house_id) REFERENCES house(id)
);

INSERT INTO inventory (kickston_id, device_password, device_username, manufacture_date_time, manufacture_factory_place)
VALUES ('000001', 'password', 'bulb', '2024-02-04 12:00:00', 'Factory1');

INSERT INTO inventory (kickston_id, device_password, device_username, manufacture_date_time, manufacture_factory_place)
VALUES ('000002', 'password', 'tubelight', '2024-02-04 14:30:00', 'Factory2');


select * from device d ;

select * from house h ;

select * from house_owner ho ;

select * from inventory i ;

select * from room r ;

select * from users u ;
