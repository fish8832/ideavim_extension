package io.github.hadixlin.iss;

import com.sun.jna.Native;

/**
 * Created by hadix on 30/03/2017.
 */
public class MacInputSourceSwitcher implements IInputSourceSwitcher {
    static final String ENGLISH_INPUT_SOURCE = "com.apple.keylayout.US";

    static {
        Native.register("input-source-switcher");
    }

    public static native String getCurrentInputSourceID();

    public static native int switchInputSource(String inputSourceID);

    public String iGetCurrentInputSourceID(){
        return getCurrentInputSourceID();    
    }

    public int iSwitchInputSource(String inputSourceID){
        return switchInputSource(inputSourceID);    
    }

    public int iSwitchToEnglishInputSource() {
        return switchInputSource(ENGLISH_INPUT_SOURCE); 
    }

    public boolean isEnglishInputSource(String inputSourceID){
        return ENGLISH_INPUT_SOURCE.equals(inputSourceID);
    }
}
