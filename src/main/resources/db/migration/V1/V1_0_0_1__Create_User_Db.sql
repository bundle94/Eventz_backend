-- CREATE TABLE users
IF  NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type in (N'U'))
  BEGIN
    CREATE TABLE [dbo].[users](
      [id] [BIGINT] IDENTITY(1,1) NOT NULL,
      [first_name] VARCHAR(50),
      [last_name] VARCHAR(50),
      [email] VARCHAR(50),
      [user_name] VARCHAR(50),
      [password] VARCHAR(100),
      [phone_number] VARCHAR(50),
      [created_on] DATETIME2(7),
      [updated_on] DATETIME2(7),
      [address] VARCHAR(100)
      CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED
        (
          [id] ASC
        )
    ) ON [PRIMARY]
  END
GO

-- CREATE STORED PROCEDURE create_user
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = object_id (N'[dbo].[create_user]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
  DROP PROCEDURE [dbo].[create_user]
GO

CREATE PROCEDURE create_user
  (
    @id BIGINT = NULL OUTPUT,
    @first_name VARCHAR(50),
    @last_name VARCHAR(50),
    @email VARCHAR(50),
    @user_name VARCHAR(50),
    @password VARCHAR(100),
    @phone_number VARCHAR(50)
  )
AS
  SET NOCOUNT ON
  INSERT INTO [dbo].[users]
  (
    first_name,
    last_name,
    email,
    user_name,
    password,
    phone_number,
    created_on,
    updated_on,
    address
  )
  VALUES
    (
      @first_name,
      @last_name,
      @email,
      @user_name,
      @password,
      @phone_number,
      GETDATE(),
      GETDATE(),
      NULL
    )
  SELECT @id = SCOPE_IDENTITY()

  RETURN @@Error
GO

-- CREATE STORED PROCEDURE find_all_users
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = object_id (N'[dbo].[find_all_users]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
  DROP PROCEDURE [dbo].[find_all_users]
GO

CREATE PROCEDURE find_all_users
AS
  SET NOCOUNT ON
  SELECT * FROM [dbo].[users] WITH (NOLOCK)

  RETURN @@Error
GO


-- CREATE STORED PROCEDURE find_user_by_username_password
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = object_id (N'[dbo].[find_user_by_username_password]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
  DROP PROCEDURE [dbo].[find_user_by_username_password]
GO

CREATE PROCEDURE find_user_by_username_password
(
    @user_name VARCHAR(50),
    @password VARCHAR(100)
)
AS
  SET NOCOUNT ON
  SELECT * FROM [dbo].[users] WITH (NOLOCK) WHERE user_name = @user_name AND password = @password

  RETURN @@Error
GO

-- CREATE STORED PROCEDURE find_user_by_email
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = object_id (N'[dbo].[find_user_by_email]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
  DROP PROCEDURE [dbo].[find_user_by_email]
GO

CREATE PROCEDURE find_user_by_email
(
    @email VARCHAR(50)
)
AS
  SET NOCOUNT ON
  SELECT * FROM [dbo].[users] WITH (NOLOCK) WHERE email = @email

  RETURN @@Error
GO