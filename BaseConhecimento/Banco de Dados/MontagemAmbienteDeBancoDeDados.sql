USE [SeuBancoAqui]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TbBaseConhecimento](
	[Codigo] [int] IDENTITY(1,1) NOT NULL,
	[Categoria] [varchar](255) NULL,
	[Produto] [varchar](255) NULL,
	[Nucleo] [varchar](100) NULL,
	[Titulo] [varchar](500) NULL,
	[Descricao] [varchar](max) NULL,
	[Solucao] [varchar](max) NULL,
	[Obs] [varchar](max) NULL,
	[Ativo] [varchar](3) NULL,
	[Autor] [varchar](255) NULL,
	[Data] [datetime] NULL,
	[link] [varchar](max) NULL,
	[Caminho] [varchar](max) NULL
)

CREATE TABLE [dbo].[TbFichaServidores](
	[Codigo] [int] IDENTITY(1,1) NOT NULL,
	[Categoria] [varchar](255) NULL,
	[Produto] [varchar](255) NULL,
	[Nucleo] [varchar](100) NULL,
	[Titulo] [varchar](500) NULL,
	[Descricao] [varchar](max) NULL,
	[Info] [varchar](max) NULL,
	[Obs] [varchar](max) NULL,
	[Ativo] [varchar](3) NULL,
	[Autor] [varchar](255) NULL,
	[Data] [datetime] NULL,
	[link] [varchar](max) NULL,
	[Caminho] [varchar](max) NULL
)

