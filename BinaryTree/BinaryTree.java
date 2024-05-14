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
	 */
	public void add(E value) 
	{
		// save the root to a temporary TreeNode<E> variable
		// If the root is null, just add a new TreeNode<E> with value.
		// If the root is not null, compare value to the value of the root
		// Check the appropriate side of the tree. If, say, the left
		// side of the tree is null, then save the TreeNode<E> to the
		// left side. If it's not null, then save the left node as 
		// the temporary node and compare again. 
		
		// save current node
		// save next node
		// if currentNode is null, it's the head. Save the value to the head
		// if currentNode is not null, do a comparison and set nextNode 
		// based on whether the method returns a value less than zero
		// or a value greater than zero.
		// if nextNode is not null, set it as currentNode. Loop with 
		// two previous steps
		// if nextNode is null, set it to the ListNode<E> value using current node
		// outside of the loop
		
		TreeNode<E> currentNode = head;
		TreeNode<E> nextNode = null;
		
		if (currentNode == null) {
			root = new TreeNode<E>(value);
		} else {
			while (nextNode != null) {
				
			}
		}
		
	}
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() { }
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { }
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { }
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();

		return balancedTree;
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
		return null;
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
