package ab1.impl.Kadiric;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {


	@Override
	public void toMinHeap(int[] data)
	{
		int n=data.length-1;       	// last position in heap
		for(int i=(n-1)/2;i>=0;i--)	// we will start from the last who has child
		{
			int tmp = data[i];
			int k=i;                    // index of the parent
			int j=i*2+1;				// index of the left child
			while(j<=n)
			{
				// we move tmp on his position
				//we want to reach heap on position i
				if(j+1<=n && data[j+1]<data[j])
					j=j+1;
				if(data[j]>=tmp)
					break;
				data[k]=data[j];
				k=j;
				j=k*2+1;
			}
			data[k]=tmp;
		}
	}

	@Override
	public void removeHeapElement(int position, int length, int[] minHeap)
	{
		int n=length-1;
		int tmp = minHeap[n];
		minHeap[n]=minHeap[position];    // sorted element goes on the end
		minHeap[position] = tmp;
		int k=position;
		if(k==0 || tmp>=minHeap[(k-1)/2])
		{
			// if tmp is the root, or smaller than its father
			// then bubble it down to its proper position
			// to restore the heap property
			while ( 2*k < n-1 )
			{
				int j = 2*k+1;
				int j2 = j+1;
				if (j2 <= n-1 && minHeap[j2]<minHeap[j])
					j=j2;
				if(tmp<=minHeap[j])
					break;
				minHeap[k]=minHeap[j];
				k=j;
			}
		}
		else
		{
			// if tmp is larger than its father, bubble it up
			// to its proper position to restore the heap property
			int j=(k-1)/2;
			while(k>0 && tmp<minHeap[j])
			{
				minHeap[k]=minHeap[j];
				k=j;
			}
		}
		minHeap[k] = tmp;
	}

	@Override
	public void heapsort(int[] data)
	{
		// create the heap, and remove repeatedly the
		// smallest element (the root of the heap)
		// in order to sort it
		toMinHeap(data);
		for(int n=data.length;n>0;n--)
			removeHeapElement(0,n,data);

	}

	@Override
	public LinkedList insert(LinkedList list, int value)
	{
		// Head node and tail node contain data!
		// They are not dummies!
		// create the new node
		ListNode new_node = new ListNode();
		new_node.value = value;
		new_node.next = null;
		new_node.prev = null;
		if(list==null)
			list = new LinkedList(); // create the list if necessary
		if(list.head==null)
		{
			// insert the new node into the empty list
			list.head = new_node;
			list.tail = new_node;
		}
		else
		{
			// special case: insert the largest element at the end
			if(new_node.value>=list.tail.value)
			{
				new_node.prev = list.tail;
				list.tail.next = new_node;
				list.tail = new_node;
			}
			else
			{
				// special case: insert the smallest element at the beginning
				if(new_node.value <= list.head.value)
				{
					new_node.next = list.head;
					list.head.prev = new_node;
					list.head = new_node;
				}
				else
				{
					// regular insertion anywhere in the list except at the beginning or the end
					for(ListNode tmp_node = list.head.next;tmp_node!=null;tmp_node=tmp_node.next)
					{
						// insert in front of tmp_node
						if(new_node.value <= tmp_node.value)
						{
							new_node.next = tmp_node;
							new_node.prev = tmp_node.prev;
							tmp_node.prev.next = new_node;
							tmp_node.prev = new_node;
							break;
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public LinkedList reverse(LinkedList list)
	{
		ListNode tmp_node_prev, tmp_node, tmp_node_next;
		// if it is the list empty , or the list has just one element , then should nothing be done
		if(list.head!=null && list.head!=list.tail)
		{
			for(tmp_node = list.head;tmp_node!=null;tmp_node = tmp_node_next)
			{
				tmp_node_prev = tmp_node.prev;
				tmp_node_next = tmp_node.next;
				tmp_node.next = tmp_node_prev;
				tmp_node.prev = tmp_node_next;
			}
			// we should now put in order tail and  head
			tmp_node = list.tail;
			list.tail = list.head;
			list.head = tmp_node;
		}
		return list;
	}

	@Override
	public ListNode maximum(LinkedList list)
	{
		return list.tail;
	}

	public void swap(int array[], int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private void medo3(int[] array,int l, int r)
	{
		if(r-l>=2)
		{
			int m=(l+r)/2;
			if(array[l]>array[m])
			{
				int tmp=array[l];
				array[l] = array[m];
				array[m]=tmp;
			}
			//now is the order l m with l < m
			if(array[m]>array[r])
			{
				// this means order of r m
				int tmp=array[m];
				array[m]=array[r];
				array[r]=tmp;
			}
			// now is the order l m r with m < r and l < r
			if(array[l] > array[m])
			{
				int tmp=array[l];
				array[l]=array[m];
				array[m] = tmp;
			}
			// now is the order  l m r
			// we should swap l and m
			int tmp2=array[l];
			array[l]=array[m];
			array[m]=tmp2;
		}

	}
	private int partition(int[] array, int l, int r)
	{
		int i = l;
		int j = r + 1;
		medo3(array, l, r);
		int pivot = array[l];
		while (true)
		{
			// all elements left of the pivot must be smaller than it
			while (array[++i] < pivot)
			{
				if (i == r) break;
			}
			// all elements right of the pivot must be larger than it
			while (pivot < array[--j])
			{
				if (j == l)
					break;
			}
			if (i >= j)
				break;    // indices have crossed
			int tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
		}

		// put the pivot at a[j] and return its location j
		array[l] = array[j];
		array[j] = pivot;
		return j;
	}

	private void quicksort_recursive(int[] array, int l, int r)
	{
		// limits are inclusive (closed interval)
		if(l<r)
		{
			int m = partition(array,l,r);
			quicksort_recursive(array, l,m-1);
			quicksort_recursive(array,m+1,r);
		}

	}

	@Override
	public void quicksort(int[] array)
	{
		quicksort_recursive(array, 0, array.length - 1);
	}
}