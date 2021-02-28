package mello.cbrcalc;

import mello.cbrcalc.configs.SpringConfig;
import mello.cbrcalc.dao.DAO;
import mello.cbrcalc.downloader.ValCodeDownloader;
import mello.cbrcalc.downloader.ValRateDownloader;
import mello.cbrcalc.xml.ValCode;
import mello.cbrcalc.xml.ValRate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.xml.bind.JAXBException;
import java.io.IOException;


public class Main {
    public static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {
//        test();
        ValCodeDownloader valCodeDownloader = context.getBean(ValCodeDownloader.class);
        ValRateDownloader valRateDownloader = context.getBean(ValRateDownloader.class);

        try {
            valCodeDownloader.updateCodesTable();
            valRateDownloader.updateCodesTable();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    static void test() {
        ValRate rate = new ValRate();
        rate.id = "asdf";

        DAO dao = context.getBean(DAO.class);
//        dao.saveOrUpdate(rate);
//        dao.delete(rate);

    }

}
