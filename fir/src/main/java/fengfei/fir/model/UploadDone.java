package fengfei.fir.model;

import java.util.HashMap;
import java.util.Map;

public class UploadDone {

    private int index;
    private String id;
    private String path;

    private Map<String, String> exif = new HashMap<>();

    public UploadDone(int index, String id, String path) {
        super();
        this.index = index;
        this.id = id;
        this.path = path;
    }

    public UploadDone(int index, String id, Map<String, String> exif) {
        super();
        this.index = index;
        this.id = id;
        this.exif = exif;
    }

    public UploadDone(int index, String id) {
        super();
        this.index = index;
        this.id = id;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getExif() {
        return exif;
    }

    public void setExif(Map<String, String> exif) {
        this.exif = exif;
    }

}
