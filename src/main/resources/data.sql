INSERT INTO users (username, password, enabled, email)
VALUES ('robbert', '$2a$10$3Ir/NL1.eapVLyq3NCCLfOyIsq.9y7ZziVJWs9hD4ChDlLNTV08mu', TRUE, 'robbert@gmail.com'),
       ('anna', '$2a$10$C/gQ5s2z3Uu2qfnl99KnsuFpNbUCTpafb0seJMuJC052k5wan2eam', TRUE, 'anna@gmail.com'),
       ('cor', '$2a$10$QU/ld5fuW0VspXD8wSbWV.WwIVzko4sVQiZKwqmw6Inj2pr4XrwyO', TRUE, 'cor@gmail.com'),
       ('admin', '$2a$10$Nr3hVx0Y2nf0aQykOA1UuuDPLp9rU.dRVEK0FMw2W5rS6AsxjOwKy', TRUE, 'admin@gmail.com');

INSERT INTO authorities (username, authority)
VALUES ('robbert', 'ROLE_STUDENT'),
       ('anna', 'ROLE_STUDENT'),
       ('cor', 'ROLE_STUDENT'),
       ('cor', 'ROLE_TEACHER'),
       ('admin', 'ROLE_ADMIN');

INSERT INTO teachers (name, email, residence, phone_number, instruments, description, experience, preference_for_lesson_type)
VALUES ('Dirk', 'dirk@gmail.com', 'Rotterdam', '0645678912', 'gitaar, basgitaar, piano', 'Hallo! Ik ben Dirk', 'Ik kan vet goed gitaarspelen enzo', 'Videolessen'),
       ('Rosalie', 'rosalie@gmail.com', 'Breda', '0698732165', 'gitaar, keyboard, harp', 'Hallo! Ik ben Rosalie', 'Ik kan vet goed dingen doen enzo', 'Les aan huis');

INSERT INTO students (name, email, residence, phone_number, instrument, request, preference_for_lesson_type, teacher_id)
VALUES ('Robbert', 'robbery@gmail.com', 'Rotterdam', '0655751563', 'gitaar', 'Ik wil graag gitaar leren spelen', 'Videolessen', 1),
       ('Bas', 'bas@gmail.com', 'Rotterdam', '0673649120', 'gitaar', 'Ik wil graag gitaar leren spelen', 'Videolessen', 1),
       ('Thijs', 'thijs@gmail.com', 'Breda', '0612345678', 'gitaar', 'Ik wil graag gitaar leren spelen', 'Les aan huis', 2),
       ('Anna', 'anna@gmail.com', 'Rotterdam', '0634848422', 'zang', 'Ik wil graag nog beter zingen', 'Videolessen', 2),
       ('Jeroen', 'jerony@gmail.com', 'Capelle aan den IJssel', '0657592833', 'keyboard', 'Ik wil graag keyboard leren spelen', 'Les aan huis', 1),
       ('Cor', 'cor@gmail.com', 'Nieuwerkerk aan den IJssel', '0654654773', 'piano', 'Ik wil graag klassieke muziek spelen', 'Videolessen', 1);

-- INSERT INTO lessons (student_id, teacher_id, homework)
-- VALUES (1, 1, 'Voor vandaag heb je nog geen huiswerk. Goed gedaan vanmiddag!');

