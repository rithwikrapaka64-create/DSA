import java.util.*;

class HashTable {
    int size;
    int[] table;

    HashTable(int size) {
        this.size = size;
        table = new int[size];
        Arrays.fill(table, -1); // -1 means empty
    }

    int hash(int key) {
        return key % size;
    }

    void insert(int key) {
        int idx = hash(key);
        int start = idx;
        while (table[idx] != -1) {
            idx = (idx + 1) % size;
            if (idx == start) {
                System.out.println("Hash table full, cannot insert " + key);
                return;
            }
        }
        table[idx] = key;
    }

    boolean search(int key) {
        int idx = hash(key);
        int start = idx;
        while (table[idx] != -1) {
            if (table[idx] == key) return true;
            idx = (idx + 1) % size;
            if (idx == start) break;
        }
        return false;
    }

    void display() {
        System.out.println("Hash Table:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + " -> " + (table[i] == -1 ? "empty" : table[i]));
        }
    }
}

public class TourPlanCO4 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter hash table size: ");
            int size = sc.nextInt();
            HashTable ht = new HashTable(size);

            System.out.print("Enter number of booking IDs to insert: ");
            int n = sc.nextInt();
            System.out.println("Enter booking IDs:");
            for (int i = 0; i < n; i++) ht.insert(sc.nextInt());

            ht.display();

            System.out.print("Enter booking ID to search: ");
            int key = sc.nextInt();
            System.out.println(ht.search(key) ? "Booking Found" : "No value is present");
        }
    }
}
