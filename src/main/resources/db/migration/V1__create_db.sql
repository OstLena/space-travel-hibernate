create TABLE IF NOT EXISTS client (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(200) NOT NULL
);

create TABLE IF NOT EXISTS planet (
   id VARCHAR PRIMARY KEY,
   name VARCHAR(500) NOT NULL
);

create TABLE IF NOT EXISTS ticket (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   created_at TIMESTAMP,
   client_id BIGINT,
   from_planet_id VARCHAR,
   to_planet_id VARCHAR,
  FOREIGN KEY(client_id) REFERENCES client(id),
  FOREIGN KEY(from_planet_id) REFERENCES planet(id),
  FOREIGN KEY(to_planet_id) REFERENCES planet(id)
);