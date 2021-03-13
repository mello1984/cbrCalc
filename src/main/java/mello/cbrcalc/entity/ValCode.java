package mello.cbrcalc.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "valuta_codes")
@XmlType(name = "Item")
@Getter
public class ValCode {
    @XmlAttribute(name = "ID")
    @Id
    @Column(name = "id")
    String id;
    @XmlElement(name = "Name")
    @Column(name = "name")
    String name;
    @XmlElement(name = "EngName")
    @Column(name = "eng_name")
    String engName;
    @XmlElement(name = "Nominal")
    @Column(name = "nominal")
    int nominal;
    @XmlElement(name = "ParentCode")
    @Column(name = "parent_code")
    String parentCode;
    @XmlElement(name = "ISO_Num_Code")
    @Column(name = "iso_num_code")
    int isoNumCode;
    @XmlElement(name = "ISO_Char_Code")
    @Column(name = "iso_char_code")
    String isoCharCode;

    @Override
    public String toString() {
        return "ValCode{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", engName='" + engName + '\'' +
                ", nominal=" + nominal +
                ", parentCode='" + parentCode + '\'' +
                ", isoNumCode=" + isoNumCode +
                ", isoCharCode='" + isoCharCode + '\'' +
                '}';
    }
}
