package io.github.poeticrainbow.voicechatplaceholders;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import io.github.poeticrainbow.voicechatplaceholders.papi.VoiceChatIconExpansion;
import io.github.poeticrainbow.voicechatplaceholders.voicechat.VoiceChatPlaceholdersPlugin;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class VoiceChatPlaceholders extends JavaPlugin implements CommandExecutor {
    public static VoiceChatPlaceholders plugin;
    private VoiceChatPlaceholdersPlugin voicechatPlugin;

    @Override
    public void onEnable() {
        plugin = this;
        loadConfigFromFile();

        // VoiceChat Hook
        BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);
        if (service != null) {
            voicechatPlugin = new VoiceChatPlaceholdersPlugin(this);
            service.registerPlugin(voicechatPlugin);
            getLogger().info("VoiceChatPlaceholders has successfully registered with VoiceChat!");
        } else {
            getLogger().severe("Could not load VoiceChat service!");
            getServer().getPluginManager().disablePlugin(this);
        }
        var registered = new VoiceChatIconExpansion().register();
        if (!registered) {
            getLogger().severe("The PlaceholderAPI expansion failed to be registered!");
        }

        // Command
        getLifecycleManager().registerEventHandler(
            LifecycleEvents.COMMANDS, event -> {
                var r = event.registrar();
                r.register(VoiceChatPlaceholdersCommand.command());
            }
        );
    }

    public String getIconForVoiceStatus(Statuses.VoiceStatus status) {
        return getConfig().getString(status.key);
    }

    public String getIconForGroupStatus(Statuses.GroupStatus status) {
        return getConfig().getString(status.key);
    }

    public String getStatusPlaceholder(UUID uniqueId) {
        return getVoiceStatusPlaceholder(uniqueId) + getGroupStatusPlaceholder(uniqueId);
    }

    public String getVoiceStatusPlaceholder(UUID uniqueId) {
        return getIconForVoiceStatus(voicechatPlugin.getVoiceStatus(uniqueId));
    }

    public String getGroupStatusPlaceholder(UUID uniqueId) {
        return getIconForGroupStatus(voicechatPlugin.getGroupStatus(uniqueId));
    }

    public void loadConfigFromFile() {
        saveDefaultConfig();
        reloadConfig();
    }
}
