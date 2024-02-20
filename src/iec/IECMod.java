package iec;

import arc.*;
import arc.util.*;
import iec.content.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class IECMod extends Mod{

    public IECMod(){
        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("RRRR").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("iec-frog")).pad(20f).row();
                dialog.cont.button("Yes.", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        IECBlocks.load();
        Log.info("Loading content.");
    }

}
