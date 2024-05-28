CREATE TABLE User (
                      id INTEGER(100) PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255) NOT NULL,
                      email VARCHAR(255) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      Active BINARY(2) NOT NULL
);

CREATE TABLE Destination (
                             id INTEGER(100) PRIMARY KEY,
                             name VARCHAR(255) UNIQUE NOT NULL,
                             description VARCHAR(255)
);

CREATE TABLE Activity (
                          id INTEGER(100) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          Destinationid INTEGER(100),
                          FOREIGN KEY (Destinationid) REFERENCES Destination(id)
);

CREATE TABLE Article (
                         id INTEGER(100) PRIMARY KEY,
                         Userid INTEGER(100),
                         title VARCHAR(255) NOT NULL,
                         date TIMESTAMP(10) NOT NULL,
                         text VARCHAR(255) NOT NULL,
                         visitNo INTEGER(100),
                         Destinationid INTEGER(100),
                         FOREIGN KEY (Userid) REFERENCES User(id),
                         FOREIGN KEY (Destinationid) REFERENCES Destination(id) ON DELETE RESTRICT
);

CREATE TABLE Comment (
                         id INTEGER(100) PRIMARY KEY,
                         Articleid INTEGER(100),
                         author VARCHAR(255) NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         FOREIGN KEY (Articleid) REFERENCES Article(id)
);

CREATE TABLE Event (
                       Articleid INTEGER(100),
                       Activityid INTEGER(100),
                       PRIMARY KEY (Articleid, Activityid),
                       FOREIGN KEY (Articleid) REFERENCES Article(id),
                       FOREIGN KEY (Activityid) REFERENCES Activity(id)
);
