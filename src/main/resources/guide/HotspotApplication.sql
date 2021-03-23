Use master
Go

CREATE DATABASE hotspot_db
Go

Use hotspot_db
Go

CREATE TABLE [dbo].[users](
    [id] [int] IDENTITY(1,1) PRIMARY KEY CLUSTERED ([id] ASC) NOT NULL,
    [first_name] [varchar](100) NOT NULL,
	[last_name] [varchar](100) NOT NULL,
	[email] [varchar](100) NOT NULL
);
GO

CREATE TABLE [dbo].[category](
    [id] [int] IDENTITY(1,1) PRIMARY KEY CLUSTERED ([id] ASC) NOT NULL,
    [description] [varchar](100) NOT NULL
);
GO


CREATE TABLE [dbo].[location](
    [id] [int] IDENTITY(1,1) PRIMARY KEY CLUSTERED ([id] ASC) NOT NULL,
    [logitude] [varchar](100) NOT NULL,
    [latitude] [varchar](100) NOT NULL,
    [description] [Text]  NOT NULL,
	[scale_serious] [varchar](100) NOT NULL,
	[category_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[date] [date] NOT NULL
);
GO

ALTER TABLE dbo.location
    ADD CONSTRAINT [FK_HotspotLocation_Users] FOREIGN KEY (user_id) REFERENCES users(id),
    	CONSTRAINT [FK_HotspotLocation_LocationCategory] FOREIGN KEY (category_id) REFERENCES category(id);
GO