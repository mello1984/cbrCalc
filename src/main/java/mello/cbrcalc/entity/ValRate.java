package mello.cbrcalc.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@Entity
@Table(name = "valuta_rates")
@XmlType(name = "ValRate")
@Getter
@IdClass(ValRatePK.class)
public class ValRate {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name ="id")
//    int id;

    @Id
    @Column(name = "val_id")
    @XmlAttribute(name = "ID")
    String valuta_id;

    @Id
    @Column(name = "date")
    LocalDate date;

    @Column(name = "num_code")
    @XmlElement(name = "NumCode")
    int numCode;

    @Column(name = "char_code")
    @XmlElement(name = "CharCode")
    String charCode;

    @Column(name = "nominal")
    @XmlElement(name = "Nominal")
    public int nominal;

    @Column(name="name")
    @XmlElement(name = "Name")
    String name;

    @XmlElement(name = "Value")
    String value;


    @Column(name = "val")
    double val;
}
