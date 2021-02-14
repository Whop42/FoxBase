package cn.enaium.foxbase.client;

import cn.enaium.cf4m.CF4M;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

/**
 * Project: FoxBase
 * -----------------------------------------------------------
 * Copyright © 2020 | Enaium | All rights reserved.
 */
public enum FoxBase {

    instance;

    public String name = "FoxBase";
    public String author = "Enaium";
    public String version = "1.0";
    public String game = "1.8.9";


    public void Start() {
        new CF4M(this, Minecraft.getMinecraft().mcDataDir + "/" + name);
        CF4M.getInstance().run();
        Display.setTitle(name + " | Author:" + author + " | Version:" + version + " | Minecraft:" + game);
    }

    public void Stop() {
        CF4M.getInstance().stop();
    }

}
