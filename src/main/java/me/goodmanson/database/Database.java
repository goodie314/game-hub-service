package me.goodmanson.database;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class Database {
    private static final String location = "game-hub-service-database.java";
    private Map<String, Object> tables;

    public Database() {
    }

    public void loadTables () throws IOException, ClassNotFoundException {
        ObjectInputStream reader;
        File file;
        FileInputStream fileInputStream;
        ObjectOutputStream writer;
        FileOutputStream fileOutputStream;

        file = new File(location);
        try {
            fileInputStream = new FileInputStream(file);
            reader = new ObjectInputStream(fileInputStream);
            this.tables = (Map<String, Object>) reader.readObject();
        } catch (Exception e) {
            fileOutputStream = new FileOutputStream(file, false);
            writer = new ObjectOutputStream(fileOutputStream);
            this.tables = new HashMap<>();
            writer.writeObject(this.tables);
            writer.flush();
            writer.close();
        }
    }

    public void addData (String tableName, Object data) throws IOException, ClassNotFoundException {
        ObjectOutputStream writer;

        if (this.tables == null) {
            this.loadTables();
        }

        this.tables.put(tableName, data);

        writer = new ObjectOutputStream(new FileOutputStream(location));
        writer.writeObject(this.tables);
        writer.flush();
        writer.close();
    }

    public Object getTable (String tableName) throws IOException, ClassNotFoundException {
        if (this.tables != null) {
            return this.tables.get(tableName);
        }

        this.loadTables();

        if (this.tables == null) {
            this.tables = new HashMap<>();
        }

        return this.tables.get(tableName);
    }
}
