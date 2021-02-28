package mello.cbrcalc.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Valuta")
public class ValCodeRoot {
   @XmlElement(name = "Item")
   public List<ValCode> list = new ArrayList<>();
}

