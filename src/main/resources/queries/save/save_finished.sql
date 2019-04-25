REPLACE INTO quests.finished (userId, finishedQuestId)
VALUES ((SELECT quests.users.id FROM quests.users WHERE quests.users.name = ?),
        (SELECT quests.quests.id FROM quests.quests WHERE quests.quests.name = ?));
