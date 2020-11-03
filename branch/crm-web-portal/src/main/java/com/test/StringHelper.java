package com.test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Java Program to find all permutations of a String
 * @author shailendra
 *
 */
public class StringHelper
{
    public static Set<String> permutationFinder( String str )
    {
        Set<String> perm = new HashSet<String>();
        //Handling error scenarios
        if ( str == null )
        {
            return null;
        }
        else if ( str.length() == 0 )
        {
            perm.add( "" );
            return perm;
        }
        char initial = str.charAt( 0 ); // first character
        System.out.println( initial );
        String rem = str.substring( 1 ); // Full string without first character
        System.out.println( rem );
        Set<String> words = permutationFinder( rem );
        for ( String strNew : words )
        {
            System.out.println( "strnew: " + strNew );
            for ( int i = 0; i <= strNew.length(); i++ )
            {
                System.out.println( "prem: " + charInsert( strNew, initial, i ) );
                perm.add( charInsert( strNew, initial, i ) );
            }
        }
        return perm;
    }

    public static String charInsert( String str, char c, int j )
    {
        String begin = str.substring( 0, j );
        String end = str.substring( j );
        return begin + c + end;
    }

    public static void main( String[] args )
    {
        /* String s = "AAC";
         String s1 = "ABC";
         String s2 = "ABCD";
         System.out.println( "\nPermutations for " + s + " are: \n" + permutationFinder( s ) );
         System.out.println( "\nPermutations for " + s1 + " are: \n" + permutationFinder( s1 ) );
         System.out.println( "\nPermutations for " + s2 + " are: \n" + permutationFinder( s2 ) );*/
        int n, c, d, swap;
        Scanner in = new Scanner( System.in );
        System.out.println( "Input number of integers to sort" );
        n = in.nextInt();
        int array[] = new int[n];
        System.out.println( "Enter " + n + " integers" );
        for ( c = 0; c < n; c++ )
            array[c] = in.nextInt();
        for ( c = 0; c < ( n - 1 ); c++ )
        {
            for ( d = 0; d < n - c - 1; d++ )
            {
                if ( array[d] > array[d + 1] )
                {
                    swap = array[d];
                    array[d] = array[d + 1];
                    array[d + 1] = swap;
                }
            }
        }
        System.out.println( "Sorted list of numbers" );
        for ( c = 0; c < n; c++ )
            System.out.println( array[c] );
        int counter = 0;
        int previousCounter = 0;
        int a=0,b=0;
        int twoD[][];
        for ( c = 0; c < n; c++ )
        {
            int k = array[c];
            for ( int g = 1; g < n; g++ )
            {
                if ( k == array[g] )
                {
                    //twoD[a][b] ={array[c],array[c]};
                }
            }
            if ( counter > previousCounter )
            {
            }
        }
    }
}