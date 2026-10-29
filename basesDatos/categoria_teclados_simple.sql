-- Crear el esquema
CREATE SCHEMA categorias_teclados;

-- Seleccionar el esquema
USE categorias_teclados;

-- Crear la tabla categoria
CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

-- Crear el esquema
CREATE SCHEMA IF NOT EXISTS categorias_teclados;

-- Seleccionar el esquema
USE categorias_teclados;

-- Crear la tabla categoria
CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

-- Insertar categorías de teclados
INSERT INTO categoria (descripcion) VALUES
('Teclados mecánicos'),
('Teclados de membrana'),
('Teclados inalámbricos'),
('Teclados con cable'),
('Teclados gaming'),
('Teclados ergonómicos'),
('Teclados compactos'),
('Teclados de tamaño completo'),
('Teclados TKL'),
('Teclados 60 por ciento'),
('Teclados 65 por ciento'),
('Teclados 75 por ciento'),
('Teclados numéricos'),
('Teclados multimedia'),
('Teclados silenciosos'),
('Teclados retroiluminados'),
('Teclados RGB'),
('Teclados para oficina'),
('Teclados para programación'),
('Teclados para escritura'),
('Teclados Bluetooth'),
('Teclados USB'),
('Teclados ópticos'),
('Teclados magnéticos'),
('Teclados de perfil bajo'),
('Teclados con switches lineales'),
('Teclados con switches táctiles'),
('Teclados con switches clicky'),
('Teclados plegables'),
('Teclados para tabletas');