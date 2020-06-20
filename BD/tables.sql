CREATE TABLE school.course (
	code varchar(4) NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT course__PK PRIMARY KEY (code)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `student` (
  `rut` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `age` int(11) NOT NULL,
  `code` varchar(4) NOT NULL,
  KEY `student_FK` (`code`),
  CONSTRAINT `student_FK` FOREIGN KEY (`code`) REFERENCES `course` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

ALTER TABLE school.student ADD id INTEGER NOT NULL;
ALTER TABLE school.student ADD CONSTRAINT student_PK PRIMARY KEY (id);
ALTER TABLE school.student MODIFY COLUMN id int(11) auto_increment NOT NULL;
