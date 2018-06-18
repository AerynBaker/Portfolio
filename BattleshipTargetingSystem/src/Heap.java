import java.util.ArrayList;

public class Heap {
	
	ArrayList<Position> heap = new ArrayList<Position>();
	
	public Heap() {}
	
	public void sift(int[][] densities) {
		//from bottom to top of heap
		for(int i=heap.size()-1; i>=0; i--) {
			int j=i;
			while(j>0) {
				if(getDensity(heap.get(j), densities) > getDensity(heap.get(j-1),densities)) {
					Position temp = heap.get(j-1);
					heap.set(j-1, heap.get(j));
					heap.set(j, temp);
					j--;
				}
				else j=0;
			}
		}
	}
	
	private int getDensity(Position p, int[][] data) {
		return data[p.getX()][p.getY()];
	}
	
	public void add(Position p) {
		heap.add(p);
	}
	
	public void add(ArrayList<Position> set) {
		for(Position p : set)
			heap.add(p);
	}
	
	public int size() {
		return heap.size();
	}
	
	public Position pop() {
		if(heap.size()<1) return null;
		Position p = heap.get(0);
		heap.remove(0);
		return p;
	}
	
	public Position peek() {
		if(heap.size()<1) return null;
		return heap.get(0);
	}
	
	public Position get(int n) {
		return this.heap.get(n);
	}
}
