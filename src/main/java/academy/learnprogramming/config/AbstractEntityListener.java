package academy.learnprogramming.config;

import academy.learnprogramming.entities.AbstractEntity;

import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AbstractEntityListener {


    @PrePersist
    public void setCreatedOn(AbstractEntity abstractEntity) {
        abstractEntity.setCreatedOn(LocalDateTime.now());
    }

    @PreUpdate
    public void setUpdatedOn(AbstractEntity abstractEntity) {
        abstractEntity.setUpdatedOn(LocalDateTime.now());
    }


}
