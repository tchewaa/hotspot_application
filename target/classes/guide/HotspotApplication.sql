Use master
Go

Use hotspot_db
Go

CREATE TABLE [dbo].[provinces](
    [province_id] [int] IDENTITY(1,1) NOT NULL,
    [province_name] [varchar](100) NOT NULL,
);
GO

ALTER TABLE dbo.provinces
ADD CONSTRAINT [PK_Provinces_PKey] PRIMARY KEY CLUSTERED ([province_id] ASC);
GO


CREATE TABLE [dbo].[cities](
    [city_id] [int] IDENTITY(1,1) NOT NULL,
    [city_name] [varchar](100) NOT NULL,
	[province_id] [int] NOT NULL,
);
GO

ALTER TABLE dbo.cities
ADD CONSTRAINT [PK_City_PKey] PRIMARY KEY CLUSTERED ([city_id] ASC);
GO

ALTER TABLE dbo.cities
ADD CONSTRAINT [FK_City_Province_FKey] FOREIGN KEY (province_id) REFERENCES provinces(province_id);
GO

INSERT INTO dbo.provinces 
(province_name)
VALUES    ('Eastern Cape'),
('Free State'),
('Gauteng'),
('KwaZulu-Natal'),
('Limpopo'),
('Mpumalanga'),
('North West'),
('Northern Cape'),
('Western Cape')
GO

INSERT INTO dbo.cities 
(city_name, province_id)
VALUES    ('Johannesburg',3),
('Vereeniging',3),
('Pietermaritzburg',4),
('Pretoria',3),
('Durban',4),
('Cape Town',9),
('Welkom',2),
('East London',1),
('Randburg',3),
('Roodepoort',3),
('Port Elizabeth',1),
('Bloemfontein',2),
('Centurion',3),
('Springs',3),
('Sandton',3),
('Polokwane',5),
('Klerksdorp',7),
('Rustenburg',7),
('Kimberley',8),
('Bhisho',1),
('Benoni',3),
('George',9),
('Middelburg',6),
('Vryheid',4),
('Potchefstroom',7),
('Umtata',1),
('Brits',7),
('Alberton',3),
('Upington',8),
('Paarl',9),
('Queenstown',1),
('Mmabatho',7),
('Kroonstad',2),
('Uitenhage',1),
('Bethal',6),
('Worcester',9),
('Vanderbijlpark',3),
('Grahamstown',1),
('Standerton',6),
('Brakpan',3),
('Thohoyandou',5),
('Saldanha',9),
('Tzaneen',5),
('Graaff-Reinet',1),
('Oudtshoorn',9),
('Mossel Bay',9),
('Port Shepstone',4),
('Knysna',9),
('Vryburg',7),
('Ladysmith',4),
('Beaufort West',9),
('Aliwal North',1),
('Volksrust',6),
('Musina',5),
('Vredenburg',9),
('Malmesbury',9),
('Lebowakgomo',5),
('Cradock',1),
('De Aar',8),
('Ulundi',4),
('Jeffrey’s Bay',1),
('Lichtenburg',7),
('Hermanus',9),
('Carletonville',3),
('Komatipoort',6),
('Middelburg',1),
('Port Alfred',1),
('Swellendam',9),
('Bloemhof',7),
('Bethlehem',2),
('Montagu',9),
('Mahikeng',7),
('Bredasdorp',9),
('Phalaborwa',5),
('Caledon',9),
('Moorreesburg',9),
('Colesberg',8),
('Brandfort',2),
('Piketberg',9),
('Saint Helena Bay',9),
('Prieska',8),
('Velddrif',9),
('Springbok',8),
('Darling',9),
('Kuruman',8),
('Villiersdorp',9),
('Tulbagh',9),
('Saron',9),
('Clanwilliam',9),
('Citrusdal',9),
('Porterville',9),
('Kleinmond',9),
('Hopefield',9),
('Vanrhynsdorp',9),
('Klawer',9),
('Lambert’s Bay',9),
('Port Saint John’s',1),
('Carnarvon',8),
('Genadendal',9),
('Riviersonderend',9),
('Onrus',9),
('Pofadder',8),
('Barrydale',9),
('Steytlerville',1),
('Rawsonville',9),
('Fraserburg',8),
('Suurbraak',9),
('Nelspruit',6)
GO

CREATE TABLE [dbo].[category](
    [category_id] [int] IDENTITY(1,1) NOT NULL,
    [description] [varchar](100) NOT NULL,
);
GO

ALTER TABLE dbo.category
ADD CONSTRAINT [PK_Category_PKey] PRIMARY KEY CLUSTERED ([category_id] ASC);
GO

INSERT INTO dbo.category 
(description)
VALUES    ('Accident Hotspot'),
('Corona Virus Hotspot'),
('Highjacking Hotspot'),
('Road Block Hotspot'),
('Metro Police Hotspot'),
('Mugging Hotspot')
GO

CREATE TABLE [dbo].[users](
    [user_id] [int] IDENTITY(1,1) PRIMARY KEY CLUSTERED ([user_id] ASC) NOT NULL,
    [first_name] [varchar](100) NOT NULL,
	[last_name] [varchar](100) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[active] [BIT] DEFAULT 'FALSE' NOT NULL
);
GO

CREATE TABLE [dbo].[location](
    [location_id] [int] IDENTITY(1,1) PRIMARY KEY CLUSTERED ([location_id] ASC) NOT NULL,
    [logitude] [varchar](100) NOT NULL,
    [latitude] [varchar](100) NOT NULL,
    [description] [Text]  NOT NULL,
	[scale_serious] [varchar](100) NOT NULL,
	[category_id] [int] NOT NULL,
	[city_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[date] [date] NOT NULL
);
GO

ALTER TABLE dbo.location
    ADD CONSTRAINT [FK_HotspotLocation_Users] FOREIGN KEY (user_id) REFERENCES users(user_id),
		CONSTRAINT [FK_HotspotLocation_Location_City] FOREIGN KEY (city_id) REFERENCES cities(city_id),
    	CONSTRAINT [FK_HotspotLocation_Location_Category] FOREIGN KEY (category_id) REFERENCES category(category_id);
GO



