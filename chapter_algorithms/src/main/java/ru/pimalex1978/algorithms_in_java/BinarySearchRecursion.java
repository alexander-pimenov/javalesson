package ru.pimalex1978.algorithms_in_java;

public class BinarySearchRecursion {
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length-1); 
	}	
	public static int rank(int key, int[] a, int lo, int hi){
		//Если key присутствует в a[], то его индекс не меньше lo и не больше hi
		if(lo>hi) return -1; //если нет такого числа в массиве
		int mid = lo + (hi-lo)/2;
	if(key<a[mid]) return rank(key, a, lo, mid-1);
	else if(key>a[mid]) return rank(key, a, mid+1, hi);
	else 				return mid;
	}
		

    public static void main(String[] args) {

    }
}
