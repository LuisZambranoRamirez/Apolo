
CREATE TABLE song (
    song_name VARCHAR(255),
    song_artist VARCHAR(255),
    song_popularity DOUBLE PRECISION,
    danceability DOUBLE PRECISION,
    energy DOUBLE PRECISION,
    key DOUBLE PRECISION,
    loudness DOUBLE PRECISION,
    mode DOUBLE PRECISION,
    speechiness DOUBLE PRECISION,
    acousticness DOUBLE PRECISION,
    instrumentalness DOUBLE PRECISION,
    liveness DOUBLE PRECISION,
    valence DOUBLE PRECISION,
    tempo DOUBLE PRECISION,
    duration_ms DOUBLE PRECISION,

    PRIMARY KEY (song_name, song_artist)
);

CREATE TABLE genre (
    genre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE subgenre (
    subgenre VARCHAR(100) PRIMARY KEY
);

CREATE TABLE song_genre_map (
    song_name VARCHAR(255),
    song_artist VARCHAR(255),
    genre VARCHAR(100),

    PRIMARY KEY (song_name, song_artist, genre),

    FOREIGN KEY (song_name, song_artist) 
        REFERENCES song(song_name, song_artist) ON DELETE CASCADE,
    FOREIGN KEY (genre) 
        REFERENCES genre(genre) ON DELETE CASCADE
);

CREATE TABLE song_subgenre_map (
    song_name VARCHAR(255),
    song_artist VARCHAR(255),
    subgenre VARCHAR(100),

    PRIMARY KEY (song_name, song_artist, subgenre),

    FOREIGN KEY (song_name, song_artist) 
        REFERENCES song(song_name, song_artist) ON DELETE CASCADE,  
    FOREIGN KEY (subgenre) 
        REFERENCES subgenre(subgenre) ON DELETE CASCADE
);

CREATE TABLE app_user (
    username VARCHAR(100) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE listening_history (
    history_id BIGSERIAL PRIMARY KEY,

    username VARCHAR(100) NOT NULL,

    song_name VARCHAR(255) NOT NULL,
    song_artist VARCHAR(255) NOT NULL,

    played_at TIMESTAMP NOT NULL,

    duration_played_ms BIGINT,

    completed BOOLEAN NOT NULL DEFAULT FALSE,

    FOREIGN KEY (username)
        REFERENCES app_user(username)
        ON DELETE CASCADE,

    FOREIGN KEY (song_name, song_artist)
        REFERENCES song(song_name, song_artist)
        ON DELETE CASCADE
);
