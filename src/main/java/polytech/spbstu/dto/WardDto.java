package polytech.spbstu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Objects;
import polytech.spbstu.entity.PeopleEntity;
import polytech.spbstu.entity.WardsEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WardDto {

    private int id;

    private String name;

    private int maxCount;

    private List<PeopleEntity> peopleEntities;

    public WardsEntity toWard() {
        WardsEntity wardsEntity = new WardsEntity();
        wardsEntity.setId(id);
        wardsEntity.setName(name);
        wardsEntity.setMaxCount(maxCount);
        return wardsEntity;
    }

    public static WardDto fromWard(WardsEntity userEntity) {
        WardDto wardDto = new WardDto();
        wardDto.setId(userEntity.getId());
        wardDto.setName(userEntity.getName());
        wardDto.setMaxCount(userEntity.getMaxCount());
        return wardDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public List<PeopleEntity> getPeopleEntities() {
        return peopleEntities;
    }

    public void setPeopleEntities(List<PeopleEntity> peopleEntities) {
        this.peopleEntities = peopleEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WardDto wardDto = (WardDto) o;
        return id == wardDto.id &&
                maxCount == wardDto.maxCount &&
                name.equals(wardDto.name) &&
                peopleEntities.equals(wardDto.peopleEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxCount, peopleEntities);
    }

    @Override
    public String toString() {
        return "WardDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxCount=" + maxCount +
                ", peopleEntities=" + peopleEntities +
                '}';
    }
}
