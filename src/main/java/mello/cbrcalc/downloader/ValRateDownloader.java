package mello.cbrcalc.downloader;

import mello.cbrcalc.service.ServiceDAO;
import mello.cbrcalc.entity.ValRateRoot;
import mello.cbrcalc.entity.ValRate;
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
public class ValRateDownloader {
    @Autowired
    ServiceDAO service;

    @Value("${cbr_calc.daily_url}")
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

        valRateRoot.valutes.forEach(service::saveOrUpdateValRate);
    }
    //TODO: дополнение таблицы, а не перезапись
}