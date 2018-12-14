/*
 * Name: ChakMan Sio
 * Login: cs11farm
 * Date: 10 13, 2015
 * Film Name: Triangles.java
 * Sources of Help:Tutors
 * Description: This program allow users to print Asterisk
 *              using checkRange method and drawLine method
 */

/**
 * this class is allow user using two method to print triangles
 * with Asterisk
 */

import java.util.Scanner;
import java.io.*;

public class Triangles{

private static final int MINV = 2;
private static final int MAXV = 19;
private static final int position = 1;

private static boolean checkRange(int value, int min, int max){

// if the value greather than min and value less than max
// it will start to drawline; however, it will show error message
    if (value>=min && value<=max){
        return true;
    }
    else{
        return false;
    }
}


/**
 * method name is main
 * purpose: run the program
 * @param first is character
 * @param second is character
 * @param firstCount is integer
 * @param total Count is integer
 * @return void
 */

private static void drawLine(char first,char second, int firstCount, int totalCount){
    int i = 0;

    // when i < firstCount, the system print first character
    // and, every time i will +1
    while (i< firstCount){
        System.out.print(first);
        i++;
    }

    // when i < total Count, the system print second character
    // and, every time i will +1
    while ( i<totalCount){
        System.out.print (second);
        i++;
    }
}

/**
 * method name is main
 * purpose: run the program
 * @param args is array of string
 * @return void
 */

public static void main(String[] args){
    int value = 0;
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the size of the triangles to display: ");

    // while there is more input (user has not hit EOF)
    while (input.hasNext()){

        // read next integer
        value = input.nextInt();

            // if the min value and max value in the check range
            if (checkRange(value, MINV,MAXV)) {
                int size = 1;
                    //when size less than value
                    while (size<=value){

// start drawLine, so it prints the first character that
// is space, and the second character is star in first line
// value-size means how many character in the
drawLine (' ','*', value-size, value);
System.out.print(" ");

// start drawLine, so it prints the first character that
// is asterisk, and the second character is space in second
// line
drawLine ('*',' ', size, value);
System.out.print(" ");
drawLine (' ','*', size-position, value);
System.out.print(" ");
drawLine ('*',' ', (value- size)+position, value);
System.out.println();
size ++;
}
}

else{
System.out.println("Triangle size muse be >=2 and <=19; Try again." +"\n");
System.out.print("Enter the size of the triangles to display: ");
}
}
}
}