import java.util.HashSet;
import java.util.PriorityQueue;

public class UglyNumber2 {
    public static int nthUglyNumberHeap(int n) {
        PriorityQueue<Long> minheap = new PriorityQueue<>(); //O(nlogn) T.C, O(n) S.C
        HashSet<Long> set = new HashSet<>();

        long current = 1; //first ugly
        minheap.offer(current);
        set.add(current);
        int[] uglies = new int[]{2,3,5};

        for(int i = 0; i<n; i++) {
            current = minheap.poll();
            for(int ugly : uglies) {
                long next = current * ugly; //multiply with ugly primes
                if(!set.contains(next)) { //check for duplicates
                    set.add(next);
                    minheap.offer(next);
                }
            }
        }
        return (int)current;
    }

    public static int nthUglyNumberPointers(int n) {
        int[] uglies = new int[n]; //O(n) S.C
        uglies[0] = 1; //first ugly number
        int p2 = 0; int p3 = 0; int p5 = 0; //pointers on 2,3,5
        int n2 = 2; int n3 = 3; int n5 = 5; //next ugly multiples of 2,3,5

        for(int i=1; i<n; i++) { //O(n) T.C
            int next = Math.min(n2, Math.min(n3, n5)); //find min among multiples
            uglies[i] = next; //place it into next ugly position

            if(next == n2) { //if next ugly is multiple of 2
                p2++; //increment its pointer
                n2 = uglies[p2] * 2; //and move to the next multiple of 2
            }
            if(next == n3) { //similarly for 3
                p3++;
                n3 = uglies[p3] * 3;
            }
            if(next == n5) { //similarly for 5
                p5++;
                n5 = uglies[p5] * 5;
            }
        }
        return uglies[n-1];
    }

    public static void main(String[] args) {
        int n1 = 19;
        System.out.println("The " + n1 + "th ugly number found using heap is: " +
                nthUglyNumberHeap(n1));

        int n2 = 78;
        System.out.println("The " + n2 + "th ugly number found using pointers is: " +
                nthUglyNumberPointers(n2));
    }
}
