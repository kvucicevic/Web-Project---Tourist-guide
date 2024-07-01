CREATE TABLE IF NOT EXISTS User (
                            id INTEGER PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            surname VARCHAR(255) NOT NULL,
                            email VARCHAR(255) UNIQUE NOT NULL,
                            password VARCHAR(255) NOT NULL,
                            Active BINARY(2) NOT NULL,
                            Roleid INTEGER,
                            FOREIGN KEY (Roleid) REFERENCES Role(id)
);

CREATE TABLE IF NOT EXISTS Role (
                            id INTEGER PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
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



INSERT INTO Role (id, name) VALUES
                                (1, 'admin'),
                                (2, 'manager'),
                                (3, 'guest');


INSERT INTO User (id, name, surname, email, password, Active, roleid) VALUES
                                                                  (1, 'John', 'Doe', 'john.doe@example.com', 'password123', true, 1),
                                                                  (2, 'Jane', 'Smith', 'jane.smith@example.com', 'password456', true, 2),
                                                                  (3, 'Alice', 'Johnson', 'alice.johnson@example.com', 'password789', true, 3);


-- Insert sample data into Destination table
INSERT INTO Destination (id, name, description) VALUES
                                                    (1, 'Paris', 'The capital city of France'),
                                                    (2, 'New York', 'The largest city in the United States'),
                                                    (3, 'Tokyo', 'The capital city of Japan'),
                                                    (4, 'Rome' , 'Eternal city');

-- Insert sample data into Activity table
INSERT INTO Activity (id, name, Destinationid) VALUES
                                                   (1, 'Sightseeing', 1),
                                                   (2, 'Shopping', 2),
                                                   (3, 'Dining', 3),
                                                   (4, 'Karaoke' ,2),
                                                   (5, 'Concerts' ,1);

-- Insert sample data into Article table
INSERT INTO Article (id, Userid, title, date, text, visitNo, Destinationid) VALUES
                                                                                (1, 1, 'A Day in Paris', '2023-01-01 10:00:00', 'Spent a wonderful day exploring Paris.', 100, 1),
                                                                                (2, 2, 'New York Adventures', '2023-02-15 15:30:00', 'New York is amazing with lots to see and do.', 200, 2),
                                                                                (3, 3, 'Tokyo Delights', '2023-03-20 08:45:00', 'Tokyo is a city that never sleeps.', 300, 3),
                                                                                (4, 2, 'Exploring the Ancient Ruins', '2024-06-02 00:00:00.00', 'An in-depth look at the ancient ruins and their historical significance.', 12, 4);

-- Insert sample data into Comment table
INSERT INTO Comment (id, Articleid, author, content) VALUES
                                                         (1, 1, 'Mary', 'Great article about Paris!'),
                                                         (2, 2, 'Paul', 'I love New York too!'),
                                                         (3, 3, 'Emma', 'Tokyo sounds fascinating!'),
                                                         (4, 1, 'Anna', 'Amazing city!');

-- Insert sample data into Event table
INSERT INTO Event (Articleid, Activityid) VALUES
                                              (1, 1),
                                              (2, 2),
                                              (3, 3);
