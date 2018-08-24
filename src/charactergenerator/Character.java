package charactergenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Character {

    private String name;
    private PlayerClass[] classes;
    private int[] classLevels;
    private PlayerClass firstClass;
    private Background background;
    private Race race;
    private Gender gender;
    private Alignment alignment;
    private int xp;
    private int[] attributes;
    private int passiveWisdom;
    private int proficienyBonus;
    private boolean[] savingThrowProfs;
    private boolean[] skillProfs;
    private Language[] languages;
    private String proficiencies;
    private int armorClass;
    private int speed;
    private int hitPoints;
    private Dice hitDice;
    private String[] attacks;
    private int[] attackBonus;
    private Dice[] attackDamage;
    private int[] coins;
    private ArrayList<String> equipment;
    private String[] personalityTraits;
    private String ideal;
    private String bond;
    private String flaw;
    private String[] features;
    private RollMode rollMode;
    private static Random rand = ThreadLocalRandom.current();
    private static String filePath = System.getProperty("user.dir") + "\\src\\resources\\storage\\";

    public Character() { //convert to array lists
        rollMode = RollMode.AVERAGE;
        xp = 0;
        race = Race.values()[rand.nextInt(Race.values().length)];
        classes = new PlayerClass[]{PlayerClass.values()[rand.nextInt(PlayerClass.values().length)]};
        classLevels = new int[]{1};
        firstClass = classes[0];
        gender = Gender.values()[rand.nextInt(Gender.values().length)];
        name = generateName();
        alignment = Alignment.values()[rand.nextInt(Alignment.values().length)];
        attributes = rollAtributes();
        shuffle(attributes);
        applyRacialIncreases();
        proficienyBonus = 2;
        speed = initSpeed();
        savingThrowProfs = initSavingThrowProfs();
        background = Background.values()[rand.nextInt(Background.values().length)];
        skillProfs = getRandomSkillProfsSubset();
        hitDice = initHitDice();
        hitPoints = initHitPoints();
        languages = initLanguages();
        ideal = initIdeal();
        bond = initBond();
        flaw = initFlaw();
        personalityTraits = initPersonalityTraits();
        equipment = new ArrayList<>();
        coins = new int[5];
        equipment.addAll(getStartingEquipment(firstClass));
        //equipment.addAll(getStartingEquipment(background));
        //equipment.addAll(getStartingEquipment());
    }

    public void levelCharacter() {

    }

    public String generateName() {
        return generateName(race);
    }

    public String generateName(Race race) {
        try {
            ArrayList<String> firstNames = new ArrayList<>();
            ArrayList<String> lastNames = new ArrayList<>();
            switch (gender) {
                case MALE:
                    switch (race) {
                        case HILL_DWARF:
                        case MOUNTAIN_DWARF:
                            firstNames = getNames("dwarfNames_male");
                            lastNames = getNames("dwarfNames_clan");
                            break;
                        case HIGH_ELF:
                        case WOOD_ELF:
                        case DROW:
                            firstNames = getNames("elfNames_male");
                            lastNames = getNames("elfNames_family");
                            break;
                        case LIGHTFOOT_HALFLING:
                        case STOUT_HALFLING:
                            firstNames = getNames("halflingNames_male");
                            lastNames = getNames("halflingNames_family");
                            break;
                        case HUMAN:
                            firstNames = getNames("humanNames_male");
                            lastNames = getNames("humanNames_surname");
                            break;
                        case DRAGONBORN:
                            firstNames = getNames("dragonbornNames_clan");
                            lastNames = getNames("dragonbornNames_male");
                            break;
                        case FOREST_GNOME:
                        case ROCK_GNOME:
                            firstNames = getNames("gnomeNames_male");
                            lastNames = getNames("gnomeNames_clan");
                            break;
                        case HALF_ELF:
                            return rand.nextInt(2) == 0 ? generateName(Race.HIGH_ELF) : generateName(Race.HUMAN);
                        case HALF_ORC:
                            if (rand.nextInt(2) == 0) {
                                return generateName(Race.HUMAN);
                            } else {
                                firstNames = getNames("orcNames_male");
                            }
                            break;
                        case TIEFLING:
                            firstNames = getNames("infernalNames_male");
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                    break;
                case FEMALE:
                    switch (race) {
                        case HILL_DWARF:
                        case MOUNTAIN_DWARF:
                            firstNames = getNames("dwarfNames_female");
                            lastNames = getNames("dwarfNames_clan");
                            break;
                        case HIGH_ELF:
                        case WOOD_ELF:
                        case DROW:
                            firstNames = getNames("elfNames_female");
                            lastNames = getNames("elfNames_family");
                            break;
                        case LIGHTFOOT_HALFLING:
                        case STOUT_HALFLING:
                            firstNames = getNames("halflingNames_female");
                            lastNames = getNames("halflingNames_family");
                            break;
                        case HUMAN:
                            firstNames = getNames("humanNames_female");
                            lastNames = getNames("humanNames_surname");
                            break;
                        case DRAGONBORN:
                            firstNames = getNames("dragonbornNames_clan");
                            lastNames = getNames("dragonbornNames_female");
                            break;
                        case FOREST_GNOME:
                        case ROCK_GNOME:
                            firstNames = getNames("gnomeNames_female");
                            lastNames = getNames("gnomeNames_clan");
                            break;
                        case HALF_ELF:
                            return rand.nextInt(2) == 0 ? generateName(Race.HIGH_ELF) : generateName(Race.HUMAN);
                        case HALF_ORC:
                            if (rand.nextInt(2) == 0) {
                                return generateName(Race.HUMAN);
                            } else {
                                firstNames = getNames("orcNames_female");
                            }
                            break;
                        case TIEFLING:
                            firstNames = getNames("infernalNames_female");
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return lastNames.size() == 0 ? firstNames.get(rand.nextInt(firstNames.size())) : firstNames.get(rand.nextInt(firstNames.size())) + " " + lastNames.get(rand.nextInt(lastNames.size()));
        } catch (Exception e) {
            e.printStackTrace();
            return "Name Name";
        }
    }

    private ArrayList<String> getNames(String fileName) throws FileNotFoundException {
        try {
            Scanner fileInput = new Scanner(new File(filePath + "names\\" + fileName + ".txt"));
            ArrayList<String> names = new ArrayList<>();
            while (fileInput.hasNext()) {
                names.add(fileInput.nextLine());
            }
            return names;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public int[] rollAtributes() {
        switch (rollMode) {
            case AVERAGE:
                return new int[]{8, 10, 12, 13, 14, 15};
            case ROLL: //will be different
                return new int[]{8, 10, 12, 13, 14, 15};
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--)
        {
          int index = rand.nextInt(i + 1);
          int a = arr[index];
          arr[index] = arr[i];
          arr[i] = a;
        }
    }

    public void applyRacialIncreases() {
        switch (race) {
            case HILL_DWARF:
                attributes[2] += 2;
                attributes[4] += 1;
                break;
            case MOUNTAIN_DWARF:
                attributes[0] += 2;
                attributes[2] += 2;
                break;
            case HIGH_ELF:
                attributes[1] += 2;
                attributes[3] += 1;
                break;
            case WOOD_ELF:
                attributes[1] += 2;
                attributes[4] += 1;
                break;
            case DROW:
                attributes[1] += 2;
                attributes[5] += 1;
                break;
            case LIGHTFOOT_HALFLING:
                attributes[1] += 2;
                attributes[5] += 1;
                break;
            case STOUT_HALFLING:
                attributes[1] += 2;
                attributes[2] += 1;
                break;
            case HUMAN:
                for (int i = 0; i < attributes.length; i++) {
                    attributes[i]++;
                }
                break;
            case DRAGONBORN:
                attributes[0] += 2;
                attributes[5] += 1;
                break;
            case FOREST_GNOME:
                attributes[1] += 1;
                attributes[3] += 2;
                break;
            case ROCK_GNOME:
                attributes[2] += 1;
                attributes[3] += 2;
                break;
            case HALF_ELF:
                attributes[3] += 1;
                attributes[5] += 2;
                break;
            case HALF_ORC:
                attributes[0] += 2;
                attributes[2] += 1;
                break;
            case TIEFLING:
                attributes[3] += 1;
                attributes[5] += 2;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int initSpeed() {
        switch (race) {
            case HILL_DWARF:
            case MOUNTAIN_DWARF:
            case LIGHTFOOT_HALFLING:
            case STOUT_HALFLING:
            case FOREST_GNOME:
            case ROCK_GNOME:
                return 25;
            case HIGH_ELF:
            case WOOD_ELF:
            case DROW:
            case HUMAN:
            case DRAGONBORN:
            case HALF_ELF:
            case HALF_ORC:
            case TIEFLING:
                return 30;
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean[] initSavingThrowProfs() {
        boolean[] profs = new boolean[6];
        switch (firstClass) {
            case BARBARIAN:
                profs[0] = true;
                profs[2] = true;
                break;
            case BARD:
                profs[1] = true;
                profs[5] = true;
                break;
            case CLERIC:
                profs[4] = true;
                profs[5] = true;
                break;
            case DRUID:
                profs[3] = true;
                profs[4] = true;
                break;
            case FIGHTER:
                profs[0] = true;
                profs[2] = true;
                break;
            case MONK:
                profs[0] = true;
                profs[1] = true;
                break;
            case PALADIN:
                profs[4] = true;
                profs[5] = true;
                break;
            case RANGER:
                profs[0] = true;
                profs[1] = true;
                break;
            case ROGUE:
                profs[1] = true;
                profs[3] = true;
                break;
            case SORCERER:
                profs[2] = true;
                profs[5] = true;
                break;
            case WARLOCK:
                profs[4] = true;
                profs[5] = true;
                break;
            case WIZARD:
                profs[3] = true;
                profs[4] = true;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return profs;
    }

    public boolean[] getRandomSkillProfsSubset() {
        boolean[] profArr = new boolean[18];
        int[] backgroundProfs = {};
        switch (background) {
            case ACOLYTE:
                backgroundProfs = new int[]{6, 14};
                break;
            case CHARLATAN:
                backgroundProfs = new int[]{4, 15};
                break;
            case CRIMINAL:
                backgroundProfs = new int[]{4, 16};
                break;
            case ENTERTAINER:
                backgroundProfs = new int[]{0, 12};
                break;
            case FOLK_HERO:
                backgroundProfs = new int[]{1, 17};
                break;
            case GUILD_ARTISAN:
                backgroundProfs = new int[]{6, 13};
                break;
            case HERMIT:
                backgroundProfs = new int[]{9, 14};
                break;
            case NOBLE:
                backgroundProfs = new int[]{5, 13};
                break;
            case OUTLANDER:
                backgroundProfs = new int[]{3, 17};
                break;
            case SAGE:
                backgroundProfs = new int[]{2, 5};
                break;
            case SAILOR:
                backgroundProfs = new int[]{3, 11};
                break;
            case SOLDIER:
                backgroundProfs = new int[]{3, 7};
                break;
            case URCHIN:
                backgroundProfs = new int[]{15, 16};
                break;
        }
        int[] classProfs = {};
        switch (firstClass) {
            case BARBARIAN:
                classProfs = chooseN(2, new int[]{1, 3, 7, 10, 11, 17}, backgroundProfs);
                break;
            case BARD:
                classProfs = chooseN(3, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, backgroundProfs);
                break;
            case CLERIC:
                classProfs = chooseN(2, new int[]{5, 6, 9, 13, 14}, backgroundProfs);
                break;
            case DRUID:
                classProfs = chooseN(2, new int[]{1, 2, 6, 9, 10, 11, 14, 17}, backgroundProfs);
                break;
            case FIGHTER:
                classProfs = chooseN(2, new int[]{0, 1, 3, 5, 6, 7, 11, 17}, backgroundProfs);
                break;
            case MONK:
                classProfs = chooseN(2, new int[]{0, 3, 5, 6, 14, 16}, backgroundProfs);
                break;
            case PALADIN:
                classProfs = chooseN(2, new int[]{3, 6, 7, 9, 13, 14}, backgroundProfs);
                break;
            case RANGER:
                classProfs = chooseN(3, new int[]{1, 3, 6, 8, 10, 11, 16, 17}, backgroundProfs);
                break;
            case ROGUE:
                classProfs = chooseN(4, new int[]{0, 3, 4, 6, 7, 8, 11, 12, 13, 15, 16}, backgroundProfs);
                break;
            case SORCERER:
                classProfs = chooseN(2, new int[]{2, 4, 6, 7, 13, 14}, backgroundProfs);
                break;
            case WARLOCK:
                classProfs = chooseN(2, new int[]{2, 4, 5, 7, 8, 10, 14}, backgroundProfs);
                break;
            case WIZARD:
                classProfs = chooseN(2, new int[]{2, 5, 6, 8, 9, 14}, backgroundProfs);
                break;
            default:
                throw new IllegalArgumentException();
        }
        for (int i : backgroundProfs) {
            profArr[i] = true;
        }
        for (int i : classProfs) {
            profArr[i] = true;
        }
        return profArr;
    }

    public int[] chooseN(int n, int... arr) {
        shuffle(arr);
        int[] returnArr = new int[n];
        for (int i = 0; i < n; i++) {
            returnArr[i] = arr[i];
        }
        return returnArr;
    }

    public int[] chooseN(int n, int[] choose, int[] exclude) {
        ArrayList<Integer> chooseArrList = new ArrayList<>();
        for (int i : choose) {
            chooseArrList.add(i);
        }
        chooseArrList.removeAll(Arrays.asList(exclude));
        choose = new int[chooseArrList.size()];
        for(int i = 0; i < chooseArrList.size(); i++) {
            choose[i] = chooseArrList.get(i);
        }
        shuffle(choose);
        int[] returnArr = new int[n];
        for (int i = 0; i < n; i++) {
            returnArr[i] = choose[i];
        }
        return returnArr;
    }

    public Dice initHitDice() {
        switch (firstClass) {
            case BARBARIAN:
                return new Dice(new int[]{0, 0, 0, 0, 1, 0, 0}, 0);
            case BARD:
            case CLERIC:
            case DRUID:
            case MONK:
            case ROGUE:
            case WARLOCK:
                return new Dice(new int[]{0, 0, 1, 0, 0, 0, 0}, 0);
            case FIGHTER:
            case PALADIN:
            case RANGER:
                return new Dice(new int[]{0, 0, 0, 1, 0, 0, 0}, 0);
            case SORCERER:
            case WIZARD:
                return new Dice(new int[]{0, 1, 0, 0, 0, 0, 0}, 0);
            default:
                throw new IllegalArgumentException();
        }
    }

    public int initHitPoints() {
        switch (firstClass) {
            case BARBARIAN:
                return 12 + getAttributeModifiers()[2];
            case PALADIN:
            case RANGER:
            case FIGHTER:
                return 10 + getAttributeModifiers()[2];
            case BARD:
            case CLERIC:
            case DRUID:
            case MONK:
            case ROGUE:
            case WARLOCK:
                return 8 + getAttributeModifiers()[2];
            case WIZARD:
            case SORCERER:
                return 6 + getAttributeModifiers()[2];
            default:
                throw new IllegalArgumentException();
        }
    }

    public Language[] initLanguages() {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(Language.COMMON);
        switch (race) {
            case HILL_DWARF:
            case MOUNTAIN_DWARF:
                languages.add(Language.DWARVISH);
                break;
            case HIGH_ELF:
            case WOOD_ELF:
            case DROW:
                languages.add(Language.ELVISH);
                break;
            case LIGHTFOOT_HALFLING:
            case STOUT_HALFLING:
                languages.add(Language.HALFLING);
                break;
            case HUMAN:
                Language humanExtra = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(humanExtra)) {
                    humanExtra = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(humanExtra);
                break;
            case DRAGONBORN:
                languages.add(Language.DRACONIC);
                break;
            case FOREST_GNOME:
            case ROCK_GNOME:
                languages.add(Language.GNOMISH);
                break;
            case HALF_ELF:
                languages.add(Language.ELVISH);
                Language halfElfExtra = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(halfElfExtra)) {
                    halfElfExtra = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(halfElfExtra);
                break;
            case HALF_ORC:
                languages.add(Language.ORC);
                break;
            case TIEFLING:
                languages.add(Language.INFERNAL);
                break;
            default:
                throw new IllegalArgumentException();
        }
        switch (background) {
            case ACOLYTE:
                Language acolyteExtra1 = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(acolyteExtra1)) {
                    acolyteExtra1 = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(acolyteExtra1);
                Language acolyteExtra2 = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(acolyteExtra2)) {
                    acolyteExtra2 = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(acolyteExtra2);
                break;
            case GUILD_ARTISAN:
                Language guildArtisanExtra = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(guildArtisanExtra)) {
                    guildArtisanExtra = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(guildArtisanExtra);
                break;
            case HERMIT:
                Language hermitExtra = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(hermitExtra)) {
                    hermitExtra = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(hermitExtra);
                break;
            case NOBLE:
                Language nobleExtra = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(nobleExtra)) {
                    nobleExtra = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(nobleExtra);
                break;
            case OUTLANDER:
                Language outlanderExtra = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(outlanderExtra)) {
                    outlanderExtra = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(outlanderExtra);
                break;
            case SAGE:
                Language sageExtra1 = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(sageExtra1)) {
                    sageExtra1 = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(sageExtra1);
                Language sageExtra2 = Language.values()[rand.nextInt(Language.values().length)];
                while (languages.contains(sageExtra2)) {
                    sageExtra2 = Language.values()[rand.nextInt(Language.values().length)];
                }
                languages.add(sageExtra2);
                break;
        }
        Language[] languageArr = new Language[languages.size()];
        for (int i = 0; i < languageArr.length; i++) {
            languageArr[i] = languages.get(i);
        }
        return languageArr;
    }

    public String initIdeal() {
        try {
            switch (background) {
                case ACOLYTE:
                    return randomIdeal("acolyteIdeals");
                case CHARLATAN:
                    return randomIdeal("charlatanIdeals");
                case CRIMINAL:
                    return randomIdeal("criminalIdeals");
                case ENTERTAINER:
                    return randomIdeal("entertainerIdeals");
                case FOLK_HERO:
                    return randomIdeal("folkHeroIdeals");
                case GUILD_ARTISAN:
                    return randomIdeal("guildArtisanIdeals");
                case HERMIT:
                    return randomIdeal("hermitIdeals");
                case NOBLE:
                    return randomIdeal("nobleIdeals");
                case OUTLANDER:
                    return randomIdeal("outlanderIdeals");
                case SAGE:
                    return randomIdeal("sageIdeals");
                case SAILOR:
                    return randomIdeal("sailorIdeals");
                case SOLDIER:
                    return randomIdeal("soldierIdeals");
                case URCHIN:
                    return randomIdeal("urchinIdeals");
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            return "Ideal";
        }
    }

    public String randomIdeal(String fileName) throws FileNotFoundException {
        try {
            Scanner fileInput = new Scanner(new File(filePath + "ideals\\" + fileName + ".txt"));
            ArrayList<String> ideals = new ArrayList<>();
            while (fileInput.hasNext()) {
                ideals.add(fileInput.nextLine());
            }
            ArrayList<SubAlignment> subAlignments = new ArrayList<>();
            for (int i = 0; i < ideals.size(); i++) {
                subAlignments.add(SubAlignment.valueOf(ideals.get(i).substring(0, ideals.get(i).indexOf(" "))));
                ideals.set(i, ideals.get(i).substring(ideals.get(i).indexOf(" ") + 1));
            }
            for (int i = ideals.size() - 1; i >= 0; i--) {
                if (subAlignments.get(i) != SubAlignment.ANY && subAlignments.get(i) != alignment.getSA1() && subAlignments.get(i) != alignment.getSA1()) {
                    ideals.remove(i);
                }
            }
            return ideals.get(rand.nextInt(ideals.size()));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public String initBond() {
        try {
            switch (background) {
                case ACOLYTE:
                    return randomTrait("bonds", "acolyteBonds");
                case CHARLATAN:
                    return randomTrait("bonds", "charlatanBonds");
                case CRIMINAL:
                    return randomTrait("bonds", "criminalBonds");
                case ENTERTAINER:
                    return randomTrait("bonds", "entertainerBonds");
                case FOLK_HERO:
                    return randomTrait("bonds", "folkHeroBonds");
                case GUILD_ARTISAN:
                    return randomTrait("bonds", "guildArtisanBonds");
                case HERMIT:
                    return randomTrait("bonds", "hermitBonds");
                case NOBLE:
                    return randomTrait("bonds", "nobleBonds");
                case OUTLANDER:
                    return randomTrait("bonds", "outlanderBonds");
                case SAGE:
                    return randomTrait("bonds", "sageBonds");
                case SAILOR:
                    return randomTrait("bonds", "sailorBonds");
                case SOLDIER:
                    return randomTrait("bonds", "soldierBonds");
                case URCHIN:
                    return randomTrait("bonds", "urchinBonds");
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            return "Bond";
        }
    }

    public String initFlaw() {
        try {
            switch (background) {
                case ACOLYTE:
                    return randomTrait("flaws", "acolyteFlaws");
                case CHARLATAN:
                    return randomTrait("flaws", "charlatanFlaws");
                case CRIMINAL:
                    return randomTrait("flaws", "criminalFlaws");
                case ENTERTAINER:
                    return randomTrait("flaws", "entertainerFlaws");
                case FOLK_HERO:
                    return randomTrait("flaws", "folkHeroFlaws");
                case GUILD_ARTISAN:
                    return randomTrait("flaws", "guildArtisanFlaws");
                case HERMIT:
                    return randomTrait("flaws", "hermitFlaws");
                case NOBLE:
                    return randomTrait("flaws", "nobleFlaws");
                case OUTLANDER:
                    return randomTrait("flaws", "outlanderFlaws");
                case SAGE:
                    return randomTrait("flaws", "sageFlaws");
                case SAILOR:
                    return randomTrait("flaws", "sailorFlaws");
                case SOLDIER:
                    return randomTrait("flaws", "soldierFlaws");
                case URCHIN:
                    return randomTrait("flaws", "urchinFlaws");
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            return "Flaw";
        }
    }

    public String randomTrait(String folder, String fileName) throws FileNotFoundException {
        try {
            Scanner fileInput = new Scanner(new File(filePath + folder + "\\" + fileName + ".txt"));
            ArrayList<String> traits = new ArrayList<>();
            while (fileInput.hasNext()) {
                traits.add(fileInput.nextLine());
            }
            return traits.get(rand.nextInt(traits.size()));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }
    
    public String[] initPersonalityTraits() {
      try {
            switch (background) {
                case ACOLYTE:
                    return randomPersonalityTraits("acolytePersonalityTraits");
                case CHARLATAN:
                    return randomPersonalityTraits("charlatanPersonalityTraits");
                case CRIMINAL:
                    return randomPersonalityTraits("criminalPersonalityTraits");
                case ENTERTAINER:
                    return randomPersonalityTraits("entertainerPersonalityTraits");
                case FOLK_HERO:
                    return randomPersonalityTraits("folkHeroPersonalityTraits");
                case GUILD_ARTISAN:
                    return randomPersonalityTraits("guildArtisanPersonalityTraits");
                case HERMIT:
                    return randomPersonalityTraits("hermitPersonalityTraits");
                case NOBLE:
                    return randomPersonalityTraits("noblePersonalityTraits");
                case OUTLANDER:
                    return randomPersonalityTraits("outlanderPersonalityTraits");
                case SAGE:
                    return randomPersonalityTraits("sagePersonalityTraits");
                case SAILOR:
                    return randomPersonalityTraits("sailorPersonalityTraits");
                case SOLDIER:
                    return randomPersonalityTraits("soldierPersonalityTraits");
                case URCHIN:
                    return randomPersonalityTraits("urchinPersonalityTraits");
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            return new String[]{"Personality Trait", "Personality Trait"};
        }
    }
    
    public String[] randomPersonalityTraits(String fileName) throws FileNotFoundException {
        try {
            Scanner fileInput = new Scanner(new File(filePath + "personalityTraits\\" + fileName + ".txt"));
            ArrayList<String> traits = new ArrayList<>();
            while (fileInput.hasNext()) {
                traits.add(fileInput.nextLine());
            }
            String[] returnTraits = new String[2];
            int i0 = rand.nextInt(traits.size());
            int i1 = rand.nextInt(traits.size());
            while (i1 == i0) {
                i1 = rand.nextInt(traits.size());
            }
            returnTraits[0] = traits.get(i0);
            returnTraits[1] = traits.get(i1);
            return returnTraits;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public ArrayList<String> getStartingEquipment(PlayerClass playerClass) {
        try {
            switch (firstClass) {
                case BARBARIAN:
                    return randomClassEquipment("barbarianClassEquipment");
                case BARD:
                    return randomClassEquipment("bardClassEquipment");
                case CLERIC:
                    return randomClassEquipment("clericClassEquipment");
                case DRUID:
                    return randomClassEquipment("druidClassEquipment");
                case FIGHTER:
                    return randomClassEquipment("fighterClassEquipment");
                case MONK:
                    return randomClassEquipment("monkClassEquipment");
                case PALADIN:
                    return randomClassEquipment("paladinClassEquipment");
                case RANGER:
                    return randomClassEquipment("rangerClassEquipment");
                case ROGUE:
                    return randomClassEquipment("rogueClassEquipment");
                case SORCERER:
                    return randomClassEquipment("sorcererClassEquipment");
                case WARLOCK:
                    return randomClassEquipment("warlockClassEquipment");
                case WIZARD:
                    return randomClassEquipment("wizardClassEquipment");
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            return new ArrayList<String>();
        }  
    }

    public ArrayList<String> randomClassEquipment(String fileName) throws FileNotFoundException {
        try {
            Scanner fileInput = new Scanner(new File(filePath + "equipment\\classEquipment\\" + fileName + ".txt"));
            ArrayList<String> equipment = new ArrayList<>();
            while (fileInput.hasNext()) {
                String line = fileInput.nextLine();
                if (line.contains("|")) {
                    line = decideEquipmentChoice(line);
                }
                if (line.contains("&")) {
                    String[] parts = line.split("&");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].contains("any(")) {
                            parts[i] = chooseEquipment(line);
                        }
                        equipment.add(parts[i]);
                    }
                } else {
                    if (line.contains("any(")) {
                        line = chooseEquipment(line);
                    }
                    equipment.add(line);
                }
            }
            return equipment;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    } 

    public ArrayList<String> getStartingEquipment(Background background) {
        return new ArrayList<String>();
    }

    public ArrayList<String> getStartingEquipment() {
        return new ArrayList<String>();
    }

    public String decideEquipmentChoice(String optionsText) {
        String[] options = optionsText.split("\\|");
        return options[rand.nextInt(options.length)];
    }

    public String chooseEquipment(String category) {
        try {
            category = category.substring(category.indexOf("any(") + 4, category.indexOf(")"));
            ArrayList<String> options = new ArrayList<>();
            options.addAll(getEquipmentList(category));
            String optionsText = "";
            for (int i = 0; i < options.size(); i++) {
                if (i < options.size() - 1) {
                    optionsText += options.get(i) + "|";
                } else {
                    optionsText += options.get(i);
                }
            }
            return decideEquipmentChoice(optionsText);
        } catch (FileNotFoundException e) {
            return "";
        }    
    }

    public ArrayList<String> getEquipmentList(String fileName) throws FileNotFoundException {
        try {
            Scanner fileInput = new Scanner(new File(filePath + "equipment\\equipmentLists\\" + fileName + ".txt"));
            ArrayList<String> equipment = new ArrayList<>();
            while (fileInput.hasNext()) {
                equipment.add(fileInput.nextLine());
            }
            return equipment;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    } 

    public String getName() {
        return name;
    }

    public PlayerClass[] getClasses() {
        return classes;
    }

    public int[] getClassLevels() {
        return classLevels;
    }

    public Background getBackground() {
        return background;
    }

    public Race getRace() {
        return race;
    }

    public Gender getGender() {
        return gender;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public int getXP() {
        return xp;
    }

    public int[] getAttributes() {
        return attributes;
    }

    public int getAttribute(int i) {
        return attributes[i];
    }

    public int[] getAttributeModifiers() {
        int[] attributeModifiers = new int[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            attributeModifiers[i] = (int) Math.floor((((double) attributes[i]) - 10) / 2);
        }
        return attributeModifiers;
    }

    public String getAttributeModifierString(int i) {
        int attributeModifier = (int) Math.floor((((double) attributes[i]) - 10) / 2);
        String attributeModifierString = attributeModifier > 0 ? "+" + attributeModifier : Integer.toString(attributeModifier);
        return attributeModifierString;
    }

    public int getPassiveWisdom() {
        return passiveWisdom;
    }

    public int getProficienyBonus() {
        return proficienyBonus;
    }

    public boolean[] getSavingThrowProfs() {
        return savingThrowProfs;
    }

    public boolean[] getSkillProfs() {
        return skillProfs;
    }

    public Language[] getLanguages() {
        return languages;
    }

    public String getProfs() {
        return proficiencies;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public String[] getAttacks() {
        return attacks;
    }

    public int[] getAttackBonus() {
        return attackBonus;
    }

    public Dice[] getAttackDamage() {
        return attackDamage;
    }

    public int[] getCoins() {
        return coins;
    }

    public ArrayList<String> getEquipment() {
        return equipment;
    }

    public String getEquipmentFormatted() {
        String equipmentText = "";
        for (int i = 0; i < equipment.size(); i++) {
            if (i < equipment.size() - 1) {
                equipmentText += equipment.get(i) + "\n";
            } else {
                equipmentText += equipment.get(i);
            }
        }
        return equipmentText;
    }

    public String[] getPersonalityTraits() {
        return personalityTraits;
    }

    public String getIdeal() {
        return ideal;
    }

    public String getBond() {
        return bond;
    }

    public String getFlaw() {
        return flaw;
    }

    public String[] getFeatures() {
        return features;
    }

    public static Random getRand() {
        return rand;
    }

    public String getFilePath() {
        return filePath;
    }
}
