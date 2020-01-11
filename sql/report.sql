--
-- show students in class "1A" using classID
--
SELECT studentID, name, surname, email
FROM student
WHERE classID = 1;

-- -------------------------------------------------------
--
-- show students in class "2A", don't know the classID
--
SELECT studentID, name, surname, email
FROM student
INNER JOIN class
ON class.classID = student.classID
WHERE (classname = "2A");