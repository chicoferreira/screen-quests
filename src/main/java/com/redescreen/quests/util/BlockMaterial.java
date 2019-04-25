package com.redescreen.quests.util;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class BlockMaterial {

    private Material material;
    private short data;

    public BlockMaterial(Material material) {
        this(material, (short) 0);
    }

    public BlockMaterial(Material material, short data) {
        this.material = material;
        this.data = data;
    }

    public BlockMaterial(Block block) {
        this(block.getType(), block.getData());
    }

    public static BlockMaterial fromString(String blockMaterial) {
        Material material;
        short data = 0;
        if (blockMaterial != null) {
            if (blockMaterial.contains(":")) {
                String[] split = blockMaterial.split(":");

                material = Material.getMaterial(split[0]);
                data = Short.parseShort(split[1]);
            } else {
                material = Material.getMaterial(blockMaterial);
            }
            if (material != null) {
                return new BlockMaterial(material, data);
            }
        }
        return null;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public short getData() {
        return data;
    }

    public void setData(short data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.getMaterial().name() + ":" + data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockMaterial that = (BlockMaterial) o;
        return data == that.data &&
                material == that.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, data);
    }

    public boolean matches(ItemStack itemStack) {
        return itemStack.getType() == this.getMaterial() && itemStack.getDurability() == this.data;
    }

    public ItemStack toItemStack() {
        return new ItemStack(this.getMaterial(), 1, this.getData());
    }
}
