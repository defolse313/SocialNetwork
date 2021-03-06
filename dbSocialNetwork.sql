USE [master]
GO
/****** Object:  Database [SocialNetwork]    Script Date: 9/30/2020 11:12:10 PM ******/
CREATE DATABASE [SocialNetwork]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SocialNetwork', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SocialNetwork.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SocialNetwork_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SocialNetwork_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SocialNetwork] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SocialNetwork].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SocialNetwork] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SocialNetwork] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SocialNetwork] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SocialNetwork] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SocialNetwork] SET ARITHABORT OFF 
GO
ALTER DATABASE [SocialNetwork] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SocialNetwork] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SocialNetwork] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SocialNetwork] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SocialNetwork] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SocialNetwork] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SocialNetwork] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SocialNetwork] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SocialNetwork] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SocialNetwork] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SocialNetwork] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SocialNetwork] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SocialNetwork] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SocialNetwork] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SocialNetwork] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SocialNetwork] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SocialNetwork] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SocialNetwork] SET RECOVERY FULL 
GO
ALTER DATABASE [SocialNetwork] SET  MULTI_USER 
GO
ALTER DATABASE [SocialNetwork] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SocialNetwork] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SocialNetwork] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SocialNetwork] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SocialNetwork] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SocialNetwork', N'ON'
GO
USE [SocialNetwork]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 9/30/2020 11:12:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Article](
	[articleID] [int] IDENTITY(1,1) NOT NULL,
	[memberId] [varchar](30) NOT NULL,
	[articleTitle] [varchar](200) NOT NULL,
	[articleContent] [nvarchar](max) NOT NULL,
	[articleImage] [nvarchar](max) NULL,
	[articleDate] [datetime] NOT NULL,
	[articleStatus] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[articleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 9/30/2020 11:12:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Comment](
	[commentId] [int] IDENTITY(1,1) NOT NULL,
	[articleId] [int] NOT NULL,
	[memberId] [varchar](30) NOT NULL,
	[commentContent] [varchar](500) NULL,
	[commentDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[commentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Emotion]    Script Date: 9/30/2020 11:12:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Emotion](
	[emotionId] [int] IDENTITY(1,1) NOT NULL,
	[articleId] [int] NOT NULL,
	[memberId] [varchar](30) NOT NULL,
	[emotion] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[emotionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Member]    Script Date: 9/30/2020 11:12:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Member](
	[memberId] [varchar](30) NOT NULL,
	[memberPassword] [varchar](100) NOT NULL,
	[memberFullname] [nvarchar](100) NOT NULL,
	[role] [varchar](10) NOT NULL,
	[memberStatus] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[memberId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Article] ON 

INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2029, N'hieune@yahoo.com', N'PEACE FEELING SOUND', N'I have just completed my art, check it out!!', N'img1.jpg', CAST(N'2000-08-18 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2030, N'itisnotme@fpt.edu.vn', N'Muichiro Tokito- Our hero', N'Muichiro is noted to have completely mastered this breathing style, having become proficient in its usage to the point where he could easily fight against and overwhelm Upper Rank 5. He is even capable of creating an entirely new seventh form to this style.', N'img2.jpg', CAST(N'2018-12-21 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2031, N'ucproqua789@gmail.com', N'Anne of the Island', N'“The night was so very still that one should have been able to hear the whisper of roses in blossom—the laughter of daisies—the piping of grasses—many sweet sounds, all tangled up together. The beauty of moonlight on familiar fields irradiated the world.”
― L.M. Montgomery.', N'img3.jpg', CAST(N'2017-04-21 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2037, N'hieudinh3103@gmail.com', N'The Inner Life of Cats', N'As anyone who has spent time with cats knows, our feline companions are mysterious—much more so than those other furry family members. Here John Bradshaw, author of Cat Sense (Basic Books, 2013), fields a selection of questions submitted by Scientific American editors and Twitter followers about the cat’s many quirks. Bradshaw is a visiting fellow at the University of Bristol School of Veterinary Sciences in England, where he studies the behavior and welfare of cats and dogs, as well as their interactions with people.', N'img9.jpg', CAST(N'2020-03-31 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2038, N'ucproqua789@gmail.com', N'BLACKPINK announce new single ‘Lovesick Girls’', N'‘Lovesick Girls’ will be the third single release from ‘THE ALBUM’ so far, which is slated for release on Friday, October 2.

It follows the release of ‘How You Like That‘ back in June and ‘Ice Cream‘ alongside Selena Gomez at the end of last month.

The two aforementioned singles follow the release of their collaboration with Lady Gaga, ‘Sour Candy‘, which featured on her album ‘Chromatica‘.', N'img4.jpg', CAST(N'2020-09-02 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2040, N'hieudinh003@gmail.com', N'The Movie Is Based In 1981 Which Was Done Deliberately', N'According to director Todd Phillips, this was done to keep the movie away from the current events in the DC Extended Universe.', N'img6.jpg', CAST(N'2020-09-30 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2046, N'hieune@yahoo.com', N'Nahh im getting angry', N'mew mew mew mew mew', N'img5.jpg', CAST(N'2020-08-08 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2047, N'ucproqua789@gmail.com', N'Just some of my sketch', N'A pink concept wth cola bottles', N'img7.jpg', CAST(N'2020-06-06 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2049, N'ucproqua789@gmail.com', N'VINAMAN is introduced to VietNamese people', N'A Vietnam superheroes universe is about to start with VINAMAN at the beginning, so excitinggg', N'img8.jpg', CAST(N'2020-09-25 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2050, N'ucproqua789@gmail.com', N'THE MOON', N'Nothing better in the mid autumn fes than eating mooncake and look at the moon', N'img10.jpg', CAST(N'1999-10-01 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2051, N'itisnotme@fpt.edu.vn', N'What is java', N' Java code can run on all platforms that support Java without the need for recompilation.[18] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but it has fewer low-level facilities than either of them. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[19][20] particularly for client-server web applications, with a reported 9 million developers', N'img11.jpg', CAST(N'2019-05-05 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2052, N'itisnotme@fpt.edu.vn', N'Demon Slayer: Kimetsu no Yaiba', N' It follows Tanjiro Kamado, a young boy who becomes a demon slayer after his family is slaughtered and his younger sister Nezuko is turned into a demon. The manga was serialized in Weekly Shōnen Jump from February 2016 to May 2020, and its chapters collected in 21 tankōbon volumes as of July 2020. It is published in English by Viz Media and simulpublished by Shueisha in English and Spanish on their Manga Plus platform.', N'img12.jpg', CAST(N'2018-05-15 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2053, N'hieudinh003@gmail.com', N'Ve Khoi Ra May', N'In meteorology, a cloud is an aerosol consisting of a visible mass of minute liquid droplets, frozen crystals, or other particles suspended in the atmosphere of a planetary body or similar space.[1] Water or various other chemicals may compose the droplets and crystals. On Earth, clouds are formed as a result of saturation of the air when it is cooled to its dew point, or when it gains sufficient moisture (usually in the form of water vapor) from an adjacent source to raise the dew point to the ambient temperature.', N'img13.jpg', CAST(N'2007-06-24 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2054, N'itisnotme@fpt.edu.vn', N'Death is like a wind, always by my side', N'"Hasagi"', N'img14.jpg', CAST(N'2007-07-07 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2055, N'itisnotme@fpt.edu.vn', N'The reunion of Stranger Things Casts', N'Stranger Things is an American science fiction horror web television series created by the Duffer Brothers and released on Netflix. The twins serve as showrunners and are executive producers along with Shawn Levy and Dan Cohen. The series premiered on Netflix on July 15, 2016. Set in the 1980s in the fictional town of Hawkins, Indiana, the first season focuses on the investigation into the disappearance of a young boy amid supernatural events occurring around the town, including the appearance of a girl with psychokinetic abilities. The series stars an ensemble cast including Winona Ryder, David Harbour, Finn Wolfhard, Millie Bobby Brown, Gaten Matarazzo, Caleb McLaughlin, Noah Schnapp, Sadie Sink, Natalia Dyer, Charlie Heaton, Joe Keery, Cara Buono and Dacre Montgomery.', N'img15.jpg', CAST(N'2013-06-13 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2056, N'hieune@yahoo.com', N'Appreciation: Chadwick Boseman held the screen with power and unerring purpose', N'Boseman coaxed forth heroes and legends from the pages of fiction and history alike: He was the mighty King T’Challa in “Black Panther,” but was also Jackie Robinson in “42,” Thurgood Marshall in “Marshall” and, in his most electrifying performance, James Brown in “Get on Up.” How does a guy casually tackle one icon after another — Black men whose pioneering achievements reshaped the predominantly white worlds of professional sports, popular music, American government and Marvel superheroics — without seeming smug or self-aggrandizing?', N'img16.jpg', CAST(N'2018-04-30 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2057, N'hieune@yahoo.com', N'Welcome home, spidey', N'Sony and Walt Disney on Friday announced that Marvel Studios, led by President Kevin Feige, will produce a third film in Spider-Man: Homecoming series. The film will star Tom Holland and is scheduled for release on July 16, 2021.', N'img17.jpg', CAST(N'2019-05-13 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2058, N'hieune@yahoo.com', N'Rave it', N'Tell me who you can recognise?', N'img18.jpg', CAST(N'2011-11-11 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2059, N'hieudinh003@gmail.com', N'A cute one', N'Just my pinky keyboard', N'img19.jpg', CAST(N'2014-05-23 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2060, N'hieudinh003@gmail.com', N'Which village you join?', N'I would be a Leaf Village Shinobu', N'img20.jpg', CAST(N'2016-03-24 00:00:00.000' AS DateTime), N'ACTIVE')
INSERT [dbo].[Article] ([articleID], [memberId], [articleTitle], [articleContent], [articleImage], [articleDate], [articleStatus]) VALUES (2061, N'hieudinh003@gmail.com', N'A wish-list-room', N'It is simply beautiful', N'img21.jpg', CAST(N'2015-06-17 00:00:00.000' AS DateTime), N'ACTIVE')
SET IDENTITY_INSERT [dbo].[Article] OFF
SET IDENTITY_INSERT [dbo].[Emotion] ON 

INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (6, 2040, N'hieudinh3103@gmail.com', N'UNDEFINED')
INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (7, 2040, N'hieudinh003@gmail.com', N'DISLIKE')
INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (8, 2038, N'hieudinh003@gmail.com', N'DISLIKE')
INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (9, 2037, N'hieudinh003@gmail.com', N'LOVE')
INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (10, 2030, N'hieudinh003@gmail.com', N'DISLIKE')
INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (11, 2040, N'hieune@yahoo.com', N'LOVE')
INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (12, 2038, N'hieune@yahoo.com', N'LOVE')
INSERT [dbo].[Emotion] ([emotionId], [articleId], [memberId], [emotion]) VALUES (13, 2029, N'hieudinh003@gmail.com', N'DISLIKE')
SET IDENTITY_INSERT [dbo].[Emotion] OFF
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'1', N'6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918', N'1', N'member', N'new')
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'aivayta@123.com', N'8d23cf6c86e834a7aa6eded54c26ce2bb2e74903538c61bdd5d2197997ab2f72', N'Hieu thu hai', N'member', N'new')
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'hieudinh003@gmail.com', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Hieu thu nhat', N'member', N'new')
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'hieudinh3103@gmail.com', N'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', N'Dau cat moi', N'admin', N'new')
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'hieune@yahoo.com', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Hieu Dinh', N'member', N'new')
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'itisnotme@fpt.edu.vn', N'138d9e809e386a7b800791d1f664f56d1c55f3d1ba411b950862729bc486c5ce', N'Hieu thu ba', N'member', N'new')
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'SN002', N'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', N'Rose Huan', N'member', N'new')
INSERT [dbo].[Member] ([memberId], [memberPassword], [memberFullname], [role], [memberStatus]) VALUES (N'ucproqua789@gmail.com', N'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', N'Huong', N'member', N'new')
ALTER TABLE [dbo].[Comment] ADD  CONSTRAINT [tft]  DEFAULT ('09/26/2020') FOR [commentDate]
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD FOREIGN KEY([memberId])
REFERENCES [dbo].[Member] ([memberId])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([articleId])
REFERENCES [dbo].[Article] ([articleID])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([memberId])
REFERENCES [dbo].[Member] ([memberId])
GO
ALTER TABLE [dbo].[Emotion]  WITH CHECK ADD FOREIGN KEY([articleId])
REFERENCES [dbo].[Article] ([articleID])
GO
ALTER TABLE [dbo].[Emotion]  WITH CHECK ADD FOREIGN KEY([memberId])
REFERENCES [dbo].[Member] ([memberId])
GO
USE [master]
GO
ALTER DATABASE [SocialNetwork] SET  READ_WRITE 
GO
