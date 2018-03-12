package fr.rtgrenoble.velascof.simtelui;

import org.json.JSONObject;

import java.io.*;

public class Configuration {

    public static final File CFG_FILE = new File("UI_CONFIG.json");
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
        try (BufferedReader reader = new BufferedReader(new FileReader(CFG_FILE))) {
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
        try (FileWriter writer = new FileWriter(CFG_FILE)) {
            writer.write(cfg.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadDefaults() {
        try {
            if (CFG_FILE.getParentFile() != null) {
                CFG_FILE.getParentFile().mkdirs();
            }
            CFG_FILE.createNewFile();
            setShowScriptOutput(false);
            setScriptFile(new File(System.getProperty("user.home"), "fichier.py").getAbsolutePath());
            save();
        } catch (IOException e) {
            System.out.println("!!! Erreur d'initialisation de la configuration !!!");
            e.printStackTrace();
        }
    }
}
