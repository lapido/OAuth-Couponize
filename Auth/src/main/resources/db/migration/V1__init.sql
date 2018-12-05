CREATE TABLE Users
(
	[ID] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	FirstName nvarchar(50) NOT NULL,
	LastName nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	[Password] nvarchar(100) NOT NULL,
	Phone nvarchar(20) NOT NULL,
	CompanySize nvarchar(50) NOT NULL,
	[Status] [bit] DEFAULT 0
)

CREATE TABLE UserApplications
(
	[ID] [INT] NOT NULL PRIMARY KEY IDENTITY(1,1),
	[User_ID] [INT] FOREIGN KEY REFERENCES Users(ID),
	[App_ID] nvarchar(100) NOT NULL,
	[App_Key] nvarchar(100) NOT NULL
)

CREATE TABLE UserClients
(
	[ID] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[User_ID] [INT] FOREIGN KEY REFERENCES Users(ID),
	Client_ID nvarchar(100) NOT NULL,
	ClientKey nvarchar(100) NOT NULL
)
GO

CREATE PROCEDURE [dbo].[uspCreate_User]
(
	@firstname nvarchar(50),
	@lastname nvarchar(50),
	@email nvarchar(50),
	@password nvarchar(100),
	@phone nvarchar(20),
	@company_size nvarchar(50)
)
AS
BEGIN TRANSACTION

DECLARE @userId int

	SELECT @userId = 0

	IF(@userId > 0)
	UPDATE Users SET FirstName = @firstname, LastName = @lastname, Email = @email,
	Phone = @phone, CompanySize = @company_size
	ELSE

	SELECT @userId = ID from Users where FirstName = @firstname
	BEGIN
	INSERT INTO Users(FirstName,LastName,Email,Phone,CompanySize,[Password])
	VALUES(@firstname,@lastname,@email,@phone,@company_size,@password)
	SELECT @userId = SCOPE_IDENTITY()
	END

	SELECT @clientId = ID from Client where ClientKey = @clientKey
	IF(@clientId > 0)
	BEGIN
	INSERT INTO Client([User_ID],Client_ID,ClientKey)
	VALUES(@userId,@clientId,@clientKey)
	SELECT  @userId= SCOPE_IDENTITY()
	END

	INSERT INTO User_Application([User_ID],App_ID)
	VALUES(@userId,@clientId)
COMMIT

