package homework_17_18_optional_and_date.common.domain;

public abstract class BaseEntity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public synchronized void setId(Long id) {
        this.id = id;
    }
}
