CREATE TABLE IF NOT EXISTS User (
                      id INTEGER(100) PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255) NOT NULL,
                      email VARCHAR(255) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      Active BINARY(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Destination (
                             id INTEGER(100) PRIMARY KEY,
                             name VARCHAR(255) UNIQUE NOT NULL,
                             description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Activity (
                          id INTEGER(100) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          Destinationid INTEGER(100),
                          FOREIGN KEY (Destinationid) REFERENCES Destination(id)
);

CREATE TABLE IF NOT EXISTS Article (
                         id INTEGER(100) PRIMARY KEY,
                         Userid INTEGER(100),
                         title VARCHAR(255) NOT NULL,
                         date TIMESTAMP(6) NOT NULL,
                         text VARCHAR(255) NOT NULL,
                         visitNo INTEGER(100),
                         Destinationid INTEGER(100),
                         FOREIGN KEY (Userid) REFERENCES User(id),
                         FOREIGN KEY (Destinationid) REFERENCES Destination(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Comment (
                         id INTEGER(100) PRIMARY KEY,
                         Articleid INTEGER(100),
                         author VARCHAR(255) NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         FOREIGN KEY (Articleid) REFERENCES Article(id)
);

CREATE TABLE IF NOT EXISTS Event (
                       Articleid INTEGER(100),
                       Activityid INTEGER(100),
                       PRIMARY KEY (Articleid, Activityid),
                       FOREIGN KEY (Articleid) REFERENCES Article(id),
                       FOREIGN KEY (Activityid) REFERENCES Activity(id)
);

-- Insert sample data into User table
INSERT INTO User (id, name, surname, email, password, Active) VALUES
                                                                  (1, 'John', 'Doe', 'john.doe@example.com', 'password123', 1),
                                                                  (2, 'Jane', 'Smith', 'jane.smith@example.com', 'password456', 1),
                                                                  (3, 'Alice', 'Johnson', 'alice.johnson@example.com', 'password789', 1);

-- Insert sample data into Destination table
INSERT INTO Destination (id, name, description) VALUES
                                                    (1, 'Paris', 'The capital city of France'),
                                                    (2, 'New York', 'The largest city in the United States'),
                                                    (3, 'Tokyo', 'The capital city of Japan');

-- Insert sample data into Activity table
INSERT INTO Activity (id, name, Destinationid) VALUES
                                                   (1, 'Sightseeing', 1),
                                                   (2, 'Shopping', 2),
                                                   (3, 'Dining', 3);

-- Insert sample data into Article table
INSERT INTO Article (id, Userid, title, date, text, visitNo, Destinationid) VALUES
                                                                                (1, 1, 'A Day in Paris', '2023-01-01 10:00:00', 'Spent a wonderful day exploring Paris.', 100, 1),
                                                                                (2, 2, 'New York Adventures', '2023-02-15 15:30:00', 'New York is amazing with lots to see and do.', 200, 2),
                                                                                (3, 3, 'Tokyo Delights', '2023-03-20 08:45:00', 'Tokyo is a city that never sleeps.', 300, 3);

-- Insert sample data into Comment table
INSERT INTO Comment (id, Articleid, author, content) VALUES
                                                         (1, 1, 'Mary', 'Great article about Paris!'),
                                                         (2, 2, 'Paul', 'I love New York too!'),
                                                         (3, 3, 'Emma', 'Tokyo sounds fascinating!');

-- Insert sample data into Event table
INSERT INTO Event (Articleid, Activityid) VALUES
                                              (1, 1),
                                              (2, 2),
                                              (3, 3);
