package com.example.oem.dev_notetaking;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class save {
    public static String ext = ".bin";
    public static String i;
    public static boolean saveitem(Context c, item it) {
        i=it.getFilename();
        String item_name = String.valueOf(i) + ext;
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = c.openFileOutput(item_name, c.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(it);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<item> getnote(Context context) {
        ArrayList<item> items = new ArrayList<>();
        File f = context.getFilesDir();
        ArrayList<String> file = new ArrayList<>();
        for (String fil : f.list()) {
            if (fil.endsWith(ext))
                file.add(fil);
        }
        int j = 0;
        FileInputStream fis;
        ObjectInputStream ois;
            for (j = 0; j < file.size(); j++) {
                try {
                    fis = context.openFileInput(file.get(j));
                    ois = new ObjectInputStream(fis);
                    items.add((item) ois.readObject());
                    fis.close();
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        return items;
    }
    public static item open(Context context,item e){
        String file=e.getFilename()+ext;
        item i=null;
        File f=new File(context.getFilesDir(),file);
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
             i = (item) ois.readObject();
        }catch (IOException|ClassNotFoundException it){it.printStackTrace();
        }
        return i;
    }
    public static boolean delete(Context context,item e){
        String filename=e.getFilename()+ext;
        File f=new File(context.getFilesDir(),filename);
        return f.delete();

    }
}


