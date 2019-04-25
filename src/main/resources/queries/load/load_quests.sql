SELECT (SELECT quests.quests.name FROM quests.quests WHERE quests.quests.id = progresses.questId) AS `quest`,
       (SELECT quests.objectives.name
        FROM quests.objectives
        WHERE quests.objectives.id = progresses.objectiveId)                                      AS `objective`,
       progresses.progress
FROM quests.progresses
         INNER JOIN quests.finished
WHERE (SELECT quests.users.id FROM quests.users WHERE quests.users.name = ?)