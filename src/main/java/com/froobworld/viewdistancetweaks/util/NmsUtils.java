package com.froobworld.viewdistancetweaks.util;

import org.bukkit.Bukkit;

import java.util.regex.Pattern;

import static org.joor.Reflect.*;

public class NmsUtils {
    private static final String NMS_PACKAGE_NAME = on(Bukkit.getServer()).call("getHandle")
            .type()
            .getPackage()
            .getName();
    private static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit.", "");
    private static final boolean PRE_1_17 = NMS_PACKAGE_NAME.contains("1");

    public static String getFullyQualifiedClassName(String className, String post1_17PackageName) {
        return (PRE_1_17 ? NMS_PACKAGE_NAME : ("net.minecraft." + post1_17PackageName)) + "." + className;
    }

    public static String getFieldOrMethodName(String pre1_17Name, String post1_17Name) {
        return PRE_1_17 ? pre1_17Name : post1_17Name;
    }

    public static String getVersion() {
        return VERSION;
    }

    public static int getMajorVersion() {
        String[] split = VERSION.replace("v", "").replace("R", "").split(Pattern.quote("_"));
        try {
            return Integer.parseInt(split[0]);
        } catch(NumberFormatException e) {
            return 1;
        }
    }

    public static int getMinorVersion() {
        String[] split = VERSION.replace("v", "").replace("R", "").split(Pattern.quote("_"));
        try {
            return split.length > 1 ? Integer.parseInt(split[1]) : 0;
        } catch(NumberFormatException e) {
            return 20;
        }
    }

    public static int getRevisionNumber() {
        String[] split = VERSION.replace("v", "").replace("R", "").split(Pattern.quote("_"));
        try {
            return split.length > 2 ? Integer.parseInt(split[2]) : 0;
        } catch(NumberFormatException e) {
            return 0;
        }
    }

}
