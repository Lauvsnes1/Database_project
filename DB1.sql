CREATE TABLE IF NOT EXISTS users(
	UserID INTEGER NOT NULL,
    Email VARCHAR(50),
    Pword VARCHAR(50),
    UserType VARCHAR(50),
    
    CONSTRAINT User_PK PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS post(
	PostID INTEGER NOT NULL,
    Content TEXT,
    Anonymous BOOLEAN,
    UserID INTEGER NOT NULL,
    
    CONSTRAINT post_PK PRIMARY KEY (PostID),
    CONSTRAINT post_FK FOREIGN KEY(UserID) REFERENCES users(UserID)
);

CREATE TABLE IF NOT EXISTS liked(
	PostID INTEGER NOT NULL,
	UserID INTEGER NOT NULL,

	CONSTRAINT liked_fk PRIMARY KEY (PostID,UserID),
	CONSTRAINT liked_fk1 FOREIGN KEY (UserID) REFERENCES users(UserID),
	CONSTRAINT liked_fk2 FOREIGN KEY (PostID) REFERENCES post(PostID)
);

CREATE TABLE IF NOT EXISTS viewed(
	PostID INTEGER NOT NULL,
	UserID INTEGER NOT NULL,
    ViewedDate DATE,

	CONSTRAINT viewed_fk PRIMARY KEY (PostID,UserID),
	CONSTRAINT viewed_fk1 FOREIGN KEY (UserID) REFERENCES users(UserID),
	CONSTRAINT viewed_fk2 FOREIGN KEY (PostID) REFERENCES post(PostID)
);

CREATE TABLE IF NOT EXISTS course(
	CourseID INTEGER NOT NULL,
    CourseName VARCHAR(50),
    Term VARCHAR(50),
    CanBeAnonymous BOOLEAN,
    UserID INTEGER NOT NULL,
    
    CONSTRAINT course_pk PRIMARY KEY (CourseID),
    CONSTRAINT course_fk FOREIGN KEY (UserID) REFERENCES users(UserID) -- den som oppretta courset
    
);

CREATE TABLE IF NOT EXISTS folder(
	FolderID INTEGER NOT NULL,
	FolderName VARCHAR(50),
    CourseID INTEGER NOT NULL,
    
    CONSTRAINT folder_pk PRIMARY KEY (FolderID),
    CONSTRAINT folder_fk FOREIGN KEY (CourseID) REFERENCES course(CourseID)
);

CREATE TABLE IF NOT EXISTS thread(
	PostID INTEGER NOT NULL,
    Anonymous BOOLEAN,
    Content TEXT,
    Tag VARCHAR(30),
    Header VARCHAR(50),
    UserID INTEGER NOT NULL,
    FolderID INTEGER NOT NULL,
    
    CONSTRAINT thread_pk PRIMARY KEY(PostID),
    CONSTRAINT thread_fk1 FOREIGN KEY (UserID) REFERENCES users(UserID),
    CONSTRAINT thread_fk2 FOREIGN KEY (FolderID) REFERENCES folder(FolderID)
);

CREATE TABLE IF NOT EXISTS reply(
	PostID INT NOT NULL,
    Anonymous BOOLEAN,
    Content TEXT,
    UserID INT NOT NULL,
    ThreadNr INT NOT NULL,
    
    CONSTRAINT reply_pk PRIMARY KEY (PostID),
    CONSTRAINT reply_fk1 FOREIGN KEY (UserID) REFERENCES users(UserID),
    CONSTRAINT reply_fk2 FOREIGN KEY (ThreadNr) REFERENCES thread(postID) -- Bruker ThreadNr som navn på thread sin PostID for å gjøre det mindre forrvirrende
    
);


