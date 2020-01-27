package homework_16_2401_java8.common.domain;

public abstract class BaseEntity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public synchronized void setId(Long id) {
        this.id = id;
    }
}
