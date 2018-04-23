package charactergenerator;

public enum Language {
    COMMON, DWARVISH, ELVISH, GIANT, GNOMISH, GOBLIN, HALFLING, ORC, ABYSSAL, CELESTIAL, DRACONIC, DEEP_SPEECH, INFERNAL, PRIMORDIAL, SYLVAN, UNDERCOMMON;
    
    @Override
    public String toString() {
        String[] words = this.name().split("_");
        String language = "";
        for (String word : words) {
            language += word.substring(0, 1) + word.substring(1).toLowerCase() + " ";
        }
        language = language.substring(0, language.length() - 1);
        return language;
    }
}
