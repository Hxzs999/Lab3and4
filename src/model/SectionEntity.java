package model;

import javax.persistence.*;

@Entity
@Table(name = "sections")
public class SectionEntity {
    private int idSection;
    private String sectionName;

    @Id
    @Column(name = "id_section")
    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    @Basic
    @Column(name = "section_name")
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionEntity that = (SectionEntity) o;

        if (idSection != that.idSection) return false;
        return sectionName != null ? sectionName.equals(that.sectionName) : that.sectionName == null;
    }

    @Override
    public int hashCode() {
        int result = idSection;
        result = 31 * result + (sectionName != null ? sectionName.hashCode() : 0);
        return result;
    }
}