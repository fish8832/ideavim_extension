package io.github.hadixlin.iss;

/**
 * Created by hadix on 28/03/2017.
 */
public class SystemInputSource {
    static IInputSourceSwitcher sInputSourceSwitcher = null;

    public enum OS {
        WINDOWS, LINUX, MAC
    };

    public static OS getOS() {
        OS os = null;
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            }
        }
        return os;
    }

    private static IInputSourceSwitcher getInputSourceSwitcher() {
        if (sInputSourceSwitcher == null) {
            switch (getOS()) {
                case LINUX:
                    sInputSourceSwitcher = new LinuxInputSourceSwitcher();
                    break;
                case MAC:
                    sInputSourceSwitcher = new MacInputSourceSwitcher();
                    break;
                case WINDOWS:
                    break;
            }
        }

       return sInputSourceSwitcher; 
    }

    public static void switchToEnglish() {
        IInputSourceSwitcher inputSourceSwitcher = getInputSourceSwitcher();
        if (inputSourceSwitcher != null) {
            inputSourceSwitcher.iSwitchToEnglishInputSource();
        }
    }

    public static void switchTo(String inputSourceId) {
        IInputSourceSwitcher inputSourceSwitcher = getInputSourceSwitcher();
        if (inputSourceSwitcher != null) {
            inputSourceSwitcher.iSwitchInputSource(inputSourceId);
        }
    }

    public static String getCurrentInputSource() {
        IInputSourceSwitcher inputSourceSwitcher = getInputSourceSwitcher();
        if (inputSourceSwitcher != null) {
            return inputSourceSwitcher.iGetCurrentInputSourceID();
        }
        return null;
    }

    public static boolean isEnglishInputSource(String inputSourceId){
        IInputSourceSwitcher inputSourceSwitcher = getInputSourceSwitcher();
        if (inputSourceSwitcher != null) {
            return inputSourceSwitcher.isEnglishInputSource(inputSourceId);
        }
        return true;
    }
}
