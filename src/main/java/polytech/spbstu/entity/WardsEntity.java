package polytech.spbstu.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wards", schema = "u0523300_bogdanmendli24680")
public class WardsEntity {
    private int id;
    private String name;
    private int maxCount;

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
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "max_count", nullable = false)
    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WardsEntity that = (WardsEntity) o;
        return id == that.id
                && maxCount == that.maxCount
                && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxCount);
    }

    @Override
    public String toString() {
        return "WardsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxCount=" + maxCount +
                ", peopleEntities=" + peopleEntities +
                '}';
    }

    @OneToMany(targetEntity = PeopleEntity.class, cascade = CascadeType.ALL, mappedBy = "wardsByWardId")
    private List<PeopleEntity> peopleEntities;
}
