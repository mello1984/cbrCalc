package mello.cbrcalc.downloader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

@Component
public class Downloader {
    @Value("${cbr_calc.currency_catalog_url}")
    private String currency_catalog_url;
    @Value("${cbr_calc.daily_url}")
    private String daily_url;

    String getXmlString(String url_string) throws IOException {
        URL url = new URL(url_string);
        InputStream stream = url.openStream();
        byte[] buffer = stream.readAllBytes();
        return new String(buffer, Charset.forName("windows-1251"));
    }
}
