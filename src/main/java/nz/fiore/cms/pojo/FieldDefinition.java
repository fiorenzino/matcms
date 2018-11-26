package nz.fiore.cms.pojo;

public class FieldDefinition {
    public String name;
    public String label;
    public String type;
    public String sqlType;
    public String sqlDefinition;
    public String defaultValue;
    public String pattern;
    public String definition;

    public FieldDefinition() {
    }

    @Override
    public String toString() {
        return "FieldDefinition{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", sqlDefinition='" + sqlDefinition + '\'' +
                ", type='" + type + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", pattern='" + pattern + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
