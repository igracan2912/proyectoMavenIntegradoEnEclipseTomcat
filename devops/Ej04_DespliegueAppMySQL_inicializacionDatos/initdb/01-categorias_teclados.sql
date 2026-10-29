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

CREATE TABLE teclado (
    id INT PRIMARY KEY,
    color VARCHAR(50),
    idioma VARCHAR(50),
    idCategoria INT,
    FOREIGN KEY (idCategoria) REFERENCES categoria(id)
);

INSERT INTO teclado (id, color, idioma, idCategoria) VALUES
(1, 'Negro', 'Español', 1),
(2, 'Blanco', 'Inglés', 2),
(3, 'Gris', 'Francés', 3),
(4, 'Rojo', 'Alemán', 4),
(5, 'Azul', 'Italiano', 5),
(6, 'Plata', 'Español', 3),
(7, 'Rosa', 'Inglés', 4),
(8, 'Negro', 'Francés', 5),
(9, 'Blanco', 'Alemán', 1),
(10, 'Gris', 'Italiano', 2),
(11, 'Rojo', 'Español', 5),
(12, 'Azul', 'Inglés', 1),
(13, 'Plata', 'Francés', 2),
(14, 'Rosa', 'Alemán', 3),
(15, 'Negro', 'Italiano', 4),
(16, 'Blanco', 'Español', 2),
(17, 'Gris', 'Inglés', 3),
(18, 'Rojo', 'Francés', 4),
(19, 'Azul', 'Alemán', 5),
(20, 'Plata', 'Italiano', 1),
(21, 'Rosa', 'Español', 4),
(22, 'Negro', 'Inglés', 5),
(23, 'Blanco', 'Francés', 1),
(24, 'Gris', 'Alemán', 2),
(25, 'Rojo', 'Italiano', 3),
(26, 'Azul', 'Español', 1),
(27, 'Plata', 'Inglés', 2),
(28, 'Rosa', 'Francés', 3),
(29, 'Negro', 'Alemán', 4),
(30, 'Blanco', 'Italiano', 5),
(31, 'Gris', 'Español', 3),
(32, 'Rojo', 'Inglés', 4),
(33, 'Azul', 'Francés', 5),
(34, 'Plata', 'Alemán', 1),
(35, 'Rosa', 'Italiano', 2),
(36, 'Negro', 'Español', 5),
(37, 'Blanco', 'Inglés', 1),
(38, 'Gris', 'Francés', 2),
(39, 'Rojo', 'Alemán', 3),
(40, 'Azul', 'Italiano', 4),
(41, 'Plata', 'Español', 2),
(42, 'Rosa', 'Inglés', 3),
(43, 'Negro', 'Francés', 4),
(44, 'Blanco', 'Alemán', 5),
(45, 'Gris', 'Italiano', 1),
(46, 'Rojo', 'Español', 4),
(47, 'Azul', 'Inglés', 5),
(48, 'Plata', 'Francés', 1),
(49, 'Rosa', 'Alemán', 2),
(50, 'Negro', 'Italiano', 3);