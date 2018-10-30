package com.proasecal.web.cache;


public class CacheAtrib {
    private static CacheAtrib ourInstance = new CacheAtrib();

    /**
     * Obtener los roles para dejarlos en cache
     */


    String textoPrueba;

    public static CacheAtrib getInstance() {
        return ourInstance;
    }

    private CacheAtrib() {
    }

    public String getTextoPrueba() {
        return textoPrueba;
    }

    public void setTextoPrueba(String textoPrueba) {
        this.textoPrueba = textoPrueba;
    }
}
