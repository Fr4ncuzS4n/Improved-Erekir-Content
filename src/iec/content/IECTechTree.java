package iec.content;

import arc.struct.ObjectFloatMap;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.game.Objectives;
import mindustry.type.Item;

import static iec.content.IECBlocks.*;
import static mindustry.Vars.content;
import static mindustry.content.Blocks.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;
import static mindustry.content.TechTree.nodeProduce;

public class IECTechTree {
    static TechTree.TechNode context = null;

    public static void load(){

        Seq<Objectives.Objective> erekirSector = Seq.with(new Objectives.OnPlanet(Planets.erekir));

        var costMultipliers = new ObjectFloatMap<Item>();
        for(var item : content.items()) costMultipliers.put(item, 0.9f);

        Planets.erekir.techTree = nodeRoot("erekir", coreBastion, true, () -> {
            context().researchCostMultipliers = costMultipliers;

            node(duct, erekirSector, () -> {
                node(ductRouter, () -> {
                    node(ductBridge, () -> {
                        node(armoredDuct, () -> {
                            node(surgeConveyor, () -> {
                                node(surgeRouter);
                            });
                        });

                        node(unitCargoLoader, () -> {
                            node(unitCargoUnloadPoint, () -> {

                            });
                        });
                    });

                    node(overflowDuct, Seq.with(new Objectives.OnSector(aegis)), () -> {
                        node(underflowDuct);
                        node(reinforcedContainer, () -> {
                            node(ductUnloader, () -> {

                            });

                            node(reinforcedVault, () -> {

                            });
                        });
                    });

                    node(reinforcedMessage, Seq.with(new Objectives.OnSector(aegis)), () -> {
                        node(canvas);
                    });
                });

                node(reinforcedPayloadConveyor, Seq.with(new Objectives.OnSector(atlas)), () -> {
                    //TODO should only be unlocked in unit sector
                    node(payloadMassDriver, Seq.with(new Objectives.Research(siliconArcFurnace), new Objectives.OnSector(split)), () -> {
                        //TODO further limitations
                        node(payloadLoader, () -> {
                            node(payloadUnloader, () -> {
                                node(largePayloadMassDriver, () -> {

                                });
                            });
                        });

                        node(constructor, Seq.with(new Objectives.OnSector(split)), () -> {
                            node(smallDeconstructor, Seq.with(new Objectives.OnSector(peaks)), () -> {
                                node(largeConstructor, Seq.with(new Objectives.OnSector(siege)), () -> {

                                });

                                node(deconstructor, Seq.with(new Objectives.OnSector(siege)), () -> {

                                });
                            });
                        });
                    });

                    node(reinforcedPayloadRouter, () -> {

                    });
                });
            });

            //TODO move into turbine condenser?
            node(plasmaBore, () -> {
                node(impactDrill, Seq.with(new Objectives.OnSector(aegis)), () -> {
                    node(largePlasmaBore, Seq.with(new Objectives.OnSector(caldera)), () -> {
                        node(eruptionDrill, Seq.with(new Objectives.OnSector(stronghold)), () -> {

                        });
                    });
                });
                node(berylliumDrill, Seq.with(new Objectives.OnSector(basin)), () -> {

                });
            });

            node(turbineCondenser, () -> {
                node(beamNode, () -> {
                    node(ventCondenser, Seq.with(new Objectives.OnSector(aegis)), () -> {
                        node(chemicalCombustionChamber, Seq.with(new Objectives.OnSector(basin)), () -> {
                            node(pyrolysisGenerator, Seq.with(new Objectives.OnSector(crevice)), () -> {
                                node(fluxReactor, Seq.with(new Objectives.OnSector(crossroads), new Objectives.Research(cyanogenSynthesizer)), () -> {
                                    node(neoplasiaReactor, Seq.with(new Objectives.OnSector(karst)), () -> {

                                    });
                                });
                            });
                        });

                        /*node(hydrogenVentCondenser, Seq.with(new Objectives.OnSector()), () -> {
                            node(ozoneVentCondenser, () -> {
                            });
                        });*/
                    });

                    node(beamTower, Seq.with(new Objectives.OnSector(peaks)), () -> {

                    });


                    node(regenProjector, Seq.with(new Objectives.OnSector(peaks)), () -> {
                        //TODO more tiers of build tower or "support" structures like overdrive projectors
                        node(buildTower, Seq.with(new Objectives.OnSector(stronghold)), () -> {
                            node(shockwaveTower, Seq.with(new Objectives.OnSector(siege)), () -> {

                            });
                        });
                    });
                });

                node(reinforcedConduit, Seq.with(new Objectives.OnSector(aegis)), () -> {
                    //TODO maybe should be even later
                    node(reinforcedPump, Seq.with(new Objectives.OnSector(basin)), () -> {
                        //TODO T2 pump, consume cyanogen or similar
                    });

                    node(reinforcedLiquidJunction, () -> {
                        node(reinforcedBridgeConduit, () -> {

                        });

                        node(reinforcedLiquidRouter, () -> {
                            node(reinforcedLiquidContainer, () -> {
                                node(reinforcedLiquidTank, Seq.with(new Objectives.SectorComplete(intersect)), () -> {

                                });
                            });
                        });
                    });
                });

                node(cliffCrusher, () -> {
                    node(siliconArcFurnace, () -> {
                        node(electrolyzer, Seq.with(new Objectives.OnSector(atlas)), () -> {
                            node(oxidationChamber, Seq.with(new Objectives.Research(tankRefabricator), new Objectives.OnSector(marsh)), () -> {

                                node(surgeCrucible, Seq.with(new Objectives.OnSector(ravine)), () -> {

                                });
                                node(heatRedirector, Seq.with(new Objectives.OnSector(ravine)), () -> {
                                    node(electricHeater, Seq.with(new Objectives.OnSector(ravine), new Objectives.Research(afflict)), () -> {
                                        node(slagHeater, Seq.with(new Objectives.OnSector(caldera)), () -> {

                                        });

                                        node(atmosphericConcentrator, Seq.with(new Objectives.OnSector(caldera)), () -> {
                                            node(cyanogenSynthesizer, Seq.with(new Objectives.OnSector(siege)), () -> {

                                            });
                                        });

                                        node(carbideCrucible, Seq.with(new Objectives.OnSector(crevice)), () -> {
                                            node(phaseSynthesizer, Seq.with(new Objectives.OnSector(karst)), () -> {
                                                node(phaseHeater, Seq.with(new Objectives.Research(phaseSynthesizer)), () -> {

                                                });
                                            });
                                        });

                                        node(heatRouter, () -> {

                                        });
                                    });
                                });
                            });

                            node(hydrolyzer, Seq.with(new Objectives.OnSector(basin)), () -> {
                                node(ozonelyzer, () -> {
                                });
                            });

                            node(slagIncinerator, Seq.with(new Objectives.OnSector(basin)), () -> {

                                //TODO these are unused.
                                //node(slagCentrifuge, () -> {});
                                //node(heatReactor, () -> {});
                            });
                        });
                    });
                    node(destroyerCliff, Seq.with(new Objectives.OnSector(stronghold)), () -> {

                    });
                });
            });


            node(breach, Seq.with(new Objectives.Research(siliconArcFurnace), new Objectives.Research(tankFabricator)), () -> {
                node(berylliumWall, () -> {
                    node(berylliumWallLarge, () -> {

                    });

                    node(tungstenWall, () -> {
                        node(tungstenWallLarge, () -> {
                            node(blastDoor, () -> {

                            });
                        });

                        node(reinforcedSurgeWall, () -> {
                            node(reinforcedSurgeWallLarge, () -> {
                                node(shieldedWall, () -> {

                                });
                            });
                        });

                        node(carbideWall, () -> {
                            node(carbideWallLarge, () -> {

                            });
                        });
                    });
                });

                node(diffuse, Seq.with(new Objectives.OnSector(lake)), () -> {
                    node(sublimate, Seq.with(new Objectives.OnSector(marsh)), () -> {
                        node(afflict, Seq.with(new Objectives.OnSector(ravine)), () -> {
                            node(titan, Seq.with(new Objectives.OnSector(stronghold)), () -> {
                                node(lustre, Seq.with(new Objectives.OnSector(crevice)), () -> {
                                    node(smite, Seq.with(new Objectives.OnSector(karst)), () -> {

                                    });
                                });
                            });
                        });
                    });

                    node(disperse, Seq.with(new Objectives.OnSector(stronghold)), () -> {
                        node(scathe, Seq.with(new Objectives.OnSector(siege)), () -> {
                            node(malign, Seq.with(new Objectives.SectorComplete(karst)), () -> {

                            });
                        });
                    });
                });


                node(radar, Seq.with(new Objectives.Research(beamNode), new Objectives.Research(turbineCondenser), new Objectives.Research(tankFabricator), new Objectives.OnSector(SectorPresets.aegis)), () -> {

                });
            });

            node(coreCitadel, Seq.with(new Objectives.SectorComplete(peaks)), () -> {
                node(coreAcropolis, Seq.with(new Objectives.SectorComplete(siege)), () -> {

                });
            });

            node(tankFabricator, Seq.with(new Objectives.Research(siliconArcFurnace), new Objectives.Research(plasmaBore), new Objectives.Research(turbineCondenser)), () -> {
                node(UnitTypes.stell);

                node(unitRepairTower, Seq.with(new Objectives.OnSector(ravine), new Objectives.Research(mechRefabricator)), () -> {

                });

                node(shipFabricator, Seq.with(new Objectives.OnSector(lake)), () -> {
                    node(UnitTypes.elude);

                    node(mechFabricator, Seq.with(new Objectives.OnSector(intersect)), () -> {
                        node(UnitTypes.merui);

                        node(tankRefabricator, Seq.with(new Objectives.OnSector(atlas)), () -> {
                            node(UnitTypes.locus);

                            node(mechRefabricator, Seq.with(new Objectives.OnSector(basin)), () -> {
                                node(UnitTypes.cleroi);

                                node(shipRefabricator, Seq.with(new Objectives.OnSector(peaks)), () -> {
                                    node(UnitTypes.avert);

                                    //TODO
                                    node(primeRefabricator, Seq.with(new Objectives.OnSector(stronghold)), () -> {
                                        node(UnitTypes.precept);
                                        node(UnitTypes.anthicus);
                                        node(UnitTypes.obviate);
                                    });

                                    node(tankAssembler, Seq.with(new Objectives.OnSector(siege), new Objectives.Research(constructor), new Objectives.Research(atmosphericConcentrator)), () -> {

                                        node(UnitTypes.vanquish, () -> {
                                            node(UnitTypes.conquer, Seq.with(new Objectives.OnSector(karst)), () -> {

                                            });
                                        });

                                        node(shipAssembler, Seq.with(new Objectives.OnSector(crossroads)), () -> {
                                            node(UnitTypes.quell, () -> {
                                                node(UnitTypes.disrupt, Seq.with(new Objectives.OnSector(karst)), () -> {

                                                });
                                            });
                                        });

                                        node(mechAssembler, Seq.with(new Objectives.OnSector(crossroads)), () -> {
                                            node(UnitTypes.tecta, () -> {
                                                node(UnitTypes.collaris, Seq.with(new Objectives.OnSector(karst)), () -> {

                                                });
                                            });
                                        });

                                        node(basicAssemblerModule, Seq.with(new Objectives.SectorComplete(karst)), () -> {

                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });

            node(onset, () -> {
                node(aegis, Seq.with(new Objectives.SectorComplete(onset), new Objectives.Research(ductRouter), new Objectives.Research(ductBridge)), () -> {
                    node(lake, Seq.with(new Objectives.SectorComplete(aegis)), () -> {

                    });

                    node(intersect, Seq.with(new Objectives.SectorComplete(aegis), new Objectives.SectorComplete(lake), new Objectives.Research(ventCondenser), new Objectives.Research(shipFabricator)), () -> {
                        node(atlas, Seq.with(new Objectives.SectorComplete(intersect), new Objectives.Research(mechFabricator)), () -> {
                            node(split, Seq.with(new Objectives.SectorComplete(atlas), new Objectives.Research(reinforcedPayloadConveyor), new Objectives.Research(reinforcedContainer)), () -> {

                            });

                            node(basin, Seq.with(new Objectives.SectorComplete(atlas)), () -> {
                                node(marsh, Seq.with(new Objectives.SectorComplete(basin)), () -> {
                                    node(ravine, Seq.with(new Objectives.SectorComplete(marsh), new Objectives.Research(Liquids.slag)), () -> {
                                        node(caldera, Seq.with(new Objectives.SectorComplete(peaks), new Objectives.Research(heatRedirector)), () -> {
                                            node(stronghold, Seq.with(new Objectives.SectorComplete(caldera), new Objectives.Research(coreCitadel)), () -> {
                                                node(crevice, Seq.with(new Objectives.SectorComplete(stronghold)), () -> {
                                                    node(siege, Seq.with(new Objectives.SectorComplete(crevice)), () -> {
                                                        node(crossroads, Seq.with(new Objectives.SectorComplete(siege)), () -> {
                                                            node(karst, Seq.with(new Objectives.SectorComplete(crossroads), new Objectives.Research(coreAcropolis)), () -> {
                                                                node(origin, Seq.with(new Objectives.SectorComplete(karst), new Objectives.Research(coreAcropolis), new Objectives.Research(UnitTypes.vanquish), new Objectives.Research(UnitTypes.disrupt), new Objectives.Research(UnitTypes.collaris), new Objectives.Research(malign), new Objectives.Research(basicAssemblerModule), new Objectives.Research(neoplasiaReactor)), () -> {

                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });

                                    node(peaks, Seq.with(new Objectives.SectorComplete(marsh), new Objectives.SectorComplete(split)), () -> {

                                    });
                                });
                            });
                        });
                    });
                });
            });

            nodeProduce(Items.beryllium, () -> {
                nodeProduce(Items.sand, () -> {
                    nodeProduce(Items.silicon, () -> {
                        nodeProduce(Items.oxide, () -> {
                            //nodeProduce(Items.fissileMatter, () -> {});
                        });
                    });
                });

                nodeProduce(Liquids.water, () -> {
                    nodeProduce(Liquids.ozone, () -> {
                        nodeProduce(Liquids.hydrogen, () -> {
                            nodeProduce(Liquids.nitrogen, () -> {

                            });

                            nodeProduce(Liquids.cyanogen, () -> {
                                nodeProduce(Liquids.neoplasm, () -> {

                                });
                            });
                        });
                    });
                });

                nodeProduce(Items.graphite, () -> {
                    nodeProduce(Items.tungsten, () -> {
                        nodeProduce(Liquids.slag, () -> {

                        });

                        nodeProduce(Liquids.arkycite, () -> {

                        });

                        nodeProduce(Items.thorium, () -> {
                            nodeProduce(Items.carbide, () -> {

                                //nodeProduce(Liquids.gallium, () -> {});
                            });
                        });

                        nodeProduce(Items.surgeAlloy, () -> {
                            nodeProduce(Items.phaseFabric, () -> {

                            });
                        });
                    });
                });
            });
        });
    }
}
