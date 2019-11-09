CREATE TABLE offer (
    id        INTEGER NOT NULL,
    group_id  INTEGER NOT NULL,
    from_id   INTEGER NOT NULL,
    date      INTEGER NOT NULL,
    ttype     TEXT,
    text      TEXT NOT NULL,
    price     INTEGER,
    latitude  FLOAT,
    longitude FLOAT,
    sold      INTEGER,
    PRIMARY KEY (id, group_id)
);

CREATE TABLE user_info (
    id        INTEGER PRIMARY KEY,
    latitude  FLOAT,
    longitude FLOAT
)
