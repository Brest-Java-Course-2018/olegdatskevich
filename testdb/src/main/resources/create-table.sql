DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
 movieId INT NOT NULL AUTO_INCREMENT,
 movieName VARCHAR(255) NOT NULL,
 movieDescription VARCHAR(255) NULL,
 movieActive BOOLEAN,
 PRIMARY KEY (movieId)
);
DROP TABLE IF EXISTS session;
CREATE TABLE session (
 sessionId INT NOT NULL AUTO_INCREMENT,
 sessionDate DATE NOT NULL,
 sessionTime VARCHAR(20) NOT NULL,
 sessionCost INT NOT NULL,
 sessionSold INT NOT NULL,
 sessionActive BOOLEAN,
 movieId INT NOT NULL,
 PRIMARY KEY (sessionId),
 CONSTRAINT mv_to_ses_fk
    FOREIGN KEY (movieId)
    REFERENCES movie (movieId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
