import java.util.HashSet;
import java.util.Set;

/**
 * defines a word builder that builds different sets of words from given parameters
 * @author Askes
 */
public class WordBuilder {

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
    public WordBuilder(char[] characters, int wordLength) {
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

    public static void main(String args[]){
        WordBuilder wordBuilder = new WordBuilder(new char[]{'A','B','C'},5);
        System.out.println(wordBuilder.countNoConsecutive());
    }

}
