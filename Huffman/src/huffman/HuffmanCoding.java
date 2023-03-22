package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import javax.print.DocFlavor.CHAR_ARRAY;
import javax.xml.stream.events.Characters;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
//StdIn.setFile(fileName);      
//ArrayList<CharFreq> sortedCharFreqList = new ArrayList<>(); already have an array list initialized up top

//int[] myIntArray = new int[128];
//int totalNumChar = 0;
//this.sortedCharFreqList = new ArrayList<CharFreq>();

//create an integer array
//arr[nextchar]++;
//totalchar++;

//temp lowest
//temp lowestIndex
//look for one with lowest freq of occorucnce
//-------------------------------------------------------------



//----------------------------------------------------------------------------------------
StdIn.setFile(fileName);
int c = 0;
ArrayList<CharFreq> one = new ArrayList<>();
ArrayList<Character> two = new ArrayList();

while(StdIn.hasNextChar()){

    char letter = StdIn.readChar();
    if(two.contains(letter)){
        for(int a = 0; a < one.size(); a++){
            if(one.get(a).getCharacter().equals(letter)){
                double another = one.get(a).getProbOcc() + 1.00;
                one.get(a).setProbOcc(another);
            }
        }
    }
    if(one.isEmpty() || !(two.contains(letter))){
        CharFreq wMan = new CharFreq(letter, 1);
        one.add(wMan);
        two.add(letter);
    }
 
    c++;
}

    for(int a = 0; a < one.size(); a++){                         
       double diviser = one.get(a).getProbOcc() /c;
       one.get(a).setProbOcc(diviser);
       
    }



                //for the input 2 so that we get b=0
    if(one.size()==1){
        char ye = one.get(0).getCharacter();
        int nah = (int) ye;
        if(nah==127){
            nah=0;
        }else{
            nah=nah+1;
        }
        ye = (char)nah;

        CharFreq yeet=new CharFreq(ye, 0);
        one.add(yeet);

        
        }

    Collections.sort(one);
    sortedCharFreqList = one;
}

//----------------------------------------------------


	/* Your code goes here */
    

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {
        CharFreq swap= new CharFreq();
        TreeNode one = new TreeNode();
        TreeNode two= new TreeNode();

        Queue<TreeNode> s = new Queue<TreeNode>();
        Queue<TreeNode> t = new Queue<TreeNode>();

         for(int a=0; a<sortedCharFreqList.size();a++){
            one = new TreeNode();
            one.setData(sortedCharFreqList.get(a));
            s.enqueue(one);
        } 
        while(t.size() != 1 || !s.isEmpty()){
            TreeNode prava = new TreeNode();
            TreeNode leva= new TreeNode();
 
 
            if(t.size()==0){
                leva = s.peek();
                s.dequeue();
                prava = s.peek();
                s.dequeue();
                 } else if(s.size()==0) {

                    leva = t.peek();
                    t.dequeue();

                    prava = t.peek();
                    t.dequeue();
            } else

                    if(s.peek().getData().getProbOcc()==t.peek().getData().getProbOcc() || s.peek().getData().compareTo(t.peek().getData()) <= 0){
                   
                    leva = s.peek();
                    s.dequeue();

                    if(s.isEmpty()){
                    prava = t.peek();
                    t.dequeue();

                        } else if(s.peek().getData().getProbOcc()==t.peek().getData().getProbOcc() || s.peek().getData().compareTo(t.peek().getData()) <= 0){
                            prava = s.peek();
                            s.dequeue();

                } else {
                    prava = t.peek();
                    t.dequeue();
                }

            } else {
                 leva = t.dequeue();

                if(t.isEmpty()){
                    prava = s.peek();
                    s.dequeue();
                } else if(s.peek().getData().getProbOcc()==t.peek().getData().getProbOcc() || s.peek().getData().compareTo(t.peek().getData()) <= 0){
                    prava = s.peek();
                    s.dequeue();

                } else {
                    prava = t.peek();
                    t.dequeue();
                }
            }

            swap= new CharFreq();
            swap.setProbOcc(prava.getData().getProbOcc()+leva.getData().getProbOcc());
            two=new TreeNode();
            two.setLeft(leva);
            two.setRight(prava);
            two.setData(swap);
            t.enqueue(two);

        }

    huffmanRoot = t.dequeue();
}


//--------------------------------------------------------------------------------
        
	/* Your code goes here */

    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */

    public void makeEncodings() {
        encodings = new String[128];
        encodings=continued(encodings,"", huffmanRoot);
    }

    public static String[] continued(String[]yerd, String you, TreeNode stud){

        if(stud.getLeft() != null){
            continued(yerd, you + "0", stud.getLeft());
        } 
        if(stud.getRight() != null){
            continued(yerd, you + "1", stud.getRight());
        }
        if(stud.getLeft() == null && stud.getRight() == null){
            yerd[(int)stud.getData().getCharacter()] = you;
        }
        return yerd;  
	/* Your code goes here */
    }

    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);
        String space= "";
        while(StdIn.hasNextChar()){
          space+=encodings[(int)StdIn.readChar()];
        }
        writeBitString(encodedFile, space);
	/* Your code goes here */
    }
 

    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);
        TreeNode top= huffmanRoot;
        String s= readBitString(encodedFile);


        for(int a=0; a<s.length();a++){
            if(top.getLeft()!=null && s.charAt(a)=='0'){ //str.charAt(a)=='0' 
                top=top.getLeft();

            } else if(top.getRight()!=null && s.charAt(a)=='1'){ //str.charAt(a)=='1' 
                top=top.getRight();
            }

            if(top.getData().getCharacter()!=null){
                StdOut.print(top.getData().getCharacter());
                top =huffmanRoot;
            }
        }
	/* Your code goes here */
    }

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
