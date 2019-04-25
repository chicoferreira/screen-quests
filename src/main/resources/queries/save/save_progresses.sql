REPLACE INTO quests.progresses (userId, questId, objectiveId, progress)
VALUES ((SELECT quests.users.id FROM quests.users WHERE quests.users.name = ?),
        (SELECT quests.quests.id FROM quests.quests WHERE quests.quests.name = ?),
        (SELECT quests.objectives.id
         FROM quests.objectives
         WHERE quests.objectives.name = ?),
        ?);