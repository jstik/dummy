package entity;

import javax.persistence.*;

/**
 * Created by Julia on 07.05.2017.
 */
@MappedSuperclass
public   class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
