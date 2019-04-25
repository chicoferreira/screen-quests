package com.redescreen.quests;

public class Defaults {

    public static final String COMMAND_HELP_FORMAT = " \n                      §e§lAJUDA DE QUESTS\n \n{commands}\n \n";
    public static final String COMMAND_HELP_COMMAND_FORMAT = "   §6• §f/{command} {arguments}§6- §e{description}";

    public static final String COMMAND_NO_PERMISSION = "§cVocê não tem permissão para executar esse comando.";
    public static final String COMMAND_NAME = "quests";

    public static final String QUEST_OBJECTIVES_FINISH = "§aVocê terminou todos os objetivos da missão §f{quest}§a. Digite §f/" + COMMAND_NAME + " §apara pegar suas recompensas.";
    public static final String QUEST_FINISH = "§aVocê completou a missão §f{quest}§a.";
    public static final String COMMAND_NOT_ENOUGH_ARGUMENTS = "§cPor favor, digite: /" + COMMAND_NAME + " {subcommand} {arguments}";
    public static final String QUEST_NOT_FOUND = "§cNão foi encontrada nenhuma missão com o nome §f{quest}§c.";
    public static final String QUEST_FORCE_FINISH = "§eVocê forçou o término da missão §f{quest} §edo jogador §f{user}§e.";
}
