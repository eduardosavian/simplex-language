import java.util.List;

public class Symbol {
    public enum Type {
        INT("int"),
        REAL("real"),
        BOOL("bool"),
        STRING("string");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    private String id = "";
    private Type type;
    private Boolean initialized = false;
    private Boolean used = false;
    private Boolean scoped = false;
    private Boolean param = false;
    private Boolean array = false;
    private Boolean matrix = false;
    private Boolean ref = false;
    private Boolean func = false;

    Symbol(String id, String type) {
        this.id = id;

        Boolean typeExists = false;

        for (Type typeSymbol : Type.values()) {
            if (type.contains(typeSymbol.getType())) {
                typeExists = true;
                break;
            }
        }

        if (!typeExists) {
            System.err.println(
                    "The id '" + id
                            + type + "' type doesn't exist");
            System.exit(1);
        }
        this.initialized = false;
        this.used = false;
        this.scoped = false;
        this.param = false;
        this.array = false;
        this.matrix = false;
        this.ref = false;
        this.func = false;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

}
