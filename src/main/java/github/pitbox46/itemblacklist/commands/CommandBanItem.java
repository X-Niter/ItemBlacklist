package github.pitbox46.itemblacklist.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.pitbox46.itemblacklist.ItemBlacklist;
import github.pitbox46.itemblacklist.JsonUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ItemArgument;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CommandBanItem implements Command<CommandSource> {
    private static final CommandBanItem CMD = new CommandBanItem();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands
                .literal("ban")
                .requires(cs -> cs.hasPermissionLevel(2))
                .then(Commands.argument("item", ItemArgument.item())
                        .executes(CMD));
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        JsonUtils.appendItemToJson(ItemBlacklist.BANLIST, ItemArgument.getItem(context, "item").getItem());
        context.getSource().getServer().getPlayerList().func_232641_a_(new StringTextComponent("Item banned: ").appendString(ItemArgument.getItem(context, "item").getItem().getRegistryName().toString()), ChatType.CHAT, Util.DUMMY_UUID);
        return 0;
    }
}
