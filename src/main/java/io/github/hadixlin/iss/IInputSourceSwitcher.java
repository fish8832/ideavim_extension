package io.github.hadixlin.iss;

import com.sun.jna.Native;

public interface IInputSourceSwitcher {
    public String iGetCurrentInputSourceID();
    public int iSwitchInputSource(String inputSourceID);
    public int iSwitchToEnglishInputSource();
    public boolean isEnglishInputSource(String inputSourceID);
}
