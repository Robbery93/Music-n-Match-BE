/* password is de username beginnend met een hoofdletter, met 123! eraan vast e.g.: username: robbert, password: Robbert123! */
INSERT INTO users (username, password, enabled)
VALUES ('robbert', '$2a$10$3Ir/NL1.eapVLyq3NCCLfOyIsq.9y7ZziVJWs9hD4ChDlLNTV08mu', TRUE),
       ('anna', '$2a$10$C/gQ5s2z3Uu2qfnl99KnsuFpNbUand CTpafb0seJMuJC052k5wan2eam', TRUE),
       ('cor', '$2a$10$QU/ld5fuW0VspXD8wSbWV.WwIVzko4sVQiZKwqmw6Inj2pr4XrwyO', TRUE),
       ('dirk', '$2a$10$EfOSvkOuW7bvg2fMHeosHO.1RU6vkuC4jBkDfgfH26zK6KxmdNuZq', TRUE),
       ('rosalie', '$2a$10$TCrY0AuuhxIfqN4e2Oxv5u2pQAH129PsvEXJFty4Ag/lxmZzZE8h6', TRUE),
       ('admin', '$2a$10$Nr3hVx0Y2nf0aQykOA1UuuDPLp9rU.dRVEK0FMw2W5rS6AsxjOwKy', TRUE);

INSERT INTO authorities (username, authority)
VALUES ('robbert', 'ROLE_STUDENT'),
       ('anna', 'ROLE_STUDENT'),
       ('cor', 'ROLE_STUDENT'),
       ('dirk', 'ROLE_TEACHER'),
       ('rosalie', 'ROLE_TEACHER'),
       ('admin', 'ROLE_ADMIN');

INSERT INTO teachers (name, email, age, residence, phone_number, instruments, description, experience, preference_for_lesson_type)
VALUES ('Dirk', 'dirk@gmail.com', '29', 'Rotterdam', '0645678912', 'gitaar, basgitaar, piano', 'Hallo! Ik ben Dirk', 'Ik kan vet goed gitaarspelen enzo', 'Videolessen'),
       ('Rosalie', 'rosalie@gmail.com', '35', 'Breda', '0698732165', 'gitaar, keyboard, harp', 'Hallo! Ik ben Rosalie', 'Ik kan vet goed dingen doen enzo', 'Les aan huis');

INSERT INTO students (name, email, age, residence, phone_number, instrument, request, preference_for_lesson_type)
VALUES ('Robbert', 'robbery@gmail.com', '28', 'Rotterdam', '0655751563', 'gitaar', 'Ik wil graag gitaar leren spelen', 'Videolessen'),
       ('Bas', 'bas@gmail.com', '16', 'Rotterdam', '0673649120', 'gitaar', 'Ik wil graag gitaar leren spelen', 'Videolessen'),
       ('Thijs', 'thijs@gmail.com', '23', 'Breda', '0612345678', 'gitaar', 'Ik wil graag gitaar leren spelen', 'Les aan huis'),
       ('Anna', 'anna@gmail.com', '23', 'Rotterdam', '0634848422', 'zang', 'Ik wil graag nog beter zingen', 'Videolessen'),
       ('Jeroen', 'jerony@gmail.com', '32', 'Capelle aan den IJssel', '0657592833', 'keyboard', 'Ik wil graag keyboard leren spelen', 'Les aan huis'),
       ('Cor', 'cor@gmail.com', '48', 'Nieuwerkerk aan den IJssel', '0654654773', 'piano', 'Ik wil graag klassieke muziek spelen', 'Videolessen');

INSERT INTO lessons (teacher_id, student_id, active, homework)
VALUES (1, 1, TRUE, 'Voor vandaag heb je nog geen huiswerk. Goed gedaan vanmiddag!'),
       (1, 2, FALSE, null),
       (2, 3, TRUE, 'Voor vandaag heb je nog geen huiswerk. Goed gedaan vanmiddag!');

