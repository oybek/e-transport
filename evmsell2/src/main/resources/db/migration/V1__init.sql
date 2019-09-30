
CREATE TABLE offer (
  id INTEGER PRIMARY KEY,
  owner_id INTEGER,
  created TIMESTAMP,
  ttype TEXT,
  text TEXT,
  price INTEGER,
  latitude FLOAT,
  longitude FLOAT
);
