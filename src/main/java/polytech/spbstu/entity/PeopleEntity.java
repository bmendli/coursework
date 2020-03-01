package polytech.spbstu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "people", schema = "u0523300_bogdanmendli24680")
public class PeopleEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private DiagnosisEntity diagnosisByDiagnosisId;
    private WardsEntity wardsByWardId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "father_name", nullable = false, length = 20)
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleEntity that = (PeopleEntity) o;
        return id == that.id
                && firstName.equals(that.firstName)
                && lastName.equals(that.lastName)
                && fatherName.equals(that.fatherName)
                && wardsByWardId.equals(that.wardsByWardId)
                && diagnosisByDiagnosisId.equals(that.diagnosisByDiagnosisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, fatherName, diagnosisByDiagnosisId, wardsByWardId);
    }

    @Override
    public String toString() {
        return "PeopleEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", diagnosisByDiagnosisId=" + diagnosisByDiagnosisId +
                ", wardsByWardId=" + wardsByWardId +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id", nullable = false)
    public DiagnosisEntity getDiagnosisByDiagnosisId() {
        return diagnosisByDiagnosisId;
    }

    public void setDiagnosisByDiagnosisId(DiagnosisEntity diagnosisByDiagnosisId) {
        this.diagnosisByDiagnosisId = diagnosisByDiagnosisId;
    }

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "id", nullable = false)
    public WardsEntity getWardsByWardId() {
        return wardsByWardId;
    }

    public void setWardsByWardId(WardsEntity wardsByWardId) {
        this.wardsByWardId = wardsByWardId;
    }
}
