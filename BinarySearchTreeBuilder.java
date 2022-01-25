/**
 * This is a Binary Search Tree Builder class that includes 
 * a constructor and methods of contains, insert, delete, and 
 * printInOrder  Using these we are able to print the record 
 * in order after forming or editing the BST.
 * @author madhurjajoo
 *
 */

class BinarySearchTreeBuilder {
  BSTNode root;

  BinarySearchTreeBuilder() { root = null;}
  protected BSTNode createNewNode(String keyword, Record recordToAdd) {
    return new BSTNode(keyword, recordToAdd);
  }

/**
 * method to check if the keyword is already
 * present in the BST or not
 * @param keyword
 * @param recordToAdd
 * @return true (if keyword found) otherwise false
 */
  public boolean contains(String keyword, Record recordToAdd){
    BSTNode current = this.root;
    
    // This code needs to check if a keyword already exists as a node in BinarySearchTree
    while(current != null) {
    // IF (key does not exist in the BinarySearchTree) return FALSE
    	if(keyword.compareTo(current.keyword) < 0)
    		current = current.left;
    // ELSE append the recordToAdd to the keyword found in the BinarySearchTree
		else {
		    if(keyword.compareTo(current.keyword) == 0) {
		    	recordToAdd.next = current.record;
		    	current.record = recordToAdd;
		    	return true;
		    }
	    	current = current.right;
    	}
    }
    return false;
    
  }
   
  /**
   * recursive method to insert the element into the 
   * BST using keyword and finding its appropriate spot
   * @param keyword
   * @param fd
   */
  void insert(String keyword, FileData fd) {
    // using the FileData reference fd, create an object of Record
	  Record record = new Record(fd.id, fd.title, fd.author, (Record)null);
    // call contains method
    // IF (contains return False call the overloaded insert method
	  if(!this.contains(keyword, record)) {
		  this.insert(keyword, record);
	  }
    
  }

  /**
   * helper insert method where inserting is done
   * @param keyword
   * @param recordToAdd
   * @return true after the element is inserted
   */
  boolean insert(String keyword, Record recordToAdd) {
    // Overloaded insert method
    // IF root is NULL then createNewNode 
	  if(this.root == null) {
		  this.root = this.createNewNode(keyword, recordToAdd);
	  }
    // ELSE Traverse the tree to determine where to insert the new keyword
    // Once we know where to insert the new keyword, then call createNewNode method
    
	  else {
		BSTNode Node1 = this.root;
		while (Node1 != null) {
			if (keyword.compareTo(Node1.keyword) < 0) {
				BSTNode Node2 = Node1;
				Node1 = Node1.left;
				if (Node1 != null) {
					continue;
				}
				Node2.left = this.createNewNode(keyword, recordToAdd);
			}
			else {
				if (keyword.compareTo(Node1.keyword) <= 0) {
					continue;
				}
				BSTNode Node3 = Node1;
				Node1 = Node1.right;
				if (Node1 != null) {
					continue;
				}
				Node3.right = this.createNewNode(keyword, recordToAdd);
			}
		}
	}  
    return true;
  }

  /**
   * method to delete a keyword from the BST
   * and setting up the rest of the elements 
   * orderly in the BST
   * @param keyword
   * @return
   */
  public boolean delete(String keyword) {
    // Use delete function from the BST class and code that logic here
	  BSTNode Node1 = null;
	  BSTNode Node2 = this.root;
	  while(Node2 != null) {
		  if(keyword.compareTo(Node2.keyword) < 0) {	//onto the left
			  Node1 = Node2;
			  Node2 = Node2.left;
		  }
		  else {
			  if (keyword.compareTo(Node2.keyword) == 0)	//found the keyword
					break;
			  
			  Node1 = Node2;								//onto the right
			  Node2 = Node2.right;
		  }
	  }
	  
	  //when its empty
	  if(Node2 == null)
		  return false;
	  
	  //if node has only one child on right
	  if(Node2.left == null) {
		  if(Node1 == null) 
			  this.root = Node2.right;
		  
		  else if(keyword.compareTo(Node2.keyword) < 0)
			  Node1.left = Node2.right;
		  
		  else
			  Node1.right = Node2.right;
	  }
	  //if node has only one child on left
	  else if(Node2.right == null) {
		  if(Node1 == null) 
			  this.root = Node2.left;
		  
		  else if(keyword.compareTo(Node2.keyword) < 0)
			  Node1.right = Node2.left;
		  
		  else
			  Node1.left = Node2.left;
	  }
	  //node for the element having both right and left child
	  else {
		BSTNode Node3 = Node2;
		BSTNode Node4;
		for (Node4 = Node2.left; Node4.right != null; Node4 = Node4.right) {
			Node3 = Node4;
		}
		Node2.keyword = Node4.keyword;
		if (Node3.right == Node4) {
			Node3.right = Node4.left;
		}
		else {
			Node3.left = Node4.left;
		}
	}
	  
    return true; // Element deleted successfully
  }
 
  	/**
  	 * printing method
  	 */
	public void print() {
		System.out.println("****************** print(Sorted)***************");
		this.printInOrder(this.root);
	}

	/**
	 * helper print method for printing new record in order
	 * @param bstNode
	 */
	private void printInOrder(final BSTNode bstNode) {
		// Use the InOrder print method from the BST class in textbook to code here
		if (bstNode != null) {
			this.printInOrder(bstNode.left);
			System.out.println(bstNode.keyword);
			Record rec = bstNode.record;
			System.out.print("    ");
			while (rec != null) {
				System.out.printf("%d|%s|%s ---> ", rec.id, rec.author, rec.title);
				rec = rec.next;
			}
			System.out.println("null");
			this.printInOrder(bstNode.right);
		}
	}
}



