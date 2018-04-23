package charactergenerator;


public enum Background {
    ACOLYTE, CHARLATAN, CRIMINAL, ENTERTAINER, FOLK_HERO, GUILD_ARTISAN, HERMIT, NOBLE, OUTLANDER, SAGE, SAILOR, SOLDIER, URCHIN;

    @Override
    public String toString() {
        String[] words = this.name().split("_");
        String background = "";
        for (String word : words) {
            background += word.substring(0, 1) + word.substring(1).toLowerCase() + " ";
        }
        background = background.substring(0, background.length() - 1);
        return background;
    }
}