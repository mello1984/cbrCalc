package mello.cbrcalc.downloader;

import mello.cbrcalc.Main;
import mello.cbrcalc.services.ValRateService;
import mello.cbrcalc.xml.ValRateRoot;
import mello.cbrcalc.xml.ValRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:config.properties")
public class ValRateDownloader {
    @Value("${daily_url}")
    private String daily_url;

    public void updateCodesTable() throws IOException, JAXBException {
        URL url = new URL(daily_url);
        InputStream stream = url.openStream();
        byte[] buffer = stream.readAllBytes();
        String string = new String(buffer, Charset.forName("windows-1251"));

        StringReader reader = new StringReader(string);
        JAXBContext context = JAXBContext.newInstance(ValRateRoot.class, ValRate.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ValRateRoot valRateRoot = (ValRateRoot) unmarshaller.unmarshal(reader);
        valRateRoot.prepareItems();

        ValRateService service = Main.context.getBean(ValRateService.class);
        valRateRoot.valutes.forEach(System.out::println);
        valRateRoot.valutes.forEach(service::saveOrUpdate);
    }
}