CREATE TABLE IF NOT EXISTS `daniels_website`.`users` (
  `userID` INT,
  `personID` INT,
  `type` VARCHAR(20),
  `status` VARCHAR(20),
  `username` VARCHAR(100),
  `password` VARCHAR(50),
  `joinDate` DATE,
  `lastLogin` DATE,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC))
ENGINE = InnoDB;

 CREATE TABLE IF NOT EXISTS `daniels_website`.`person` (
  `personID` INT,
  `firstName` VARCHAR(50),
  `lastName` VARCHAR(50),
  `phone` VARCHAR(20),
  `address` VARCHAR(100),
  PRIMARY KEY (`personID`),
  UNIQUE INDEX `personID_UNIQUE` (`personID` ASC))
ENGINE = InnoDB;

 CREATE TABLE IF NOT EXISTS `daniels_website`.`students` (
  `studentID` INT,
  `personID` INT,
  `countryOfOrigin` VARCHAR(50),
  `parentID` INT,
  `hostID` INT,
  `school` VARCHAR(100),
  `endDate` Date,
  PRIMARY KEY (`studentID`),
  UNIQUE INDEX `studentID_UNIQUE` (`studentID` ASC))
ENGINE = InnoDB;

insert into`daniels_website`.`users`
values (10000, 10000, 'admin', 'active', 'admin', 'password', current_date(), current_date()),
(10001, 10001, 'student', 'active', 'student1', 'password', current_date(), current_date()),
(10002, 10002, 'student', 'active', 'student2', 'password', current_date(), current_date()),
(10003, 10003, 'host_family', 'active', 'host', 'password', current_date(), current_date()),
(10004, 10004, 'student_family', 'active', 'family', 'password', current_date(), current_date()),
(10005, 10005, 'student', 'inactive', 'student1Inactive', 'password', current_date(), current_date()),
(10006, 10006, 'student', 'inactive', 'student2Inactive', 'password', current_date(), current_date()),
(10007, 10007, 'host_family', 'inactive', 'hostInactive', 'password', current_date(), current_date()),
(10008, 10008, 'student_family', 'inactive', 'familyInactive', 'password', current_date(), current_date());
commit;

insert into`daniels_website`.`person`
values (10000, 'admin', 'admin', '', ''),
(10001, 'Student', 'One', '704-123-1234', '123 Main Street, Coldtown, Alaska 90001'),
(10002, 'Student', 'Two', '704-123-1235', '124 Main Street, Coldtown, Alaska 90001'),
(10003, 'Host', 'Family', '704-123-1236', '125 Main Street, Coldtown, Alaska 90001'),
(10004, 'Student', 'Family', '704-123-1237', '126 Main Street, Coldtown, Alaska 90001'),
(10005, 'Student', 'OneInactive', '704-123-1238', '127 Main Street, Coldtown, Alaska 90001'),
(10006, 'Student', 'TwoInactive', '704-123-1239', '128 Main Street, Coldtown, Alaska 90001'),
(10007, 'Host', 'FamilyInactive', '704-123-1240', '129 Main Street, Coldtown, Alaska 90001'),
(10008, 'Student', 'FamilyInactive', '704-123-1241', '130 Main Street, Coldtown, Alaska 90001');
commit;

insert into`daniels_website`.`students`
values (10000, 10001, 'Japan', 10004, 10003, 'Coldtown High School', null),
(10001, 10002, 'Korea', 10004, 10003, 'Coldtown Middle School', null),
(10002, 10005, 'Japan', 10008, 10007, 'Coldtown High School', current_date()),
(10003, 10006, 'Korea', 10008, 10007, 'Coldtown Middle School', current_date());
commit;