package mello.cbrcalc.downloader;

import mello.cbrcalc.services.ValCodeService;
import mello.cbrcalc.xml.ValCode;
import mello.cbrcalc.xml.ValCodeRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

@Component
public class ValCodeDownloader {
    @Autowired
    private ValCodeService valCodeService;

    @Value("${currency_catalog_url}")
    private String currency_catalog_url;

    public void updateCodesTable() throws IOException, JAXBException {
        URL url = new URL(currency_catalog_url);
        InputStream stream = url.openStream();
        byte[] buffer = stream.readAllBytes();
        String string = new String(buffer, Charset.forName("windows-1251"));

        StringReader reader = new StringReader(string);
        JAXBContext context = JAXBContext.newInstance(ValCode.class, ValCodeRoot.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ValCodeRoot root = (ValCodeRoot) unmarshaller.unmarshal(reader);

        root.list.forEach(valCodeService::saveOrUpdate);
    }
}
