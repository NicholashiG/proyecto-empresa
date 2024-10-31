USE [master]
GO
/****** Object:  Database [PruebaSQL]    Script Date: 10/30/2024 10:01:48 PM ******/
CREATE DATABASE [PruebaSQL]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PruebaSQL', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\PruebaSQL.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PruebaSQL_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\PruebaSQL_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [PruebaSQL] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PruebaSQL].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PruebaSQL] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PruebaSQL] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PruebaSQL] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PruebaSQL] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PruebaSQL] SET ARITHABORT OFF 
GO
ALTER DATABASE [PruebaSQL] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PruebaSQL] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PruebaSQL] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PruebaSQL] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PruebaSQL] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PruebaSQL] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PruebaSQL] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PruebaSQL] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PruebaSQL] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PruebaSQL] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PruebaSQL] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PruebaSQL] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PruebaSQL] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PruebaSQL] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PruebaSQL] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PruebaSQL] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PruebaSQL] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PruebaSQL] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PruebaSQL] SET  MULTI_USER 
GO
ALTER DATABASE [PruebaSQL] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PruebaSQL] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PruebaSQL] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PruebaSQL] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PruebaSQL] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PruebaSQL] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [PruebaSQL] SET QUERY_STORE = ON
GO
ALTER DATABASE [PruebaSQL] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [PruebaSQL]
GO
USE [PruebaSQL]
GO
/****** Object:  Sequence [dbo].[reserva_seq]    Script Date: 10/30/2024 10:01:49 PM ******/
CREATE SEQUENCE [dbo].[reserva_seq] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[auditoria]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[auditoria](
	[id_auditoria] [int] NOT NULL,
	[accion] [varchar](110) NOT NULL,
	[fecha] [varchar](30) NOT NULL,
	[hora] [varchar](30) NOT NULL,
	[usuario_id_usuario] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_auditoria] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[departamento]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[departamento](
	[id_departamento] [int] NOT NULL,
	[nombre] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_departamento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[empleado_nico]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[empleado_nico](
	[id_empleado] [int] IDENTITY(1,1) NOT NULL,
	[apellido] [varchar](255) NOT NULL,
	[nombre] [varchar](255) NOT NULL,
	[sucursal_id_sucursal] [int] NOT NULL,
	[tipo_empleado_id_tipo_empleado] [int] NOT NULL,
	[usuario_id_usuario] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_empleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[municipio]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[municipio](
	[id_municipio] [int] NOT NULL,
	[nombre] [varchar](30) NOT NULL,
	[departamento_id_departamento] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_municipio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nivel_acceso]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nivel_acceso](
	[id_nivel] [int] NOT NULL,
	[descripcion] [varchar](110) NOT NULL,
	[nombre] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_nivel] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[pago_cuota]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pago_cuota](
	[id_pago_cuota] [int] NOT NULL,
	[fecha_pago] [varchar](20) NOT NULL,
	[numero_cuota] [int] NOT NULL,
	[valor_pago] [real] NOT NULL,
	[prestamo_id_prestamo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_pago_cuota] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[periodo_solicitud]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[periodo_solicitud](
	[id_periodo_solicitud] [int] NOT NULL,
	[meses] [int] NOT NULL,
	[tasa_interes] [real] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_periodo_solicitud] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[prestamo]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[prestamo](
	[id_prestamo] [int] NOT NULL,
	[fecha_desembolso] [varchar](20) NOT NULL,
	[saldo] [real] NOT NULL,
	[solicitud_id_solicitud] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_prestamo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[solicitud]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[solicitud](
	[id_solicitud] [int] NOT NULL,
	[estado] [varchar](50) NOT NULL,
	[fecha] [varchar](20) NOT NULL,
	[monto_solicitado] [real] NOT NULL,
	[empleado_id_empleado] [int] NOT NULL,
	[periodo_solicitud_id_periodo_solicitud] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_solicitud] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sucursal]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sucursal](
	[id_sucursal] [int] NOT NULL,
	[direccion] [varchar](110) NOT NULL,
	[nombre] [varchar](30) NOT NULL,
	[municipio_id_municipio] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_sucursal] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tipo_empleado]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tipo_empleado](
	[id_tipo_empleado] [int] NOT NULL,
	[nombre] [varchar](30) NOT NULL,
	[prestamo_maximo] [real] NOT NULL,
	[salario_mensual] [real] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_tipo_empleado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usuario]    Script Date: 10/30/2024 10:01:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuario](
	[id_usuario] [int] IDENTITY(1,1) NOT NULL,
	[clave] [varchar](255) NOT NULL,
	[login] [varchar](255) NOT NULL,
	[nivel_acceso_id_nivel] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[auditoria]  WITH CHECK ADD  CONSTRAINT [FKrw1i5bi4ipdsf4axdlvtxwd0t] FOREIGN KEY([usuario_id_usuario])
REFERENCES [dbo].[usuario] ([id_usuario])
GO
ALTER TABLE [dbo].[auditoria] CHECK CONSTRAINT [FKrw1i5bi4ipdsf4axdlvtxwd0t]
GO
ALTER TABLE [dbo].[empleado_nico]  WITH CHECK ADD  CONSTRAINT [FK113weqda1huj8t8j44rsgt2wv] FOREIGN KEY([sucursal_id_sucursal])
REFERENCES [dbo].[sucursal] ([id_sucursal])
GO
ALTER TABLE [dbo].[empleado_nico] CHECK CONSTRAINT [FK113weqda1huj8t8j44rsgt2wv]
GO
ALTER TABLE [dbo].[empleado_nico]  WITH CHECK ADD  CONSTRAINT [FK1v4at8qfnqol1eis3wvhsdnqr] FOREIGN KEY([tipo_empleado_id_tipo_empleado])
REFERENCES [dbo].[tipo_empleado] ([id_tipo_empleado])
GO
ALTER TABLE [dbo].[empleado_nico] CHECK CONSTRAINT [FK1v4at8qfnqol1eis3wvhsdnqr]
GO
ALTER TABLE [dbo].[empleado_nico]  WITH CHECK ADD  CONSTRAINT [FK9jxi86cbplb8q88j66665ojm8] FOREIGN KEY([usuario_id_usuario])
REFERENCES [dbo].[usuario] ([id_usuario])
GO
ALTER TABLE [dbo].[empleado_nico] CHECK CONSTRAINT [FK9jxi86cbplb8q88j66665ojm8]
GO
ALTER TABLE [dbo].[municipio]  WITH CHECK ADD  CONSTRAINT [FK10n3o3tsaa7f2287jsiwy7dxw] FOREIGN KEY([departamento_id_departamento])
REFERENCES [dbo].[departamento] ([id_departamento])
GO
ALTER TABLE [dbo].[municipio] CHECK CONSTRAINT [FK10n3o3tsaa7f2287jsiwy7dxw]
GO
ALTER TABLE [dbo].[pago_cuota]  WITH CHECK ADD  CONSTRAINT [FKg0ld9vg0awqq9nwfvrg7yfuus] FOREIGN KEY([prestamo_id_prestamo])
REFERENCES [dbo].[prestamo] ([id_prestamo])
GO
ALTER TABLE [dbo].[pago_cuota] CHECK CONSTRAINT [FKg0ld9vg0awqq9nwfvrg7yfuus]
GO
ALTER TABLE [dbo].[prestamo]  WITH CHECK ADD  CONSTRAINT [FKmb0iad8qebvetbdjvpwbr60hq] FOREIGN KEY([solicitud_id_solicitud])
REFERENCES [dbo].[solicitud] ([id_solicitud])
GO
ALTER TABLE [dbo].[prestamo] CHECK CONSTRAINT [FKmb0iad8qebvetbdjvpwbr60hq]
GO
ALTER TABLE [dbo].[solicitud]  WITH CHECK ADD  CONSTRAINT [FK7tkb2ot30k33nsdk0dmyioro1] FOREIGN KEY([periodo_solicitud_id_periodo_solicitud])
REFERENCES [dbo].[periodo_solicitud] ([id_periodo_solicitud])
GO
ALTER TABLE [dbo].[solicitud] CHECK CONSTRAINT [FK7tkb2ot30k33nsdk0dmyioro1]
GO
ALTER TABLE [dbo].[solicitud]  WITH CHECK ADD  CONSTRAINT [FKectdt73jaccibs6y3luf624ed] FOREIGN KEY([empleado_id_empleado])
REFERENCES [dbo].[empleado_nico] ([id_empleado])
GO
ALTER TABLE [dbo].[solicitud] CHECK CONSTRAINT [FKectdt73jaccibs6y3luf624ed]
GO
ALTER TABLE [dbo].[sucursal]  WITH CHECK ADD  CONSTRAINT [FKl0l9ucw5ljnw2h8ag6c9gxhc1] FOREIGN KEY([municipio_id_municipio])
REFERENCES [dbo].[municipio] ([id_municipio])
GO
ALTER TABLE [dbo].[sucursal] CHECK CONSTRAINT [FKl0l9ucw5ljnw2h8ag6c9gxhc1]
GO
ALTER TABLE [dbo].[usuario]  WITH CHECK ADD  CONSTRAINT [FKhvsk62iin3adsl290kdnr0pdj] FOREIGN KEY([nivel_acceso_id_nivel])
REFERENCES [dbo].[nivel_acceso] ([id_nivel])
GO
ALTER TABLE [dbo].[usuario] CHECK CONSTRAINT [FKhvsk62iin3adsl290kdnr0pdj]
GO
USE [master]
GO
ALTER DATABASE [PruebaSQL] SET  READ_WRITE 
GO
