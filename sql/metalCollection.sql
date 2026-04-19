CREATE TABLE "user"(
    id SERIAL,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY(id)
);
CREATE TABLE band(
    id SERIAL,
    name TEXT NOT NULL,
    provenance TEXT,
    CONSTRAINT pk_band PRIMARY KEY (id)
);
CREATE TABLE genre(
    id SERIAL,
    genre TEXT NOT NULL,
    CONSTRAINT pk_genre PRIMARY KEY (id)
);
CREATE TABLE song(
    id SERIAL,
    id_band INT NOT NULL,
    title TEXT NOT NULL,
    duration_minute INT,
    duration_second INT NOT NULL,
    CONSTRAINT pk_song PRIMARY KEY (id),
    CONSTRAINT  fk_band FOREIGN KEY (id_band) REFERENCES band (id)
);

CREATE TABLE song_genre(
    id_song INT NOT NULL,
    id_genre INT NOT NULL,
    CONSTRAINT pk_song_genre PRIMARY KEY (id_song, id_genre),
    CONSTRAINT fk_song FOREIGN KEY (id_song) REFERENCES song(id),
    CONSTRAINT fk_genre FOREIGN KEY (id_genre) REFERENCES genre(id)
);

INSERT INTO genre (genre) VALUES
('Heavy Metal'),
('Thrash Metal'),
('Death Metal'),
('Black Metal'),
('Power Metal');

INSERT INTO band (name, provenance) VALUES
('Iron Maiden', 'UK'),
('Metallica', 'USA'),
('Death', 'USA'),
('Mayhem', 'Norway'),
('Blind Guardian', 'Germany');

INSERT INTO song (id_band, title, duration_minute, duration_second) VALUES
(1, 'Hallowed Be Thy Name', 7, 11),
(2, 'Master of Puppets', 8, 35),
(3, 'Crystal Mountain', 5, 7),
(4, 'Freezing Moon', 6, 23),
(5, 'Mirror Mirror', 5, 6);

INSERT INTO song_genre (id_song, id_genre) VALUES
(1, 1), -- Hallowed Be Thy Name -> Heavy Metal
(2, 2), -- Master of Puppets -> Thrash Metal
(3, 3), -- Crystal Mountain -> Death Metal
(4, 4), -- Freezing Moon -> Black Metal
(5, 5); -- Mirror Mirror -> Power Metal

INSERT INTO song (id_band, title, duration_minute, duration_second) VALUES
-- Iron Maiden (id_band: 1)
(1, 'The Trooper', 4, 12),
(1, 'Fear of the Dark', 7, 18),

-- Metallica (id_band: 2)
(2, 'Enter Sandman', 5, 31),
(2, 'One', 7, 27),

-- Death (id_band: 3)
(3, 'Pull the Plug', 4, 27),
(3, 'Spirit Crusher', 6, 44),

-- Mayhem (id_band: 4)
(4, 'De Mysteriis Dom Sathanas', 6, 22),
(4, 'Deathcrush', 3, 33),

-- Blind Guardian (id_band: 5)
(5, 'The Bard''s Song - In the Forest', 3, 10),
(5, 'Nightfall', 5, 34);

INSERT INTO song_genre (id_song, id_genre) VALUES
-- Canzoni degli Iron Maiden (ID 6, 7) -> Heavy Metal (ID 1)
(6, 1), -- The Trooper
(7, 1), -- Fear of the Dark

-- Canzoni dei Metallica (ID 8, 9) -> Thrash Metal (ID 2)
(8, 2), -- Enter Sandman
(9, 2), -- One

-- Canzoni dei Death (ID 10, 11) -> Death Metal (ID 3)
(10, 3), -- Pull the Plug
(11, 3), -- Spirit Crusher

-- Canzoni dei Mayhem (ID 12, 13) -> Black Metal (ID 4)
(12, 4), -- De Mysteriis Dom Sathanas
(13, 4), -- Deathcrush

-- Canzoni dei Blind Guardian (ID 14, 15) -> Power Metal (ID 5)
(14, 5), -- The Bard's Song
(15, 5); -- Nightfall

SELECT * FROM "user";

DROP TABLE band CASCADE;
DROP TABLE genre CASCADE;
DROP TABLE song CASCADE;
DROP TABLE song_genre CASCADE;

