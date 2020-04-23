package io.github.fablabsmc.fablabs.mixin.stylebuilder;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.class_5251;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

@Mixin(Style.class)
public interface StyleAccessor {
	@Invoker("<init>")
	static Style callConstructor(class_5251 color, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough, Boolean obfuscated, ClickEvent clickEvent, HoverEvent hoverEvent, String insertion, Identifier font) {
		throw new AssertionError("Mixin dummy");
	}

	@Accessor
	Boolean getBold();

	@Accessor
	Boolean getItalic();

	@Accessor
	Boolean getUnderline();

	@Accessor
	Boolean getStrikethrough();

	@Accessor
	Boolean getObfuscated();

	@Accessor("field_24361")
	Identifier accessFont();
}
