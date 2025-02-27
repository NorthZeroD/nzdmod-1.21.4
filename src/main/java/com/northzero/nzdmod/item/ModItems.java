package com.northzero.nzdmod.item;

import com.northzero.nzdmod.NzdMod;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final Item SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new, new Item.Settings());
    public static final Item LIGHTNING_STICK = register("lightning_stick", LightningStick::new, new Item.Settings());

    public static final ToolMaterial GUIDITE_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1561,
            64.0F,
            3.0F,
            10,
            ItemTags.DIAMOND_TOOL_MATERIALS);

    public static final Item GUIDITE_SWORD = register(
            "guidite_sword",
            settings -> new SwordItem(GUIDITE_TOOL_MATERIAL, 3.0F, -2.4F, settings),
            new Item.Settings()
    );

    public static final Item GUIDITE_PICKAXE = register(
            "guidite_pickaxe",
            settings -> new PickaxeItem(GUIDITE_TOOL_MATERIAL, 1.0F, -2.8F, settings),
            new Item.Settings()
    );

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // 创建 item 键。
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NzdMod.MOD_ID, name));

        // 创建 item 实例。
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // 注册项目。
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {

        // 将可疑物质添加到堆肥注册表中，有 30% 的几率提高堆肥桶的水平。
        CompostingChanceRegistry.INSTANCE.add(ModItems.SUSPICIOUS_SUBSTANCE, 0.3f);

        // 将可疑物质添加到燃料注册表中，燃烧时间为 30 秒。
        // 请记住，Minecraft 使用刻度处理基于逻辑的时间。
        // 20 个时钟周期 = 1 秒。
        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 30 * 20));
    }
}
