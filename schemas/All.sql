CREATE TABLE Url (
  id SERIAL PRIMARY KEY NOT NULL,
  address VARCHAR NOT NULL
);

CREATE TABLE Click (
  id SERIAL PRIMARY KEY NOT NULL,
  time TIMESTAMP NOT NULL,
  ip VARCHAR NOT NULL,
  address INT REFERENCES Url(id) ON DELETE CASCADE
);