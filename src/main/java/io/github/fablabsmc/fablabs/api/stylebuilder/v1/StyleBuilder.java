package io.github.fablabsmc.fablabs.api.stylebuilder.v1;

import java.util.Objects;

import io.github.fablabsmc.fablabs.mixin.stylebuilder.StyleAccessor;

import net.minecraft.class_5251;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public final class StyleBuilder {
	// all nullable!
	private class_5251 color;
	private Boolean bold;
	private Boolean italic;
	private Boolean underlined;
	private Boolean strikethrough;
	private Boolean obfuscated;
	private ClickEvent clickEvent;
	private HoverEvent hoverEvent;
	private String insertion;
	private Identifier font;
	//
	private Style cache;

	public static StyleBuilder create() {
		return new StyleBuilder();
	}

	public static StyleBuilder create(Style style) {
		return new StyleBuilder().from(style);
	}

	private StyleBuilder() {
		reset();
	}

	public StyleBuilder color(class_5251 color) {
		this.color = color;
		return refresh();
	}

	public StyleBuilder bold(Boolean bold) {
		this.bold = bold;
		return refresh();
	}

	public StyleBuilder italic(Boolean italic) {
		this.italic = italic;
		return refresh();
	}

	public StyleBuilder underlined(Boolean underlined) {
		this.underlined = underlined;
		return refresh();
	}

	public StyleBuilder strikethrough(Boolean strikethrough) {
		this.strikethrough = strikethrough;
		return refresh();
	}

	public StyleBuilder obfuscated(Boolean obfuscated) {
		this.obfuscated = obfuscated;
		return refresh();
	}

	public StyleBuilder clickEvent(ClickEvent clickEvent) {
		this.clickEvent = clickEvent;
		return refresh();
	}

	public StyleBuilder hoverEvent(HoverEvent hoverEvent) {
		this.hoverEvent = hoverEvent;
		return refresh();
	}

	public StyleBuilder insertion(String insertion) {
		this.insertion = insertion;
		return refresh();
	}

	public StyleBuilder font(Identifier font) {
		this.font = font;
		return refresh();
	}

	public StyleBuilder format(Formatting formatting) {
		return format(formatting, true);
	}

	public StyleBuilder format(Formatting... formattings) {
		for (Formatting formatting : formattings) {
			format(formatting);
		}

		return this;
	}

	public StyleBuilder format(Formatting formatting, Boolean value) {
		if (formatting.isModifier()) {
			switch (formatting) {
			case OBFUSCATED:
				return obfuscated(value);
			case BOLD:
				return bold(value);
			case UNDERLINE:
				return underlined(value);
			case ITALIC:
				return italic(value);
			case STRIKETHROUGH:
				return strikethrough(value);
			default:
				throw new IllegalArgumentException("Unknown modifier formatting " + formatting);
			}
		}

		if (formatting.isColor()) {
			class_5251 newColor = class_5251.method_27718(formatting);

			if (value == null || !value) {
				if (Objects.equals(color, newColor)) {
					color = null; // unset
				}
			} else {
				color = newColor;
			}

			return refresh();
		}

		if (formatting == Formatting.RESET) {
			if (value != null && value) {
				return reset();
			} else {
				return this; // no changes
			}
		}

		throw new IllegalArgumentException("Unknown formatting " + formatting);
	}

	public StyleBuilder format(Boolean value, Formatting... formattings) {
		for (Formatting formatting : formattings) {
			format(formatting, value);
		}

		return this;
	}

	public StyleBuilder reset() {
		color = null;
		bold = null;
		underlined = null;
		strikethrough = null;
		italic = null;
		obfuscated = null;
		clickEvent = null;
		hoverEvent = null;
		insertion = null;
		font = null;
		// guaranteed empty style
		cache = Style.field_24360;
		return this;
	}

	private StyleBuilder refresh() {
		this.cache = null;
		return this;
	}

	public StyleBuilder from(Style style) {
		if (style.isEmpty()) {
			return reset();
		}

		StyleAccessor accessor = (StyleAccessor) style;

		color(style.getColor());
		bold(accessor.getBold());
		obfuscated(accessor.getObfuscated());
		underlined(accessor.getUnderline());
		italic(accessor.getItalic());
		strikethrough(accessor.getStrikethrough());
		clickEvent(style.getClickEvent());
		hoverEvent(style.getHoverEvent());
		insertion(style.getInsertion());
		font(accessor.accessFont());

		return refresh();
	}

	public StyleBuilder parent(Style parent) {
		if (parent.isEmpty()) {
			return this;
		}

		StyleAccessor accessor = (StyleAccessor) parent;

		class_5251 color = parent.getColor();

		if (color != null) {
			color(color);
		}

		Boolean bold = accessor.getBold();

		if (bold != null) {
			bold(bold);
		}

		Boolean obfuscated = accessor.getObfuscated();

		if (obfuscated != null) {
			obfuscated(obfuscated);
		}

		Boolean underlined = accessor.getUnderline();

		if (underlined != null) {
			underlined(underlined);
		}

		Boolean italic = accessor.getItalic();

		if (italic != null) {
			italic(italic);
		}

		Boolean strikethrough = accessor.getStrikethrough();

		if (strikethrough != null) {
			strikethrough(strikethrough);
		}

		ClickEvent clickEvent = parent.getClickEvent();

		if (clickEvent != null) {
			clickEvent(clickEvent);
		}

		HoverEvent hoverEvent = parent.getHoverEvent();

		if (hoverEvent != null) {
			hoverEvent(hoverEvent);
		}

		String insertion = parent.getInsertion();

		if (insertion != null) {
			insertion(insertion);
		}

		Identifier font = accessor.accessFont();

		if (font != null) {
			font(accessor.accessFont());
		}

		return this;
	}

	public Style build() {
		if (cache == null) {
			if (color == null && bold == null && italic == null && underlined == null && strikethrough == null && obfuscated == null
					&& clickEvent == null && hoverEvent == null && insertion == null && font == null) {
				cache = Style.field_24360;
			} else {
				cache = StyleAccessor.callConstructor(color, bold, italic, underlined, strikethrough, obfuscated, clickEvent, hoverEvent, insertion, font);
			}
		}

		return cache;
	}
}
