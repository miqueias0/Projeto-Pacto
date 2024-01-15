package br.com.mike.comum.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class CarregarProperties {

    private Properties properties;

    public CarregarProperties(String nomeProperties) throws Exception {
        InputStream fis;
        properties = new Properties();
        var arquivo_propriedades = new File(nomeProperties);
        if (arquivo_propriedades.exists()) {
            fis = new FileInputStream(arquivo_propriedades);
        } else {
            fis = getClass().getClassLoader().
                    getResourceAsStream(nomeProperties);
        }
        if (fis != null) {
            properties.load(fis);
            fis.close();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
