package ab1;

public interface Ab1 {

	/**
	 * Konstruiert aus dem übergebenen Array einen Min-Heap, d.h. nach dem
	 * Aufruf gilt die Heap-Bedingung H(1, data.length).
	 *
	 * @param data das Array, das in einen Min-Heap umgewandelt werden soll
	 */
	public void toMinHeap(int[] data);

	/**
	 * Entfernt das Element an der angegebenen Stelle aus dem Min-Heap
	 * durch Tauschen mit dem letzten Element. Die Heap-Bedingung muss auf
	 * dem restlichen Array wiederhergestellt werden. Es werden nur
	 * Positionen angegeben, sodass das letzte Element des Heaps ein
	 * Kindknoten des Elements an der angegebenen Position ist.
	 *
	 * @param position die Position des Elements, das entfernt werden soll
	 * @param length die Länge des Abschnitts des minHeap-Arrays, in dem
	 * 		 die Heap-Bedingung gilt (das minHeap-Array kann
	 * 		 möglicherweise länger sein; gehen Sie aber davon aus,
	 * 		 dass es an Stelle length - 1 endet)
	 * @param minHeap ein gültiger MinHeap, d.h. es gilt H(1, length)
	 */
	public void removeHeapElement(int position, int length, int[] minHeap);

	/**
	 * Sortiert das übergebene Array mithilfe des Heapsort-Algorithmus und
	 * eines Min-Heaps absteigend.
	 *
	 * @param data das zu sortierende Array
	 */
	public void heapsort(int[] data);

	/**
	 * Eine Klasse um Elemente einer doppelt-verkettete Liste zu
	 * repräsentieren.
	 */
	public class ListNode {
		public ListNode next; /** Pointer to next element */
		public ListNode prev; /** Pointer to next element */
		public int value; /** The value of the current element */
	}

	/**
	 * Eine Klasse, um eine doppelt-verkettete Liste zu repräsentieren.
	 */
	public class LinkedList {
		public ListNode head;
		public ListNode tail;
	}

	/**
	 * Fügt ein neues Element in die übergebene Liste ein und erhält die
	 * Sortierung.
	 *
	 * Nehmen Sie an, dass die Liste aufsteigend sortiert ist.
	 *
	 * @param list die sortierte Liste
	 * @param value der einzufügende Wert
	 *
	 * @return die Liste, in die das Element eingefügt wurde
	 */
	public LinkedList insert(LinkedList list, int value);

	/**
	 * Kehrt die Reihenfolge der Elemente in einer Liste um. Z.B. wird eine
	 * aufsteigend sortierte Liste durch Umkehrung absteigend sortiert.
	 *
	 * @param list die Liste
	 * @return die umgekehrte Liste
	 */
	public LinkedList reverse(LinkedList list);

	/**
	 * Gibt das größte Element in der Liste zurück.
	 *
	 * Nehmen Sie an, dass die Liste aufsteigend sortiert ist.
	 *
	 * @param list die zu durchsuchende Liste
	 * @return das Listenelement mit dem größten Wert
	 */
	public ListNode maximum(LinkedList list);

	/**
	 * Sortiert das gegebene Array mittels Quicksort, wobei ein
	 * Median-of-Three-Ansatz zur Bestimmung des Pivotelements verwendet
	 * werden soll. Hierzu soll, wie in der Angabe beschrieben, das erste,
	 * das mittlere (abgerundet), und das letzte Element des übergebenen
	 * Arrays zur Bestimmung des Pivot-Elements herangezogen werden.
	 *
	 * @param array das zu sortierende Array
	 */
	public void quicksort(int[] array);
}
