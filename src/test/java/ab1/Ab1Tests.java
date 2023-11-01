package ab1;

import ab1.impl.Kadiric.Ab1Impl;
import ab1.Ab1.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Ab1Tests {

    private Random rand = new Random(System.currentTimeMillis());

    private static Ab1 ab1Impl = new Ab1Impl();

    private static int NUM_TESTS = 50;
    private static int NUM_TESTS_LARGE = 200000;
    private static int ARRAY_SIZE_SMALL = 7500;
    private static int ARRAY_SIZE_LARGE = 500000;
    private static int ARRAY_SIZE_HUGE = 25_000_000;

    @Test
    public void testToMinHeapSmall()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_SMALL);
            ab1Impl.toMinHeap(test);
            assertTrue(checkHeap(0, test.length, test));
        }
    }

    @Test
    public void testToMinHeapLarge()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_LARGE);
            ab1Impl.toMinHeap(test);
            assertTrue(checkHeap(0, test.length, test));
        }
    }

    @Test
    public void testToMinHeapSorted()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_SMALL);
            Arrays.sort(test);
            ab1Impl.toMinHeap(test);
            assertTrue(checkHeap(0, test.length, test));
        }
    }

    @Test
    public void testToMinHeapCornerCases()
    {
        // empty array
        int[] test = new int[0];
        ab1Impl.toMinHeap(test);

        // array of size 1
        test = getRandomArray(1);
        ab1Impl.toMinHeap(test);
        assertTrue(checkHeap(0, test.length, test));

        // array with all elements zero
        test = new int[ARRAY_SIZE_SMALL];
        ab1Impl.toMinHeap(test);
        assertTrue(checkHeap(0, test.length, test));

        // array with all elements zero, except the last one
        test = new int[ARRAY_SIZE_SMALL];
        test[test.length - 1] = 1;
        ab1Impl.toMinHeap(test);
        assertTrue(checkHeap(0, test.length, test));
    }

    @Test
    public void testRemoveHeapElementSmall()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomMinHeap(ARRAY_SIZE_SMALL);

            for(int j = 0; j < NUM_TESTS; ++j)
            {
                int up = Math.abs(rand.nextInt((int)(Math.log(test.length - j - 1)/Math.log(2))));
                int pos = test.length - j - 1;
                for(; up >= 0; --up) pos = (pos - 1)/2;

                int element = test[pos];
                ab1Impl.removeHeapElement(pos, test.length - j, test);

                assertTrue(checkHeap(0, test.length - j - 1, test));
                assertEquals(element, test[test.length - j - 1]);
            }

        }
    }

    @Test
    public void testRemoveHeapElementLarge()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomMinHeap(ARRAY_SIZE_LARGE);

            for(int j = 0; j < NUM_TESTS; ++j)
            {
                int up = Math.abs(rand.nextInt((int)(Math.log(test.length - j - 1)/Math.log(2))));
                int pos = test.length - j - 1;
                for(; up >= 0; --up) pos = (pos - 1)/2;

                int element = test[pos];
                ab1Impl.removeHeapElement(pos, test.length - j, test);

                assertTrue(checkHeap(0, test.length - j - 1, test));
                assertEquals(element, test[test.length - j - 1]);
            }

        }
    }

    @Test
    public void testHeapSortSmall()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_SMALL);
            int[] copy = Arrays.copyOf(test, test.length);

            ab1Impl.heapsort(test);


            Arrays.sort(copy);
            int[] reference = new int[ARRAY_SIZE_SMALL];
            for(int j = 1; j <= copy.length; ++j)
                reference[j-1] = copy[copy.length - j];

            assertArrayEquals(reference, test);
        }
    }

    @Test
    public void testHeapSortLarge()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_LARGE);
            int[] copy = Arrays.copyOf(test, test.length);
            ab1Impl.heapsort(test);

            Arrays.sort(copy);
            int[] reference = new int[ARRAY_SIZE_LARGE];
            for(int j = 1; j <= copy.length; ++j)
                reference[j-1] = copy[copy.length - j];

            assertArrayEquals(reference, test);
        }
    }

    @Test
    public void testListInsert()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_SMALL);
            Ab1.LinkedList list = new Ab1.LinkedList();
            list.head = null;
            list.tail = null;
            for(int j = 0; j < test.length; ++j)
            {
                list = ab1Impl.insert(list, test[j]);
                assertNotNull(list);
                assertNotNull(list.head);
                assertNotNull(list.tail);
            }

            ListNode head = list.head;
            Arrays.sort(test);
            for(int j = 0; j < test.length; ++j)
            {
                assertEquals(test[j], head.value);
                head = head.next;
            }
            assertNull(head);
        }
    }

    @Test
    public void testListReverse()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_SMALL);
            Ab1.LinkedList list = new Ab1.LinkedList();
            list.head = null;
            list.tail = null;
            for(int j = 0; j < test.length; ++j)
            {
                list = ab1Impl.insert(list, test[j]);
                assertNotNull(list);
                assertNotNull(list.head);
                assertNotNull(list.tail);
            }

            list = ab1Impl.reverse(list);
            ListNode tail = list.tail;
            Arrays.sort(test);
            for(int j = 0; j < test.length; ++j)
            {
                assertEquals(test[j], tail.value);
                tail = tail.prev;
            }
            assertNull(tail);
        }
    }

    @Test
    public void testListMaximum()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_SMALL);
            int max = Integer.MIN_VALUE;
            Ab1.LinkedList list = new Ab1.LinkedList();
            list.head = null;
            list.tail = null;
            for(int j = 0; j < test.length; ++j)
            {
                list = ab1Impl.insert(list, test[j]);
                assertNotNull(list);
                assertNotNull(list.head);
                assertNotNull(list.tail);
                if(max < test[j]) max = test[j];
            }

            ListNode foundMax = ab1Impl.maximum(list);
            assertEquals(max, foundMax.value);
        }
    }

    @Test
    public void testQuickSortSmall()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_SMALL);
            int[] copy = Arrays.copyOf(test, test.length);
            ab1Impl.quicksort(test);
            Arrays.sort(copy);
            assertArrayEquals(copy, test);
        }
    }

    @Test
    public void testQuickSortLarge()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            int[] test = getRandomArray(ARRAY_SIZE_LARGE);
            int[] copy = Arrays.copyOf(test, test.length);
            ab1Impl.quicksort(test);
            Arrays.sort(copy);
            assertArrayEquals(copy, test);
        }
    }

    private int[] getRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i)
            arr[i] = Math.abs(rand.nextInt(2 * size));
        return arr;
    }

    private int[] getRandomMinHeap(int size) {
        int[] arr = new int[size];
        arr[0] = Math.abs(rand.nextInt(10));

        int pos = 0;
        while(true)
        {
            int l = 2*pos + 1;
            int r = 2*pos + 2;

            if(l >= arr.length) break;
            arr[l] = arr[pos] + Math.abs(rand.nextInt(9) + 1);

            if(r >= arr.length) break;
            arr[r] = arr[pos] + Math.abs(rand.nextInt(9) + 1);

            ++pos;
        }

        return arr;
    }

    private boolean checkHeap(int start, int end, int[] array)
    {
        for (int i = start; i <= end / 2 - 1; ++i)
            if (array[2*i + 1] < array[i] || 2*i + 2 < end && array[2*i + 2] < array[i])
                return false;
        return true;
    }

    private void printArray(int[] array)
    {
        for(int i = 0; i < array.length; ++i)
            System.out.println("[" + i + "]: " + array[i]);
    }

}
