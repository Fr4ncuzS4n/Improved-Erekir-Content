package iec.world.power;

import arc.Core;
import arc.math.Mathf;
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
                () -> entity.heat / maxHeat
            )
        );
    }

    public class IECHeaterGeneratorBuild extends VariableReactorBuild implements HeatConsumer{ }
}
