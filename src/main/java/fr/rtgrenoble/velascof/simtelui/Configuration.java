package fr.rtgrenoble.velascof.simtelui;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {

    private boolean showScriptOutput;
    private String scriptFile;

    public boolean isShowScriptOutput() {
        return showScriptOutput;
    }

    public void setShowScriptOutput(boolean showScriptOutput) {
        this.showScriptOutput = showScriptOutput;
    }

    public String getScriptFile() {
        return scriptFile;
    }

    public void setScriptFile(String scriptFile) {
        this.scriptFile = scriptFile;
    }

    public void load() {
        StringBuilder s = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(Main.CFG_FILE))) {
            while ((line = reader.readLine()) != null) {
                s.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject cfg = new JSONObject(s.toString());
        showScriptOutput = cfg.getBoolean("afficher_sortie_script");
        scriptFile = cfg.getString("chemin_vers_fichier.py");
    }

    public void save() {
        JSONObject cfg = new JSONObject();
        cfg.put("afficher_sortie_script", showScriptOutput);
        cfg.put("chemin_vers_fichier.py", scriptFile);
        try (FileWriter writer = new FileWriter(Main.CFG_FILE)) {
            writer.write(cfg.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
