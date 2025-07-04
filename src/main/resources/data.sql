INSERT INTO categorias_productos (`categoria`) VALUES
('Chocolates'),
('Bombones'),
('Envases'),
('Madera'),
('Galletas');

INSERT INTO proveedores (`direccion`,`email`,`hora_atencion`,`nombre_empresa`,`nombre_vendedor`,`pagina_web`,`telefono_contacto`) VALUES 
('Arquer','vendedor1@arquer.com','15:00 a 21:00','Arquer','Vendedor 1','www.arquer.com.ar','3333333333'),
('Goana','vendedor2@goana.com','15:00 a 21:00','Goana','Vendedor 2','www.goana.com.ar','2222222222');

INSERT INTO insumos (`cantidad`,`costo_insumo`,`descripcion`,`img`,`stock`,`unidad`,`categoria_id`,`proveedor_id`) VALUES 
(1000,978,'Chocolate','imagen 1',37100,'gramos',1,NULL),
(150,9149.49,'Chocolate Aguila','https://http2.mlstatic.com/D_NQ_NP_860417-MLU76232252964_052024-O.webp',5630,'gramos',1,1),
(50,1199,'Coco rayado Alicante','https://alicante.com.ar/wp-content/uploads/2022/06/51-1200x1500.png',1,'gramos',1,1),
(6000,60200,'Chocolate Alpino Pins','https://http2.mlstatic.com/D_NQ_NP_632989-MLA74552432694_022024-O.webp',1,'gramos',1,1),
(500,5301,'Chocolate Lodiser Alpino semiamargo tableta','https://http2.mlstatic.com/D_NQ_NP_687923-MLU74116137030_012024-O.webp',1,'gramos',1,1),
(1000,13500,'Pirotines Blanco / Color Nº4','https://http2.mlstatic.com/D_NQ_NP_978470-MLA80939092065_112024-O.webp',1,'unidad',3,1),
(90,3199,'Palitos Brochette De Madera 24 cm','https://http2.mlstatic.com/D_NQ_NP_814177-MLA73148279473_112023-O.webp',1,'unidades',4,1),
(1000,15664.6,'Galletitas Oreo Granel','https://http2.mlstatic.com/D_NQ_NP_764377-MLA75741977404_042024-O.webp',1,'gramos',5,1),
(10,10000,'Planchas De Cartón Pallet Divisor 80 cm X 100 cm ','https://http2.mlstatic.com/D_NQ_NP_793611-MLA81579230738_012025-O.webp',1,'unidad',4,1);

INSERT INTO insumo_links (`insumo_id`,`link`) VALUES 
(1, 'https://www.mercadolibre.com.ar/chocolate-belga-la-goulue-n4-milk-35-almendras-tostadas/p/MLA21631932#origin=supermarket_carousel&from=search-frontend'),
(3, 'https://www.mercadolibre.com.ar/coco-rallado-alicante-sachet-x-50-gr-color-blanco/p/MLA19956702#polycard_client=search-nordic&searchVariation=MLA19956702&wid=MLA1286576077&position=15&search_layout=stack&type=product&tracking_id=16239e4f-85d9-4834-bb'),
(2, 'https://articulo.mercadolibre.com.ar/MLA-1101857716-chocolate-taza-tableteado-sa-aguila-9985-caja-x-5-kg-_JM?matt_tool=38279459&matt_word=&matt_source=google&matt_campaign_id=22107887208&matt_ad_group_id=173357538956&matt_match_type=&matt_network=g&m'),
(4, 'https://articulo.mercadolibre.com.ar/MLA-844983179-chocolate-alpino-pins-por-6kgsuper-ofertapara-moldear-_JM?searchVariation=174277653080#polycard_client=search-nordic&searchVariation=174277653080&position=13&search_layout=stack&type=item&tracking_id=123'),
(5, 'https://www.mercadolibre.com.ar/chocolate-lodiser-alpino-semiamargo-tableta-de-500g/p/MLA20029044#polycard_client=search-nordic&searchVariation=MLA20029044&wid=MLA1489170949&position=4&search_layout=stack&type=product&tracking_id=3dee0d6d-b7e8-4b62-9abc'),
(6, 'https://articulo.mercadolibre.com.ar/MLA-727071360-pirotines-bombones-bco-color-n4-x1000-dunia-pol-_JM?searchVariation=36293042009#polycard_client=search-nordic&searchVariation=36293042009&position=22&search_layout=grid&type=item&tracking_id=2dbb60e6'),
(8, 'https://articulo.mercadolibre.com.ar/MLA-1746623782-oferta-galletitas-oreo-enteras-granel-x-1kg-tortas-helados-_JM#polycard_client=search-nordic&position=16&search_layout=stack&type=item&tracking_id=765d2b98-740e-4b9a-9df4-011c4cbb8372&wid=MLA1746623'),
(9, 'https://articulo.mercadolibre.com.ar/MLA-1393697806-planchas-de-carton-pallet-divisor-80cm-x-1m-pack-x-10-u-_JM#polycard_client=search-nordic&position=8&search_layout=grid&type=item&tracking_id=8b742ae3-005e-439c-b0b5-fb09e3870a92&wid=MLA1393697806&s=xyz'),
(7, 'https://libreriaoasis.com.ar/cdn/shop/products/New-New-95-Pcs-Camping-Wooden-Color-Bamboo-BBQ-Skewers-font-b-Barbecue-b-font-Shish.jpg?v=1579739415');

INSERT INTO compras (`fecha_compra`,`precio`) VALUES 
('2025-03-23',4890),
('2025-03-31',6846);

INSERT INTO categorias_productos_oferta (`categoria`) VALUES
('Bombones'),
('Chocomensajes'),
('Pascuas'),
('Navidad'),
('Bombones por Peso'),
('Sin TACC'),
('Box'),
('Ramos de Chocolates'),
('Rosas'),
('Varios');

INSERT INTO productos (`descripcion`,`disponible`,`img_url`,`precio`,`categoria_id`,`costo_base`) VALUES 
('Ramo de Flores de Chocolate','1','https://http2.mlstatic.com/D_NQ_NP_707010-MLA74848951498_032024-O.webp',6499.99,8,2833.68),
('Caja de chocolatines','1','https://http2.mlstatic.com/D_NQ_NP_976822-MLA45295111210_032021-O.webp',14400,7,6244.5),
('Bombones Para El Día Del Padre','1','https://http2.mlstatic.com/D_NQ_NP_835739-MLA76734636625_052024-O.webp',3999.99,2,1702.1),
('Bombones Para El Día Del Padre','1','https://http2.mlstatic.com/D_NQ_NP_622218-MLA75623688216_042024-O.webp',4399.99,2,1911.6),
('Huevo De Pascuas Bonafide Conejo Relleno 60 G','1','https://http2.mlstatic.com/D_NQ_NP_778246-MLA74218967083_012024-O.webp',2499.99,3,1058.8),
('Huevo de Pascuas chocolate Arcor 1kg','1','https://http2.mlstatic.com/D_NQ_NP_708864-MLA49391392515_032022-O.webp',142600,3,62000),
('Caja Bombones Chocolates Artesanales 1kg Regalo','1','https://http2.mlstatic.com/D_NQ_NP_861478-MLA70254812813_072023-O.webp',26680,5,11600),
('Chocolates por Kilo','1','https://http2.mlstatic.com/D_NQ_NP_827474-MLA70254861871_072023-O.webp',4699.99,5,1980),
('Chocolate Navidad Felices Fiestas Bombones Lata Navideña','1','https://http2.mlstatic.com/D_NQ_NP_987802-MLA48466946374_122021-O.webp',890,4,351.05),
('Chocolate Navidad Felices Fiestas','1','https://http2.mlstatic.com/D_NQ_NP_976965-MLA48466967095_122021-O.webp',890,4,351.05),
('Bombon De Chocolate Bonobon Snack Con Leche Arcor X 800g Sin Tacc','1','https://http2.mlstatic.com/D_NQ_NP_923394-MLU54957778763_042023-O.webp',1890,6,784),
('Chocolate Semiamargo Codeland X 1 Kg Sin Tacc Y Apto Veganos','1','https://http2.mlstatic.com/D_NQ_NP_938629-MLU72641140979_112023-O.webp',2260,6,980),
('Caja De Bombones Y Chocolates Seleccion Del Turista 130g','1','https://http2.mlstatic.com/D_NQ_NP_607985-MLA79757119927_102024-O.webp',18239,7,7930),
('Ramo 15 rosas De chocolate','1','https://http2.mlstatic.com/D_NQ_NP_739610-MLA49036752272_022022-O.webp',39289,8,17080),
('Rosa De Chocolate Día De La Madre, De La Primavera X10','1','https://http2.mlstatic.com/D_NQ_NP_934035-MLA51886997208_102022-O.webp',13600,9,2650),
('Rosa De Chocolate Regalo Día De La Madre De La Primavera X 25','1','https://http2.mlstatic.com/D_NQ_NP_778619-MLA77891388782_082024-O.webp',32000,9,10030);

INSERT INTO insumos_comprados (`cantidad`,`precio`,`compra_id`,`insumo_id`) VALUES
(3,978,1,7),
(2,978,1,7),
(7,978,2,7);

INSERT INTO insumo_producto (`cantidad`,`insumo_id`,`producto_id`) VALUES
(150,1,2),
(100,2,2),
(240,3,1),
(12,4,1),
(70,3,3),
(1,5,3),
(86,6,4),
(1,5,4),
(1,5,5),
(60,1,5),
(1000,2,6),
(1,5,6),
(1000,6,7),
(1,5,7),
(1000,1,8),
(1,5,8),
(35,3,9),
(35,3,10),
(800,1,11),
(1000,1,12),
(130,2,13),
(280,2,14),
(250,6,15),
(1000,3,16);

INSERT INTO ventas (`fecha_venta`,`precio`) VALUES 
('2025-04-19',4665.8);

INSERT INTO productos_vendidos (`cantidad`,`precio`,`producto_id`,`venta_id`) VALUES 
(2,2265.4,2,1),
(3,45,1,1);

INSERT INTO usuario (`apellido`,`contrasena`,`nombre`,`rol`,`username`) VALUES 
('Gomez','{bcrypt}$2a$12$RPXV1MRopHPDM8hqIG9KA.F61eIBnCsnhCcHCiEUfEu5Wcv/s0Gei','Claudio','ROL_LECTURA','lectura'),
('Munizaga','{bcrypt}$2a$12$0yal6n7r3um0plcB9ib24ecVwLDcXumEHfr1jbkRnYhCeRJyyk1jO','Carlos','ROL_ADMIN','admin'),
('Lopez','{bcrypt}$2a$12$eatm4XknySoWsDyBjZjYfu/a.w61yg1N6WIDhYpPkJBrU/5kHo9sq','Claudia','ROL_ESCRITURA','escritura');

INSERT INTO valor_agregado (`descripcion`,`porcentaje`) VALUES 
('Mano de Obra',100),
('Impuestos',30);


