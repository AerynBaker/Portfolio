import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Huffman {

	public void main(String arg0) {
		
	}
	
	public static boolean[][] makeHuffmanCodes(int[] freq) {

		int[][] freqArray = processFreqArray(freq);
		BinaryMinHeap heap = new BinaryMinHeap(freqArray);
		int heapLength = heap.getHuffmanHeap().length;

		HuffmanTree mainTree = null;
		for (int i = 1; i < heapLength; i++) {
			mainTree = null;
            HuffmanTreeNode node1 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
            HuffmanTreeNode node2 = heap.deleteFromHeapAt(heap.getSmallestNodeIndex());
            mainTree = HuffmanTree.merge(node1, node2);
            heap.insertIntoHeap(mainTree);
		}

		boolean[][] codes = new boolean[heapLength][];
		int mainTreeDepth = mainTree.getDepth(mainTree);
		
		for (int i = 0; i < heapLength; i++){
			codes[i] = getLeafHuffmanCode(new boolean[mainTreeDepth], 
					mainTree, 0, mainTreeDepth, freqArray[i][0], freqArray[i][1]);
		}
		return codes;
	}
	
	//calculate freq, paired values and freq
	private static int[][] processFreqArray(int[] freq) {
		int[][] mergedFreqArray = new int[freq.length][2];

		int[] bytes = new int[freq.length];
		for (int i = 0; i < bytes.length; i++)
			bytes[i] = i - 128;

		for (int i = 0; i < freq.length; i++) {
			mergedFreqArray[i][0] = freq[i];
			mergedFreqArray[i][1] = bytes[i];
		}

		int freqCount = 0;
		for (int i = 0; i < mergedFreqArray.length - freqCount; i++) {
            if (mergedFreqArray[i][0] == 0) {
            	freqCount++;
            	boolean isSetToTheBack = false;
            	int j = 1;
            	while (!isSetToTheBack) {
            		if (mergedFreqArray[mergedFreqArray.length - j][0] == 0)
            			j++;
            		else {
                        int temp = mergedFreqArray[mergedFreqArray.length - j][0];
                        mergedFreqArray[mergedFreqArray.length - j][0] = mergedFreqArray[i][0];
                        mergedFreqArray[i][0] = temp;
                        isSetToTheBack = true;
            		}
            	}
            }
		}
		
		int[][] freqArray = new int[freq.length - freqCount][2];
		for (int i = 0; i < freqArray.length; i++) {
			freqArray[i][0] = mergedFreqArray[i][0];
			freqArray[i][1] = mergedFreqArray[i][1];
		}
		
		return freqArray;
	}
	
	protected static int[][] processFreqArrayPublic(int[] freq) {
		return processFreqArray(freq);
	}
	
	//sets codes in the tree
	public static boolean[] getLeafHuffmanCode(boolean[] huffmanCode, HuffmanTreeNode root, int depth, int mainTreeDepth, int intFreqToFind, int intByteToFind) {
		if (root != null) {
			//left
			if (root.getLeft() != null) {
				huffmanCode[depth] = false;
				boolean[] returnCode = getLeafHuffmanCode(huffmanCode, root.getLeft(), depth + 1, mainTreeDepth, intFreqToFind, intByteToFind);
				if (returnCode != null) {
					if (returnCode.length == mainTreeDepth) {
						boolean[] truncatedCode = new boolean[depth + 1];
                        for (int i = 0; i < truncatedCode.length; i++)
                                truncatedCode[i] = returnCode[i];
                        returnCode = truncatedCode;
					}
					return returnCode;
				}
				else
					huffmanCode[depth] = false;
			}

			//right
			if (root.getRight() != null) {
				huffmanCode[depth] = true;
				boolean[] returnCode = getLeafHuffmanCode(huffmanCode, root.getRight(), depth + 1, mainTreeDepth, intFreqToFind, intByteToFind);
				if (returnCode != null) {
					if (returnCode.length == mainTreeDepth) {
						boolean[] truncatedCode = new boolean[depth + 1];
                        for (int i = 0; i < truncatedCode.length; i++)
                                truncatedCode[i] = returnCode[i];
                        returnCode = truncatedCode;
					}
					return returnCode;
				}
				else
					huffmanCode[depth] = false;
			}

			if (root.isLeaf() && (intFreqToFind == root.getIntByteFrequency()) && (intByteToFind == root.getByteValue()))
				return huffmanCode;
			else
				return null;
		}
		else
			return null;
	}
}
