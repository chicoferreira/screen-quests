CREATE DATABASE IF NOT EXISTS `quests` COLLATE utf8mb4_bin;
CREATE TABLE IF NOT EXISTS `quests`.`users`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(16)  NOT NULL UNIQUE
)
    ENGINE = InnoDB
    CHARSET = utf8mb4
    COLLATE utf8mb4_bin;
CREATE TABLE IF NOT EXISTS `quests`.`quests`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(32)  NOT NULL UNIQUE
) ENGINE = InnoDB
  CHARSET = utf8mb4
  COLLATE utf8mb4_bin;
CREATE TABLE IF NOT EXISTS `quests`.`objectives`
(
    `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(32)  NOT NULL UNIQUE
) ENGINE = InnoDB
  CHARSET = utf8mb4
  COLLATE utf8mb4_bin;
CREATE TABLE IF NOT EXISTS `quests`.`progresses`
(
    `userId`      INT UNSIGNED NOT NULL,
    `questId`     INT UNSIGNED NOT NULL,
    `objectiveId` INT UNSIGNED NOT NULL,
    `progress`    INT          NOT NULL,
    FOREIGN KEY (userId) REFERENCES `quests`.`users` (id),
    FOREIGN KEY (questId) REFERENCES `quests`.`quests` (id),
    FOREIGN KEY (objectiveId) REFERENCES `quests`.`objectives` (id),
    UNIQUE KEY (`userId`, `objectiveId`, `questId`)
) ENGINE = InnoDB
  CHARSET = utf8mb4
  COLLATE utf8mb4_bin;
CREATE TABLE IF NOT EXISTS `quests`.`finished`
(
    `userId`          INT UNSIGNED NOT NULL,
    `finishedQuestId` INT UNSIGNED NOT NULL,
    FOREIGN KEY (userId) REFERENCES `quests`.`users` (id),
    FOREIGN KEY (finishedQuestId) REFERENCES `quests`.`quests` (id),
    UNIQUE KEY (`userId`, `finishedQuestId`)
) ENGINE = InnoDB
  CHARSET = utf8mb4
  COLLATE utf8mb4_bin;