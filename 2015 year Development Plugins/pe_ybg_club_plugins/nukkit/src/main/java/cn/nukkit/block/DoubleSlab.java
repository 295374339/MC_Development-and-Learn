package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.Tool;
import cn.nukkit.utils.BlockColor;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class DoubleSlab extends Solid {
    public static final int STONE = 0;
    public static final int SANDSTONE = 1;
    public static final int WOODEN = 2;
    public static final int COBBLESTONE = 3;
    public static final int BRICK = 4;
    public static final int STONE_BRICK = 5;
    public static final int QUARTZ = 6;
    public static final int NETHER_BRICK = 7;

    public DoubleSlab() {
        this(0);
    }

    public DoubleSlab(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DOUBLE_SLAB;
    }

    //todo hardness and residence

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public int getToolType() {
        return Tool.TYPE_PICKAXE;
    }

    @Override
    public String getName() {
        String[] names = new String[]{
                "Stone",
                "Sandstone",
                "Wooden",
                "Cobblestone",
                "Brick",
                "Stone Brick",
                "Quartz",
                "Nether Brick"
        };
        return "Double " + names[this.meta & 0x07] + " Slab";
    }

    @Override
    public int[][] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= Tool.TIER_WOODEN) {
            return new int[][]{new int[]{Item.SLAB, this.meta & 0x07, 2}};
        } else {
            return new int[0][];
        }
    }

    @Override
    public BlockColor getColor() {
        switch (this.meta & 0x07) {
            case DoubleSlab.STONE:
                return BlockColor.STONE_BLOCK_COLOR;
            case DoubleSlab.SANDSTONE:
                return BlockColor.SAND_BLOCK_COLOR;
            case DoubleSlab.WOODEN:
                return BlockColor.WOOD_BLOCK_COLOR;
            case DoubleSlab.COBBLESTONE:
                return BlockColor.STONE_BLOCK_COLOR;
            case DoubleSlab.BRICK:
                return BlockColor.STONE_BLOCK_COLOR;
            case DoubleSlab.STONE_BRICK:
                return BlockColor.STONE_BLOCK_COLOR;
            case DoubleSlab.QUARTZ:
                return BlockColor.QUARTZ_BLOCK_COLOR;
            case DoubleSlab.NETHER_BRICK:
                return BlockColor.NETHERRACK_BLOCK_COLOR;

            default:
                return BlockColor.STONE_BLOCK_COLOR;     //unreachable
        }
    }
}
