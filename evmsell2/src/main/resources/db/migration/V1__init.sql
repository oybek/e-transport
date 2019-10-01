
CREATE TABLE offer (
  id INTEGER PRIMARY KEY,
  from_id INTEGER,
  date INTEGER,
  ttype TEXT,
  text TEXT,
  price INTEGER,
  latitude FLOAT,
  longitude FLOAT,
  sold BOOLEAN
);
