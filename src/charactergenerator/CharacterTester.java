package charactergenerator;

public class CharacterTester {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Character character = new Character();
            System.out.printf("Character %d:%n%s%n%s %s%n%s %s %d%n%n", i, character.getName(), character.getGender(), character.getRace(), character.getAlignment(), character.getClasses()[0], character.getClassLevels()[0]);
            CharacterWriter.write(character);
        }
    }
}
