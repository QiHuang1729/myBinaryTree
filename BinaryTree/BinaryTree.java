import java.util.ArrayList;

/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	[comments later?]
 * 
 *	@author	Qi Huang
 *	@since	5/14/2024
 */
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree() { }
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 *
	public void add(E value) 
	{
		TreeNode<E> currentNode = root;
		TreeNode<E> nextNode = null;
		E currentValue = null;
		
		if (currentNode == null) {
			root = new TreeNode<E>(value);
		} else {
			// find the nextNode
			currentValue = currentNode.getValue();
			if (value.compareTo(currentValue) < 0) {
				nextNode = currentNode.getLeft();
			} else {
				nextNode = currentNode.getRight();
			}
			
			// if nextNode is not null, continue moving nextNode up to 
			// currentNode and setting the appropriate next node from 
			// currentNode to nextNode
			while (nextNode != null) {
				currentNode = nextNode;
				currentValue = currentNode.getValue();
				if (value.compareTo(currentValue) < 0) {
					nextNode = currentNode.getLeft();
				} else {
					nextNode = currentNode.getRight();
				}
			}
			
			// if nextNode is equal to null, set the appropriate pointer
			// in the currentNode to the input value, which we store 
			// in nextNode.
			nextNode = new TreeNode<E>(value);
			if (value.compareTo(currentValue) < 0) {
				currentNode.setLeft(nextNode);
			} else {
				// if value is "equal" to the currentValue, it's set 
				// to the right, but that doesn't appear in this problem
				currentNode.setRight(nextNode);
			}
		}
	} */
	
	/**	Recusively add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) {
		if (root == null) {
			root = new TreeNode<E>(value);
		} else {
			recursiveAdd(value, root);
		}
	}
	
	/**	Recusively add a node to the tree
	 *	@param value		the value to put into the tree
	 *  @param node			the part of the tree the value will be in
	 */
	/** Test thoroughly if time lol */
	private void recursiveAdd(E value, TreeNode<E> node) {
		TreeNode<E> nextNode = null;
		TreeNode<E> valueNode = null;
				
		if (value.compareTo(node.getValue()) < 0) {
			nextNode = node.getLeft();
			if (nextNode == null) {
				valueNode = new TreeNode<E>(value);
				node.setLeft(valueNode);
			} else {
				recursiveAdd(value, nextNode);
			}
		} else {
			nextNode = node.getRight();
			if (nextNode == null) {
				valueNode = new TreeNode<E>(value);
				node.setRight(valueNode);
			} else {
				recursiveAdd(value, nextNode);
			}
		}
	}
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() {
		ArrayList<E> inOrder = new ArrayList<E>();
		storeInorder(inOrder, root);
		for (int i = 0; i < inOrder.size(); i++) {
			System.out.print(inOrder.get(i) + " ");
		}
	}
	
	/** 
	 * Print inorder a Binary Subtree starting from "node"
	 * @param node The node where the subtree begins
	 */
	private void storeInorder(ArrayList<E> inOrder, TreeNode<E> node) {
		if (node.getLeft() != null) {
			storeInorder(inOrder, node.getLeft());
		}
		inOrder.add(node.getValue());
		if (node.getRight() != null) {
			storeInorder(inOrder, node.getRight());
		}
	}
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { 
		printPreorder(root);
	}
	
	/** 
	 * Print inorder a Binary Subtree starting from "node"
	 * @param node The node where the subtree begins
	 */
	private void printPreorder(TreeNode<E> node) {
		System.out.print(node.getValue() + " ");
		if (node.getLeft() != null) {
			printPreorder(node.getLeft());
		}
		if (node.getRight() != null) {
			printPreorder(node.getRight());
		}
	}
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { 
		printPostorder(root);
	}
	
	/** 
	 * Print inorder a Binary Subtree starting from "node"
	 * @param node The node where the subtree begins
	 */
	private void printPostorder(TreeNode<E> node) {
		if (node.getLeft() != null) {
			printPostorder(node.getLeft());
		}
		if (node.getRight() != null) {
			printPostorder(node.getRight());
		}
		System.out.print(node.getValue() + " ");
	}
	
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();
		
		ArrayList<E> inorder = new ArrayList<E>();
		storeInorder(inorder, root);
		balancedTree.root = makeBalancedNode(inorder, 0, inorder.size() - 1);
		
		return balancedTree;
	}
	
	private TreeNode<E> makeBalancedNode(ArrayList<E> inorder, int low, int high) {
		if (low > high) return null;
		int mid = (low + high) / 2;
		TreeNode<E> theNode = new TreeNode<E>(inorder.get(mid));
		TreeNode<E> leftNode = makeBalancedNode(inorder, low, mid - 1);
		TreeNode<E> rightNode = makeBalancedNode(inorder, mid + 1, high);
		theNode.setLeft(leftNode);
		theNode.setRight(rightNode);
		
		return theNode;
	}
	
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(E value) {
		root = remove(root, value);
	}
	
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {		
		TreeNode<E> nodeChild = null; // used when "node" is not removed
		
		if (value.compareTo(node.getValue()) != 0) {
			if (value.compareTo(node.getValue()) < 0) {
				nodeChild = remove(node.getLeft(), value);
				node.setLeft(nodeChild);
			} else {
				nodeChild = remove(node.getRight(), value);
				node.setRight(nodeChild);
			}
			return node;
		} else {
			if (node.getLeft() == null && node.getRight() == null) {
				return null;
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else {
				// iterate until secondNode/bottom node has no left
				TreeNode<E> firstNode = node;
				TreeNode<E> secondNode = node.getRight();
				
				while (secondNode.getLeft() != null) {
					firstNode = secondNode;
					secondNode = secondNode.getLeft();
				}
				
				// remove secondNode from the tree and connect the 
				// appropriate replacement to firstNode
				if (firstNode == node) {
					firstNode.setRight(remove(secondNode, secondNode.getValue()));
				} else {
					firstNode.setLeft(remove(secondNode, secondNode.getValue()));
				}
				
				// secondNode is the appropriate replacement for removing "node".
				// connect it to the left and right subtree of "node"
				secondNode.setLeft(node.getLeft());
				secondNode.setRight(node.getRight());

				return secondNode;
			}
		}
	}
	

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	
	
}
