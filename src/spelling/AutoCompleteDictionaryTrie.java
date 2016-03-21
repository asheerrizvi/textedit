package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		word = word.toLowerCase();
		TrieNode node = root;
		for(int i = 0; i < word.length(); i++)
		{
			if(node.getChild(word.charAt(i)) == null)
			{
				node = node.insert(word.charAt(i));
			}
			else if(node.getChild(word.charAt(i)) != null && (node.getChild(word.charAt(i)).getText().equals(word)) && (node.getChild(word.charAt(i))).endsWord() != true)
			{
				node = node.getChild(word.charAt(i));
				node.setEndsWord(true);
				size++;
				return true;
			}
			else
			{
				node = node.getChild(word.charAt(i));
				if((node.getText()).equals(word))
					return false;
			}
		}
		node.setEndsWord(true);
		size++;
		return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s.length() == 0)
			return false;
		s = s.toLowerCase();
		TrieNode node = root;
		for(int i = 0; i < s.length(); i++)
		{
			if(node.getChild(s.charAt(i)) != null)
			{
				node = node.getChild(s.charAt(i));
			}
		}
		if((node.getText()).equals(s) && node.endsWord() == true)
			return true;
		else
			return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 TrieNode node = root;
    	 for(int i = 0; i < prefix.length(); i++)
    	 {
    		 if(node.getChild(prefix.charAt(i)) != null)
    		 {
    			 node = node.getChild(prefix.charAt(i));
    		 }
    		 else
    		 {
    			 LinkedList<String> list = new LinkedList<String>();
    			 return list;
    		 }
    	 }
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.addLast(node);
    	 LinkedList<String> completions = new LinkedList<String>();
    	 while(queue.size() != 0 && completions.size() < numCompletions)
    	 {
    		 TrieNode curr = queue.removeFirst();
    		 if(curr.endsWord())
    		 {
    			 completions.add(curr.getText());
    		 }
    		 Set<Character> set = curr.getValidNextCharacters();
			 for(Character c:set)
			 {
				 if(curr.getChild(c) != null)
				 {
					 queue.addLast(curr.getChild(c));
				 }
			 }
    	 }
         return completions;
     }
     
     
    // For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}