/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.daniel.search;

import java.util.Collections;

/**
 *
 * @author Tiedye <tiedye1@hotmail.com>
 */
public class Search {
    
    private Search() {}
    
    /**
     * Preforms a binary search of an array for the specified item.
     * @param a The array.
     * @param n The item.
     * @return The index if the found item.
     */
    public static int BinarySearch(int[] a, int n){
        int low = 0,high = a.length-1;
        for(int mid = high/2; mid >= low; mid = (low + high)/2) {
            if (a[mid] > n) high = mid - 1;
            else if (a[mid] < n) low = mid + 1;
            else return mid;
        }
        return -1;
    }
    
    /**
     * Preforms a binary search of an array for the specified item.
     * @param a The array.
     * @param n The item.
     * @return The index if the found item.
     */
    public static int BinarySearch(String[] a, String n){
        int low = 0,high = a.length-1;
        for(int mid = high/2; mid >= low; mid = (low + high)/2) {
            if (a[mid].compareTo(n) > 0) high = mid - 1;
            else if (a[mid].compareTo(n) < 0) low = mid + 1;
            else return mid;
        }
        return -1;
    }
    
    /**
     * Preforms a binary search of an array for the specified item.
     * @param a The array.
     * @param n The item.
     * @return The index if the found item.
     */
    public static int LinearSearch(int[] a, int n){
        for(int i = 0; i < a.length; i++){
            if(a[i] == n) return i;
        }
        return -1;
    }
    
    /**
     * Preforms a binary search of an array for the specified item.
     * @param a The array.
     * @param n The item.
     * @return The index if the found item.
     */
    public static int LinearSearch(String[] a, String n){
        for(int i = 0; i < a.length; i++){
            if(a[i].equals(n)) return i;
        }
        return -1;
    }
    
}
