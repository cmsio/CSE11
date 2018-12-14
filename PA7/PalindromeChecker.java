
public class PalindromeChecker{
    
    public static void main (String[] args){
    	String s = "$ab%uha*$";
    	System.out.println(PalindromeChecker.filter(s));

    }
    
	public static String filter(String phrase) {
		String newPhrase = "";
	    for (int i = 0; i < phrase.length(); i++){
            // this one is make sure the phrase is in the range
	        if(phrase.charAt(i) >= 'a' && phrase.charAt(i) <= 'z'){
	            newPhrase += phrase.charAt(i);
	        }
	    }
	    return newPhrase;
	}
    

    public static boolean isPalindrome(String phrase, int low, int high) {
        if (low >= high){
            return true;
        } else {
            if (phrase.charAt(low) == phrase.charAt(high)){
                return isPalindrome(phrase, ++low, --high);
            } else {
                return false;
            }
        }
    }
    
    public static boolean isPalindrome(String phrase) {
    	if (phrase == null || phrase.length() <= 1){
    		return true;
    	} else {
    		char first = phrase.charAt(0);
    		char last = phrase.charAt(phrase.length() - 1);
    		if (first == last) {
    			String temp = phrase.substring(1, phrase.length() - 1);
    			return isPalindrome(temp);
    		} else {
    			return false;
    		}
    	}
    }
}

                            

    
