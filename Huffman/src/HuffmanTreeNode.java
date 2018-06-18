
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

	private int data;
	private int freq;
	private HuffmanTreeNode left;
	private HuffmanTreeNode right;

	public HuffmanTreeNode(int intByte, int intFrequency, HuffmanTreeNode left, HuffmanTreeNode right) {
		this.setByteValue(intByte);
		this.setIntByteFrequency(intFrequency);
		this.setLeft(left);
		this.setRight(right);
	}

	//returns depth of the node in a tree (non-zero)
	public int getDepth(HuffmanTreeNode node) {
		if (node == null)
			return 0;
		else {
			int leftDepth = getDepth(node.getLeft());
			int rightDepth = getDepth(node.getRight());
			
			if (leftDepth > rightDepth)
				return (leftDepth+1);
			else
				return (rightDepth+1);
		}
	}
	
	public boolean isLeaf() {
		return ( left == null && right == null );
	}

	@Override
	public int compareTo(HuffmanTreeNode node) {
		return this.freq - node.freq;
	}
	
	public String toString() {
		return "{ " + this.getByteValue() + ":" + this.getIntByteFrequency() + " }";
	}

	public int getByteValue() {
		return data;
	}

	public void setByteValue(int intByte) {
		this.data = intByte;
	}

	public int getIntByteFrequency() {
		return freq;
	}

	public void setIntByteFrequency(int intFrequency) {
		this.freq = intFrequency;
	}

	public HuffmanTreeNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}

	public HuffmanTreeNode getRight() {
		return right;
	}

	public void setRight(HuffmanTreeNode right) {
		this.right = right;
	}
}
