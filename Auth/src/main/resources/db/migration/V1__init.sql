CREATE TABLE Users
(
	[ID] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	FirstName nvarchar(50) NOT NULL,
	LastName nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	[Password] nvarchar(50) NOT NULL,
	Phone nvarchar(15) NOT NULL,
	CompanySize nvarchar(50) NOT NULL,
	[Status] [bit] NOT NULL
)

CREATE TABLE User_Application
(
	[ID] [INT] NOT NULL PRIMARY KEY IDENTITY(1,1),
	[User_ID] [INT] NOT NULL,
	[App_ID] [INT] NOT NULL,
	CONSTRAINT FK_User FOREIGN KEY ([User_ID]) REFERENCES [Users](ID),
)

CREATE TABLE Client
(
	[ID] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[User_ID] INT NOT NULL,
	Client_ID INT NOT NULL,
	ClientKey INT NOT NULL
	CONSTRAINT FK_ClientUser FOREIGN KEY ([User_ID]) REFERENCES [Users](ID),
)
GO

CREATE PROCEDURE [dbo].[uspCreate_User]
(
	@firstname nvarchar(50),
	@lastname nvarchar(50),
	@email nvarchar(50),
	@password nvarchar(50),
	@phone nvarchar(15),
	@company_size nvarchar(50),
	@clientKey INT,
	@clientId INT,
	@status BIT
)
AS
BEGIN TRANSACTION

DECLARE @userId int

	SELECT @userId = 0

	IF(@userId > 0)
	UPDATE Users SET FirstName = @firstname, LastName = @lastname, Email = @email,
	Phone = @phone, CompanySize = @company_size, [Status] = @status
	ELSE

	SELECT @userId = ID from Users where FirstName = @firstname
	BEGIN
	INSERT INTO Users(FirstName,LastName,Email,Phone,CompanySize,[Password],[Status])
	VALUES(@firstname,@lastname,@email,@phone,@company_size,@password,@status)
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

