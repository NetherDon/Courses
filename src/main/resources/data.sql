CREATE TABLE IF NOT EXISTS course ( 
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    places INTEGER NOT NULL,
    registered INTEGER NOT NULL DEFAULT(0),
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS student ( 
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS enrollment ( 
    id SERIAL,
    course_id INTEGER NOT NULL,
    student_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    UNIQUE (course_id, student_id)
);

INSERT INTO course (name, places, start_time, end_time) VALUES
    ('Frontend', 20, NOW(), NOW() + INTERVAL '2 days'),
    ('Backend', 2, NOW() - INTERVAL '1 day', NOW() + INTERVAL '3 days'),
    ('DevOps', 10, NOW() - INTERVAL '4 days', NOW() - INTERVAL '2 days'),
    ('Android', 10, NOW() + INTERVAL '4 days', NOW() + INTERVAL '6 days');

INSERT INTO student (name, surname) VALUES
    ('Ivan', 'Ivanov'),
    ('Ekaterina', 'Sokolova'),
    ('Dmitry', 'Afanasiev'),
    ('Kseniya', 'Frolova');

CREATE OR REPLACE FUNCTION enroll(course_id2 INTEGER, student_id2 INTEGER, date_time TIMESTAMPTZ)
    RETURNS INTEGER 
    LANGUAGE plpgsql
    AS E'
        DECLARE course_row course%ROWTYPE;
        BEGIN
            SELECT * INTO course_row FROM course WHERE id = course_id2 LIMIT 1;
            IF (course_row IS NULL OR course_row.places <= course_row.registered) THEN
                RETURN 4;
            END IF;

            IF (course_row.start_time > date_time) THEN
                RETURN 3;
            END IF;

            IF (course_row.end_time < date_time) THEN
                RETURN 2;
            END IF;

            BEGIN
                INSERT INTO enrollment(course_id, student_id)
                VALUES (course_id2, student_id2);
                EXCEPTION WHEN unique_violation THEN
                    RETURN 1;
            END;
            RETURN 0;
        END
    ';


CREATE OR REPLACE FUNCTION studentRegistered() 
    RETURNS TRIGGER 
    LANGUAGE plpgsql
    AS '
        BEGIN
            UPDATE course 
            SET registered = registered + 1 
            WHERE NEW.course_id = id;
            RETURN NEW;
        END
    ';

CREATE OR REPLACE TRIGGER studentRegistered 
    AFTER INSERT ON enrollment
    FOR EACH ROW 
    EXECUTE FUNCTION studentRegistered();