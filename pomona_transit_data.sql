INSERT INTO Trip VALUES
(1, 'Pomona', 'LA'),
(2, 'Pomona', 'San Diego');

Select * from TripOffering;

INSERT INTO Driver VALUES
('John Doe', '123-456-7890'),
('Jane Smith', '987-654-3210');

INSERT INTO Bus VALUES
(101, 'Model X', 2020),
(102, 'Model Y', 2021);

INSERT INTO TripOffering VALUES
(1, '2025-04-18', '08:00:00', '10:00:00', 'John Doe', 101),
(1, '2025-04-19', '09:00:00', '11:00:00', 'Jane Smith', 102);

INSERT INTO Stop VALUES
(1, 'Pomona Central Station'),
(2, 'LA Union Station');

INSERT INTO TripStopInfo VALUES
(1, 1, 1, 0),
(1, 2, 2, 120);

INSERT INTO ActualTripStopInfo VALUES
(1, '2025-04-18', '08:00:00', 1, '08:00:00', '08:05:00', '08:10:00', 10, 0),
(1, '2025-04-18', '08:00:00', 2, '10:00:00', '10:05:00', '10:10:00', 0, 10);