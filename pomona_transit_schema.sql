CREATE TABLE Trip (
    TripNumber INT PRIMARY KEY,
    StartLocationName VARCHAR(100),
    DestinationName VARCHAR(100)
);

CREATE TABLE Driver (
    DriverName VARCHAR(100) PRIMARY KEY,
    DriverTelephoneNumber VARCHAR(20)
);

CREATE TABLE Bus (
    BusID INT PRIMARY KEY,
    Model VARCHAR(50),
    Year INT
);

CREATE TABLE TripOffering (
    TripNumber INT,
    Date DATE,
    ScheduledStartTime TIME,
    ScheduledArrivalTime TIME,
    DriverName VARCHAR(100),
    BusID INT,
    PRIMARY KEY (TripNumber, Date, ScheduledStartTime),
    FOREIGN KEY (TripNumber) REFERENCES Trip(TripNumber),
    FOREIGN KEY (DriverName) REFERENCES Driver(DriverName),
    FOREIGN KEY (BusID) REFERENCES Bus(BusID)
);

CREATE TABLE Stop (
    StopNumber INT PRIMARY KEY,
    StopAddress VARCHAR(255)
);

CREATE TABLE TripStopInfo (
    TripNumber INT,
    StopNumber INT,
    SequenceNumber INT,
    DrivingTime INT,
    PRIMARY KEY (TripNumber, StopNumber),
    FOREIGN KEY (TripNumber) REFERENCES Trip(TripNumber),
    FOREIGN KEY (StopNumber) REFERENCES Stop(StopNumber)
);

CREATE TABLE ActualTripStopInfo (
    TripNumber INT,
    Date DATE,
    ScheduledStartTime TIME,
    StopNumber INT,
    ScheduledArrivalTime TIME,
    ActualStartTime TIME,
    ActualArrivalTime TIME,
    NumberOfPassengerIn INT,
    NumberOfPassengerOut INT,
    PRIMARY KEY (TripNumber, Date, ScheduledStartTime, StopNumber),
    FOREIGN KEY (TripNumber) REFERENCES Trip(TripNumber),
    FOREIGN KEY (StopNumber) REFERENCES Stop(StopNumber)
);
