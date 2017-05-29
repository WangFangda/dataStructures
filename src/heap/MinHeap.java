package heap;

import java.util.Comparator;

/**
 * @author fangda.wang
 */
public class MinHeap {

    private static final int START_POINT = 1;

    public HeapNode[] val;

    public Comparator<HeapNode> comparator;

    private int nextIndex = START_POINT;

    private int iteratorIndex = START_POINT;


    public MinHeap (final int size, final Comparator<HeapNode> comparator) {
        val = new HeapNode[size + START_POINT];
        this.comparator = comparator;
    }

    public boolean hasNext() {
        return iteratorIndex < nextIndex;
    }

    public HeapNode next() {
        HeapNode result = val[iteratorIndex];
        iteratorIndex++;
        return result;
    }

    public boolean isEmpty() {
        return nextIndex == START_POINT;
    }

    public int size() {return nextIndex - START_POINT;}

    /**
     * Use judiciously
     * @param node
     */
    public void addWithoutAdjustment (final HeapNode node) {
        val[nextIndex] = node;
        nextIndex++;
    }

    public void add (final HeapNode node) {
        val[nextIndex] = node;
        bubbleUp(nextIndex);
        nextIndex++;
    }

    private void bubbleUp (final int currentIndex) {
        if (1 == currentIndex) return;

        int parentIndex = currentIndex / 2;
        if (comparator.compare(val[parentIndex], val[currentIndex]) > 0) {
            HeapNode tmp = val[currentIndex];
            val[currentIndex] = val[parentIndex];
            val[parentIndex] = tmp;
            bubbleUp(parentIndex);
        }
    }

    public HeapNode extractTop () throws CannotExtractException {
        if (1 == nextIndex) throw new CannotExtractException();

        HeapNode result = val[1];
        nextIndex--;
        val[1] = val[nextIndex];
        val[nextIndex] = null;
        bubbleDown(1);
        return result;
    }

    private void bubbleDown(final int currentIndex) {
        int leftChildPos = 2 * currentIndex;
        int rightChildPos = leftChildPos + 1;

        int smallerChildPos = currentIndex;

        if (leftChildPos < nextIndex && comparator.compare(val[leftChildPos], val[smallerChildPos]) < 0)
            smallerChildPos = leftChildPos;
        if (rightChildPos < nextIndex && comparator.compare(val[rightChildPos], val[smallerChildPos]) < 0)
            smallerChildPos = rightChildPos;

        if (currentIndex == smallerChildPos) return;

        HeapNode tmp = val[currentIndex];
        val[currentIndex] = val[smallerChildPos];
        val[smallerChildPos] = tmp;
        bubbleDown(smallerChildPos);
    }

    public class CannotExtractException extends Exception {
    }

}
