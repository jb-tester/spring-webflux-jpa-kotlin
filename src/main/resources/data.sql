-- Insert sample owners
INSERT INTO owner (id, first_name, last_name) VALUES
    (1, 'John', 'Doe'),
    (2, 'Jane', 'Smith'),
    (3, 'Robert', 'Johnson'),
    (4, 'Emily', 'Williams');

-- Insert sample cats
INSERT INTO animal (id, animal_type, name, age, owner_id, version, meow_volume, bark_volume) VALUES
    (1, 'CAT', 'Whiskers', 3, 1, 0, 7, NULL),
    (2, 'CAT', 'Mittens', 2, 2, 0, 5, NULL),
    (3, 'CAT', 'Oliver', 4, 3, 0, 8, NULL),
    (4, 'CAT', 'Luna', 1, 4, 0, 6, NULL);

-- Insert sample dogs
INSERT INTO animal (id, animal_type, name, age, owner_id, version, meow_volume, bark_volume) VALUES
    (5, 'DOG', 'Max', 5, 1, 0, NULL, 9),
    (6, 'DOG', 'Buddy', 3, 2, 0, NULL, 8),
    (7, 'DOG', 'Rocky', 7, 3, 0, NULL, 10),
    (8, 'DOG', 'Bella', 2, 4, 0, NULL, 7);
