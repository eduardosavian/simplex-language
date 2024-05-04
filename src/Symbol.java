import java.util.List;

public class Symbol {
    private String name = "";
    private String type = "";
    private Boolean initialized = false;
    private Boolean used = false;
    private Boolean scoped = false;
    private Boolean param = false;
    private Boolean array = false;
    private Boolean matrix = false;
    private Boolean ref = false;
    private Boolean func = false;

    public List<String> types = List.of("int", "real", "bool", "string");

    public Symbol(String name, String type) {
        this.name = name;
        this.type = type;
        this.initialized = false;
        this.used = false;
        this.scoped = false;
        this.param = false;
        this.array = false;
        this.matrix = false;
        this.ref = false;
        this.func = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Boolean getScoped() {
        return scoped;
    }

    public void setScoped(Boolean scoped) {
        this.scoped = scoped;
    }

    public Boolean getParam() {
        return param;
    }

    public void setParam(Boolean param) {
        this.param = param;
    }

    public Boolean getArray() {
        return array;
    }

    public void setArray(Boolean array) {
        this.array = array;
    }

    public Boolean getMatrix() {
        return matrix;
    }

    public void setMatrix(Boolean matrix) {
        this.matrix = matrix;
    }

    public Boolean getRef() {
        return ref;
    }

    public void setRef(Boolean ref) {
        this.ref = ref;
    }

    public Boolean getFunc() {
        return func;
    }

    public void setFunc(Boolean func) {
        this.func = func;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
