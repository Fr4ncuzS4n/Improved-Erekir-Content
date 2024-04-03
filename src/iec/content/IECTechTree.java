package iec.content;

import arc.struct.ObjectFloatMap;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.Item;

import static iec.content.IECBlocks.*;
import static mindustry.Vars.content;
import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.TechTree.nodeProduce;

public class IECTechTree{
    static TechTree.TechNode context = null;

    public static void load(){

        Seq<Objectives.Objective> erekirSector = Seq.with(new Objectives.OnPlanet(Planets.erekir));

        var costMultipliers = new ObjectFloatMap<Item>();
        for(var item : content.items()) costMultipliers.put(item, 0.9f);

        Planets.erekir.techTree = nodeRoot("erekir", coreBastion, true, () -> {
            context().researchCostMultipliers = costMultipliers;

            vanillaNode(plasmaBore, () -> {
                node(berylliumDrill, Seq.with(new Objectives.OnSector(basin)), () -> {

                });
            });

            /*vanillaNode(ventCondenser, Seq.with(new Objectives.OnSector(aegis)), () -> {

                node(hydrogenVentCondenser, Seq.with(new Objectives.OnSector()), () -> {
                    node(ozoneVentCondenser, () -> {
                    });
                });
            });*/

            vanillaNode(cliffCrusher, () -> {
                node(destroyerCliff, Seq.with(new Objectives.OnSector(stronghold)), () -> {

                });
            });

            vanillaNode(electrolyzer, () -> {
                node(hydrolyzer, Seq.with(new Objectives.OnSector(basin)), () -> {
                    node(ozonelyzer, () -> {
                    });
                });
            });
        });
    }
    private static void vanillaNode(UnlockableContent parent, Runnable children) {
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }
}
