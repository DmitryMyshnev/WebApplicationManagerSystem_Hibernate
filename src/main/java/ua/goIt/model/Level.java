package ua.goIt.model;

public enum Level {
    JUNIOR("junior"),
    MIDDLE("middle"),
    SENIOR("senior");
    private String value;

    Level(String value) {
        this.value = value;
    }
    public String getLevel(){
        return value;
    }
}
