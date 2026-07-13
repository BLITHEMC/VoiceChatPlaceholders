package com.bocktom.voicechatplaceholders;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.ConsoleCommandSender;

import static com.bocktom.voicechatplaceholders.VoiceChatPlaceholders.plugin;
import static io.papermc.paper.command.brigadier.Commands.literal;

public class VoiceChatPlaceholdersCommand {
    public static LiteralCommandNode<CommandSourceStack> command() {
        return literal("voicechatplaceholders").requires(source -> {
            var sender = source.getSender();
            return sender instanceof ConsoleCommandSender || sender.hasPermission("voicechatplaceholders.admin");
        }).then(literal("reload").executes(context -> {
            plugin.loadConfigFromFile();
            context.getSource().getSender().sendRichMessage("<blue>Reloaded the config.yml from file.");
            return 1;
        })).then(literal("list").executes(context -> {
            var sender = context.getSource().getSender();

            sender.sendRichMessage("<gold><b>Supported Voice Statuses</b>:");
            for (VoiceStatus status : VoiceStatus.values()) {
                sender.sendRichMessage("- <blue><status><gray>: <white><icon>", Placeholder.component("status", Component.text(status.key)), Placeholder.component("icon", Component.text(plugin.getIconForStatus(status))));
            }

            return 1;
        })).build();
    }
}
