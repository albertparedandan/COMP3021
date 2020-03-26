package lab5;
import java.util.Scanner;
import java.util.Random;
import java.util.Date;

public class StringLab {
    // use a static Scanner object, close at the end of main()
    // if create a new scanner object for each method and then close it before leaving the method, the input stream will be closed, and issues will pop up when you return back to main to display the menu again
    static Scanner sc=new Scanner(System.in);

    public static void runLengthEncode(){
        //
        // 1. Count the characters, if the frequency of a character is bigger than 1, then display frequency and then the character.
        //    Otherwise if the frequency of a character is 1, just display the original character without any digit in front of the
        //    character.
        // 2. Assume user input only lower case letters.
        // 3. Assume no spaces in the middle of the input, there is no need to check if the entered string composed of letters only,
        //    assume user inputs are always correct.
        // 4. To avoid possible issue in the scanner object input stream. when you read from the input (sc), always remember to use sc.nextLine().
        System.out.println("Please enter your string");
        String input = sc.nextLine();
        String encodedString = "";

        for (int i = 0, count = 1; i < input.length(); i++) {
            if (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1))
                count++;
            else {
                if(count > 1) {
                    encodedString = encodedString.concat(Integer.toString(count));
                }
                encodedString = encodedString.concat(Character.toString(input.charAt(i)));
                count = 1;
            }
        }

        System.out.println("The encoded String is");
        System.out.println(encodedString);

    }


    public static void convertBinaryToHex(){
        // 1. No need to check if the input string is in binary, assume input is correct and always a binary sequence
        // 2. The basic procedure is first to see if the input binary string could be grouped into groups of 4 binary digits.
        //    And then if the binary string contains number of digits that is not a multiple of 4, you need to zero extend binary
        //    string so that the number of digits in the extended binary string becomes a multiple of 4.
        //    ie. suppose input string is "10101", it has 5 digits, not a multiple of 4, you need to extend it to "00010101",
        //    you may find the "%" operator useful for this task
        // 3. Then you group the binary string into groups of 4 binary digits and then use the following hexValues[] array
        //    to find the corresponding hexadecimal value represented by each 4-digit binary group

        // The following hexValues[] array is provided to you, you can decide to use it or not to use it
        char[] hexValues={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        System.out.println("Please enter your string");
        String input = sc.nextLine();
        String lastBlock= "";
        String result = "";


        //separate into blocks of 4
        for (int i = 0; i < (input.length()/4); i++) {
            //System.out.println(input.substring(((input.length()-1)-(4*i))-3,((input.length()-1)-(4*i))+1));
            String test = input.substring(((input.length()-1)-(4*i))-3,((input.length()-1)-(4*i))+1);

            //which binary?
            if (test.equals("0000")) {
                result = Character.toString(hexValues[0]) + result;
            }
            else if (test.equals("0001")) {
                result = Character.toString(hexValues[1]) + result;
            }
            else if (test.equals("0010")) {
                result = Character.toString(hexValues[2]) + result;
            }
            else if (test.equals("0011")) {
                result = Character.toString(hexValues[3]) + result;
            }
            else if (test.equals("0100")) {
                result = Character.toString(hexValues[4]) + result;
            }
            else if (test.equals("0101")) {
                result = Character.toString(hexValues[5]) + result;
            }
            else if (test.equals("0110")) {
                result = Character.toString(hexValues[6]) + result;
            }
            else if (test.equals("0111")) {
                result = Character.toString(hexValues[7]) + result;
            }
            else if (test.equals("1000")) {
                result = Character.toString(hexValues[8]) + result;
            }
            else if (test.equals("1001")) {
                result = Character.toString(hexValues[9]) + result;
            }
            else if (test.equals("1010")) {
                result = Character.toString(hexValues[10]) + result;
            }
            else if (test.equals("1011")) {
                result = Character.toString(hexValues[11]) + result;
            }
            else if (test.equals("1100")) {
                result = Character.toString(hexValues[12]) + result;
            }
            else if (test.equals("1101")) {
                result = Character.toString(hexValues[13]) + result;
            }
            else if (test.equals("1110")) {
                result = Character.toString(hexValues[14]) + result;
            }
            else if (test.equals("1111")) {
                result = Character.toString(hexValues[15]) + result;
            }

            lastBlock = input.substring(0,((input.length()-1)-(4*i))-3);
        }
        if (lastBlock.length() != 0) {
            if (lastBlock.length() == 1) {
                lastBlock = "000" + lastBlock;
            }
            else if (lastBlock.length() == 2) {
                lastBlock = "00" + lastBlock;
            }
            else {
                lastBlock = "0" + lastBlock;
            }
        }

        if (lastBlock.equals("0000")) {
            result = Character.toString(hexValues[0]) + result;
        }
        else if (lastBlock.equals("0001")) {
            result = Character.toString(hexValues[1]) + result;
        }
        else if (lastBlock.equals("0010")) {
            result = Character.toString(hexValues[2]) + result;
        }
        else if (lastBlock.equals("0011")) {
            result = Character.toString(hexValues[3]) + result;
        }
        else if (lastBlock.equals("0100")) {
            result = Character.toString(hexValues[4]) + result;
        }
        else if (lastBlock.equals("0101")) {
            result = Character.toString(hexValues[5]) + result;
        }
        else if (lastBlock.equals("0110")) {
            result = Character.toString(hexValues[6]) + result;
        }
        else if (lastBlock.equals("0111")) {
            result = Character.toString(hexValues[7]) + result;
        }
        else if (lastBlock.equals("1000")) {
            result = Character.toString(hexValues[8]) + result;
        }
        else if (lastBlock.equals("1001")) {
            result = Character.toString(hexValues[9]) + result;
        }
        else if (lastBlock.equals("1010")) {
            result = Character.toString(hexValues[10]) + result;
        }
        else if (lastBlock.equals("1011")) {
            result = Character.toString(hexValues[11]) + result;
        }
        else if (lastBlock.equals("1100")) {
            result = Character.toString(hexValues[12]) + result;
        }
        else if (lastBlock.equals("1101")) {
            result = Character.toString(hexValues[13]) + result;
        }
        else if (lastBlock.equals("1110")) {
            result = Character.toString(hexValues[14]) + result;
        }
        else if (lastBlock.equals("1111")) {
            result = Character.toString(hexValues[15]) + result;
        }

        System.out.println("The corresponding Hexadecimal number is");
        System.out.println("0x" + result);
    }


    public static void cipher(){
        //1. Set up a random object, use the random object to generate a random value uniformly distributed in the range 0-25.
        //2. Shift each character of the user input string using the random value found in 1.
        //3. Assume English letters are in 'A'-'Z' or 'a'-'z'. Ignore any non English letter character (i.e. do not shift it).
        //4. Make sure the shifted character is still in 'A'-'Z' or 'a'-'z'.
        //5. You can compare the ascii values of two characters by using the comparison operators,
        //   For example you can do the check ('a'<'b'), and Java will return true because 'b' has bigger ASCII value than 'a'
        //   You can also see the difference between characters 'a' and 'b' by the statement 'b'-'a' (this will give you 1).
        System.out.println("Please enter your string");
        Random rand = new Random();
        int n = rand.nextInt(26);
        String input = sc.nextLine();
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isUpperCase(charArray[i])) {
                int dummy = charArray[i]+n;
                if (dummy>90) {
                    dummy = 64+(dummy-90);
                }
                charArray[i] = (char)dummy;
            }
            else if (Character.isLowerCase(charArray[i])) {
                int dummy = charArray[i]+n;
                if (dummy>122) {
                    dummy = 96+(dummy-122);
                }
                charArray[i] = (char)dummy;
            }
        }
        String result = new String(charArray);
        System.out.println("The corresponding encoded string after shifting for " + n + " digits is");
        System.out.println(result);
    }

    public static void countMostFreqChar(){
        // 1. This method is case insensitive, ie. 'A' is the same as 'a'.
        // 2. This method counts only 'A'-'Z', or 'a'-'z' in the input string.
        // 3. If more than one most frequent characters, this method will output the one that appears the earliest in the string.
        // 4. if it is a numerical character or a symbol, ignore it and do not count its frequency.
        System.out.println("Please enter your string");

        String input = sc.nextLine();
        input = input.toUpperCase();
        input = input.replaceAll("[^a-zA-Z]", "");
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int [] count = new int[26];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        char [] c = input.toCharArray();
        for (char x : c) {
            for (int i = 0; i < alphabet.length(); i++) {
                if (alphabet.charAt(i) == x) {
                    count[i]++;
                }
            }
        }

        int maxCount = 0;
        char maxLetter = Character.MIN_VALUE;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                maxLetter = alphabet.charAt(i);
            }
        }

        System.out.println("The most frequent character is " + maxLetter + " and it appears for " + maxCount + " times");
    }

    public static void main(String[] args){
        String input="";

        while (!input.equals("Q")) {
            System.out.println("=================String Operations================");
            System.out.println("1. Run Length Encoder ");
            System.out.println("2. Convert binary string to Hexadecimal");
            System.out.println("3. Caesar cipher with a random shift");
            System.out.println("4. Count the most frequent character in a string");
            System.out.println("=================String Operations=================");
            System.out.println("Please enter 1,2,3 or 4 to select an option");
            System.out.println("Enter \"Q\" to terminate the program");
            input = sc.nextLine();

            switch (input) {
                case "1":
                    runLengthEncode();
                    break;
                case "2":
                    convertBinaryToHex();
                    break;
                case "3":
                    cipher();
                    break;
                case "4":
                    countMostFreqChar();
                    break;
                case "Q":
                    break;
                default:
                    break;

            }

        }
        sc.close();
    }


}