package io.github.poeticrainbow.voicechatplaceholders;

public class Statuses {
    public enum VoiceStatus {
        TALKING("talking"),
        WHISPERING("whispering"),
        QUIET("quiet"),
        DEAFENED("deafened"),
        DISCONNECTED("disconnected");

        public final String key;

        VoiceStatus(String key) {
            this.key = key;
        }
    }

    public enum GroupStatus {
        NORMAL_GROUP("normal_group"),
        OPEN_GROUP("open_group"),
        ISOLATED_GROUP("isolated_group"),
        NOT_IN_GROUP("not_in_group");

        public final String key;

        GroupStatus(String key) {
            this.key = key;
        }
    }
}
