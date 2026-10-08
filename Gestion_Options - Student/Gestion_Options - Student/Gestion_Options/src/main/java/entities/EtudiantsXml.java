package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "etudiants")
public class EtudiantsXml {
    private List<Etudiant> etudiants = new ArrayList<>();

    public EtudiantsXml() {}

    public EtudiantsXml(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    @XmlElement(name = "etudiant")
    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}