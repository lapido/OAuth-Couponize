CREATE TABLE Users
(
	[ID] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	FirstName nvarchar(50) NOT NULL,
	LastName nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	[Password] nvarchar(100) NOT NULL,
	Phone nvarchar(20) NOT NULL,
	CompanySize nvarchar(50) NOT NULL,
	[IsDeleted] [bit] DEFAULT 0
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
	Client_Key nvarchar(100) NOT NULL
)
GO

CREATE PROCEDURE [dbo].[uspCreateUser]
(
	@firstname nvarchar(50),
	@lastname nvarchar(50),
	@email nvarchar(50),
	@password nvarchar(100),
	@phone nvarchar(20),
	@company_size nvarchar(50),
	@clientId nvarchar(100),
	@clientKey nvarchar(100),
	@appId nvarchar(100),
	@appKey nvarchar(100)
)
AS
BEGIN TRANSACTION

    DECLARE @userId int

	SELECT @userId = 0

    SELECT @userId = ID from Users where Email = @email

	IF(@userId > 0)
	    UPDATE Users SET FirstName = @firstname, LastName = @lastname, Email = @email,
	    Phone = @phone, CompanySize = @company_size
	ELSE

	BEGIN
        INSERT INTO Users(FirstName,LastName,Email,Phone,CompanySize,[Password])
        VALUES(@firstname,@lastname,@email,@phone,@company_size,@password)
        SELECT @userId = SCOPE_IDENTITY()
	END


	INSERT INTO UserClients([User_ID],Client_ID,Client_Key)
	    VALUES(@userId,@clientId,@clientKey)



	INSERT INTO User_Applications([User_ID], App_ID, App_Key)
	    VALUES(@userId, @appId, @appKey)
COMMIT
GO
