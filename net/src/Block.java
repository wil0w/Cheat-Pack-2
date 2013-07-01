package net.minecraft.src;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.kodehawa.mods.Vars;

public class Block
{
    /**
     * used as foreach item, if item.tab = current tab, display it on the screen
     */
    private CreativeTabs displayOnCreativeTab;
    protected String field_111026_f;
    public static final StepSound soundPowderFootstep = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound soundWoodFootstep = new StepSound("wood", 1.0F, 1.0F);
    public static final StepSound soundGravelFootstep = new StepSound("gravel", 1.0F, 1.0F);
    public static final StepSound soundGrassFootstep = new StepSound("grass", 1.0F, 1.0F);
    public static final StepSound soundStoneFootstep = new StepSound("stone", 1.0F, 1.0F);
    public static final StepSound soundMetalFootstep = new StepSound("stone", 1.0F, 1.5F);
    public static final StepSound soundGlassFootstep = new StepSoundStone("stone", 1.0F, 1.0F);
    public static final StepSound soundClothFootstep = new StepSound("cloth", 1.0F, 1.0F);
    public static final StepSound soundSandFootstep = new StepSound("sand", 1.0F, 1.0F);
    public static final StepSound soundSnowFootstep = new StepSound("snow", 1.0F, 1.0F);
    public static final StepSound soundLadderFootstep = new StepSoundSand("ladder", 1.0F, 1.0F);
    public static final StepSound soundAnvilFootstep = new StepSoundAnvil("anvil", 0.3F, 1.0F);

    /** List of ly/ff (BlockType) containing the already registered blocks. */
    public static final Block[] blocksList = new Block[4096];

    /**
     * An array of 4096 booleans corresponding to the result of the isOpaqueCube() method for each block ID
     */
    public static final boolean[] opaqueCubeLookup = new boolean[4096];

    /** How much light is subtracted for going through this block */
    public static final int[] lightOpacity = new int[4096];

    /** Array of booleans that tells if a block can grass */
    public static final boolean[] canBlockGrass = new boolean[4096];

    /** Amount of light emitted */
    public static final int[] lightValue = new int[4096];

    /**
     * Flag if block ID should use the brightest neighbor light value as its own
     */
    public static boolean[] useNeighborBrightness = new boolean[4096];
    public static final Block stone = (new BlockStone(1)).setHardness(1.5F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stone").func_111022_d("stone");
    public static final BlockGrass grass = (BlockGrass)(new BlockGrass(2)).setHardness(0.6F).setStepSound(soundGrassFootstep).setUnlocalizedName("grass").func_111022_d("grass");
    public static final Block dirt = (new BlockDirt(3)).setHardness(0.5F).setStepSound(soundGravelFootstep).setUnlocalizedName("dirt").func_111022_d("dirt");
    public static final Block cobblestone = (new Block(4, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stonebrick").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("cobblestone");
    public static final Block planks = (new BlockWood(5)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("wood").func_111022_d("planks");
    public static final Block sapling = (new BlockSapling(6)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("sapling").func_111022_d("sapling");
    public static final Block bedrock = (new Block(7, Material.rock)).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("bedrock").disableStats().setCreativeTab(CreativeTabs.tabBlock).func_111022_d("bedrock");
    public static final BlockFluid waterMoving = (BlockFluid)(new BlockFlowing(8, Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats().func_111022_d("water_flow");
    public static final Block waterStill = (new BlockStationary(9, Material.water)).setHardness(100.0F).setLightOpacity(3).setUnlocalizedName("water").disableStats().func_111022_d("water_still");
    public static final BlockFluid lavaMoving = (BlockFluid)(new BlockFlowing(10, Material.lava)).setHardness(0.0F).setLightValue(1.0F).setUnlocalizedName("lava").disableStats().func_111022_d("lava_flow");

    /** Stationary lava source block */
    public static final Block lavaStill = (new BlockStationary(11, Material.lava)).setHardness(100.0F).setLightValue(1.0F).setUnlocalizedName("lava").disableStats().func_111022_d("lava_still");
    public static final Block sand = (new BlockSand(12)).setHardness(0.5F).setStepSound(soundSandFootstep).setUnlocalizedName("sand").func_111022_d("sand");
    public static final Block gravel = (new BlockGravel(13)).setHardness(0.6F).setStepSound(soundGravelFootstep).setUnlocalizedName("gravel").func_111022_d("gravel");
    public static final Block oreGold = (new BlockOre(14)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreGold").func_111022_d("gold_ore");
    public static final Block oreIron = (new BlockOre(15)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreIron").func_111022_d("iron_ore");
    public static final Block oreCoal = (new BlockOre(16)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreCoal").func_111022_d("coal_ore");
    public static final Block wood = (new BlockLog(17)).setHardness(2.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("log").func_111022_d("log");
    public static final BlockLeaves leaves = (BlockLeaves)(new BlockLeaves(18)).setHardness(0.2F).setLightOpacity(1).setStepSound(soundGrassFootstep).setUnlocalizedName("leaves").func_111022_d("leaves");
    public static final Block sponge = (new BlockSponge(19)).setHardness(0.6F).setStepSound(soundGrassFootstep).setUnlocalizedName("sponge").func_111022_d("sponge");
    public static final Block glass = (new BlockGlass(20, Material.glass, false)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("glass").func_111022_d("glass");
    public static final Block oreLapis = (new BlockOre(21)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreLapis").func_111022_d("lapis_ore");
    public static final Block blockLapis = (new Block(22, Material.rock)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("blockLapis").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("lapis_block");
    public static final Block dispenser = (new BlockDispenser(23)).setHardness(3.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("dispenser").func_111022_d("dispenser");
    public static final Block sandStone = (new BlockSandStone(24)).setStepSound(soundStoneFootstep).setHardness(0.8F).setUnlocalizedName("sandStone").func_111022_d("sandstone");
    public static final Block music = (new BlockNote(25)).setHardness(0.8F).setUnlocalizedName("musicBlock").func_111022_d("noteblock");
    public static final Block bed = (new BlockBed(26)).setHardness(0.2F).setUnlocalizedName("bed").disableStats().func_111022_d("bed");
    public static final Block railPowered = (new BlockRailPowered(27)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("goldenRail").func_111022_d("rail_golden");
    public static final Block railDetector = (new BlockDetectorRail(28)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("detectorRail").func_111022_d("rail_detector");
    public static final BlockPistonBase pistonStickyBase = (BlockPistonBase)(new BlockPistonBase(29, true)).setUnlocalizedName("pistonStickyBase");
    public static final Block web = (new BlockWeb(30)).setLightOpacity(1).setHardness(4.0F).setUnlocalizedName("web").func_111022_d("web");
    public static final BlockTallGrass tallGrass = (BlockTallGrass)(new BlockTallGrass(31)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("tallgrass");
    public static final BlockDeadBush deadBush = (BlockDeadBush)(new BlockDeadBush(32)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("deadbush").func_111022_d("deadbush");
    public static final BlockPistonBase pistonBase = (BlockPistonBase)(new BlockPistonBase(33, false)).setUnlocalizedName("pistonBase");
    public static final BlockPistonExtension pistonExtension = new BlockPistonExtension(34);
    public static final Block cloth = (new BlockColored(35, Material.cloth)).setHardness(0.8F).setStepSound(soundClothFootstep).setUnlocalizedName("cloth").func_111022_d("wool_colored");
    public static final BlockPistonMoving pistonMoving = new BlockPistonMoving(36);
    public static final BlockFlower plantYellow = (BlockFlower)(new BlockFlower(37)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("flower").func_111022_d("flower_dandelion");
    public static final BlockFlower plantRed = (BlockFlower)(new BlockFlower(38)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("rose").func_111022_d("flower_rose");
    public static final BlockFlower mushroomBrown = (BlockFlower)(new BlockMushroom(39)).setHardness(0.0F).setStepSound(soundGrassFootstep).setLightValue(0.125F).setUnlocalizedName("mushroom").func_111022_d("mushroom_brown");
    public static final BlockFlower mushroomRed = (BlockFlower)(new BlockMushroom(40)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("mushroom").func_111022_d("mushroom_red");
    public static final Block blockGold = (new BlockOreStorage(41)).setHardness(3.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockGold").func_111022_d("gold_block");
    public static final Block blockIron = (new BlockOreStorage(42)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockIron").func_111022_d("iron_block");

    /** stoneDoubleSlab */
    public static final BlockHalfSlab stoneDoubleSlab = (BlockHalfSlab)(new BlockStep(43, true)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stoneSlab");

    /** stoneSingleSlab */
    public static final BlockHalfSlab stoneSingleSlab = (BlockHalfSlab)(new BlockStep(44, false)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stoneSlab");
    public static final Block brick = (new Block(45, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("brick").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("brick");
    public static final Block tnt = (new BlockTNT(46)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("tnt").func_111022_d("tnt");
    public static final Block bookShelf = (new BlockBookshelf(47)).setHardness(1.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("bookshelf").func_111022_d("bookshelf");
    public static final Block cobblestoneMossy = (new Block(48, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stoneMoss").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("cobblestone_mossy");
    public static final Block obsidian = (new BlockObsidian(49)).setHardness(50.0F).setResistance(2000.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("obsidian").func_111022_d("obsidian");
    public static final Block torchWood = (new BlockTorch(50)).setHardness(0.0F).setLightValue(0.9375F).setStepSound(soundWoodFootstep).setUnlocalizedName("torch").func_111022_d("torch_on");
    public static final BlockFire fire = (BlockFire)(new BlockFire(51)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fire").disableStats().func_111022_d("fire");
    public static final Block mobSpawner = (new BlockMobSpawner(52)).setHardness(5.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("mobSpawner").disableStats().func_111022_d("mob_spawner");
    public static final Block stairsWoodOak = (new BlockStairs(53, planks, 0)).setUnlocalizedName("stairsWood");
    public static final BlockChest chest = (BlockChest)(new BlockChest(54, 0)).setHardness(2.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("chest");
    public static final BlockRedstoneWire redstoneWire = (BlockRedstoneWire)(new BlockRedstoneWire(55)).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("redstoneDust").disableStats().func_111022_d("redstone_dust");
    public static final Block oreDiamond = (new BlockOre(56)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreDiamond").func_111022_d("diamond_ore");
    public static final Block blockDiamond = (new BlockOreStorage(57)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockDiamond").func_111022_d("diamond_block");
    public static final Block workbench = (new BlockWorkbench(58)).setHardness(2.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("workbench").func_111022_d("crafting_table");
    public static final Block crops = (new BlockCrops(59)).setUnlocalizedName("crops").func_111022_d("wheat");
    public static final Block tilledField = (new BlockFarmland(60)).setHardness(0.6F).setStepSound(soundGravelFootstep).setUnlocalizedName("farmland").func_111022_d("farmland");
    public static final Block furnaceIdle = (new BlockFurnace(61, false)).setHardness(3.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("furnace").setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block furnaceBurning = (new BlockFurnace(62, true)).setHardness(3.5F).setStepSound(soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("furnace");
    public static final Block signPost = (new BlockSign(63, TileEntitySign.class, true)).setHardness(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("sign").disableStats();
    public static final Block doorWood = (new BlockDoor(64, Material.wood)).setHardness(3.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("doorWood").disableStats().func_111022_d("door_wood");
    public static final Block ladder = (new BlockLadder(65)).setHardness(0.4F).setStepSound(soundLadderFootstep).setUnlocalizedName("ladder").func_111022_d("ladder");
    public static final Block rail = (new BlockRail(66)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("rail").func_111022_d("rail_normal");
    public static final Block stairsCobblestone = (new BlockStairs(67, cobblestone, 0)).setUnlocalizedName("stairsStone");
    public static final Block signWall = (new BlockSign(68, TileEntitySign.class, false)).setHardness(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("sign").disableStats();
    public static final Block lever = (new BlockLever(69)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("lever").func_111022_d("lever");
    public static final Block pressurePlateStone = (new BlockPressurePlate(70, "stone", Material.rock, EnumMobType.mobs)).setHardness(0.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("pressurePlate");
    public static final Block doorIron = (new BlockDoor(71, Material.iron)).setHardness(5.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("doorIron").disableStats().func_111022_d("door_iron");
    public static final Block pressurePlatePlanks = (new BlockPressurePlate(72, "planks_oak", Material.wood, EnumMobType.everything)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("pressurePlate");
    public static final Block oreRedstone = (new BlockRedstoneOre(73, false)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreRedstone").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("redstone_ore");
    public static final Block oreRedstoneGlowing = (new BlockRedstoneOre(74, true)).setLightValue(0.625F).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreRedstone").func_111022_d("redstone_ore");
    public static final Block torchRedstoneIdle = (new BlockRedstoneTorch(75, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("notGate").func_111022_d("redstone_torch_off");
    public static final Block torchRedstoneActive = (new BlockRedstoneTorch(76, true)).setHardness(0.0F).setLightValue(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("notGate").setCreativeTab(CreativeTabs.tabRedstone).func_111022_d("redstone_torch_on");
    public static final Block stoneButton = (new BlockButtonStone(77)).setHardness(0.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("button");
    public static final Block snow = (new BlockSnow(78)).setHardness(0.1F).setStepSound(soundSnowFootstep).setUnlocalizedName("snow").setLightOpacity(0).func_111022_d("snow");
    public static final Block ice = (new BlockIce(79)).setHardness(0.5F).setLightOpacity(3).setStepSound(soundGlassFootstep).setUnlocalizedName("ice").func_111022_d("ice");
    public static final Block blockSnow = (new BlockSnowBlock(80)).setHardness(0.2F).setStepSound(soundSnowFootstep).setUnlocalizedName("snow").func_111022_d("snow");
    public static final Block cactus = (new BlockCactus(81)).setHardness(0.4F).setStepSound(soundClothFootstep).setUnlocalizedName("cactus").func_111022_d("cactus");
    public static final Block blockClay = (new BlockClay(82)).setHardness(0.6F).setStepSound(soundGravelFootstep).setUnlocalizedName("clay").func_111022_d("clay");
    public static final Block reed = (new BlockReed(83)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("reeds").disableStats().func_111022_d("reeds");
    public static final Block jukebox = (new BlockJukeBox(84)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("jukebox").func_111022_d("jukebox");
    public static final Block fence = (new BlockFence(85, "planks_oak", Material.wood)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fence");
    public static final Block pumpkin = (new BlockPumpkin(86, false)).setHardness(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("pumpkin").func_111022_d("pumpkin");
    public static final Block netherrack = (new BlockNetherrack(87)).setHardness(0.4F).setStepSound(soundStoneFootstep).setUnlocalizedName("hellrock").func_111022_d("netherrack");
    public static final Block slowSand = (new BlockSoulSand(88)).setHardness(0.5F).setStepSound(soundSandFootstep).setUnlocalizedName("hellsand").func_111022_d("soul_sand");
    public static final Block glowStone = (new BlockGlowStone(89, Material.glass)).setHardness(0.3F).setStepSound(soundGlassFootstep).setLightValue(1.0F).setUnlocalizedName("lightgem").func_111022_d("glowstone");

    /** The purple teleport blocks inside the obsidian circle */
    public static final BlockPortal portal = (BlockPortal)(new BlockPortal(90)).setHardness(-1.0F).setStepSound(soundGlassFootstep).setLightValue(0.75F).setUnlocalizedName("portal").func_111022_d("portal");
    public static final Block pumpkinLantern = (new BlockPumpkin(91, true)).setHardness(1.0F).setStepSound(soundWoodFootstep).setLightValue(1.0F).setUnlocalizedName("litpumpkin").func_111022_d("pumpkin");
    public static final Block cake = (new BlockCake(92)).setHardness(0.5F).setStepSound(soundClothFootstep).setUnlocalizedName("cake").disableStats().func_111022_d("cake");
    public static final BlockRedstoneRepeater redstoneRepeaterIdle = (BlockRedstoneRepeater)(new BlockRedstoneRepeater(93, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("diode").disableStats().func_111022_d("repeater_off");
    public static final BlockRedstoneRepeater redstoneRepeaterActive = (BlockRedstoneRepeater)(new BlockRedstoneRepeater(94, true)).setHardness(0.0F).setLightValue(0.625F).setStepSound(soundWoodFootstep).setUnlocalizedName("diode").disableStats().func_111022_d("repeater_on");

    /**
     * April fools secret locked chest, only spawns on new chunks on 1st April.
     */
    public static final Block lockedChest = (new BlockLockedChest(95)).setHardness(0.0F).setLightValue(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("lockedchest").setTickRandomly(true);
    public static final Block trapdoor = (new BlockTrapDoor(96, Material.wood)).setHardness(3.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("trapdoor").disableStats().func_111022_d("trapdoor");
    public static final Block silverfish = (new BlockSilverfish(97)).setHardness(0.75F).setUnlocalizedName("monsterStoneEgg");
    public static final Block stoneBrick = (new BlockStoneBrick(98)).setHardness(1.5F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("stonebricksmooth").func_111022_d("stonebrick");
    public static final Block mushroomCapBrown = (new BlockMushroomCap(99, Material.wood, 0)).setHardness(0.2F).setStepSound(soundWoodFootstep).setUnlocalizedName("mushroom").func_111022_d("mushroom_block");
    public static final Block mushroomCapRed = (new BlockMushroomCap(100, Material.wood, 1)).setHardness(0.2F).setStepSound(soundWoodFootstep).setUnlocalizedName("mushroom").func_111022_d("mushroom_block");
    public static final Block fenceIron = (new BlockPane(101, "iron_bars", "iron_bars", Material.iron, true)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("fenceIron");
    public static final Block thinGlass = (new BlockPane(102, "glass", "glass_pane_top", Material.glass, false)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("thinGlass");
    public static final Block melon = (new BlockMelon(103)).setHardness(1.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("melon").func_111022_d("melon");
    public static final Block pumpkinStem = (new BlockStem(104, pumpkin)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("pumpkinStem").func_111022_d("pumpkin_stem");
    public static final Block melonStem = (new BlockStem(105, melon)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("pumpkinStem").func_111022_d("melon_stem");
    public static final Block vine = (new BlockVine(106)).setHardness(0.2F).setStepSound(soundGrassFootstep).setUnlocalizedName("vine").func_111022_d("vine");
    public static final Block fenceGate = (new BlockFenceGate(107)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("fenceGate");
    public static final Block stairsBrick = (new BlockStairs(108, brick, 0)).setUnlocalizedName("stairsBrick");
    public static final Block stairsStoneBrick = (new BlockStairs(109, stoneBrick, 0)).setUnlocalizedName("stairsStoneBrickSmooth");
    public static final BlockMycelium mycelium = (BlockMycelium)(new BlockMycelium(110)).setHardness(0.6F).setStepSound(soundGrassFootstep).setUnlocalizedName("mycel").func_111022_d("mycelium");
    public static final Block waterlily = (new BlockLilyPad(111)).setHardness(0.0F).setStepSound(soundGrassFootstep).setUnlocalizedName("waterlily").func_111022_d("waterlily");
    public static final Block netherBrick = (new Block(112, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("netherBrick").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("nether_brick");
    public static final Block netherFence = (new BlockFence(113, "nether_brick", Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("netherFence");
    public static final Block stairsNetherBrick = (new BlockStairs(114, netherBrick, 0)).setUnlocalizedName("stairsNetherBrick");
    public static final Block netherStalk = (new BlockNetherStalk(115)).setUnlocalizedName("netherStalk").func_111022_d("nether_wart");
    public static final Block enchantmentTable = (new BlockEnchantmentTable(116)).setHardness(5.0F).setResistance(2000.0F).setUnlocalizedName("enchantmentTable").func_111022_d("enchanting_table");
    public static final Block brewingStand = (new BlockBrewingStand(117)).setHardness(0.5F).setLightValue(0.125F).setUnlocalizedName("brewingStand").func_111022_d("brewing_stand");
    public static final BlockCauldron cauldron = (BlockCauldron)(new BlockCauldron(118)).setHardness(2.0F).setUnlocalizedName("cauldron").func_111022_d("cauldron");
    public static final Block endPortal = (new BlockEndPortal(119, Material.portal)).setHardness(-1.0F).setResistance(6000000.0F);
    public static final Block endPortalFrame = (new BlockEndPortalFrame(120)).setStepSound(soundGlassFootstep).setLightValue(0.125F).setHardness(-1.0F).setUnlocalizedName("endPortalFrame").setResistance(6000000.0F).setCreativeTab(CreativeTabs.tabDecorations).func_111022_d("endframe");

    /** The rock found in The End. */
    public static final Block whiteStone = (new Block(121, Material.rock)).setHardness(3.0F).setResistance(15.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("whiteStone").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("end_stone");
    public static final Block dragonEgg = (new BlockDragonEgg(122)).setHardness(3.0F).setResistance(15.0F).setStepSound(soundStoneFootstep).setLightValue(0.125F).setUnlocalizedName("dragonEgg").func_111022_d("dragon_egg");
    public static final Block redstoneLampIdle = (new BlockRedstoneLight(123, false)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("redstoneLight").setCreativeTab(CreativeTabs.tabRedstone).func_111022_d("redstone_lamp_off");
    public static final Block redstoneLampActive = (new BlockRedstoneLight(124, true)).setHardness(0.3F).setStepSound(soundGlassFootstep).setUnlocalizedName("redstoneLight").func_111022_d("redstone_lamp_on");
    public static final BlockHalfSlab woodDoubleSlab = (BlockHalfSlab)(new BlockWoodSlab(125, true)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("woodSlab");
    public static final BlockHalfSlab woodSingleSlab = (BlockHalfSlab)(new BlockWoodSlab(126, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("woodSlab");
    public static final Block cocoaPlant = (new BlockCocoa(127)).setHardness(0.2F).setResistance(5.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("cocoa").func_111022_d("cocoa");
    public static final Block stairsSandStone = (new BlockStairs(128, sandStone, 0)).setUnlocalizedName("stairsSandStone");
    public static final Block oreEmerald = (new BlockOre(129)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("oreEmerald").func_111022_d("emerald_ore");
    public static final Block enderChest = (new BlockEnderChest(130)).setHardness(22.5F).setResistance(1000.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("enderChest").setLightValue(0.5F);
    public static final BlockTripWireSource tripWireSource = (BlockTripWireSource)(new BlockTripWireSource(131)).setUnlocalizedName("tripWireSource").func_111022_d("trip_wire_source");
    public static final Block tripWire = (new BlockTripWire(132)).setUnlocalizedName("tripWire").func_111022_d("trip_wire");
    public static final Block blockEmerald = (new BlockOreStorage(133)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockEmerald").func_111022_d("emerald_block");
    public static final Block stairsWoodSpruce = (new BlockStairs(134, planks, 1)).setUnlocalizedName("stairsWoodSpruce");
    public static final Block stairsWoodBirch = (new BlockStairs(135, planks, 2)).setUnlocalizedName("stairsWoodBirch");
    public static final Block stairsWoodJungle = (new BlockStairs(136, planks, 3)).setUnlocalizedName("stairsWoodJungle");
    public static final Block commandBlock = (new BlockCommandBlock(137)).setBlockUnbreakable().setResistance(6000000.0F).setUnlocalizedName("commandBlock").func_111022_d("command_block");
    public static final BlockBeacon beacon = (BlockBeacon)(new BlockBeacon(138)).setUnlocalizedName("beacon").setLightValue(1.0F).func_111022_d("beacon");
    public static final Block cobblestoneWall = (new BlockWall(139, cobblestone)).setUnlocalizedName("cobbleWall");
    public static final Block flowerPot = (new BlockFlowerPot(140)).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("flowerPot").func_111022_d("flower_pot");
    public static final Block carrot = (new BlockCarrot(141)).setUnlocalizedName("carrots").func_111022_d("carrots");
    public static final Block potato = (new BlockPotato(142)).setUnlocalizedName("potatoes").func_111022_d("potatoes");
    public static final Block woodenButton = (new BlockButtonWood(143)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("button");
    public static final Block skull = (new BlockSkull(144)).setHardness(1.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("skull").func_111022_d("skull");
    public static final Block anvil = (new BlockAnvil(145)).setHardness(5.0F).setStepSound(soundAnvilFootstep).setResistance(2000.0F).setUnlocalizedName("anvil");
    public static final Block chestTrapped = (new BlockChest(146, 1)).setHardness(2.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("chestTrap");
    public static final Block pressurePlateGold = (new BlockPressurePlateWeighted(147, "gold_block", Material.iron, 64)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("weightedPlate_light");
    public static final Block pressurePlateIron = (new BlockPressurePlateWeighted(148, "iron_block", Material.iron, 640)).setHardness(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("weightedPlate_heavy");
    public static final BlockComparator redstoneComparatorIdle = (BlockComparator)(new BlockComparator(149, false)).setHardness(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("comparator").disableStats().func_111022_d("comparator_off");
    public static final BlockComparator redstoneComparatorActive = (BlockComparator)(new BlockComparator(150, true)).setHardness(0.0F).setLightValue(0.625F).setStepSound(soundWoodFootstep).setUnlocalizedName("comparator").disableStats().func_111022_d("comparator_on");
    public static final BlockDaylightDetector daylightSensor = (BlockDaylightDetector)(new BlockDaylightDetector(151)).setHardness(0.2F).setStepSound(soundWoodFootstep).setUnlocalizedName("daylightDetector").func_111022_d("daylight_detector");
    public static final Block blockRedstone = (new BlockPoweredOre(152)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setUnlocalizedName("blockRedstone").func_111022_d("redstone_block");
    public static final Block oreNetherQuartz = (new BlockOre(153)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("netherquartz").func_111022_d("quartz_ore");
    public static final BlockHopper hopperBlock = (BlockHopper)(new BlockHopper(154)).setHardness(3.0F).setResistance(8.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("hopper").func_111022_d("hopper");
    public static final Block blockNetherQuartz = (new BlockQuartz(155)).setStepSound(soundStoneFootstep).setHardness(0.8F).setUnlocalizedName("quartzBlock").func_111022_d("quartz_block");
    public static final Block stairsNetherQuartz = (new BlockStairs(156, blockNetherQuartz, 0)).setUnlocalizedName("stairsQuartz");
    public static final Block railActivator = (new BlockRailPowered(157)).setHardness(0.7F).setStepSound(soundMetalFootstep).setUnlocalizedName("activatorRail").func_111022_d("rail_activator");
    public static final Block dropper = (new BlockDropper(158)).setHardness(3.5F).setStepSound(soundStoneFootstep).setUnlocalizedName("dropper").func_111022_d("dropper");
    public static final Block field_111039_cA = (new BlockColored(159, Material.rock)).setHardness(1.25F).setResistance(7.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("clayHardenedStained").func_111022_d("hardened_clay_stained");
    public static final Block field_111038_cB = (new BlockHay(170)).setHardness(0.5F).setStepSound(soundGrassFootstep).setUnlocalizedName("hayBlock").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("hay_block");
    public static final Block field_111031_cC = (new BlockCarpet(171)).setHardness(0.1F).setStepSound(soundClothFootstep).setUnlocalizedName("woolCarpet").setLightOpacity(0);
    public static final Block field_111032_cD = (new Block(172, Material.rock)).setHardness(1.25F).setResistance(7.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("clayHardened").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("hardened_clay");
    public static final Block field_111034_cE = (new Block(173, Material.rock)).setHardness(5.0F).setResistance(10.0F).setStepSound(soundStoneFootstep).setUnlocalizedName("blockCoal").setCreativeTab(CreativeTabs.tabBlock).func_111022_d("coal_block");

    /** ID of the block. */
    public final int blockID;

    /** Indicates how many hits it takes to break a block. */
    protected float blockHardness;

    /** Indicates the blocks resistance to explosions. */
    protected float blockResistance;

    /**
     * set to true when Block's constructor is called through the chain of super()'s. Note: Never used
     */
    protected boolean blockConstructorCalled = true;

    /**
     * If this field is true, the block is counted for statistics (mined or placed)
     */
    protected boolean enableStats = true;

    /**
     * Flags whether or not this block is of a type that needs random ticking. Ref-counted by ExtendedBlockStorage in
     * order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    protected boolean needsRandomTick;

    /** true if the Block contains a Tile Entity */
    protected boolean isBlockContainer;

    /** minimum X for the block bounds (local coordinates) */
    protected double minX;

    /** minimum Y for the block bounds (local coordinates) */
    protected double minY;

    /** minimum Z for the block bounds (local coordinates) */
    protected double minZ;

    /** maximum X for the block bounds (local coordinates) */
    protected double maxX;

    /** maximum Y for the block bounds (local coordinates) */
    protected double maxY;

    /** maximum Z for the block bounds (local coordinates) */
    protected double maxZ;

    /** Sound of stepping on the block */
    public StepSound stepSound;
    public float blockParticleGravity;

    /** Block material definition. */
    public final Material blockMaterial;

    /**
     * Determines how much velocity is maintained while moving on top of this block
     */
    public float slipperiness;

    /** The unlocalized name of this block. */
    private String unlocalizedName;
    protected Icon blockIcon;

    protected Block(int p_i2273_1_, Material p_i2273_2_)
    {
        this.stepSound = soundPowderFootstep;
        this.blockParticleGravity = 1.0F;
        this.slipperiness = 0.6F;

        if (blocksList[p_i2273_1_] != null)
        {
            throw new IllegalArgumentException("Slot " + p_i2273_1_ + " is already occupied by " + blocksList[p_i2273_1_] + " when adding " + this);
        }
        else
        {
            this.blockMaterial = p_i2273_2_;
            blocksList[p_i2273_1_] = this;
            this.blockID = p_i2273_1_;
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            opaqueCubeLookup[p_i2273_1_] = this.isOpaqueCube();
            lightOpacity[p_i2273_1_] = this.isOpaqueCube() ? 255 : 0;
            canBlockGrass[p_i2273_1_] = !p_i2273_2_.getCanBlockGrass();
        }
    }

    /**
     * This method is called on a block after all other blocks gets already created. You can use it to reference and
     * configure something on the block that needs the others ones.
     */
    protected void initializeBlock() {}

    /**
     * Sets the footstep sound for the block. Returns the object for convenience in constructing.
     */
    protected Block setStepSound(StepSound p_71884_1_)
    {
        this.stepSound = p_71884_1_;
        return this;
    }

    /**
     * Sets how much light is blocked going through this block. Returns the object for convenience in constructing.
     */
    protected Block setLightOpacity(int p_71868_1_)
    {
        lightOpacity[this.blockID] = p_71868_1_;
        return this;
    }

    /**
     * Sets the amount of light emitted by a block from 0.0f to 1.0f (converts internally to 0-15). Returns the object
     * for convenience in constructing.
     */
    protected Block setLightValue(float p_71900_1_)
    {
        lightValue[this.blockID] = (int)(15.0F * p_71900_1_);
        return this;
    }

    /**
     * Sets the the blocks resistance to explosions. Returns the object for convenience in constructing.
     */
    protected Block setResistance(float p_71894_1_)
    {
        this.blockResistance = p_71894_1_ * 3.0F;
        return this;
    }

    public static boolean isNormalCube(int p_71932_0_)
    {
        Block var1 = blocksList[p_71932_0_];
        return var1 == null ? false : var1.blockMaterial.isOpaque() && var1.renderAsNormalBlock() && !var1.canProvidePower();
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    public boolean getBlocksMovement(IBlockAccess p_71918_1_, int p_71918_2_, int p_71918_3_, int p_71918_4_)
    {
        return !this.blockMaterial.blocksMovement();
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 0;
    }

    /**
     * Sets how many hits it takes to break a block.
     */
    protected Block setHardness(float p_71848_1_)
    {
        this.blockHardness = p_71848_1_;

        if (this.blockResistance < p_71848_1_ * 5.0F)
        {
            this.blockResistance = p_71848_1_ * 5.0F;
        }

        return this;
    }

    /**
     * This method will make the hardness of the block equals to -1, and the block is indestructible.
     */
    protected Block setBlockUnbreakable()
    {
        this.setHardness(-1.0F);
        return this;
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World p_71934_1_, int p_71934_2_, int p_71934_3_, int p_71934_4_)
    {
        return this.blockHardness;
    }

    /**
     * Sets whether this block type will receive random update ticks
     */
    protected Block setTickRandomly(boolean p_71907_1_)
    {
        this.needsRandomTick = p_71907_1_;
        return this;
    }

    /**
     * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
     * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
     */
    public boolean getTickRandomly()
    {
        return this.needsRandomTick;
    }

    public boolean hasTileEntity()
    {
        return this.isBlockContainer;
    }

    /**
     * Sets the bounds of the block.  minX, minY, minZ, maxX, maxY, maxZ
     */
    protected final void setBlockBounds(float p_71905_1_, float p_71905_2_, float p_71905_3_, float p_71905_4_, float p_71905_5_, float p_71905_6_)
    {
        this.minX = (double)p_71905_1_;
        this.minY = (double)p_71905_2_;
        this.minZ = (double)p_71905_3_;
        this.maxX = (double)p_71905_4_;
        this.maxY = (double)p_71905_5_;
        this.maxZ = (double)p_71905_6_;
    }

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getBrightness(par2, par3, par4, lightValue[par1IBlockAccess.getBlockId(par2, par3, par4)]);
    }

    /**
     * Goes straight to getLightBrightnessForSkyBlocks for Blocks, does some fancy computing for Fluids
     */
    public int getMixedBrightnessForBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getLightBrightnessForSkyBlocks(par2, par3, par4, lightValue[par1IBlockAccess.getBlockId(par2, par3, par4)]);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if ( !Vars.xray ) {
			return ( par5 == 0 ) && ( this.minY > 0.0D ) ? true : ( ( par5 == 1 ) && ( this.maxY < 1.0D ) ? true : ( ( par5 == 2 ) && ( this.minZ > 0.0D ) ? true : ( ( par5 == 3 ) && ( this.maxZ < 1.0D ) ? true : ( ( par5 == 4 ) && ( this.minX > 0.0D ) ? true : ( ( par5 == 5 ) && ( this.maxX < 1.0D ) ? true : !par1IBlockAccess.isBlockOpaqueCube( par2, par3, par4 ) ) ) ) ) );
		} else {
			return Arrays.asList( Vars.xrayBlocks ).contains( blockID );
		}
	}

    /**
     * Returns Returns true if the given side of this block type should be rendered (if it's solid or not), if the
     * adjacent block is at the given coordinates. Args: blockAccess, x, y, z, side
     */
    public boolean isBlockSolid(IBlockAccess p_71924_1_, int p_71924_2_, int p_71924_3_, int p_71924_4_, int p_71924_5_)
    {
        return p_71924_1_.getBlockMaterial(p_71924_2_, p_71924_3_, p_71924_4_).isSolid();
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.getIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.blockIcon;
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public final Icon getBlockTextureFromSide(int par1)
    {
        return this.getIcon(par1, 0);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)par2 + this.minX, (double)par3 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)par3 + this.maxY, (double)par4 + this.maxZ);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World p_71871_1_, int p_71871_2_, int p_71871_3_, int p_71871_4_, AxisAlignedBB p_71871_5_, List p_71871_6_, Entity p_71871_7_)
    {
        AxisAlignedBB var8 = this.getCollisionBoundingBoxFromPool(p_71871_1_, p_71871_2_, p_71871_3_, p_71871_4_);

        if (var8 != null && p_71871_5_.intersectsWith(var8))
        {
            p_71871_6_.add(var8);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_71872_1_, int p_71872_2_, int p_71872_3_, int p_71872_4_)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)p_71872_2_ + this.minX, (double)p_71872_3_ + this.minY, (double)p_71872_4_ + this.minZ, (double)p_71872_2_ + this.maxX, (double)p_71872_3_ + this.maxY, (double)p_71872_4_ + this.maxZ);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return true;
    }

    /**
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int p_71913_1_, boolean p_71913_2_)
    {
        return this.isCollidable();
    }

    /**
     * Returns if this block is collidable (only used by Fire). Args: x, y, z
     */
    public boolean isCollidable()
    {
        return true;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_71847_1_, int p_71847_2_, int p_71847_3_, int p_71847_4_, Random p_71847_5_) {}

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {}

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World p_71898_1_, int p_71898_2_, int p_71898_3_, int p_71898_4_, int p_71898_5_) {}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World p_71863_1_, int p_71863_2_, int p_71863_3_, int p_71863_4_, int p_71863_5_) {}

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World p_71859_1_)
    {
        return 10;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World p_71861_1_, int p_71861_2_, int p_71861_3_, int p_71861_4_) {}

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World p_71852_1_, int p_71852_2_, int p_71852_3_, int p_71852_4_, int p_71852_5_, int p_71852_6_) {}

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_71925_1_)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int p_71885_1_, Random p_71885_2_, int p_71885_3_)
    {
        return this.blockID;
    }

    /**
     * Gets the hardness of block at the given coordinates in the given world, relative to the ability of the given
     * EntityPlayer.
     */
    public float getPlayerRelativeBlockHardness(EntityPlayer p_71908_1_, World p_71908_2_, int p_71908_3_, int p_71908_4_, int p_71908_5_)
    {
        float var6 = this.getBlockHardness(p_71908_2_, p_71908_3_, p_71908_4_, p_71908_5_);
        return var6 < 0.0F ? 0.0F : (!p_71908_1_.canHarvestBlock(this) ? p_71908_1_.getCurrentPlayerStrVsBlock(this, false) / var6 / 100.0F : p_71908_1_.getCurrentPlayerStrVsBlock(this, true) / var6 / 30.0F);
    }

    /**
     * Drops the specified block items
     */
    public final void dropBlockAsItem(World p_71897_1_, int p_71897_2_, int p_71897_3_, int p_71897_4_, int p_71897_5_, int p_71897_6_)
    {
        this.dropBlockAsItemWithChance(p_71897_1_, p_71897_2_, p_71897_3_, p_71897_4_, p_71897_5_, 1.0F, p_71897_6_);
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World p_71914_1_, int p_71914_2_, int p_71914_3_, int p_71914_4_, int p_71914_5_, float p_71914_6_, int p_71914_7_)
    {
        if (!p_71914_1_.isRemote)
        {
            int var8 = this.quantityDroppedWithBonus(p_71914_7_, p_71914_1_.rand);

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (p_71914_1_.rand.nextFloat() <= p_71914_6_)
                {
                    int var10 = this.idDropped(p_71914_5_, p_71914_1_.rand, p_71914_7_);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(p_71914_1_, p_71914_2_, p_71914_3_, p_71914_4_, new ItemStack(var10, 1, this.damageDropped(p_71914_5_)));
                    }
                }
            }
        }
    }

    /**
     * Spawns EntityItem in the world for the given ItemStack if the world is not remote.
     */
    protected void dropBlockAsItem_do(World p_71929_1_, int p_71929_2_, int p_71929_3_, int p_71929_4_, ItemStack p_71929_5_)
    {
        if (!p_71929_1_.isRemote && p_71929_1_.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            float var6 = 0.7F;
            double var7 = (double)(p_71929_1_.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            double var9 = (double)(p_71929_1_.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            double var11 = (double)(p_71929_1_.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            EntityItem var13 = new EntityItem(p_71929_1_, (double)p_71929_2_ + var7, (double)p_71929_3_ + var9, (double)p_71929_4_ + var11, p_71929_5_);
            var13.delayBeforeCanPickup = 10;
            p_71929_1_.spawnEntityInWorld(var13);
        }
    }

    /**
     * called by spawner, ore, redstoneOre blocks
     */
    protected void dropXpOnBlockBreak(World p_71923_1_, int p_71923_2_, int p_71923_3_, int p_71923_4_, int p_71923_5_)
    {
        if (!p_71923_1_.isRemote)
        {
            while (p_71923_5_ > 0)
            {
                int var6 = EntityXPOrb.getXPSplit(p_71923_5_);
                p_71923_5_ -= var6;
                p_71923_1_.spawnEntityInWorld(new EntityXPOrb(p_71923_1_, (double)p_71923_2_ + 0.5D, (double)p_71923_3_ + 0.5D, (double)p_71923_4_ + 0.5D, var6));
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_71899_1_)
    {
        return 0;
    }

    /**
     * Returns how much this block can resist explosions from the passed in entity.
     */
    public float getExplosionResistance(Entity p_71904_1_)
    {
        return this.blockResistance / 5.0F;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World p_71878_1_, int p_71878_2_, int p_71878_3_, int p_71878_4_, Vec3 p_71878_5_, Vec3 p_71878_6_)
    {
        this.setBlockBoundsBasedOnState(p_71878_1_, p_71878_2_, p_71878_3_, p_71878_4_);
        p_71878_5_ = p_71878_5_.addVector((double)(-p_71878_2_), (double)(-p_71878_3_), (double)(-p_71878_4_));
        p_71878_6_ = p_71878_6_.addVector((double)(-p_71878_2_), (double)(-p_71878_3_), (double)(-p_71878_4_));
        Vec3 var7 = p_71878_5_.getIntermediateWithXValue(p_71878_6_, this.minX);
        Vec3 var8 = p_71878_5_.getIntermediateWithXValue(p_71878_6_, this.maxX);
        Vec3 var9 = p_71878_5_.getIntermediateWithYValue(p_71878_6_, this.minY);
        Vec3 var10 = p_71878_5_.getIntermediateWithYValue(p_71878_6_, this.maxY);
        Vec3 var11 = p_71878_5_.getIntermediateWithZValue(p_71878_6_, this.minZ);
        Vec3 var12 = p_71878_5_.getIntermediateWithZValue(p_71878_6_, this.maxZ);

        if (!this.isVecInsideYZBounds(var7))
        {
            var7 = null;
        }

        if (!this.isVecInsideYZBounds(var8))
        {
            var8 = null;
        }

        if (!this.isVecInsideXZBounds(var9))
        {
            var9 = null;
        }

        if (!this.isVecInsideXZBounds(var10))
        {
            var10 = null;
        }

        if (!this.isVecInsideXYBounds(var11))
        {
            var11 = null;
        }

        if (!this.isVecInsideXYBounds(var12))
        {
            var12 = null;
        }

        Vec3 var13 = null;

        if (var7 != null && (var13 == null || p_71878_5_.squareDistanceTo(var7) < p_71878_5_.squareDistanceTo(var13)))
        {
            var13 = var7;
        }

        if (var8 != null && (var13 == null || p_71878_5_.squareDistanceTo(var8) < p_71878_5_.squareDistanceTo(var13)))
        {
            var13 = var8;
        }

        if (var9 != null && (var13 == null || p_71878_5_.squareDistanceTo(var9) < p_71878_5_.squareDistanceTo(var13)))
        {
            var13 = var9;
        }

        if (var10 != null && (var13 == null || p_71878_5_.squareDistanceTo(var10) < p_71878_5_.squareDistanceTo(var13)))
        {
            var13 = var10;
        }

        if (var11 != null && (var13 == null || p_71878_5_.squareDistanceTo(var11) < p_71878_5_.squareDistanceTo(var13)))
        {
            var13 = var11;
        }

        if (var12 != null && (var13 == null || p_71878_5_.squareDistanceTo(var12) < p_71878_5_.squareDistanceTo(var13)))
        {
            var13 = var12;
        }

        if (var13 == null)
        {
            return null;
        }
        else
        {
            byte var14 = -1;

            if (var13 == var7)
            {
                var14 = 4;
            }

            if (var13 == var8)
            {
                var14 = 5;
            }

            if (var13 == var9)
            {
                var14 = 0;
            }

            if (var13 == var10)
            {
                var14 = 1;
            }

            if (var13 == var11)
            {
                var14 = 2;
            }

            if (var13 == var12)
            {
                var14 = 3;
            }

            return new MovingObjectPosition(p_71878_2_, p_71878_3_, p_71878_4_, var14, var13.addVector((double)p_71878_2_, (double)p_71878_3_, (double)p_71878_4_));
        }
    }

    /**
     * Checks if a vector is within the Y and Z bounds of the block.
     */
    private boolean isVecInsideYZBounds(Vec3 p_71916_1_)
    {
        return p_71916_1_ == null ? false : p_71916_1_.yCoord >= this.minY && p_71916_1_.yCoord <= this.maxY && p_71916_1_.zCoord >= this.minZ && p_71916_1_.zCoord <= this.maxZ;
    }

    /**
     * Checks if a vector is within the X and Z bounds of the block.
     */
    private boolean isVecInsideXZBounds(Vec3 p_71936_1_)
    {
        return p_71936_1_ == null ? false : p_71936_1_.xCoord >= this.minX && p_71936_1_.xCoord <= this.maxX && p_71936_1_.zCoord >= this.minZ && p_71936_1_.zCoord <= this.maxZ;
    }

    /**
     * Checks if a vector is within the X and Y bounds of the block.
     */
    private boolean isVecInsideXYBounds(Vec3 p_71890_1_)
    {
        return p_71890_1_ == null ? false : p_71890_1_.xCoord >= this.minX && p_71890_1_.xCoord <= this.maxX && p_71890_1_.yCoord >= this.minY && p_71890_1_.yCoord <= this.maxY;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World p_71867_1_, int p_71867_2_, int p_71867_3_, int p_71867_4_, Explosion p_71867_5_) {}

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    public boolean canPlaceBlockOnSide(World p_94331_1_, int p_94331_2_, int p_94331_3_, int p_94331_4_, int p_94331_5_, ItemStack p_94331_6_)
    {
        return this.canPlaceBlockOnSide(p_94331_1_, p_94331_2_, p_94331_3_, p_94331_4_, p_94331_5_);
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World p_71850_1_, int p_71850_2_, int p_71850_3_, int p_71850_4_, int p_71850_5_)
    {
        return this.canPlaceBlockAt(p_71850_1_, p_71850_2_, p_71850_3_, p_71850_4_);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World p_71930_1_, int p_71930_2_, int p_71930_3_, int p_71930_4_)
    {
        int var5 = p_71930_1_.getBlockId(p_71930_2_, p_71930_3_, p_71930_4_);
        return var5 == 0 || blocksList[var5].blockMaterial.isReplaceable();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World p_71903_1_, int p_71903_2_, int p_71903_3_, int p_71903_4_, EntityPlayer p_71903_5_, int p_71903_6_, float p_71903_7_, float p_71903_8_, float p_71903_9_)
    {
        return false;
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World p_71891_1_, int p_71891_2_, int p_71891_3_, int p_71891_4_, Entity p_71891_5_) {}

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World p_85104_1_, int p_85104_2_, int p_85104_3_, int p_85104_4_, int p_85104_5_, float p_85104_6_, float p_85104_7_, float p_85104_8_, int p_85104_9_)
    {
        return p_85104_9_;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World p_71921_1_, int p_71921_2_, int p_71921_3_, int p_71921_4_, EntityPlayer p_71921_5_) {}

    /**
     * Can add to the passed in vector for a movement vector to be applied to the entity. Args: x, y, z, entity, vec3d
     */
    public void velocityToAddToEntity(World p_71901_1_, int p_71901_2_, int p_71901_3_, int p_71901_4_, Entity p_71901_5_, Vec3 p_71901_6_) {}

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess p_71902_1_, int p_71902_2_, int p_71902_3_, int p_71902_4_) {}

    /**
     * returns the block bounderies minX value
     */
    public final double getBlockBoundsMinX()
    {
        return this.minX;
    }

    /**
     * returns the block bounderies maxX value
     */
    public final double getBlockBoundsMaxX()
    {
        return this.maxX;
    }

    /**
     * returns the block bounderies minY value
     */
    public final double getBlockBoundsMinY()
    {
        return this.minY;
    }

    /**
     * returns the block bounderies maxY value
     */
    public final double getBlockBoundsMaxY()
    {
        return this.maxY;
    }

    /**
     * returns the block bounderies minZ value
     */
    public final double getBlockBoundsMinZ()
    {
        return this.minZ;
    }

    /**
     * returns the block bounderies maxZ value
     */
    public final double getBlockBoundsMaxZ()
    {
        return this.maxZ;
    }

    public int getBlockColor()
    {
        return 16777215;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return 16777215;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 16777215;
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess p_71865_1_, int p_71865_2_, int p_71865_3_, int p_71865_4_, int p_71865_5_)
    {
        return 0;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return false;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World p_71869_1_, int p_71869_2_, int p_71869_3_, int p_71869_4_, Entity p_71869_5_) {}

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess p_71855_1_, int p_71855_2_, int p_71855_3_, int p_71855_4_, int p_71855_5_)
    {
        return 0;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {}

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World p_71893_1_, EntityPlayer p_71893_2_, int p_71893_3_, int p_71893_4_, int p_71893_5_, int p_71893_6_)
    {
        p_71893_2_.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        p_71893_2_.addExhaustion(0.025F);

        if (this.canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier(p_71893_2_))
        {
            ItemStack var8 = this.createStackedBlock(p_71893_6_);

            if (var8 != null)
            {
                this.dropBlockAsItem_do(p_71893_1_, p_71893_3_, p_71893_4_, p_71893_5_, var8);
            }
        }
        else
        {
            int var7 = EnchantmentHelper.getFortuneModifier(p_71893_2_);
            this.dropBlockAsItem(p_71893_1_, p_71893_3_, p_71893_4_, p_71893_5_, p_71893_6_, var7);
        }
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return this.renderAsNormalBlock() && !this.isBlockContainer;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int p_71880_1_)
    {
        int var2 = 0;

        if (this.blockID >= 0 && this.blockID < Item.itemsList.length && Item.itemsList[this.blockID].getHasSubtypes())
        {
            var2 = p_71880_1_;
        }

        return new ItemStack(this.blockID, 1, var2);
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int p_71910_1_, Random p_71910_2_)
    {
        return this.quantityDropped(p_71910_2_);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World p_71854_1_, int p_71854_2_, int p_71854_3_, int p_71854_4_)
    {
        return true;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_71860_1_, int p_71860_2_, int p_71860_3_, int p_71860_4_, EntityLivingBase p_71860_5_, ItemStack p_71860_6_) {}

    /**
     * Called after a block is placed
     */
    public void onPostBlockPlaced(World p_85105_1_, int p_85105_2_, int p_85105_3_, int p_85105_4_, int p_85105_5_) {}

    public Block setUnlocalizedName(String p_71864_1_)
    {
        this.unlocalizedName = p_71864_1_;
        return this;
    }

    /**
     * Gets the localized name of this block. Used for the statistics page.
     */
    public String getLocalizedName()
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
    }

    /**
     * Returns the unlocalized name of this block.
     */
    public String getUnlocalizedName()
    {
        return "tile." + this.unlocalizedName;
    }

    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    public boolean onBlockEventReceived(World p_71883_1_, int p_71883_2_, int p_71883_3_, int p_71883_4_, int p_71883_5_, int p_71883_6_)
    {
        return false;
    }

    /**
     * Return the state of blocks statistics flags - if the block is counted for mined and placed.
     */
    public boolean getEnableStats()
    {
        return this.enableStats;
    }

    /**
     * Disable statistics for the block, the block will no count for mined or placed.
     */
    protected Block disableStats()
    {
        this.enableStats = false;
        return this;
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return this.blockMaterial.getMaterialMobility();
    }

    /**
     * Returns the default ambient occlusion value based on block opacity
     */
    public float getAmbientOcclusionLightValue(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.isBlockNormalCube(par2, par3, par4) ? 0.2F : 1.0F;
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World p_71866_1_, int p_71866_2_, int p_71866_3_, int p_71866_4_, Entity p_71866_5_, float p_71866_6_) {}

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.blockID;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_71873_1_, int p_71873_2_, int p_71873_3_, int p_71873_4_)
    {
        return this.damageDropped(p_71873_1_.getBlockMetadata(p_71873_2_, p_71873_3_, p_71873_4_));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    /**
     * Returns the CreativeTab to display the given block on.
     */
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return this.displayOnCreativeTab;
    }

    /**
     * Sets the CreativeTab to display this block on.
     */
    public Block setCreativeTab(CreativeTabs p_71849_1_)
    {
        this.displayOnCreativeTab = p_71849_1_;
        return this;
    }

    /**
     * Called when the block is attempted to be harvested
     */
    public void onBlockHarvested(World p_71846_1_, int p_71846_2_, int p_71846_3_, int p_71846_4_, int p_71846_5_, EntityPlayer p_71846_6_) {}

    /**
     * Called when this block is set (with meta data).
     */
    public void onSetBlockIDWithMetaData(World p_71927_1_, int p_71927_2_, int p_71927_3_, int p_71927_4_, int p_71927_5_) {}

    /**
     * currently only used by BlockCauldron to incrament meta-data during rain
     */
    public void fillWithRain(World p_71892_1_, int p_71892_2_, int p_71892_3_, int p_71892_4_) {}

    /**
     * Returns true only if block is flowerPot
     */
    public boolean isFlowerPot()
    {
        return false;
    }

    public boolean func_82506_l()
    {
        return true;
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion p_85103_1_)
    {
        return true;
    }

    /**
     * Returns true if the given block ID is equivalent to this one. Example: redstoneTorchOn matches itself and
     * redstoneTorchOff, and vice versa. Most blocks only match themselves.
     */
    public boolean isAssociatedBlockID(int p_94334_1_)
    {
        return this.blockID == p_94334_1_;
    }

    /**
     * Static version of isAssociatedBlockID.
     */
    public static boolean isAssociatedBlockID(int p_94329_0_, int p_94329_1_)
    {
        return p_94329_0_ == p_94329_1_ ? true : (p_94329_0_ != 0 && p_94329_1_ != 0 && blocksList[p_94329_0_] != null && blocksList[p_94329_1_] != null ? blocksList[p_94329_0_].isAssociatedBlockID(p_94329_1_) : false);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return false;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World p_94328_1_, int p_94328_2_, int p_94328_3_, int p_94328_4_, int p_94328_5_)
    {
        return 0;
    }

    protected Block func_111022_d(String p_111022_1_)
    {
        this.field_111026_f = p_111022_1_;
        return this;
    }

    protected String func_111023_E()
    {
        return this.field_111026_f == null ? "MISSING_ICON_TILE_" + this.blockID + "_" + this.unlocalizedName : this.field_111026_f;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.func_111023_E());
    }

    /**
     * Gets the icon name of the ItemBlock corresponding to this block. Used by hoppers.
     */
    public String getItemIconName()
    {
        return null;
    }

    static
    {
        Item.itemsList[cloth.blockID] = (new ItemCloth(cloth.blockID - 256)).setUnlocalizedName("cloth");
        Item.itemsList[field_111039_cA.blockID] = (new ItemCloth(field_111039_cA.blockID - 256)).setUnlocalizedName("clayHardenedStained");
        Item.itemsList[field_111031_cC.blockID] = (new ItemCloth(field_111031_cC.blockID - 256)).setUnlocalizedName("woolCarpet");
        Item.itemsList[wood.blockID] = (new ItemMultiTextureTile(wood.blockID - 256, wood, BlockLog.woodType)).setUnlocalizedName("log");
        Item.itemsList[planks.blockID] = (new ItemMultiTextureTile(planks.blockID - 256, planks, BlockWood.woodType)).setUnlocalizedName("wood");
        Item.itemsList[silverfish.blockID] = (new ItemMultiTextureTile(silverfish.blockID - 256, silverfish, BlockSilverfish.silverfishStoneTypes)).setUnlocalizedName("monsterStoneEgg");
        Item.itemsList[stoneBrick.blockID] = (new ItemMultiTextureTile(stoneBrick.blockID - 256, stoneBrick, BlockStoneBrick.STONE_BRICK_TYPES)).setUnlocalizedName("stonebricksmooth");
        Item.itemsList[sandStone.blockID] = (new ItemMultiTextureTile(sandStone.blockID - 256, sandStone, BlockSandStone.SAND_STONE_TYPES)).setUnlocalizedName("sandStone");
        Item.itemsList[blockNetherQuartz.blockID] = (new ItemMultiTextureTile(blockNetherQuartz.blockID - 256, blockNetherQuartz, BlockQuartz.quartzBlockTypes)).setUnlocalizedName("quartzBlock");
        Item.itemsList[stoneSingleSlab.blockID] = (new ItemSlab(stoneSingleSlab.blockID - 256, stoneSingleSlab, stoneDoubleSlab, false)).setUnlocalizedName("stoneSlab");
        Item.itemsList[stoneDoubleSlab.blockID] = (new ItemSlab(stoneDoubleSlab.blockID - 256, stoneSingleSlab, stoneDoubleSlab, true)).setUnlocalizedName("stoneSlab");
        Item.itemsList[woodSingleSlab.blockID] = (new ItemSlab(woodSingleSlab.blockID - 256, woodSingleSlab, woodDoubleSlab, false)).setUnlocalizedName("woodSlab");
        Item.itemsList[woodDoubleSlab.blockID] = (new ItemSlab(woodDoubleSlab.blockID - 256, woodSingleSlab, woodDoubleSlab, true)).setUnlocalizedName("woodSlab");
        Item.itemsList[sapling.blockID] = (new ItemMultiTextureTile(sapling.blockID - 256, sapling, BlockSapling.WOOD_TYPES)).setUnlocalizedName("sapling");
        Item.itemsList[leaves.blockID] = (new ItemLeaves(leaves.blockID - 256)).setUnlocalizedName("leaves");
        Item.itemsList[vine.blockID] = new ItemColored(vine.blockID - 256, false);
        Item.itemsList[tallGrass.blockID] = (new ItemColored(tallGrass.blockID - 256, true)).setBlockNames(new String[] {"shrub", "grass", "fern"});
        Item.itemsList[snow.blockID] = new ItemSnow(snow.blockID - 256, snow);
        Item.itemsList[waterlily.blockID] = new ItemLilyPad(waterlily.blockID - 256);
        Item.itemsList[pistonBase.blockID] = new ItemPiston(pistonBase.blockID - 256);
        Item.itemsList[pistonStickyBase.blockID] = new ItemPiston(pistonStickyBase.blockID - 256);
        Item.itemsList[cobblestoneWall.blockID] = (new ItemMultiTextureTile(cobblestoneWall.blockID - 256, cobblestoneWall, BlockWall.types)).setUnlocalizedName("cobbleWall");
        Item.itemsList[anvil.blockID] = (new ItemAnvilBlock(anvil)).setUnlocalizedName("anvil");

        for (int var0 = 0; var0 < 256; ++var0)
        {
            if (blocksList[var0] != null)
            {
                if (Item.itemsList[var0] == null)
                {
                    Item.itemsList[var0] = new ItemBlock(var0 - 256);
                    blocksList[var0].initializeBlock();
                }

                boolean var1 = false;

                if (var0 > 0 && blocksList[var0].getRenderType() == 10)
                {
                    var1 = true;
                }

                if (var0 > 0 && blocksList[var0] instanceof BlockHalfSlab)
                {
                    var1 = true;
                }

                if (var0 == tilledField.blockID)
                {
                    var1 = true;
                }

                if (canBlockGrass[var0])
                {
                    var1 = true;
                }

                if (lightOpacity[var0] == 0)
                {
                    var1 = true;
                }

                useNeighborBrightness[var0] = var1;
            }
        }

        canBlockGrass[0] = true;
        StatList.initBreakableStats();
    }
}
