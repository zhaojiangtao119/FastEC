package com.labelwall.latte.delegates.web.event;


import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-11-30.
 */

public class EventManager {

    private static final HashMap<String,Event> EVENTS = new HashMap<>();

    private EventManager(){
    }

    //惰性单例模式
    private static class Holder{
        private static final EventManager INTSANCE = new EventManager();
    }
    public static EventManager getInstace(){
        return Holder.INTSANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event){
        EVENTS.put(name,event);
        return this;
    }

    public Event getEvent(@NonNull String name){
        final Event event = EVENTS.get(name);
        if(event == null){
            return new UndefineEvent();
        }
        return event;
    }
}
