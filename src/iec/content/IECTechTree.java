package iec.content;

import arc.struct.ObjectFloatMap;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static iec.content.IECBlocks.*;
import static mindustry.Vars.content;
import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;

public class IECTechTree{
    static TechTree.TechNode context = null;

    public static void load(){

        Seq<Objective> erekirSector = Seq.with(new OnPlanet(Planets.erekir));

        var costMultipliers = new ObjectFloatMap<Item>();
        for(var item : content.items()) costMultipliers.put(item, 0.9f);

        Planets.erekir.techTree = nodeRoot("erekir", coreBastion, true, () -> {
            context().researchCostMultipliers = costMultipliers;

            vanillaNode(plasmaBore, () -> {
                node(berylliumDrill, Seq.with(new OnSector(basin)), () -> {

                });
            });

            /*vanillaNode(ventCondenser, Seq.with(new Objectives.OnSector(aegis)), () -> {

                node(hydrogenVentCondenser, Seq.with(new Objectives.OnSector()), () -> {
                    node(ozoneVentCondenser, () -> {
                    });
                });
            });*/

            vanillaNode(cliffCrusher, () -> {
                node(destroyerCliff, Seq.with(new OnSector(stronghold)), () -> {

                });
            });

            vanillaNode(electrolyzer, () -> {
                node(hydrolyzer, Seq.with(new OnSector(basin)), () -> {
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

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children) {
        TechNode node = new TechNode(context, content, requirements);
        if (objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children) {
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children) {
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives) {
        node(content, objectives, () -> {});
    }

    private static void node(UnlockableContent content, Runnable children) {
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block) {
        node(block, () -> {});
    }
}
