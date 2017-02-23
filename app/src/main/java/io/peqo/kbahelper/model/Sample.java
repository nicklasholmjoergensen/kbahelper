package io.peqo.kbahelper.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Sample {

    @Id(autoincrement = true)
    private Long id;

    private Long requisitionId;

    private String name;

    public Sample() {}

    @Generated(hash = 549334974)
    public Sample(Long id, Long requisitionId, String name) {
        this.id = id;
        this.requisitionId = requisitionId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequisitionId() {
        return requisitionId;
    }

    public void setRequisitionId(Long requisitionId) {
        this.requisitionId = requisitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id=" + id +
                ", requisitionId=" + requisitionId +
                ", name='" + name + '\'' +
                '}';
    }
}
