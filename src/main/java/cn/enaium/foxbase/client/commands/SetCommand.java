package cn.enaium.foxbase.client.commands;

import cn.enaium.cf4m.CF4M;
import cn.enaium.cf4m.annotation.Command;
import cn.enaium.cf4m.command.ICommand;
import cn.enaium.cf4m.setting.SettingBase;
import cn.enaium.cf4m.setting.settings.*;

import java.util.ArrayList;

/**
 * Project: FoxBase
 * -----------------------------------------------------------
 * Copyright © 2020 | Enaium | All rights reserved.
 */
@Command({"s","set"})
public class SetCommand implements ICommand {


    @Override
    public boolean run(String[] args) {



        if (args.length == 2 || args.length == 4) {

            Object module = CF4M.getInstance().module.getModule(args[1]);
            ArrayList<SettingBase> settings = CF4M.getInstance().module.getSettings(module);

            if(module == null) {
                CF4M.getInstance().configuration.message("The module with the name \"" + args[1] + "\" does not exist.");
                return true;
            }

            if(settings == null) {
                CF4M.getInstance().configuration.message("The module with the name \"" + args[1] + "\" no setting exists.");
                return true;
            }

            if(args.length == 2) {
                CF4M.getInstance().configuration.message("Here are the list of settings:");

                for (SettingBase s : settings) {
                    CF4M.getInstance().configuration.message(s.getName() + "(" + s.getClass().getSimpleName() + ")");
                    if(s instanceof ModeSetting) {
                        ((ModeSetting) s).getModes().forEach(CF4M.getInstance().configuration::message);
                    }
                }
            }

            if(args.length == 4) {
                for (SettingBase s : settings) {
                    if(s.getName().equalsIgnoreCase(args[2])) {
                        if(s instanceof EnableSetting) {
                            ((EnableSetting) s).setEnable(Boolean.parseBoolean(args[3]));
                        }else if(s instanceof DoubleSetting) {
                            ((DoubleSetting) s).setCurrent(Double.parseDouble(args[3]));
                        }else if(s instanceof FloatSetting) {
                            ((FloatSetting) s).setCurrent(Float.parseFloat(args[3]));
                        }else if(s instanceof LongSetting) {
                            ((LongSetting) s).setCurrent(Long.parseLong(args[3]));
                        }else if(s instanceof IntegerSetting) {
                            ((IntegerSetting) s).setCurrent(Integer.parseInt(args[3]));
                        }else if(s instanceof ModeSetting) {
                            ((ModeSetting) s).setCurrent(args[3]);
                        }

                        CF4M.getInstance().configuration.message(s.getName() + " has setting to " + args[3] + ".");
                    }
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String usage() {
        return "<module>\n<module> <setting> <value>";
    }
}
