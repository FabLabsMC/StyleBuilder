package io.github.fablabsmc.fablabs.impl.stylebuilder.user;

import com.mojang.brigadier.Command;
import io.github.fablabsmc.fablabs.api.stylebuilder.v1.StyleBuilder;

import net.minecraft.server.command.CommandManager;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import net.fabricmc.fabric.api.registry.CommandRegistry;

public final class StyleBuilderUser {
	private StyleBuilderUser() {
	}

	public static void init() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> dispatcher.register(
				CommandManager.literal("apple")
						.executes(ctx -> {
							LiteralText text = new LiteralText("Testing");
							StyleBuilder styleBuilder = StyleBuilder.create();
							styleBuilder.format(Formatting.AQUA)
									.strikethrough(true)
									.obfuscated(false)
									.underlined(true)
									.clickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/help"));
							text.setStyle(styleBuilder.build());
							ctx.getSource().sendFeedback(text, false);
							return Command.SINGLE_SUCCESS;
						})
		));
	}
}
