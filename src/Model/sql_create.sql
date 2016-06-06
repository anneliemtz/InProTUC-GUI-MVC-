CREATE TABLE student_mob(
id SERIAL PRIMARY KEY,
id_s_m_a int REFERENCES s_m_a(id) NOT NULL,
durchfuehrung character varying(200),
art character varying(200),
CHECK (durchfuehrung = 'ja' OR durchfuehrung  = 'nein'),
UNIQUE (id_s_m_a)
);

-- Für ein ID der sich selber hochaddiert --
ALTER TABLE student_status DROP COLUMN id;
ALTER TABLE student_status ADD id SERIAL PRIMARY KEY;


CREATE TABLE s_m_a(
id SERIAL PRIMARY KEY,
urz character varying(200) REFERENCES student(urz) NOT NULL,
id_m_a int REFERENCES m_a(id) NOT NULL,
semester character varying(200),
bemerkung character varying(200),
UNIQUE (urz, id_m_a)
);

CREATE TABLE m_a(
id SERIAL PRIMARY KEY,
aktivitaet_name character varying(200) REFERENCES aktivitaet(aktivitaet_name) NOT NULL,
massnahme_name character varying(200) REFERENCES massnahme(massnahme_name) NOT NULL,
UNIQUE (aktivitaet_name, massnahme_name)
);

//TODO: ON DELETE CASCADE ??

ALTER TABLE aktivitaet
ADD UNIQUE (beschreibung);

WS_05_15        | 07.07.2015     | Designing Effective Academic Posters id 8

