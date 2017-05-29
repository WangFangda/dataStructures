package sorting;

import java.util.Comparator;
import java.util.List;

/**
 * @author fangda.wang
 */
public class SearchUtils {


    /**
     * returns the insertion position, and
     * all the values before the insertion position is smaller than or equal to target.
     **/
    public static <T> int biSearchEnd(final T[] data,
                                      final int start,
                                      final int end,
                                      final T targetValue,
                                      Comparator<T> comparator) {

        if (end > data.length - 1) return data.length;

        if (comparator.compare(data[start], targetValue) > 0) return start;
        if (comparator.compare(data[end], targetValue) <= 0) return end;

        if (start + 1 >= end) {
            if (comparator.compare(data[end], targetValue) <= 0) return end;
            return start;
        }

        final int mid = start + (end - start) / 2;
        if (comparator.compare(data[mid], targetValue) <= 0)
            return biSearchEnd(data, mid, end, targetValue, comparator);
        return biSearchEnd(data, start, mid, targetValue, comparator);

    }

    /**
     * returns the insertion position, and
     * all the values before the insertion position is smaller than or equal to target.
     **/
    public static <T extends Comparable<? super T>> int biSearchEnd(final T[] data,
                                                                    final int start,
                                                                    final int end,
                                                                    final T targetValue) {

        if (end > data.length - 1) return data.length;

        if (data[start].compareTo(targetValue) > 0) return start;
        if (data[end].compareTo(targetValue) <= 0) return end;

        if (start + 1 >= end) {
            if (data[end].compareTo(targetValue) <= 0) return end;
            return start;
        }

        final int mid = start + (end - start) / 2;
        if (data[mid].compareTo(targetValue) <= 0)
            return biSearchEnd(data, mid, end, targetValue);
        return biSearchEnd(data, start, mid, targetValue);

    }

    /**
     * returns the insertion position, and
     * all the values before the insertion position is smaller than or equal to target.
     **/
    public static <T> int biSearchEnd(final List<T> data,
                                      final int start,
                                      final int end,
                                      final T targetValue,
                                      Comparator<T> comparator) {

        if (end > data.size() - 1) return data.size();

        if (comparator.compare(data.get(start), targetValue) > 0) return start;
        if (comparator.compare(data.get(end), targetValue) <= 0) return end;

        if (start + 1 >= end) {
            if (comparator.compare(data.get(end), targetValue) <= 0) return end;
            return start;
        }

        final int mid = start + (end - start) / 2;
        if (comparator.compare(data.get(mid), targetValue) <= 0)
            return biSearchEnd(data, mid, end, targetValue, comparator);
        return biSearchEnd(data, start, mid, targetValue, comparator);

    }

    /**
     * returns the insertion position, and
     * all the values before the insertion position is smaller than or equal to target.
     **/
    public static <T extends Comparable<? super T>> int biSearchEnd(final List<T> data,
                                                                    final int start,
                                                                    final int end,
                                                                    final T targetValue) {

        if (end > data.size() - 1) return data.size();

        if (data.get(start).compareTo(targetValue) > 0) return start;
        if (data.get(end).compareTo(targetValue) <= 0) return end;

        if (start + 1 >= end) {
            if (data.get(end).compareTo(targetValue) <= 0) return end;
            return start;
        }

        final int mid = start + (end - start) / 2;
        if (data.get(mid).compareTo(targetValue) <= 0)
            return biSearchEnd(data, mid, end, targetValue);
        return biSearchEnd(data, start, mid, targetValue);

    }

    /**
     * returns the insertion position, and
     * all the values at and after the insertion position equal to or larger than target.
     */
    public static <T> int biSearchStart(final T[] data,
                                        final int start,
                                        final int end,
                                        final T targetValue,
                                        Comparator<T> comparator) {
        if (end > data.length - 1) return data.length;

        if (comparator.compare(data[start], targetValue) >= 0) return start;

        if (start + 1 >= end) {
            if (comparator.compare(targetValue, data[end]) > 0) return end+1;
            if (comparator.compare(targetValue, data[end]) <= 0) return end;
            if (comparator.compare(data[start], targetValue) >= 0) return start;
            return end;
        }

        final int mid = start + (end - start) / 2;
        if (comparator.compare(data[mid], targetValue) < 0)
            return biSearchStart(data, mid, end, targetValue, comparator);
        return biSearchStart(data, start, mid, targetValue, comparator);
    }

    /**
     * returns the insertion position, and
     * all the values at and after the insertion position equal to or larger than target.
     */
    public static <T extends Comparable<? super T>> int biSearchStart(final T[] data,
                                                                      final int start,
                                                                      final int end,
                                                                      final T targetValue) {
        if (end > data.length - 1) return data.length;

        if (data[start].compareTo(targetValue) >= 0) return start;

        if (start + 1 >= end) {
            if (targetValue.compareTo(data[end]) > 0) return end+1;
            if (targetValue.compareTo(data[end]) <= 0) return end;
            if (data[start].compareTo(targetValue) >= 0) return start;
            return end;
        }

        final int mid = start + (end - start) / 2;
        if (data[mid].compareTo(targetValue) < 0)
            return biSearchStart(data, mid, end, targetValue);
        return biSearchStart(data, start, mid, targetValue);
    }

    /**
     * returns the insertion position, and
     * all the values at and after the insertion position equal to or larger than target.
     */
    public static <T> int biSearchStart(final List<T> data,
                                        final int start,
                                        final int end,
                                        final T targetValue,
                                        Comparator<T> comparator) {

        if (end > data.size() - 1) return data.size();

        if (comparator.compare(data.get(start), targetValue) >= 0) return start;

        if (start + 1 >= end) {
            if (comparator.compare(targetValue, data.get(end)) > 0) return end+1;
            if (comparator.compare(targetValue, data.get(end)) <= 0) return end;
            if (comparator.compare(data.get(start), targetValue) >= 0) return start;
            return end;
        }

        final int mid = start + (end - start) / 2;
        if (comparator.compare(data.get(mid), targetValue) < 0)
            return biSearchStart(data, mid, end, targetValue, comparator);
        return biSearchStart(data, start, mid, targetValue, comparator);
    }

    /**
     * returns the insertion position, and
     * all the values at and after the insertion position equal to or larger than target.
     */
    public static <T extends Comparable<? super T>> int biSearchStart(final List<T> data,
                                                                      final int start,
                                                                      final int end,
                                                                      final T targetValue) {

        if (end > data.size() - 1) return data.size();

        if (data.get(start).compareTo(targetValue) >= 0) return start;

        if (start + 1 >= end) {
            if (targetValue.compareTo(data.get(end)) > 0) return end+1;
            if (targetValue.compareTo(data.get(end)) <= 0) return end;
            if (data.get(start).compareTo(targetValue) >= 0) return start;
            return end;
        }

        final int mid = start + (end - start) / 2;
        if (data.get(mid).compareTo(targetValue) < 0)
            return biSearchStart(data, mid, end, targetValue);
        return biSearchStart(data, start, mid, targetValue);
    }

}
