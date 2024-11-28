--Part 1
  id: int
  employer: varchar(255)
  name: varchar(255)
  questTags: varchar(255)

--Part 2
SELECT name FROM employer WHERE location = "St. Louis City";

--Part 3
DROP TABLE quest;

--Part 4
SELECT *
FROM questTag
LEFT JOIN quest_questTags
ON questTag.id = quest_questTags.questTags_id
ORDER BY name ASC;