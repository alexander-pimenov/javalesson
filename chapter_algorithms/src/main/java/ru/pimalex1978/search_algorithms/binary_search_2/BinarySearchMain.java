package ru.pimalex1978.search_algorithms.binary_search_2;

/**
 * Пример кода двоичного поиска
 */
public class BinarySearchMain {
    public static void main(String[] args) {
        BinarySearchMain binarySearchMain = new BinarySearchMain();
        binarySearchMain.binSearch();
    }

    private void binSearch() {
        int[] inpArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer result = binSearchF(inpArr, 1, 0, inpArr.length - 1);
        System.out.println("-----------------------");
        result = binSearchF(inpArr, 2, 0, inpArr.length - 1);
        System.out.println("Found at position " + result);
    }

    private Integer binSearchF(int[] inpArr, int searchValue, int low, int high) {
        Integer index = null;
        while (low <= high) {
            System.out.println("New iteration, low = " + low + ", high = " + high);
            int mid = (low + high) / 2;
            System.out.println("trying mid = " + mid + " inpArr[mid] = " + inpArr[mid]);
            if (inpArr[mid] < searchValue) {
                low = mid + 1;
                System.out.println("inpArr[mid] (" + inpArr[mid] + ") < searchValue(" + searchValue + "), mid = " + mid
                        + ", setting low = " + low);
            } else if (inpArr[mid] > searchValue) {
                high = mid - 1;
                System.out.println("inpArr[mid] (" + inpArr[mid] + ") > searchValue(" + searchValue + "), mid = " + mid
                        + ", setting high = " + high);
            } else if (inpArr[mid] == searchValue) {
                index = mid;
                System.out.println("found at index " + mid);
                break;
            }
        }
        return index;
    }
}
