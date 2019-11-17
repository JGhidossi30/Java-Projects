DROP DATABASE IF EXISTS DinerOrders;
CREATE DATABASE DinerOrders;

USE DinerOrders;

CREATE TABLE Orders(
	id int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    duration double(5, 2) NOT NULL
);

CREATE TABLE FoodItem(
	id int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL
);
CREATE TABLE Times(
	startTime varchar(20) NOT NULL,
	endTime varchar(20) NOT NULL
);

INSERT INTO FoodItem (name)
	VALUES ("french fries"),
		   ("funnel cake"),
		   ("corn dog"),
		   ("hamburger"),
		   ("veggie burger"),
		   ("chocolate shake"),
		   ("strawberry shake"),
		   ("vanilla shake"),
		   ("small drink"),
		   ("medium drink"),
		   ("large drink"),
		   ("coffee");