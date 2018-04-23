package charactergenerator;


public enum PlayerClass {
    BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD;

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }
}
