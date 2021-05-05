package ml.pkom.jsonconfig.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ml.pkom.jsonconfig.configuration.JsonToYaml;
import ml.pkom.jsonconfig.configuration.YamlToJson;

public class JsonConfiguration {

    private FileConfiguration newConfig;
    private File jsonFile;

    private File tmpFile;

    public JsonConfiguration(File jsonFile){
        setJsonConfigFile(jsonFile);
    }

    public void setJsonConfigFile(File jsonFile){
        if (jsonFile.isDirectory()){
            jsonFile = new File(jsonFile, "config.json");
        }
        this.jsonFile = jsonFile;
    }

    public File getJsonConfigFile(){
        return this.jsonFile;
    }

    public FileConfiguration getJsonConfig() {
        if (newConfig == null) {
            reloadJsonConfig();
        }
        return newConfig;
    }

    public void saveJsonConfig() {
        String yamlData;
        String jsonData;
        try {
            tmpFile = Files.createTempFile(Paths.get("/tmp"), "prefix", ".suffix").toFile();
            getJsonConfig().save(tmpFile);
            InputStream inStream = new FileInputStream(tmpFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            PrintWriter printWriter = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmpFile), "UTF-8")));
            String line;
            yamlData = "";
            while ((line = bufferedReader.readLine()) != null) {
                yamlData = yamlData + "\n" + line;
            }
            jsonData = YamlToJson.convert(yamlData);
            printWriter.write(jsonData);
            bufferedReader.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tmpFile != null && tmpFile.exists()) {
            tmpFile.delete();
        }
    }

    public Configuration reloadJsonConfig() {
        String yamlData;
        String jsonData;
        try {
            tmpFile = Files.createTempFile(Paths.get("/tmp"), "prefix", ".suffix").toFile();

            InputStream inStream = new FileInputStream(jsonFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            PrintWriter printWriter = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmpFile), "UTF-8")));
            String line;
            jsonData = "";
            while ((line = bufferedReader.readLine()) != null) {
                jsonData = jsonData + "\n" + line;
            }
            yamlData = JsonToYaml.convert(jsonData);
            printWriter.write(yamlData);
            bufferedReader.close();
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("ファイルが見つかりませんでした。");
        } catch (IOException e) {
            e.printStackTrace();
        }
        newConfig = YamlConfiguration.loadConfiguration(tmpFile);
        if (tmpFile != null && tmpFile.exists()) {
            tmpFile.delete();
        }
        return newConfig;
    }
}
