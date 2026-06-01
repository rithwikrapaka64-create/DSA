import java.util.*;

class BTreeNode {
    int[] keys;
    int t; // minimum degree
    BTreeNode[] children;
    int n;
    boolean leaf;

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2*t - 1];
        this.children = new BTreeNode[2*t];
        this.n = 0;
    }

    void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf) children[i].traverse();
            System.out.print(keys[i] + " ");
        }
        if (!leaf) children[i].traverse();
    }

    BTreeNode search(int k) {
        int i = 0;
        while (i < n && k > keys[i]) i++;
        if (i < n && keys[i] == k) return this;
        if (leaf) return null;
        return children[i].search(k);
    }
}

class BTree {
    BTreeNode root;
    int t;

    BTree(int t) { this.root = null; this.t = t; }

    void traverse() { if (root != null) root.traverse(); }

    BTreeNode search(int k) { return (root == null) ? null : root.search(k); }

    void insert(int k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2*t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                splitChild(s, 0, root);
                int i = 0;
                if (s.keys[0] < k) i++;
                insertNonFull(s.children[i], k);
                root = s;
            } else insertNonFull(root, k);
        }
    }

    void insertNonFull(BTreeNode x, int k) {
        int i = x.n - 1;
        if (x.leaf) {
            while (i >= 0 && x.keys[i] > k) {
                x.keys[i+1] = x.keys[i];
                i--;
            }
            x.keys[i+1] = k;
            x.n++;
        } else {
            while (i >= 0 && x.keys[i] > k) i--;
            if (x.children[i+1].n == 2*t - 1) {
                splitChild(x, i+1, x.children[i+1]);
                if (x.keys[i+1] < k) i++;
            }
            insertNonFull(x.children[i+1], k);
        }
    }

    void splitChild(BTreeNode x, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;
        for (int j = 0; j < t-1; j++) z.keys[j] = y.keys[j+t];
        if (!y.leaf) for (int j = 0; j < t; j++) z.children[j] = y.children[j+t];
        y.n = t - 1;
        for (int j = x.n; j >= i+1; j--) x.children[j+1] = x.children[j];
        x.children[i+1] = z;
        for (int j = x.n-1; j >= i; j--) x.keys[j+1] = x.keys[j];
        x.keys[i] = y.keys[t-1];
        x.n++;
    }
}

// Fenwick Tree
class FenwickTree {
    int[] BIT;
    int n;

    FenwickTree(int n) {
        this.n = n;
        BIT = new int[n+1];
    }

    void update(int idx, int val) {
        while (idx <= n) {
            BIT[idx] += val;
            idx += idx & -idx;
        }
    }

    int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += BIT[idx];
            idx -= idx & -idx;
        }
        return sum;
    }

    int rangeQuery(int l, int r) {
        return query(r) - query(l-1);
    }
}

public class TourPlanCO2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // B-Tree input
        System.out.print("Enter number of destinations: ");
        int n = sc.nextInt();
        BTree bt = new BTree(3); // degree 3
        System.out.println("Enter destination IDs:");
        for (int i = 0; i < n; i++) bt.insert(sc.nextInt());

        System.out.println("B-Tree traversal of destinations:");
        bt.traverse();
        System.out.println();

        System.out.print("Enter destination ID to search: ");
        int key = sc.nextInt();
        System.out.println(bt.search(key) != null ? "Destination Found" : "No value is present");

        // Fenwick Tree input
        System.out.print("\nEnter number of days for bookings: ");
        int days = sc.nextInt();
        FenwickTree ft = new FenwickTree(days);
        System.out.println("Enter bookings for each day:");
        for (int i = 1; i <= days; i++) ft.update(i, sc.nextInt());

        System.out.print("Enter range (l r) to query bookings: ");
        int l = sc.nextInt(), r = sc.nextInt();
        System.out.println("Total bookings from day " + l + " to " + r + ": " + ft.rangeQuery(l, r));

        sc.close();
    }
}
