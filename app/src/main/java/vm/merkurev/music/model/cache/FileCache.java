package vm.merkurev.music.model.cache;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import vm.merkurev.music.model.Singer;

/**
 * File cache implementation of ICache
 * saves to cache file List of singers
 * and reads it back
 */

public class FileCache implements ICache {
    private static final String FILENAME = "singer_cache";
    private static final String ERROR = "CACHE_ERROR";
    private Context context;

    public FileCache(Context context) {
        this.context = context;
    }

    @Override
    public void putInCache(List<Singer> cacheObject) {
        //parsing object into string and writing it to file
        Gson gson = new Gson();
        String toFile = gson.toJson(cacheObject, cacheObject.getClass());
        File cachedFile = getCachedFile();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(cachedFile);
            try {
                fileOutputStream.write(toFile.getBytes());
            } catch (IOException e) {
                Log.d(ERROR, "putInCache: cannot write to file " + e.getMessage());
            } finally {
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(ERROR, "putInCache: file not found +" + e.getMessage());
        }
    }

    @Override
    public List<Singer> getFromCache() {
        //parsing string read from file into List<Singers>
        ArrayList<Singer> singers = new ArrayList<>();
        File cachedFile = getCachedFile();
        long length = cachedFile.length();
        byte[] bytes = new byte[(int) length];
        try {
            FileInputStream fileInputStream = new FileInputStream(cachedFile);
            try {
                fileInputStream.read(bytes);
                String fromJson = new String(bytes);
                Gson gson = new Gson();
                //token is necessary, because of java type erasion
                Type type = new TypeToken<List<Singer>>() {
                }.getType();
                singers = gson.fromJson(fromJson, type);
            } catch (IOException e) {
                Log.d(ERROR, "putInCache: cannot read to file " + e.getMessage());
            } finally {
                fileInputStream.close();
            }
        } catch (IOException e) {
            Log.d(ERROR, "putInCache: file not found +" + e.getMessage());
        }
        return singers;
    }

    @Override
    public void invalidate() {
        //empty cache
        putInCache(new ArrayList<Singer>());
    }

    private File getCachedFile() {
        File cachedFilePath = context.getCacheDir();
        return new File(cachedFilePath, FILENAME);
    }
}
