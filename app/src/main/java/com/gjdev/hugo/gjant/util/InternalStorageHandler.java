package com.gjdev.hugo.gjant.util;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo on 28/12/2016.
 * Project: GjAnt2
 */

public class InternalStorageHandler {

    private Context mContext;

    public InternalStorageHandler(Context context){
        mContext = context;
    }

    public void saveObject(int fileNameStringResId, Object object, int MODE) {
        String mFilename = getStringResource(fileNameStringResId);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(mContext.openFileOutput(mFilename, MODE));
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public Object readObject(int fileNameStringResId) {
        String mFilename = getStringResource(fileNameStringResId);
        Object object = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(mContext.openFileInput(mFilename));
            object = objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (IOException|ClassNotFoundException e) { e.printStackTrace(); }

        return object;
    }

    public boolean deleteObject(int fileNameStringResId) {
        File file = new File(mContext.getFilesDir(), getStringResource(fileNameStringResId));
        return file.delete();
    }

    @NonNull
    private String getStringResource(int resId){
        return mContext.getString(resId);
    }

    /*@NonNull
    private SharedPreferences getSharedPreferences(int stringResId, int MODE){
        return mContext.getSharedPreferences(getStringResource(stringResId), MODE);
    }*/

   /* public boolean setSharedPreferencesData(int stringResId, int MODE, String key, Object value, int TYPE) {
        SharedPreferences.Editor editor = getSharedPreferences(stringResId, MODE).edit();
        switch (TYPE){
            case TYPE_STRING:
                return editor.putString(key, (String)value).commit();
        }
        return false;
    }

    public String getSharedPreferencesData(int stringResId, int MODE, String key){
        SharedPreferences sharedPreferences = getSharedPreferences(stringResId, MODE);
        String DEFAULT_VALUE = "Not found";
        return sharedPreferences.getString(key, DEFAULT_VALUE);
    }

    public String getSharedPreferencesData(int stringResId, int MODE, String key, String defValue){
        SharedPreferences sharedPreferences = getSharedPreferences(stringResId, MODE);
        return sharedPreferences.getString(key, defValue);
    }*/

}
