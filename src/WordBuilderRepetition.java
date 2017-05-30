import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Defines a word builder that builds different sets of words from given parameters.
 * <ul>
 *     <li>Words can have repeated characters</li>
 *     <li>Words are of a fixed length</li>
 *     <li>Words are generated from a specific set of characters</li>
 * </ul>
 * @author Askes
 */
public class WordBuilderRepetition {

    public static final char[] ALPHEBET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * defines the set of CHARACTERS that can be used to create words
     */
    public final char[] CHARACTERS;
    /**
     * defines the number of CHARACTERS in our word
     */
    public final int WORD_LENGTH;

    /**
     * creates a new Word builder
     * @param characters the possible CHARACTERS to be used in the word
     * @param wordLength the length of the word
     */
    public WordBuilderRepetition(char[] characters, int wordLength) {
        this.CHARACTERS = characters;
        this.WORD_LENGTH = wordLength;
    }



    /**
     * counts the total number of possible words that do not have any consecutive CHARACTERS
     * @return the number of possible words that do not have any consecutive words
     */
    public long countNoConsecutive(){
        return getAllWordsNoConsecutive().size();
    }

    /**
     * gets all of the possible words that do not have any consecutive CHARACTERS
     * @return a set of all the possible words that do not have any consecutive CHARACTERS
     */
    public Set<String> getAllWordsNoConsecutive(){
        Set<String> allPossibleWords = getAllPossibleWords();

        Set<String> wordsNoConsecutive = new HashSet<>();

        for (String word : allPossibleWords) {
            boolean hasConsecutive = false;
            for (int i = 0; i < word.length()-1; i++) {
                if (word.charAt(i) == word.charAt(i+1)){
                    hasConsecutive = true;
                    break;
                }
            }
            if (!hasConsecutive) wordsNoConsecutive.add(word);
        }
        return wordsNoConsecutive;
    }

    /**
     * find all the words that do not contain any sub strings equal to the provided strings
     * @param strings that strings that are forbidden in the words
     * @return a set of words that do not conatin any of the given sub strings
     */
    public Set<String> getAllWordsExcluding(Set<String> strings){
        Set<String> allWords = getAllPossibleWords();
        Set<String> wordsExcludingForbidden = new HashSet<>();

        for (String word : allWords) {
            boolean containsString = false;
            for (String string : strings) {
                if (word.contains(string)) containsString = true;
            }
            if (!containsString) wordsExcludingForbidden.add(word);
        }
        return wordsExcludingForbidden;
    }

    /**
     * calculates all the possible words that can be made
     * @return a set of all possible words
     */
    public Set<String> getAllPossibleWords(){
        Set<String> possibleWords = new HashSet<>();
        iterate(CHARACTERS,WORD_LENGTH,new char[WORD_LENGTH],0,possibleWords);

        return possibleWords;
    }

    /**
     * iterate through and find all possible combinations of a given set with a given length.<br>
     * note: is primarily a helper for getAllPossibleWords().
     * @param chars the possible CHARACTERS
     * @param len the length of the word
     * @param build an array to store the word in
     * @param pos the current position in the generated word
     * @param words a set that all the words will be added to
     */
    private static void iterate(char[] chars, int len, char[] build, int pos, Set<String> words) {
        if (pos == len) {
            String word = new String(build);
            words.add(word);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            build[pos] = chars[i];
            iterate(chars, len, build, pos + 1,words);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordBuilderRepetition that = (WordBuilderRepetition) o;
        return WORD_LENGTH == that.WORD_LENGTH &&
                Arrays.equals(CHARACTERS, that.CHARACTERS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CHARACTERS, WORD_LENGTH);
    }

    public static void main(String args[]){
        WordBuilderRepetition wordBuilder = new WordBuilderRepetition(new char[]{'A','B','C'},5);
        System.out.println("Words with no consectitive letters: " + wordBuilder.countNoConsecutive());
//        System.out.println(wordBuilder.g);
    }

}
