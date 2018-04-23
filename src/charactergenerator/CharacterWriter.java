package charactergenerator;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public abstract class CharacterWriter {

    private static String filePath = System.getProperty("user.dir") + "\\src\\resources\\";

    public static void write(Character character) {
        try {
            PDDocument doc = new PDDocument().load(new File(filePath + "blankCharacterSheet.pdf"));
            List<PDField> fields = doc.getDocumentCatalog().getAcroForm().getFields();
            int score;
            for (PDField field : fields) {
                switch (field.getPartialName()) {
                    case "CharacterName":
                        field.setValue(character.getName());
                        break;
                    case "Race":
                        field.setValue(character.getRace().toString());
                        break;
                    case "Alignment":
                        field.setValue(character.getAlignment().toString());
                        break;
                    case "ClassLevel":
                        field.setValue(character.getClasses()[0].toString() + " " + character.getClassLevels()[0]);
                        break;
                    case "Background":
                        field.setValue(character.getBackground().toString());
                        break;
                    case "STR":
                        field.setValue(character.getAttributeModifierString(0));
                        break;
                    case "DEX":
                        field.setValue(character.getAttributeModifierString(1));
                        break;
                    case "CON":
                        field.setValue(character.getAttributeModifierString(2));
                        break;
                    case "INT":
                        field.setValue(character.getAttributeModifierString(3));
                        break;
                    case "WIS":
                        field.setValue(character.getAttributeModifierString(4));
                        break;
                    case "CHA":
                        field.setValue(character.getAttributeModifierString(5));
                        break;
                    case "STRscore":
                        field.setValue(Integer.toString(character.getAttribute(0)));
                        break;
                    case "DEXscore":
                        field.setValue(Integer.toString(character.getAttribute(1)));
                        break;
                    case "CONscore":
                        field.setValue(Integer.toString(character.getAttribute(2)));
                        break;
                    case "INTscore":
                        field.setValue(Integer.toString(character.getAttribute(3)));
                        break;
                    case "WISscore":
                        field.setValue(Integer.toString(character.getAttribute(4)));
                        break;
                    case "CHAscore":
                        field.setValue(Integer.toString(character.getAttribute(5)));
                        break;
                    case "Inspiration":
                        field.setValue("-");
                        break;
                    case "ProfBonus":
                        field.setValue("+" + character.getProficienyBonus());
                        break;
                    case "Initiative":
                        field.setValue(character.getAttributeModifierString(1));
                        break;
                    case "Speed":
                        field.setValue(Integer.toString(character.getSpeed()));
                        break;
                    case "STRstProf":
                        field.setValue(character.getSavingThrowProfs()[0] ? "Yes" : "Off");
                        break;
                    case "DEXstProf":
                        field.setValue(character.getSavingThrowProfs()[1] ? "Yes" : "Off");
                        break;
                    case "CONstProf":
                        field.setValue(character.getSavingThrowProfs()[2] ? "Yes" : "Off");
                        break;
                    case "INTstProf":
                        field.setValue(character.getSavingThrowProfs()[3] ? "Yes" : "Off");
                        break;
                    case "WISstProf":
                        field.setValue(character.getSavingThrowProfs()[4] ? "Yes" : "Off");
                        break;
                    case "CHAstProf":
                        field.setValue(character.getSavingThrowProfs()[5] ? "Yes" : "Off");
                        break;
                    case "ST Strength":
                        score = character.getSavingThrowProfs()[0] ? character.getProficienyBonus() + character.getAttributeModifiers()[0] : character.getAttributeModifiers()[0];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "ST Dexterity":
                        score = character.getSavingThrowProfs()[1] ? character.getProficienyBonus() + character.getAttributeModifiers()[1] : character.getAttributeModifiers()[1];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "ST Constitution":
                        score = character.getSavingThrowProfs()[2] ? character.getProficienyBonus() + character.getAttributeModifiers()[2] : character.getAttributeModifiers()[2];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "ST Intelligence":
                        score = character.getSavingThrowProfs()[3] ? character.getProficienyBonus() + character.getAttributeModifiers()[3] : character.getAttributeModifiers()[3];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "ST Wisdom":
                        score = character.getSavingThrowProfs()[4] ? character.getProficienyBonus() + character.getAttributeModifiers()[4] : character.getAttributeModifiers()[4];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "ST Charisma":
                        score = character.getSavingThrowProfs()[5] ? character.getProficienyBonus() + character.getAttributeModifiers()[5] : character.getAttributeModifiers()[5];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Check Box 23":
                        field.setValue(character.getSkillProfs()[0] ? "Yes" : "Off");
                        break;
                    case "Check Box 24":
                        field.setValue(character.getSkillProfs()[1] ? "Yes" : "Off");
                        break;
                    case "Check Box 25":
                        field.setValue(character.getSkillProfs()[2] ? "Yes" : "Off");
                        break;
                    case "Check Box 26":
                        field.setValue(character.getSkillProfs()[3] ? "Yes" : "Off");
                        break;
                    case "Check Box 27":
                        field.setValue(character.getSkillProfs()[4] ? "Yes" : "Off");
                        break;
                    case "Check Box 28":
                        field.setValue(character.getSkillProfs()[5] ? "Yes" : "Off");
                        break;
                    case "Check Box 29":
                        field.setValue(character.getSkillProfs()[6] ? "Yes" : "Off");
                        break;
                    case "Check Box 30":
                        field.setValue(character.getSkillProfs()[7] ? "Yes" : "Off");
                        break;
                    case "Check Box 31":
                        field.setValue(character.getSkillProfs()[8] ? "Yes" : "Off");
                        break;
                    case "Check Box 32":
                        field.setValue(character.getSkillProfs()[9] ? "Yes" : "Off");
                        break;
                    case "Check Box 33":
                        field.setValue(character.getSkillProfs()[10] ? "Yes" : "Off");
                        break;
                    case "Check Box 34":
                        field.setValue(character.getSkillProfs()[11] ? "Yes" : "Off");
                        break;
                    case "Check Box 35":
                        field.setValue(character.getSkillProfs()[12] ? "Yes" : "Off");
                        break;
                    case "Check Box 36":
                        field.setValue(character.getSkillProfs()[13] ? "Yes" : "Off");
                        break;
                    case "Check Box 37":
                        field.setValue(character.getSkillProfs()[14] ? "Yes" : "Off");
                        break;
                    case "Check Box 38":
                        field.setValue(character.getSkillProfs()[15] ? "Yes" : "Off");
                        break;
                    case "Check Box 39":
                        field.setValue(character.getSkillProfs()[16] ? "Yes" : "Off");
                        break;
                    case "Check Box 40":
                        field.setValue(character.getSkillProfs()[17] ? "Yes" : "Off");
                        break;
                    case "Acrobatics":
                        score = character.getSkillProfs()[0] ? character.getProficienyBonus() + character.getAttributeModifiers()[1] : character.getAttributeModifiers()[1];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Animal":
                        score = character.getSkillProfs()[1] ? character.getProficienyBonus() + character.getAttributeModifiers()[4] : character.getAttributeModifiers()[4];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Arcana":
                        score = character.getSkillProfs()[2] ? character.getProficienyBonus() + character.getAttributeModifiers()[3] : character.getAttributeModifiers()[3];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Athletics":
                        score = character.getSkillProfs()[3] ? character.getProficienyBonus() + character.getAttributeModifiers()[0] : character.getAttributeModifiers()[0];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Deception":
                        score = character.getSkillProfs()[4] ? character.getProficienyBonus() + character.getAttributeModifiers()[5] : character.getAttributeModifiers()[5];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "History":
                        score = character.getSkillProfs()[5] ? character.getProficienyBonus() + character.getAttributeModifiers()[3] : character.getAttributeModifiers()[3];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Insight":
                        score = character.getSkillProfs()[6] ? character.getProficienyBonus() + character.getAttributeModifiers()[4] : character.getAttributeModifiers()[4];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Intimidation":
                        score = character.getSkillProfs()[7] ? character.getProficienyBonus() + character.getAttributeModifiers()[5] : character.getAttributeModifiers()[5];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Investigation":
                        score = character.getSkillProfs()[8] ? character.getProficienyBonus() + character.getAttributeModifiers()[3] : character.getAttributeModifiers()[3];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Medicine":
                        score = character.getSkillProfs()[9] ? character.getProficienyBonus() + character.getAttributeModifiers()[4] : character.getAttributeModifiers()[4];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Nature":
                        score = character.getSkillProfs()[10] ? character.getProficienyBonus() + character.getAttributeModifiers()[3] : character.getAttributeModifiers()[3];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Perception":
                        score = character.getSkillProfs()[11] ? character.getProficienyBonus() + character.getAttributeModifiers()[4] : character.getAttributeModifiers()[4];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Performance":
                        score = character.getSkillProfs()[12] ? character.getProficienyBonus() + character.getAttributeModifiers()[5] : character.getAttributeModifiers()[5];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Persuasion":
                        score = character.getSkillProfs()[13] ? character.getProficienyBonus() + character.getAttributeModifiers()[5] : character.getAttributeModifiers()[5];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Religion":
                        score = character.getSkillProfs()[14] ? character.getProficienyBonus() + character.getAttributeModifiers()[3] : character.getAttributeModifiers()[3];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "SleightofHand":
                        score = character.getSkillProfs()[15] ? character.getProficienyBonus() + character.getAttributeModifiers()[1] : character.getAttributeModifiers()[1];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Stealth":
                        score = character.getSkillProfs()[16] ? character.getProficienyBonus() + character.getAttributeModifiers()[1] : character.getAttributeModifiers()[1];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Survival":
                        score = character.getSkillProfs()[17] ? character.getProficienyBonus() + character.getAttributeModifiers()[4] : character.getAttributeModifiers()[4];
                        field.setValue( score > 0 ? "+" + score : Integer.toString(score));
                        break;
                    case "Passive":
                        score = 10 + (character.getSkillProfs()[11] ? character.getProficienyBonus() + character.getAttributeModifiers()[4] : character.getAttributeModifiers()[4]);
                        field.setValue(Integer.toString(score));
                        break;
                    case "HDTotal":
                    case "HD":
                        field.setValue(character.getHitDice().toString());
                        break;
                    case "HPMax":
                    case "HPCurrent":
                        field.setValue(Integer.toString(character.getHitPoints()));
                        break;
                    case "XP":
                        field.setValue(Integer.toString(character.getXP()));
                        break;
                    case "ProficienciesLang":
                        field.setValue("Languages: " + Arrays.toString(character.getLanguages()).substring(1, Arrays.toString(character.getLanguages()).length() - 1));
                        break;
                    case "Ideals":
                        field.setValue(character.getIdeal());
                        break;
                    case "Bonds":
                        field.setValue(character.getBond());
                        break;
                    case "Flaws":
                        field.setValue(character.getFlaw());
                        break;
                    case "PersonalityTraits":
                        field.setValue("1) " + character.getPersonalityTraits()[0] + "\n2) " + character.getPersonalityTraits()[1]);
                        break;
                }
            }
            doc.getDocumentCatalog().getAcroForm().refreshAppearances();
            doc.save(filePath + "\\generatedCharacters\\" + character.getName() + ".pdf");
            doc.close();
        } catch (Exception e) {
            System.out.println("Ahh! Error!");
        }
    }
}
