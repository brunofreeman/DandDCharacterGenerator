package charactergenerator;


public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }
}
