DROP TABLE IF EXISTS department;
CREATE TABLE department (
 departmentId INT NOT NULL AUTO_INCREMENT,
 departmentName VARCHAR(255) NOT NULL,
 departmentDescription VARCHAR(255) NULL,
 PRIMARY KEY (departmentId)
)
