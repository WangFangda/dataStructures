package heap;

import java.util.Comparator;

/**
 * @author fangda.wang
 */
public class HeapNode {
    public static final Comparator comparatorOnV1 = new Comparator<HeapNode>() {
        @Override public int compare(HeapNode o1, HeapNode o2) {
            if (o1.v1 == o2.v1) return 0;
            return o1.v1 - o2.v1 > 0 ? 1 : -1;
        }
    };

    public static final Comparator comparatorOnV2 = new Comparator<HeapNode>() {
        @Override public int compare(HeapNode o1, HeapNode o2) {
            if (o1.v2 == o2.v2) return 0;
            return o1.v2 - o2.v2 > 0 ? 1 : -1;
        }
    };

    public long v1;
    public long v2;

    public HeapNode (long v1, long v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}
