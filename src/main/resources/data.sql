INSERT INTO categorias_productos (`id`,`categoria`) VALUES
(41,'Chocolates'),
(42,'Bombones'),
(51,'Envases'),
(52,'Madera'),
(53,'Galletas');

INSERT INTO proveedores (`id`,`direccion`,`email`,`hora_atencion`,`nombre_empresa`,`nombre_vendedor`,`pagina_web`,`telefono_contacto`) VALUES 
(46,'Arquer','vendedor1@arquer.com','15:00 a 21:00','Arquer','Vendedor 1','www.arquer.com.ar','3333333333'),
(47,'Goana','vendedor2@goana.com','15:00 a 21:00','Goana','Vendedor 2','www.goana.com.ar','2222222222');

INSERT INTO insumos (`id`,`cantidad`,`costo_insumo`,`descripcion`,`img`,`stock`,`unidad`,`categoria_id`,`proveedor_id`) VALUES 
(7,1000,978,'Chocolate','imagen 1',37100,'gramos',42,NULL),
(9,150,9149.49,'Chocolate Aguila','https://http2.mlstatic.com/D_NQ_NP_860417-MLU76232252964_052024-O.webp',5630,'gramos',41,46),
(12,50,1199,'Coco rayado Alicante','https://alicante.com.ar/wp-content/uploads/2022/06/51-1200x1500.png',1,'gramos',41,46),
(13,6000,60200,'Chocolate Alpino Pins','https://http2.mlstatic.com/D_NQ_NP_632989-MLA74552432694_022024-O.webp',1,'gramos',41,46),
(14,500,5301,'Chocolate Lodiser Alpino semiamargo tableta','https://http2.mlstatic.com/D_NQ_NP_687923-MLU74116137030_012024-O.webp',1,'gramos',41,46),
(15,1000,13500,'Pirotines Blanco / Color Nº4','https://http2.mlstatic.com/D_NQ_NP_978470-MLA80939092065_112024-O.webp',1,'unidad',51,46),
(16,90,3199,'Palitos Brochette De Madera 24 cm','https://http2.mlstatic.com/D_NQ_NP_814177-MLA73148279473_112023-O.webp',1,'unidades',52,46),
(17,1000,15664.6,'Galletitas Oreo Granel','https://http2.mlstatic.com/D_NQ_NP_764377-MLA75741977404_042024-O.webp',1,'gramos',53,46),
(18,10,10000,'Planchas De Cartón Pallet Divisor 80 cm X 100 cm ','https://http2.mlstatic.com/D_NQ_NP_793611-MLA81579230738_012025-O.webp',1,'unidad',52,46);

INSERT INTO insumo_links (`insumo_id`,`link`) VALUES 
(7, 'https://www.mercadolibre.com.ar/chocolate-belga-la-goulue-n4-milk-35-almendras-tostadas/p/MLA21631932#origin=supermarket_carousel&from=search-frontend'),
(12, 'https://www.mercadolibre.com.ar/coco-rallado-alicante-sachet-x-50-gr-color-blanco/p/MLA19956702#polycard_client=search-nordic&searchVariation=MLA19956702&wid=MLA1286576077&position=15&search_layout=stack&type=product&tracking_id=16239e4f-85d9-4834-bb'),
(9, 'https://articulo.mercadolibre.com.ar/MLA-1101857716-chocolate-taza-tableteado-sa-aguila-9985-caja-x-5-kg-_JM?matt_tool=38279459&matt_word=&matt_source=google&matt_campaign_id=22107887208&matt_ad_group_id=173357538956&matt_match_type=&matt_network=g&m'),
(13, 'https://articulo.mercadolibre.com.ar/MLA-844983179-chocolate-alpino-pins-por-6kgsuper-ofertapara-moldear-_JM?searchVariation=174277653080#polycard_client=search-nordic&searchVariation=174277653080&position=13&search_layout=stack&type=item&tracking_id=123'),
(14, 'https://www.mercadolibre.com.ar/chocolate-lodiser-alpino-semiamargo-tableta-de-500g/p/MLA20029044#polycard_client=search-nordic&searchVariation=MLA20029044&wid=MLA1489170949&position=4&search_layout=stack&type=product&tracking_id=3dee0d6d-b7e8-4b62-9abc'),
(15, 'https://articulo.mercadolibre.com.ar/MLA-727071360-pirotines-bombones-bco-color-n4-x1000-dunia-pol-_JM?searchVariation=36293042009#polycard_client=search-nordic&searchVariation=36293042009&position=22&search_layout=grid&type=item&tracking_id=2dbb60e6'),
(17, 'https://articulo.mercadolibre.com.ar/MLA-1746623782-oferta-galletitas-oreo-enteras-granel-x-1kg-tortas-helados-_JM#polycard_client=search-nordic&position=16&search_layout=stack&type=item&tracking_id=765d2b98-740e-4b9a-9df4-011c4cbb8372&wid=MLA1746623'),
(18, 'https://articulo.mercadolibre.com.ar/MLA-1393697806-planchas-de-carton-pallet-divisor-80cm-x-1m-pack-x-10-u-_JM#polycard_client=search-nordic&position=8&search_layout=grid&type=item&tracking_id=8b742ae3-005e-439c-b0b5-fb09e3870a92&wid=MLA1393697806&s=xyz'),
(16, 'https://libreriaoasis.com.ar/cdn/shop/products/New-New-95-Pcs-Camping-Wooden-Color-Bamboo-BBQ-Skewers-font-b-Barbecue-b-font-Shish.jpg?v=1579739415');

INSERT INTO compras (`id`,`fecha_compra`,`precio`) VALUES 
(1,'2025-03-23',4890),
(3,'2025-03-31',6846);

INSERT INTO categorias_productos_oferta (`id`,`categoria`) VALUES
(1,'Bombones'),
(2,'Chocomensajes'),
(3,'Pascuas'),
(4,'Navidad'),
(5,'Bombones por Peso'),
(6,'Sin TACC'),
(7,'Box'),
(8,'Ramos de Chocolates'),
(9,'Rosas'),
(10,'Varios');

INSERT INTO productos (`id`,`descripcion`,`disponible`,`img_url`,`precio`,`categoria_id`,`costo_base`) VALUES 
(1,'Ramo de Flores de Chocolate','1','https://http2.mlstatic.com/D_NQ_NP_707010-MLA74848951498_032024-O.webp',6499.99,8,2833.68),
(3,'Caja de chocolatines','1','https://http2.mlstatic.com/D_NQ_NP_976822-MLA45295111210_032021-O.webp',14400,7,6244.5),
(4,'Bombones Para El Día Del Padre','1','https://http2.mlstatic.com/D_NQ_NP_835739-MLA76734636625_052024-O.webp',3999.99,2,1702.1),
(5,'Bombones Para El Día Del Padre','1','https://http2.mlstatic.com/D_NQ_NP_622218-MLA75623688216_042024-O.webp',4399.99,2,1911.6),
(6,'Huevo De Pascuas Bonafide Conejo Relleno 60 G','1','https://http2.mlstatic.com/D_NQ_NP_778246-MLA74218967083_012024-O.webp',2499.99,3,1058.8),
(7,'Huevo de Pascuas chocolate Arcor 1kg','1','https://http2.mlstatic.com/D_NQ_NP_708864-MLA49391392515_032022-O.webp',142600,3,62000),
(8,'Caja Bombones Chocolates Artesanales 1kg Regalo','1','https://http2.mlstatic.com/D_NQ_NP_861478-MLA70254812813_072023-O.webp',26680,5,11600),
(9,'Chocolates por Kilo','1','https://http2.mlstatic.com/D_NQ_NP_827474-MLA70254861871_072023-O.webp',4699.99,5,1980),
(10,'Chocolate Navidad Felices Fiestas Bombones Lata Navideña','1','https://http2.mlstatic.com/D_NQ_NP_987802-MLA48466946374_122021-O.webp',890,4,351.05),
(11,'Chocolate Navidad Felices Fiestas','1','https://http2.mlstatic.com/D_NQ_NP_976965-MLA48466967095_122021-O.webp',890,4,351.05),
(12,'Bombon De Chocolate Bonobon Snack Con Leche Arcor X 800g Sin Tacc','1','https://http2.mlstatic.com/D_NQ_NP_923394-MLU54957778763_042023-O.webp',1890,6,784),
(13,'Chocolate Semiamargo Codeland X 1 Kg Sin Tacc Y Apto Veganos','1','https://http2.mlstatic.com/D_NQ_NP_938629-MLU72641140979_112023-O.webp',2260,6,980),
(14,'Caja De Bombones Y Chocolates Seleccion Del Turista 130g','1','https://http2.mlstatic.com/D_NQ_NP_607985-MLA79757119927_102024-O.webp',18239,7,7930),
(15,'Ramo 15 rosas De chocolate','1','https://http2.mlstatic.com/D_NQ_NP_739610-MLA49036752272_022022-O.webp',39289,8,17080),
(16,'Rosa De Chocolate Día De La Madre, De La Primavera X10','1','https://http2.mlstatic.com/D_NQ_NP_934035-MLA51886997208_102022-O.webp',13600,9,2650),
(17,'Rosa De Chocolate Regalo Día De La Madre De La Primavera X 25','1','https://http2.mlstatic.com/D_NQ_NP_778619-MLA77891388782_082024-O.webp',32000,9,10030);

INSERT INTO insumos_comprados (`id`,`cantidad`,`precio`,`compra_id`,`insumo_id`) VALUES
(1,3,978,1,7),
(2,2,978,1,7),
(10,7,978,3,7);

INSERT INTO insumo_producto (`id`,`cantidad`,`insumo_id`,`producto_id`) VALUES
(18,150,7,3),
(19,100,9,3),
(20,240,13,1),
(21,12,16,1),
(22,70,13,4),
(23,1,18,4),
(24,86,14,5),
(25,1,18,5),
(26,1,18,6),
(27,60,7,6),
(28,1000,9,7),
(29,1,18,7),
(30,1000,14,8),
(31,1,18,8),
(32,1000,7,9),
(33,1,18,9),
(34,35,13,10),
(35,35,13,11),
(36,800,7,12),
(37,1000,7,13),
(38,130,9,14),
(39,280,9,15),
(40,250,14,16),
(41,1000,13,17);

INSERT INTO ventas (`id`,`fecha_venta`,`precio`) VALUES 
(2,'2025-04-19',4665.8);

INSERT INTO productos_vendidos (`id`,`cantidad`,`precio`,`producto_id`,`venta_id`) VALUES 
(4,2,2265.4,3,2),
(5,3,45,1,2);

INSERT INTO usuario (`id`,`apellido`,`contrasena`,`nombre`,`rol`,`username`) VALUES 
(1,'Gomez','{bcrypt}$2a$12$RPXV1MRopHPDM8hqIG9KA.F61eIBnCsnhCcHCiEUfEu5Wcv/s0Gei','Claudio','ROL_LECTURA','lectura'),
(2,'Munizaga','{bcrypt}$2a$12$0yal6n7r3um0plcB9ib24ecVwLDcXumEHfr1jbkRnYhCeRJyyk1jO','Carlos','ROL_ADMIN','admin'),
(3,'Lopez','{bcrypt}$2a$12$eatm4XknySoWsDyBjZjYfu/a.w61yg1N6WIDhYpPkJBrU/5kHo9sq','Claudia','ROL_ESCRITURA','escritura');

INSERT INTO valor_agregado (`id`,`descripcion`,`porcentaje`) VALUES 
(2,'Mano de Obra',100),
(3,'Impuestos',30);


