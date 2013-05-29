package fengfei.spruce.utils;


public class Path {

    static final int MaxFolder = 1024;
    static final String root = "../upload";
    static final String DefaultCatalog = "default";

    public static String getPath(String id, String catalog, String name) {
        StringBuilder sb = new StringBuilder(root);
        sb.append("/");
        String path1 = hashDiv(id);
        sb.append(path1).append("/");
        sb.append(hashDiv(path1)).append("/");
        sb.append(id).append("/");
        sb.append(catalog == null || "".equals(catalog) ? DefaultCatalog : catalog).append("/");
        sb.append(name);
        return sb.toString();
    }

    public static String hashDiv(String str) {
        return hashDiv(str, MaxFolder);
    }

    public static String hashDiv(String str, int max) {
        int hash = Hash.hash(str.getBytes());
        int div = hash % max;
        return Integer.toString(div < 0 ? -div : div, 16).toUpperCase();
    }

}
