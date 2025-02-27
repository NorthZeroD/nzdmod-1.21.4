package com.northzero.nzdmod.block;

import com.northzero.nzdmod.NzdMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final Block CONDENSED_DIRT = register(
            "condensed_dirt",
            Block::new,
            AbstractBlock.Settings.copy(Blocks.DIRT),
            true
    );

    public static final Block CONDENSED_OAK_LOG = register(
            "condensed_oak_log",
            PillarBlock::new,
            AbstractBlock.Settings.copy(Blocks.OAK_LOG).strength(3.0F),
            true
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // 为块创建注册表项
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // 创建块实例
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // 有时，您可能不想为块注册项目。
        // 例如：如果它是像 'minecraft：moving_piston' 或 'minecraft：end_gateway' 这样的技术块
        if (shouldRegisterItem) {
            // 项目需要使用不同类型的注册表项进行注册，但 ID 可以相同。
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(NzdMod.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NzdMod.MOD_ID, name));
    }

    public static void initialize() {
    }

}