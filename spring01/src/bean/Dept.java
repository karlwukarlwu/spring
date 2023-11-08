package bean;

/**
 * Karl Rules!
 * 2023/11/1
 * now File Encoding is UTF-8
 */
public class Dept {
    private String name;

    public Dept() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
