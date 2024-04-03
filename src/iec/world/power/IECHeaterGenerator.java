package iec.world.power;

import arc.Core;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.heat.HeatConsumer;
import mindustry.world.blocks.power.VariableReactor;

public class IECHeaterGenerator extends VariableReactor {
    public IECHeaterGenerator(String name){
        super(name);
        rebuildable = true;
    }

    @Override
    public void setBars(){
        super.setBars();

        removeBar("instability");

        addBar("heat", (IECHeaterGeneratorBuild entity) ->
                new Bar(() ->
                        Core.bundle.format("bar.heatpercent", (int)entity.heat, (int)(Mathf.clamp(entity.heat / maxHeat) * 100)),
                        () -> Pal.lightOrange,
                        () -> entity.heat / maxHeat));
    }

    public class IECHeaterGeneratorBuild extends VariableReactorBuild implements HeatConsumer{
        public float[] sideHeat = new float[4];
        public float heat = 0f, totalProgress, warmup;

        @Override
        public void updateTile(){
            heat = calculateHeat(sideHeat);

            productionEfficiency = efficiency;
            warmup = Mathf.lerpDelta(warmup, productionEfficiency > 0 ? 1f : 0f, warmupSpeed);

            totalProgress += productionEfficiency * Time.delta;

            if(Mathf.chanceDelta(effectChance * warmup)){
                effect.at(x, y, effectColor);
            }
        }

        @Override
        public void draw(){
            super.draw();

        }

        @Override
        public float totalProgress(){
            return totalProgress;
        }

        @Override
        public float warmup(){
            return warmup;
        }

        @Override
        public void updateEfficiencyMultiplier(){
            //at this stage efficiency = how much coolant is provided

            //target efficiency value
            float target = Mathf.clamp(heat / maxHeat);

            //now scale efficiency by target, so it consumes less depending on heat
            efficiency *= target;
        }

        @Override
        public float[] sideHeat(){
            return sideHeat;
        }

        @Override
        public float heatRequirement(){
            return maxHeat;
        }

        @Override
        public void write(Writes write){
            super.write(write);

            write.f(heat);
            write.f(warmup);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            heat = read.f();
            warmup = read.f();
        }
    }
}
