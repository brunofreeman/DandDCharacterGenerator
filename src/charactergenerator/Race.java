package charactergenerator;

public enum Race {
    HILL_DWARF, MOUNTAIN_DWARF, HIGH_ELF, WOOD_ELF, DROW, LIGHTFOOT_HALFLING, STOUT_HALFLING, HUMAN, DRAGONBORN, FOREST_GNOME, ROCK_GNOME, HALF_ELF, HALF_ORC, TIEFLING;

    @Override
    public String toString() {
        String[] words = this.name().split("_");
        String race = "";
        for (String word : words) {
            race += word.substring(0, 1) + word.substring(1).toLowerCase() + " ";
        }
        race = race.substring(0, race.length() - 1);
        return race;
    }
}
