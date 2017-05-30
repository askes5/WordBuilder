import java.util.HashSet;
import java.util.Set;

/**
 * Defines a word builder that generates sets of words from given parameters.<br>
 *     O(n!/(n-k)!) n = character , k = word length
 * <ul>
 *     <li>Words do not have any repeated characters, i.e each character used only once</li>
 *     <li>Words are of a fixed length</li>
 *     <li>Words only contains characters from a specified set</li>
 * </ul>
 * @author Askes
 */
public class WordBuilderPermutations {

    /**
     * defines the set of allowed characters in the words
     */
    public final char[] CHARACTERS;

    /**
     * defines the length of the words
     */
    public final int WORD_LENGTH;

    /**
     * creates a new word builder that finds all permutations of characters with a given length
     * @param CHARACTERS the characters to use in generating words
     * @param WORD_LENGTH the length of the words
     */
    public WordBuilderPermutations(char[] CHARACTERS, int WORD_LENGTH) {
        this.CHARACTERS = CHARACTERS;
        this.WORD_LENGTH = WORD_LENGTH;
    }

    /**
     * Finds all the words that do not contain any of the given strings
     * @param strings the forbidden strings
     * @return the set of all the words that do not contain any of the given strings
     */
    public Set<String> getAllWordsExcluding(String... strings){
        Set<String> allWords = getAllWords();
        Set<String> wordsExcluding = new HashSet<>();
        for (String word : allWords) {
            boolean containsForbidden = false;
            for (String string : strings) {
                if (word.contains(string)){
                    containsForbidden = true;
                }
            }
            if (!containsForbidden){
                wordsExcluding.add(word);
            }
        }
        return wordsExcluding;
    }

    public Set<String> getAllWords(){
        Set<String> words = new HashSet<>();
        permutation(new char[WORD_LENGTH], 0 ,new String(CHARACTERS),words);
        return words;
    }

    /**
     * recessive part of getting all words (i,e, permutations)
     * @param perm a char array to store the current permutation, the length of this determines the length of words generated
     * @param pos the current position
     * @param words a set to store all the generated words in
     */
    private void permutation(char[] perm, int pos, String str, Set<String> words) {
        if (pos == perm.length) {
            words.add(new String(perm));
        } else {
            for (int i = 0 ; i < str.length() ; i++) {
                perm[pos] = str.charAt(i);
                StringBuilder newString = new StringBuilder(str);
                newString.deleteCharAt(i);
                permutation(perm, pos+1, newString.toString(), words);
            }
        }
    }

    public static void main(String args[]){
        WordBuilderPermutations wordBuilder = new WordBuilderPermutations("abcdefghijklmnopqrstuvwxyz".toCharArray(),4);
        System.out.println(wordBuilder.getAllWordsExcluding("be","lap","scum", "third").size());
    }

}
