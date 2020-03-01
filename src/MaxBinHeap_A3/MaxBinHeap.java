package MaxBinHeap_A3;

public class MaxBinHeap implements Heap_Interface {
	
  private double[] array; //load this array
  private int size;
  private static final int arraySize = 15; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MaxBinHeap() {
    this.array = new double[arraySize];
    array[0] = Double.NaN;  //0th will be unused for simplicity 
                            //of child/parent computations...
                            //the book/animation page both do this.
    size = 0;
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  	@Override
  	public double[] getHeap() { 
  		return this.array;
  	}

	@Override
	public void insert(double element) {
		// TODO Auto-generated method stub		
		
		if(size == 9999) return;
		
		size++;
		array[size] = element;
		
		trickleUp(size);
	}
	
	private void trickleUp(int idx) {
		int parentIdx = idx/2;
		double newVal = array[idx];
		
		while(array[parentIdx] < newVal && idx > 1) {
			array[idx] = array[parentIdx];
			idx = parentIdx;
			parentIdx = parentIdx/2;
		}
		
		array[idx] = newVal;
		
	}
	
	@Override
	public void delMax() {
		// TODO Auto-generated method stub'
		if(size == 0) return;
		
		array[1] = array[size]; 
		array[size] = Double.NaN;

		size--;
		trickleDown(array[1]);

	}
	
	private void trickleDown(double top) {
		
		int currentIndx = 1;
		
		while(currentIndx < (size/2)+1) {
			int leftIndx = (2*currentIndx);
			int rightIndx = leftIndx+1;
			
			int largerIndx;
			
			if(rightIndx < size+1 && array[leftIndx] <  array[rightIndx]) {
				largerIndx = rightIndx;
			} else {
				largerIndx = leftIndx;
			}
			
			if(array[currentIndx] >= array[largerIndx]) break;
			
			array[currentIndx] = array[largerIndx];
			currentIndx = largerIndx;
			array[currentIndx] = top;
		}
		
		array[currentIndx] = top;
		
	}

	@Override
	public double getMax() {
		// TODO Auto-generated method stub
		if(size == 0) return Double.NaN;
		
		return array[1];
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i = 1; i < size; i++) array[i] = Double.NaN;
		size = 0;
		
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public void build(double[] elements) {
		// TODO Auto-generated method stub
		int size = elements.length;
		this.size = size;
		
		array[0] = Double.NaN;
		for(int i = 0; i < size; i++) {
			array[i+1] = elements[i];
		}
		
		
		for(int i = size/2; i >= 1; i--) 
			maxHeapify(array, size, i);
	}
	
	private void maxHeapify(double[] elements, int size, int i) {
		
		int largest = i;
		int left = 2*i;
		int right = left + 1;
		
		if(left < size+1 && elements[left] > elements[largest]) {
			largest = left;
		}
		
		if(right < size+1 && elements[right] > elements[largest]) {
			largest = right;
		}
		
		if(largest != i) {
			
			double temp = elements[i];
			elements[i] = array[largest];
			elements[largest] = temp;
			
			maxHeapify(elements, size, largest);
		}
		
	}
	
	@Override
	public double[] sort(double[] elements) {
		// TODO Auto-generated method stub
		clear();
		
		build(elements);
		
		double temp[] = new double[size+1];
		temp[0] = Double.NaN;
		int tempSize = size;
		for(int i = 1; i < tempSize+1; i++) {
			temp[i] = getMax();
			delMax();
		}
		
		/*for(int i = size; i >= 1; i--) {
			double temp = array[1];
			array[1] = array[i];
			array[i] = temp;
			
			maxHeapify(array, i, 1);
		}*/
		
		int j = tempSize;
		array[0] = Double.NaN;
		for(int i = 1; i < tempSize+1; i++) {
			array[i] = temp[j];
			j--;
		}
		
		size = tempSize;
		
		return array;
				
	}

}