package com.froobworld.viewdistancetweaks.limiter;

import com.froobworld.viewdistancetweaks.hook.viewdistance.ViewDistanceHook;
import org.bukkit.World;

import java.util.Collection;
import java.util.function.Function;

public class StartupClampTask {
    private final ViewDistanceHook viewDistanceHook;
    private final Function<World, Integer> maxViewDistance;
    private final Function<World, Integer> minViewDistance;

    public StartupClampTask(ViewDistanceHook viewDistanceHook, Function<World, Integer> maxViewDistance, Function<World, Integer> minViewDistance) {
        this.viewDistanceHook = viewDistanceHook;
        this.maxViewDistance = maxViewDistance;
        this.minViewDistance = minViewDistance;
    }


    public void runOnWorlds(Collection<World> worlds) {
        for (World world : worlds) {
            int viewDistance = viewDistanceHook.getViewDistance(world);
            int newViewDistance = Math.max(Math.min(viewDistance, maxViewDistance.apply(world)), minViewDistance.apply(world));
            if (newViewDistance != viewDistance) {
                viewDistanceHook.setViewDistance(world, newViewDistance);
            }
        }
    }

}
