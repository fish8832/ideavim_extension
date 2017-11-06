package io.github.hadixlin.iss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxInputSourceSwitcher implements IInputSourceSwitcher {
    static final String ENGLISH_INPUT_SOURCE = "1";
    static final String OTHER_INPUT_SOURCE = "2";

    public String iGetCurrentInputSourceID(){
        String result = "";
        try {
            String line;
            Process p = Runtime.getRuntime().exec ("fcitx-remote");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result != null && result.length() > 0 ? result : null;
    }

    public int iSwitchInputSource(String inputSourceID){
        if (inputSourceID != null) {
            String cmd = null;
            if (inputSourceID.equals(ENGLISH_INPUT_SOURCE)) {
                cmd = "fcitx-remote -c";
            } else if (inputSourceID.equals(OTHER_INPUT_SOURCE)) {
                cmd = "fcitx-remote -o";
            } 

            if (cmd != null) {
                try {
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;    
    }

    public int iSwitchToEnglishInputSource() {
        return iSwitchInputSource(ENGLISH_INPUT_SOURCE); 
    }

    public boolean isEnglishInputSource(String inputSourceID){
        return ENGLISH_INPUT_SOURCE.equals(inputSourceID);
    }
}
