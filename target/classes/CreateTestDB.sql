

CREATE TABLE IF NOT EXISTS users (
  userId INT(11) PRIMARY KEY AUTO_INCREMENT,
  position VARCHAR(25) NOT NULL,
  name VARCHAR(85) NOT NULL, /*83 is max length name in the world*/
  login VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(45) NOT NULL,
  check_word VARCHAR(45),
  salary DECIMAL(10,2),
  number_mistake INT(11),
  experience INT(11)
);

CREATE TABLE IF NOT EXISTS products (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  code_product INT(11)  NOT NULL UNIQUE,
  name VARCHAR(85) NOT NULL UNIQUE, /*83 is max length name in the world*/
  countable TINYINT(1) NOT NULL,
  price_for_one DECIMAL(10,3)  NOT NULL
);

CREATE TABLE IF NOT EXISTS stock (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  product_id INT(11) NOT NULL,
  number DECIMAL(10,3)  NOT NULL
);

CREATE TABLE IF NOT EXISTS check_reports (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  number_of_product INT(11)  NOT NULL,
  check_amount DECIMAL(10,2)  NOT NULL,
  time_close DATETIME NOT NULL,
  FOREIGN KEY (user_id)
  REFERENCES users (userId)
);

CREATE TABLE IF NOT EXISTS current_check (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  product_id INT(11) NOT NULL,
  user_id INT(11) NOT NULL,
  count DECIMAL(10,3)  NOT NULL,
  result_price DECIMAL(10,2)  NOT NULL,
  FOREIGN KEY (user_id)
  REFERENCES users (userId),
  FOREIGN KEY (product_id)
  REFERENCES products (id)
);