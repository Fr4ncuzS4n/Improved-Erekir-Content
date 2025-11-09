package iec.content;

import arc.graphics.Color;
import arc.struct.Seq;
import iec.world.block.power.IECHeaterGenerator;
import mindustry.content.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.environment.SteamVent;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;
import mindustry.world.meta.BlockGroup;

import static mindustry.type.ItemStack.*;


public class IECBlocks{
    public static Block
            //Production - Erekir
            destroyerCliff, berylliumDrill, hydrolyzer, ozonelyzer, ozoneCondenser, hydrogenCondenser, heaterGenerator,

            //Floor – Attributes
            hydroVent, ozoneVent;

    public static void load() {

        hydroVent = new SteamVent("hydro-vent"){{
            attributes.set(Attribute.add("hydrogen"), 1f);
        }};

        ozoneVent = new SteamVent("ozone-vent"){{
            attributes.set(Attribute.add("ozone"), 1f);
        }};

        destroyerCliff = new WallCrafter("destroyer-cliff") {{
            requirements(Category.production, with(Items.graphite, 60, Items.beryllium, 75, Items.silicon, 60));
            consumePower(34f / 60f);
            consumeLiquid(Liquids.hydrogen, 0.01f);

            drillTime = 75f;
            size = 3;
            attribute = Attribute.sand;
            output = Items.sand;
            researchCostMultiplier = 1.2f;
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.04f;
        }};

        berylliumDrill = new BurstDrill("beryllium-drill") {{
            requirements(Category.production, with(Items.beryllium, 50));
            consumePower(40f / 60f);
            consumeLiquid(Liquids.water, 5f / 60f);

            size = 3;
            drillTime = 210;
            itemCapacity = 20;
            liquidCapacity = 10;
            tier = 3;
            shake = 2;
            arrows = 1;
            squareSprite = false;
            researchCostMultiplier = 1.5f;
            drillEffect = new MultiEffect(
                Fx.mineImpact,
                Fx.drillSteam,
                Fx.mineImpactWave.wrap(Pal.berylShot, 30f));
        }};

        hydrolyzer = new GenericCrafter("hydrolyzer"){{
            requirements(Category.crafting, with(Items.silicon, 60, Items.graphite, 45, Items.beryllium, 135, Items.tungsten, 90));
            consumePower(1f);
            consumeLiquid(Liquids.water, 10f / 60f);

            craftTime = 10f;
            size = 3;
            liquidCapacity = 50;
            rotate = true;
            invertFlip = true;
            squareSprite = false;
            researchCostMultiplier = 1.4f;

            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.08f;

            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(Liquids.water, 2f),
                new DrawBubbles(Color.valueOf("7693e3")){{
                    sides = 10;
                    recurrence = 3f;
                    spread = 6;
                    radius = 1.5f;
                    amount = 20;
                }},
                new DrawRegion(),
                new DrawLiquidOutputs(),
                new DrawGlowRegion(){{
                    alpha = 1.4f;
                    color = Color.valueOf("c4bdf3");
                    glowIntensity = 0.6f;
                    glowScale = 8f;
                }}
            );

            regionRotated1 = 3;
            outputLiquids = LiquidStack.with(Liquids.hydrogen, 12f / 60f);
            liquidOutputDirections = new int[]{1};
        }};


        ozonelyzer = new GenericCrafter("ozonelyzer"){{
            requirements(Category.crafting, BuildVisibility.sandboxOnly, with(Items.silicon, 80, Items.graphite, 60, Items.beryllium, 150, Items.tungsten, 140));
            consumePower(1f);
            consumeLiquid(Liquids.water, 10f / 60f);

            craftTime = 10f;
            size = 3;
            liquidCapacity = 50;
            rotate = true;
            invertFlip = true;
            squareSprite = false;
            researchCostMultiplier = 1.6f;

            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.08f;

            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(Liquids.water, 2f),
                new DrawBubbles(Color.valueOf("7693e3")){{
                    sides = 10;
                    recurrence = 3f;
                    spread = 6;
                    radius = 1.5f;
                    amount = 20;
                }},
                new DrawRegion(),
                new DrawLiquidOutputs(),
                new DrawGlowRegion(){{
                    alpha = 1.4f;
                    color = Color.valueOf("c4bdf3");
                    glowIntensity = 0.6f;
                    glowScale = 8f;
                }}
            );

            regionRotated1 = 3;
            outputLiquids = LiquidStack.with(Liquids.ozone, 8f / 60f);
            liquidOutputDirections = new int[]{1};
        }};

        ozoneCondenser = new AttributeCrafter("ozone-condenser"){{
            requirements(Category.production, with(Items.beryllium, 145));
            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            minEfficiency = 9f - 0.0001f;
            baseEfficiency = 0f;
            displayEfficiency = false;
            craftEffect = Fx.turbinegenerate;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawBlurSpin("-rotator", 6f),
                new DrawRegion("-mid"),
                new DrawLiquidTile(Liquids.ozone, 38f / 4f),
                new DrawDefault()
            );
            craftTime = 120f;
            size = 3;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            hasLiquids = true;
            boostScale = 1f / 9f;

            outputLiquid = new LiquidStack(Liquids.ozone, 24f / 60f);
            consumePower(70f / 60f);
            liquidCapacity = 60f;
        }};

        hydrogenCondenser = new AttributeCrafter("hydrogen-condenser"){{
            requirements(Category.production, with(Items.beryllium, 145));
            attribute = Attribute.steam;
            group = BlockGroup.liquids;
            minEfficiency = 9f - 0.0001f;
            baseEfficiency = 0f;
            displayEfficiency = false;
            craftEffect = Fx.turbinegenerate;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawBlurSpin("-rotator", 6f),
                new DrawRegion("-mid"),
                new DrawLiquidTile(Liquids.hydrogen, 38f / 4f),
                new DrawDefault()
            );
            craftTime = 120f;
            size = 3;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            hasLiquids = true;
            boostScale = 1f / 9f;
            outputLiquid = new LiquidStack(Liquids.hydrogen, 32f / 60f);
            consumePower(80f / 60f);
            liquidCapacity = 60f;
        }};

        heaterGenerator = new IECHeaterGenerator("heater-generator"){{
            requirements(Category.power, BuildVisibility.shown, with(Items.beryllium, 120));
            powerProduction = 50f;
            maxHeat = 100f;
            size = 5;
        }};

        /*Blocks.shipRefabricator.requirements(Category.units, BuildVisibility.shown, with(Items.beryllium, 250, Items.tungsten, 120, Items.silicon, 150, Items.oxide, 15));
        Blocks.shipRefabricator = new Reconstructor(Blocks.shipRefabricator.name){{
            requirements(Category.units, with(Items.beryllium, 250, Items.tungsten, 120, Items.silicon, 150, Items.oxide, 15));
            regionSuffix = "-dark";

            size = 3;
            consumePower(2.5f);
            consumeLiquid(Liquids.hydrogen, 3f / 60f);
            consumeItems(with(Items.silicon, 60, Items.tungsten, 40));

            constructTime = 60f * 50f;

            //IT WORKS!!
            upgrades = Seq.with(
                    new UnitType[]{UnitTypes.elude, UnitTypes.avert},
                    new UnitType[]{UnitTypes.stell, UnitTypes.avert}
            );

            researchCost = with(Items.beryllium, 500, Items.tungsten, 200, Items.silicon, 300, Items.oxide, 80);
        }};*/
    }
}