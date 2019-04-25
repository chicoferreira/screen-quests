SELECT (SELECT quests.quests.name
        FROM quests.quests
        WHERE quests.quests.id = quests.finished.finishedQuestId) AS `finishedQuest`

FROM quests.finished
WHERE (SELECT quests.users.id FROM quests.users WHERE quests.users.name = ?);