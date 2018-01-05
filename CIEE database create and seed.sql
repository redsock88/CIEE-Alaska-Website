CREATE TABLE IF NOT EXISTS `daniels_website`.`users` (
  `userID` INT NULL,
  `type` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `joinDate` DATE NULL,
  `lastLogin` DATE NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC))
ENGINE = InnoDB;

insert into`daniels_website`.`users`
values (10000, 'admin', 'active', 'admin', 'password', current_date(), current_date()),
(10001, 'student', 'active', 'student1', 'password', current_date(), current_date()),
(10002, 'student', 'active', 'student2', 'password', current_date(), current_date()),
(10003, 'host_family', 'active', 'host', 'password', current_date(), current_date()),
(10004, 'student_family', 'active', 'family', 'password', current_date(), current_date()),
(10005, 'student', 'inactive', 'student1Inactive', 'password', current_date(), current_date()),
(10006, 'student', 'inactive', 'student2Inactive', 'password', current_date(), current_date()),
(10007, 'host_family', 'inactive', 'hostInactive', 'password', current_date(), current_date()),
(10008, 'student_family', 'inactive', 'familyInactive', 'password', current_date(), current_date());
commit;