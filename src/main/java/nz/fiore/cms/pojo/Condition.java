package nz.fiore.cms.pojo;

public class Condition {
    public String separator;
    public String condition;
    public String subQuery;

    public Condition() {
    }

    @Override
    public String toString() {
        return "Condition{" +
                "separator='" + separator + '\'' +
                "condition='" + condition + '\'' +
                ", subQuery='" + subQuery + '\'' +
                '}';
    }
}
