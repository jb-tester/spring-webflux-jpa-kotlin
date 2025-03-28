-- Drop tables if they exist to avoid conflicts
DROP TABLE IF EXISTS animal;
DROP TABLE IF EXISTS owner;

-- Create owner table
CREATE TABLE owner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

-- Create animal table with single table inheritance
-- This table will store both cats and dogs with a discriminator column
CREATE TABLE animal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    animal_type VARCHAR(31) NOT NULL,  -- Discriminator column
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    owner_id BIGINT,
    version BIGINT DEFAULT 0 NOT NULL, -- Version column for optimistic locking
    meow_volume INT,  -- Specific to Cat
    bark_volume INT,  -- Specific to Dog
    
    -- Foreign key constraint
    CONSTRAINT fk_animal_owner FOREIGN KEY (owner_id) REFERENCES owner(id)
);

-- Create indexes
CREATE INDEX idx_animal_owner ON animal(owner_id);
CREATE INDEX idx_animal_type ON animal(animal_type);
