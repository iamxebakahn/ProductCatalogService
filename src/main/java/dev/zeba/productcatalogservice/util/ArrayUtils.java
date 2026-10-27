package dev.zeba.productcatalogservice.util;

public class ArrayUtils {
    public static int getElementByIndex(int a[], int idx){
        if(idx < 0 || idx > a.length)
            throw new IllegalArgumentException("Index out of bounds");

        return a[idx];
        //return a[idx+1]; //if this mistake is made by future developer, compile time error will come
        //3 types of scenarios/good practise to think of while writing test cases:
        //happy case (idx - 0 to n-1), sad case (<0 and >=n), boundary case (exact 0, n-1, l, null)
        //write test cases in test folder
    }
}
