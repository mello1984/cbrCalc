package mello.cbrcalc.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "valuta_daily_codes")
@XmlType(name = "Item")
@XmlAccessorType(XmlAccessType.NONE)
@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValCodeDaily {
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
}
