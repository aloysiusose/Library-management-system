
CREATE SEQUENCE contact_sequence INCREMENT BY 1 MINVALUE 1;
CREATE TABLE contact_information (
      id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('contact_sequence'),
      telephone varchar(255) NOT NULL,
      street_name varchar(255) NOT NULL,
      province varchar(255) NOT NULL,
      country varchar(255) NOT NULL

);

CREATE SEQUENCE patron_sequence INCREMENT BY 1 MINVALUE 1;

CREATE TABLE patrons (
  id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('patron_sequence'),
  patron_id varchar(255) NOT NULL UNIQUE,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) UNIQUE NOT NULL,
  user_password varchar(255) NOT NULL,
  role varchar(255) NOT NULL,
  contact_information_id bigint UNIQUE,
  FOREIGN KEY (contact_information_id) REFERENCES contact_information(id)
);

CREATE SEQUENCE book_sequence INCREMENT BY 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS books (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('book_sequence'),
    book_id VARCHAR(50) NOT NULL,
    isbn VARCHAR(255),
    book_title VARCHAR(50) NOT NULL,
    authors VARCHAR(255),
    publication_date DATE,
    available_copies NUMERIC NOT NULL
);

CREATE SEQUENCE borrowed_sequence INCREMENT BY 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS borrowed_books(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('borrowed_sequence'),
    book_name VARCHAR(50) NOT NULL,
    date_of_borrow DATE,
    date_of_return DATE,
    book_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES books(id),
    patron_id BIGINT,
    FOREIGN KEY (patron_id) REFERENCES patrons(id)

);