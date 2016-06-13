%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
SUCHE NACH NAMEN
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

SELECT name, vorname, fakultaet FROM student
INNER JOIN s_m_a ON student.urz = s_m_a.urz
INNER JOIN m_a ON s_m_a.id_m_a = m_a.id
INNER JOIN aktivitaet ON m_a.aktivitaet_name = aktivitaet.aktivitaet_name
WHERE name = 'Ahmad';

SELECT DISTINCT student.urz, name, vorname, fakultaet, massnahme_name, m_a.aktivitaet_name, beschreibung, status_typ FROM student
INNER JOIN s_m_a ON student.urz = s_m_a.urz
INNER JOIN m_a ON s_m_a.id_m_a = m_a.id
INNER JOIN aktivitaet ON m_a.aktivitaet_name = aktivitaet.aktivitaet_name
INNER JOIN student_status ON student.urz = student_status.urz
WHERE student.urz = 'anto3';

%%%%%%%%%%%% TABELLE STUDENT %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

SELECT DISTINCT student.urz, name, vorname FROM student
WHERE name = 'Ahmad';

%%%%%%%%%%%% TABELLE AKTIVITAET %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

SELECT DISTINCT m_a.aktivitaet_name, zeitraum, beschreibung, massnahme_name FROM student
INNER JOIN s_m_a ON student.urz = s_m_a.urz
INNER JOIN m_a ON s_m_a.id_m_a = m_a.id
INNER JOIN aktivitaet ON m_a.aktivitaet_name = aktivitaet.aktivitaet_name
WHERE name = 'Ahmad';

%%%%%%%%%%%% TABELLE STATUS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

SELECT DISTINCT status_typ FROM student
INNER JOIN student_status ON student.urz = student_status.urz
WHERE name = 'Ahmad';

select aktivitaet_name, zeitraum, beschreibung, massnahme_name from aktivitaet;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

SELECT s_m_a.id FROM s_m_a INNER JOIN m_a ON m_a.id = id_m_a INNER JOIN aktivitaet ON aktivitaet.aktivitaet_name = m_a.aktivitaet_name WHERE beschreibung = 'Förderzeitraum 15.10.2015 - 31.03.2016' AND urz = 'anto3';

SELECT m_a.id FROM m_a INNER JOIN aktivitaet ON aktivitaet.aktivitaet_name = m_a.aktivitaet_name WHERE beschreibung = 'Kontaktreisen zwischen August und Dez. 2015';

SELECT student.urz, name, vorname, geburtsdatum, fakultaet, telefon, email, status_typ FROM student
INNER JOIN student_status ON student.urz = student_status.urz
WHERE name = 'Ahmad';

%%%%%%%%%%%%%%%%%%%% get s_m_a %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
select id from s_m_a where urz = 'ahhu1' and id_m_a = '14';








select aktivitaet_name from aktivitaet where beschreibung = 'Förderzeitraum – Nov. - Dez. 2014';
select id from m_a where aktivitaet_name = 'M_01_14';

String query ="select aktivitaet_name from aktivitaet where beschreibung = '" + aktivitaetBeschreibung + "'; select id from m_a where aktivitaet_name = '"+ aktivitaetName+"';";