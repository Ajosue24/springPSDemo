package com.proasecal.web.bootstrap;

import com.proasecal.web.cache.CacheAtrib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class Bootstrap implements InitializingBean {
    private final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);
    private CacheAtrib cacheAtrib = CacheAtrib.getInstance();


    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("obtener roles y accesos a url");
        cacheAtrib.setTextoPrueba("texto");
    }
}
