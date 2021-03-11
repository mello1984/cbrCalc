package mello.cbrcalc.xml;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@Entity
@Table(name = "currency_rates")
@XmlType(name = "ValRate")
@Getter
public class ValRate {


    @Id
    @Column(name = "id")
    @XmlAttribute(name = "ID")
    public String id;
    @XmlElement(name = "NumCode")
    int numCode;
    @XmlElement(name = "CharCode")
    String charCode;
    @XmlElement(name = "Nominal")
    @Column(name = "nominal")
    public int nominal;
    @XmlElement(name = "Name")
    String name;
    @XmlElement(name = "Value")
    String value;
    @Column(name = "date1")
    public LocalDate date;
    @Column(name = "val")
    public double val;
}
