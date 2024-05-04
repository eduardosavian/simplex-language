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

    private String identifier = "";
    private Type type;
    private Boolean isInitialized = false;
    private Boolean used = false;
    private String scoped = "";
    private Boolean isParameter = false;
    private Boolean isArray = false;
    private Boolean isMatrix = false;
    private Boolean isReference = false;
    private Boolean isFunction = false;

    Symbol(String identifier, String type, Boolean expressionsExist) {
        this.identifier = identifier;

        Boolean typeExists = false;

        for (Type typeSymbol : Type.values()) {
            if (type.contains(typeSymbol.getType())) {
                this.type = typeSymbol;
                typeExists = true;
                break;
            }
        }

        if (!typeExists) {
            System.err.println(
                    "The id '" + identifier
                            + type + "' type doesn't exist");
            System.exit(1);
        }

        this.isMatrix = (type.contains("["));

        this.isInitialized = expressionsExist;
        
        this.used = false;
        this.scoped = "";
        this.isParameter = false;
        this.isArray = false;
        this.isReference = false;
        this.isFunction = false;

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getIsInitialized() {
        return isInitialized;
    }

    public void setISInitialized(Boolean initialized) {
        this.isInitialized = initialized;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getScoped() {
        return scoped;
    }

    public void setScoped(String scoped) {
        this.scoped = scoped;
    }

    public Boolean getIsParameter() {
        return isParameter;
    }

    public void setIsParameter(Boolean isParameter) {
        this.isParameter = isParameter;
    }

    public Boolean getIsArray() {
        return isArray;
    }

    public void setIsArray(Boolean isArray) {
        this.isArray = isArray;
    }

    public Boolean getIsMatrix() {
        return isMatrix;
    }

    public void setIsMatrix(Boolean isMatrix) {
        this.isMatrix = isMatrix;
    }

    public Boolean getIsReference() {
        return isReference;
    }

    public void setIsReference(Boolean isReference) {
        this.isReference = isReference;
    }

    public Boolean getIsFunction() {
        return isFunction;
    }

    public void setIsFunction(Boolean isFunction) {
        this.isFunction = isFunction;
    }

    
    public void print() {
        System.out.print("Identifier: " + identifier);
        System.out.print(" | ");
        System.out.print("Type: " + type.getType());
        System.out.print(" | ");
        System.out.print("Initialized: " + isInitialized);
        System.out.print(" | ");
        System.out.print("Used: " + used);
        System.out.print(" | ");
        System.out.print("Scoped: " + scoped);
        System.out.print(" | ");
        System.out.print("Parameter: " + isParameter);
        System.out.print(" | ");
        System.out.print("Array: " + isArray);
        System.out.print(" | ");
        System.out.print("Matrix: " + isMatrix);
        System.out.print(" | ");
        System.out.print("Reference: " + isReference);
        System.out.print(" | ");
        System.out.println("Function: " + isFunction);
    }

}
