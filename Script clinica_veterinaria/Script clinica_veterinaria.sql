DROP DATABASE IF EXISTS clinica_veterinaria;
CREATE DATABASE clinica_veterinaria;

USE clinica_veterinaria;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE mascotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    especie VARCHAR(50) NOT NULL, -- ej: Perro, Gato, Pájaro
    raza VARCHAR(50),
    edad INT,
    cliente_id INT, 
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- 5. INSERTAMOS DATOS DE PRUEBA (Para verlos luego en JavaFX)
INSERT INTO clientes (nombre, telefono, email) VALUES 
('Carlos Pérez', '600123456', 'carlos@email.com'),
('Laura Gómez', '600654321', 'laura@email.com');

INSERT INTO mascotas (nombre, especie, raza, edad, cliente_id) VALUES 
('Toby', 'Perro', 'Golden Retriever', 3, 1), -- Toby es de Carlos (cliente 1)
('Luna', 'Gato', 'Siamés', 2, 1),           -- Luna también es de Carlos
('Kira', 'Perro', 'Husky', 5, 2);           -- Kira es de Laura (cliente 2)


SELECT * FROM clientes;







