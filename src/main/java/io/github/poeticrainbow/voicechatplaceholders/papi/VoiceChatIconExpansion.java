package io.github.poeticrainbow.voicechatplaceholders.papi;

import io.github.poeticrainbow.voicechatplaceholders.Statuses;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.github.poeticrainbow.voicechatplaceholders.VoiceChatPlaceholders.plugin;

public class VoiceChatIconExpansion extends PlaceholderExpansion {
    public static final String PREFIX = "vcp";

    @Override
    public @NotNull String getIdentifier() {
        return PREFIX;
    }

    @Override
    public @NotNull String getAuthor() {
        return "PoeticRainbow";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        switch (params) {
            case "status" -> {
                if (player == null) {
                    return plugin.getIconForVoiceStatus(Statuses.VoiceStatus.DISCONNECTED);
                }
                return plugin.getStatusPlaceholder(player.getUniqueId());
            }
            case "voice_status" -> {
                if (player == null) {
                    return plugin.getIconForVoiceStatus(Statuses.VoiceStatus.DISCONNECTED);
                }
                return plugin.getVoiceStatusPlaceholder(player.getUniqueId());
            }
            case "group_status" -> {
                if (player == null) {
                    return plugin.getIconForGroupStatus(Statuses.GroupStatus.NOT_IN_GROUP);
                }
                return plugin.getGroupStatusPlaceholder(player.getUniqueId());
            }
            default -> {
                return String.format("Usage: %%%s_(status|voice_status|group_status)%%", PREFIX);
            }
        }
    }

    @Override
    public boolean persist() {
        return true;
    }
}
