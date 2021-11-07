package ua.goit.dao.model;


public enum Stack {
    JAVA("Java"),
    CPLUS("C++"),
    CSHARP("C#"),
    JS("JS");

    private String value;

    Stack(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
