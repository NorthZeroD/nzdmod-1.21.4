package com.northzero.nzdmod;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import com.northzero.nzdmod.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class NzdModBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected NzdModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // 使凝结的泥土掉落其方块物品。
        // 如果适用，还添加了它在爆炸中幸存下来的条件，
        addDrop(ModBlocks.CONDENSED_DIRT);
        addDrop(ModBlocks.CONDENSED_OAK_LOG);

        // 让海晶灯只用丝绸之触掉下来
//        addDropWithSilkTouch(ModBlocks.PRISMARINE_LAMP);

        // 将浓缩的橡木原木放在 7 到 9 根橡木原木之间
//        addDrop(ModBlocks.CONDENSED_OAK_LOG, LootTable.builder().pool(addSurvivesExplosionCondition(Items.OAK_LOG, LootPool.builder()
//                .rolls(new UniformLootNumberProvider(new ConstantLootNumberProvider(7), new ConstantLootNumberProvider(9)))
//                .with(ItemEntry.builder(Items.OAK_LOG))))
//        );
    }
}