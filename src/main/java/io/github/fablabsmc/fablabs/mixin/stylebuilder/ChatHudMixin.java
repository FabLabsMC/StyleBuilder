package io.github.fablabsmc.fablabs.mixin.stylebuilder;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
	@Redirect(method = "render(Lnet/minecraft/client/util/math/MatrixStack;I)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V"))
	private void redirectMatrixStackTranslate(MatrixStack stack, double x, double y, double z) {
		stack.translate(x, y, -z); // 0, 0, -(-100)
	}
}
