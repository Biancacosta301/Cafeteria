CREATE TABLE estoque (
    id INT PRIMARY KEY AUTO_INCREMENT,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    unidade VARCHAR(20), -- Ex: unidades, kg, litros
    data_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_saida TIMESTAMP NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
