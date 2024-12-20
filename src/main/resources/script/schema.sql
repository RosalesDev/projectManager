USE [master]
GO
/****** Object:  Database [PROJECT_MANAGER_1]    Script Date: 18/11/2024 0:02:54 ******/
CREATE DATABASE [PROJECT_MANAGER_1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PROJECT_MANAGER_1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PROJECT_MANAGER_1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PROJECT_MANAGER_1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PROJECT_MANAGER_1_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PROJECT_MANAGER_1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET ARITHABORT OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET RECOVERY FULL 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET  MULTI_USER 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'PROJECT_MANAGER_1', N'ON'
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET QUERY_STORE = OFF
GO
USE [PROJECT_MANAGER_1]
GO
/****** Object:  User [api_user]    Script Date: 18/11/2024 0:02:54 ******/
CREATE USER [api_user] FOR LOGIN [api_user] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [api_user]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [api_user]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [api_user]
GO
ALTER ROLE [db_datareader] ADD MEMBER [api_user]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [api_user]
GO
/****** Object:  Table [dbo].[comment]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[step_id] [int] NULL,
	[task_id] [int] NULL,
	[user_person_id] [int] NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
	[status] [varchar](255) NULL,
	[text] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[project]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[project](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[manager_id] [int] NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
	[description] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[status] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[project_has_user_person]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[project_has_user_person](
	[enable] [bit] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[project_id] [int] NOT NULL,
	[user_person_id] [int] NOT NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[enable] [bit] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
	[code] [varchar](255) NULL,
	[description] [varchar](255) NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[step]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[step](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[is_finalized] [bit] NULL,
	[task_id] [int] NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
	[description] [varchar](255) NULL,
	[name] [varchar](255) NOT NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[task]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[task](
	[assigned_to] [int] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[project_id] [int] NOT NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[end_date] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
	[start_date] [datetime2](6) NULL,
	[description] [varchar](255) NULL,
	[status] [varchar](255) NULL,
	[title] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_person]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_person](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
	[email] [varchar](255) NULL,
	[firstname] [varchar](255) NULL,
	[lastname] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[status] [varchar](20) NOT NULL,
	[username] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UKimws8jk77jka6wimgad5r80g0] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_person_has_role]    Script Date: 18/11/2024 0:02:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_person_has_role](
	[enable] [bit] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[role_id] [int] NOT NULL,
	[user_person_id] [int] NOT NULL,
	[created_by] [bigint] NOT NULL,
	[date_created] [datetime2](6) NOT NULL,
	[date_modified] [datetime2](6) NULL,
	[modified_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKql4hek6lao2mbnbor60lvf10b]    Script Date: 18/11/2024 0:02:54 ******/
CREATE UNIQUE NONCLUSTERED INDEX [UKql4hek6lao2mbnbor60lvf10b] ON [dbo].[user_person]
(
	[email] ASC
)
WHERE ([email] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[project] ADD  DEFAULT ('PLANNING') FOR [status]
GO
ALTER TABLE [dbo].[project_has_user_person] ADD  DEFAULT ((1)) FOR [enable]
GO
ALTER TABLE [dbo].[role] ADD  DEFAULT ((1)) FOR [enable]
GO
ALTER TABLE [dbo].[step] ADD  DEFAULT ((1)) FOR [is_finalized]
GO
ALTER TABLE [dbo].[user_person_has_role] ADD  DEFAULT ((1)) FOR [enable]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD  CONSTRAINT [FK5pjxff9tt4d7b680ovwje6neh] FOREIGN KEY([user_person_id])
REFERENCES [dbo].[user_person] ([id])
GO
ALTER TABLE [dbo].[comment] CHECK CONSTRAINT [FK5pjxff9tt4d7b680ovwje6neh]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD  CONSTRAINT [FKfknte4fhjhet3l1802m1yqa50] FOREIGN KEY([task_id])
REFERENCES [dbo].[task] ([id])
GO
ALTER TABLE [dbo].[comment] CHECK CONSTRAINT [FKfknte4fhjhet3l1802m1yqa50]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD  CONSTRAINT [FKmibswcnn5e3g6y5utuqqghgk1] FOREIGN KEY([step_id])
REFERENCES [dbo].[step] ([id])
GO
ALTER TABLE [dbo].[comment] CHECK CONSTRAINT [FKmibswcnn5e3g6y5utuqqghgk1]
GO
ALTER TABLE [dbo].[project]  WITH CHECK ADD  CONSTRAINT [FKnxsstuj7agdndvltdbq5leev4] FOREIGN KEY([manager_id])
REFERENCES [dbo].[user_person] ([id])
GO
ALTER TABLE [dbo].[project] CHECK CONSTRAINT [FKnxsstuj7agdndvltdbq5leev4]
GO
ALTER TABLE [dbo].[project_has_user_person]  WITH CHECK ADD  CONSTRAINT [FK15dsffteuc2flutog2psu5j9e] FOREIGN KEY([project_id])
REFERENCES [dbo].[project] ([id])
GO
ALTER TABLE [dbo].[project_has_user_person] CHECK CONSTRAINT [FK15dsffteuc2flutog2psu5j9e]
GO
ALTER TABLE [dbo].[project_has_user_person]  WITH CHECK ADD  CONSTRAINT [FKad4fwjjee0eyu2uk5lkkq96ra] FOREIGN KEY([user_person_id])
REFERENCES [dbo].[user_person] ([id])
GO
ALTER TABLE [dbo].[project_has_user_person] CHECK CONSTRAINT [FKad4fwjjee0eyu2uk5lkkq96ra]
GO
ALTER TABLE [dbo].[step]  WITH CHECK ADD  CONSTRAINT [FKps32v8qvqmdl3ek14wmd5qam2] FOREIGN KEY([task_id])
REFERENCES [dbo].[task] ([id])
GO
ALTER TABLE [dbo].[step] CHECK CONSTRAINT [FKps32v8qvqmdl3ek14wmd5qam2]
GO
ALTER TABLE [dbo].[task]  WITH CHECK ADD  CONSTRAINT [FKam6pcssbjvofhwnx380phcto4] FOREIGN KEY([assigned_to])
REFERENCES [dbo].[user_person] ([id])
GO
ALTER TABLE [dbo].[task] CHECK CONSTRAINT [FKam6pcssbjvofhwnx380phcto4]
GO
ALTER TABLE [dbo].[task]  WITH CHECK ADD  CONSTRAINT [FKk8qrwowg31kx7hp93sru1pdqa] FOREIGN KEY([project_id])
REFERENCES [dbo].[project] ([id])
GO
ALTER TABLE [dbo].[task] CHECK CONSTRAINT [FKk8qrwowg31kx7hp93sru1pdqa]
GO
ALTER TABLE [dbo].[user_person_has_role]  WITH CHECK ADD  CONSTRAINT [FKj29udxn8gh2escmy8u9lonahc] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[user_person_has_role] CHECK CONSTRAINT [FKj29udxn8gh2escmy8u9lonahc]
GO
ALTER TABLE [dbo].[user_person_has_role]  WITH CHECK ADD  CONSTRAINT [FKqw74njf2hos40k5r48tfpk44g] FOREIGN KEY([user_person_id])
REFERENCES [dbo].[user_person] ([id])
GO
ALTER TABLE [dbo].[user_person_has_role] CHECK CONSTRAINT [FKqw74njf2hos40k5r48tfpk44g]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD CHECK  (([status]='DELETED' OR [status]='FLAGGED' OR [status]='REJECTED' OR [status]='APPROVED'))
GO
ALTER TABLE [dbo].[project]  WITH CHECK ADD CHECK  (([status]='CANCELED' OR [status]='COMPLETED' OR [status]='UNDER_REVIEW' OR [status]='PAUSED' OR [status]='IN_PROGRESS' OR [status]='ON_HOLD' OR [status]='PLANNING'))
GO
ALTER TABLE [dbo].[step]  WITH CHECK ADD CHECK  (([status]='DEACTIVE' OR [status]='COMPLETED' OR [status]='STARTED' OR [status]='PENDING'))
GO
ALTER TABLE [dbo].[task]  WITH CHECK ADD CHECK  (([status]='COMPLETED' OR [status]='STARTED' OR [status]='PENDING'))
GO
ALTER TABLE [dbo].[user_person]  WITH CHECK ADD CHECK  (([status]='DEACTIVE' OR [status]='ACTIVE'))
GO
USE [master]
GO
ALTER DATABASE [PROJECT_MANAGER_1] SET  READ_WRITE 
GO
