-- 1. Usuário Admin
INSERT INTO usuarios (nome, email, senha, cpf, telefone, tipo) VALUES 
('Bianca Nascimento', 'admin@gmail.com', '123', '000.000.000-00', '(84) 99999-9999', 'VENDEDOR');

-- 2. Categorias
INSERT INTO categorias (nome) VALUES ('Cafés Especiais'); -- ID 1
INSERT INTO categorias (nome) VALUES ('Donuts Artesanais'); -- ID 2
INSERT INTO categorias (nome) VALUES ('Doces'); -- ID 3
INSERT INTO categorias (nome) VALUES ('Lanches'); -- ID 4
INSERT INTO categorias (nome) VALUES ('Bebidas Geladas'); -- ID 5

-- 3. Produtos (Com Imagens)
INSERT INTO produtos (nome, descricao, preco, quantidade_estoque, categoria_id, imagem) VALUES
('Cappuccino de Avelã', 'Borda de Nutella e chantilly cremoso.', 12.90, 50, 1, 'https://images.unsplash.com/photo-1572442388796-11668a67e53d?auto=format&fit=crop&w=600&q=80'),

('Espresso Duplo', 'Intenso e revigorante, grãos selecionados.', 6.00, 100, 1, 'https://img.freepik.com/fotos-gratis/copo-de-cafe-com-leite_1203-7771.jpg?semt=ais_hybrid&w=740&q=80'),

('Donut Homer', 'Clássico com cobertura rosa e granulado.', 8.50, 30, 2, 'https://s2-casaejardim.glbimg.com/4_QCps9ssOoafpcMtGVj7QvvSPY=/0x0:1400x934/924x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_a0b7e59562ef42049f4e191fe476fe7d/internal_photos/bs/2022/M/f/NXCKLwSf6OiaGOCeAC0Q/receita-donut-homer-tradicional-the-good-cop.jpg'),

('Donut Red Velvet', 'Massa vermelha com recheio de cream cheese.', 9.90, 25, 2, 'https://www.barleyandsage.com/wp-content/uploads/2022/02/red-velvet-baked-donuts-1200x1200-1.jpg'),

('Croissant Queijo', 'Massa folhada francesa amanteigada.', 10.00, 20, 4, 'https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&w=600&q=80'),

('Soda Italiana', 'Maçã verde refrescante com água com gás.', 12.00, 40, 5, 'https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?auto=format&fit=crop&w=600&q=80'),

('Milkshake Morango', 'Feito com sorvete artesanal e calda.', 18.00, 15, 5, 'https://images.unsplash.com/photo-1579954115545-a95591f28bfc?auto=format&fit=crop&w=600&q=80'),

('Cupcake Blueberry', 'Massa fofinha com mirtilos frescos.', 7.50, 40, 3, 'https://i.etsystatic.com/48059243/r/il/0eda51/7006902442/il_fullxfull.7006902442_hfvg.jpg');