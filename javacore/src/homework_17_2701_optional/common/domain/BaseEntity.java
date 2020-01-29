package homework_17_2701_optional.common.domain;

public abstract class BaseEntity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public synchronized void setId(Long id) {
        this.id = id;
    }
}
