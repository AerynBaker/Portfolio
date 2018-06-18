
public class HuffmanTree extends HuffmanTreeNode {

	public HuffmanTree(HuffmanTreeNode left, HuffmanTreeNode right) {
		super(left.getByteValue() + right.getByteValue(), left.getIntByteFrequency() + right.getIntByteFrequency(), left, right);
	}
	
	//merges two trees or nodes together
	public static HuffmanTree merge(HuffmanTreeNode left, HuffmanTreeNode right) {
		return new HuffmanTree(left, right);
	}
	

	//print tree inorder
	public String printInline(HuffmanTreeNode node) {
		
		if (node != null) {
			StringBuffer sb = new StringBuffer();

			if (node.getLeft() != null)
                sb.append(this.printInline(node.getLeft()));
			if (node instanceof HuffmanTree)
				sb.append(((HuffmanTree)node).toStringTree());
			else
				sb.append(node.toString());
			if (node.getRight() != null)
                sb.append(this.printInline(node.getRight()));
			
			return sb.toString();
		}
		else
			return null;
	}

	public String toString() {
		return printInline(this);
	}
	
	public String toStringTree() {
		return ("{ " + this.getByteValue() + ":" + this.getIntByteFrequency() + " }");
	}
}
