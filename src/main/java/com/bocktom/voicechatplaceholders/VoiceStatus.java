package com.bocktom.voicechatplaceholders;

public enum VoiceStatus {
	TALKING("talking"),
	WHISPERING("whispering"),
	QUIET("quiet"),
	DISABLED("disabled"),
	NOT_INSTALLED("not_installed");

	public final String key;

	VoiceStatus(String key) {
		this.key = key;
	}
}
