
class BinaryTree<Type extends Comparable<Type>>{ 

    private BinaryNode<Type> root = null; 

    public BinaryTree(BinaryNode<Type> root) { 
        this.root = root; 
    }

    public BinaryTree() { 
        this.root = null;
    }




    private boolean leftOrRight(BinaryNode<Type> comparatorNode, BinaryNode<Type> nodeToCompareTo) { 
        // comparator is node to add, node to compare to is fake root
        if(nodeToCompareTo != null && comparatorNode.getData().compareTo(nodeToCompareTo.getData()) > 0) { 
            // goes right
            return true; 
        } else { 
            return false; 
        }
    }


    public void insertRecursive(BinaryNode<Type> nodeToAdd, BinaryNode<Type> root) {
        
        
        if(leftOrRight(nodeToAdd, root)) { 
            // go right
            if(root.getRight() != null) { 
                insertRecursive(nodeToAdd, root.getRight());
            } else {
                root.setRight(nodeToAdd);
            }
        } else { 
            if(root.getLeft() != null) { 
                insertRecursive(nodeToAdd, root.getLeft());
            } else { 
                root.setLeft(nodeToAdd);
            }
        }
        
        

    }


    public void insert(Type data) { 
        BinaryNode<Type> nodeToAdd = new BinaryNode<Type>(data);
        if(root == null) { 
            root = nodeToAdd;
        } else {
            insertRecursive(nodeToAdd, root);
        }
        balance();
    }

    
    



    public BinaryNode<Type> getRoot() { 
        return root; 
    }
    


    



    public void printPreOrder() { 
        printPreOrder(root);
    }

    private void printPreOrder(BinaryNode<Type> newRoot) { 
        
        System.out.println(newRoot.getData()); // print first data
        if(newRoot.getLeft() != null) { 
            printPreOrder(newRoot.getLeft());
        } 
        if(newRoot.getRight() != null) { 
            printPreOrder(newRoot.getRight());
        }
    }

    // todo fix
    public void postOrder() { 
        postOrder(root);
    }

    private void postOrder(BinaryNode<Type> newRoot) {
        if(newRoot.getLeft() != null) { 
            postOrder(newRoot.getLeft());
            if(newRoot.getRight() != null) { 
                postOrder(newRoot.getRight());
            } 
        } else { 
            if(newRoot.getRight() == null) { 
                System.out.println(newRoot.getData());
            } else { 
                postOrder(newRoot.getRight());
            }
        }
        if(newRoot.getLeft() != null || newRoot.getRight() != null) {
            System.out.println(newRoot.getData());
        }
    }

    public boolean contains(Type value) { 
        return realContain(root, value);
    }

    public boolean realContain(BinaryNode<Type> newRoot, Type value) {
        if(newRoot.getLeft() != null && !newRoot.getData().equals(value)) {
            realContain(newRoot.getLeft(), value); // goes until the line ends but save the instance and print when the line is over in sequential order
            return false; 
        }
        if(newRoot.getRight() != null && !newRoot.getData().equals(value)) { 
            realContain(newRoot.getRight(), value);
            return false; 
            
        } 
        return true; 
    }


    public void remove(Type value) { 
        removeRecursive(root, value);
    }


    //TODO: cleanup
    private void removeRecursive(BinaryNode<Type> currentNode, Type value) { 
        

        if(currentNode.getData().equals(value) && currentNode.getData().compareTo(root.getData()) == 0) {
            boolean b = true;
            while(b) { 
                if(currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                    if(currentNode.getLeft() != null) {
                        BinaryNode<Type> booyah = new BinaryNode<Type>(currentNode.getLeft().getData(), root.getLeft(), root.getRight());
                        if(currentNode.getLeft().getRight() != null) { 
                            BinaryNode<Type> temp1 = currentNode.getLeft().getRight();
                            if(temp1.getLeft() != null) { 
                                if(currentNode.getLeft().getLeft() != null) { 
                                    temp1.getLeft().setLeft(currentNode.getLeft().getLeft());
                                } 
                            } else {
                                temp1.setLeft(currentNode.getLeft().getLeft());
                            }
                            currentNode.setLeft(temp1);

                        } else if(currentNode.getLeft().getLeft() != null) {

                            currentNode.setLeft(currentNode.getLeft().getLeft());
                        } else {

                            currentNode.setLeft(null);
                        }
                        // new root
                        root.setNode(booyah);
                        b = false;
                    }
                } else { 
                    root.setNode(currentNode.getLeft());
                    b = false; 
                }
        }
    }
        
        boolean leftOrRight = leftOrRight(new BinaryNode<Type>(value), currentNode); // find where i am supposed to go 
        
        
        if(currentNode != null) { 
            if(leftOrRight) { 
                if(currentNode.getRight() != null) { 
                    if(currentNode.getRight().getData().compareTo(value) == 0) { 
                        BinaryNode<Type> nodeToRemove = currentNode.getRight();
                        if(nodeToRemove.getLeft() != null) { 
                            currentNode.setRight(nodeToRemove.getLeft());
                            if(nodeToRemove.getRight() != null) { 
                                currentNode.getRight().setRight(nodeToRemove.getRight());
                            }
                        } else { 
                            if(nodeToRemove.getRight() != null && nodeToRemove.getLeft() == null) { 
                                currentNode.setRight(nodeToRemove.getRight());
                            }
                            else { 
                                currentNode.setRight(null);
                            }
                        }
                    }
                removeRecursive(currentNode.getRight(), value);
                }
            } else { 
                if(currentNode.getLeft() != null) { 
                    if(currentNode.getLeft().getData().compareTo(value) == 0) { 
                        BinaryNode<Type> nodeToRemove = currentNode.getLeft();
                        if(nodeToRemove.getLeft() != null) { 
                            currentNode.setLeft(nodeToRemove.getLeft());
                            if(nodeToRemove.getRight() != null) { 
                                currentNode.getLeft().setRight(nodeToRemove.getRight());
                            }
                        } else { 
                            if(nodeToRemove.getLeft() != null && nodeToRemove.getRight() == null) { 
                                currentNode.setLeft(nodeToRemove.getLeft());
                            }
                            else { 
                                currentNode.setLeft(null);
                            }
                        }
                    }
                    removeRecursive(currentNode.getLeft(), value);
                }
            }
        }
    }


    

    private int calculateRightHeight(BinaryNode<Type> currentNode){ //calculate the right side of a given node
        int c = 0; 
        if(currentNode != null && currentNode.getRight() != null) {
            c++;
            c += calculateRightHeight(currentNode.getRight());
            if(currentNode.getRight().getLeft() != null) { 
                c++;
                c += calculateRightHeight(currentNode.getRight().getLeft());
            }
        }
        return c;
    }

    private int calculateLeftHeight(BinaryNode<Type> currentNode) {  // calculate the left side of a given node
        int c = 0; 
        if(currentNode != null && currentNode.getLeft() != null) {
            c++;
            c += calculateLeftHeight(currentNode.getLeft());
            
            if(currentNode.getLeft().getRight() != null) { 
                c += calculateRightHeight(currentNode.getLeft());
            }
            return c;
        }
        return c; 
    }

    public void balance() {
       balanceRecur(root); 
    }

    private void rotateLeft(BinaryNode<Type> nodeToSwap) { 
        if(nodeToSwap.getRight() != null) { 
            if(nodeToSwap.getRight().getLeft() == null) { 
                BinaryNode<Type> current = new BinaryNode<Type>(nodeToSwap.getData(), nodeToSwap.getLeft(), null);
                nodeToSwap.setNode(nodeToSwap.getRight());
                nodeToSwap.setLeft(current);
            } else { 
                BinaryNode<Type> currentNode = new BinaryNode<Type>(nodeToSwap.getData());
                BinaryNode<Type> temp = nodeToSwap.getRight().getLeft();
                currentNode.setRight(temp);
                if(nodeToSwap.getLeft() != null) { 
                    
                    currentNode.setLeft(nodeToSwap.getLeft());
                    nodeToSwap.getRight().setLeft(null);
                }
                nodeToSwap.setNode(nodeToSwap.getRight());
                nodeToSwap.setLeft(currentNode);
            }
        } 
    }


    private void rotateRight(BinaryNode<Type> nodeToSwap) { 
        if(nodeToSwap.getLeft() != null) { 
            if(nodeToSwap.getRight() == null) { 
                BinaryNode<Type> current = new BinaryNode<Type>(nodeToSwap.getData());
                if(nodeToSwap.getLeft().getRight() != null) { 
                    current.setLeft(nodeToSwap.getLeft().getRight());
                }
                nodeToSwap.setNode(nodeToSwap.getLeft());
                nodeToSwap.setRight(current);
                
            } else { 
                BinaryNode<Type> currentNode = new BinaryNode<Type>(nodeToSwap.getData());
                currentNode.setRight(nodeToSwap.getRight());
                currentNode.setLeft(nodeToSwap.getLeft().getRight());
                nodeToSwap.setNode(nodeToSwap.getLeft());
                nodeToSwap.setRight(currentNode);
            }
        } 
    }



    private void balanceRecur(BinaryNode<Type> currentNode) {
        if(calculateRightHeight(currentNode) - calculateLeftHeight(currentNode) >= 2) {
            rotateLeft(currentNode);
                balanceRecur(currentNode.getRight());
                if(calculateLeftHeight(currentNode) - calculateRightHeight(currentNode) >= 2) { 
                    if(calculateRightHeight(currentNode.getLeft()) > calculateLeftHeight(currentNode.getLeft())) {
                        Type val = currentNode.getData();
                        if(currentNode == root) { 
                            System.out.println("remove");
                            remove(val);
                        insert(val);
                        balanceRecur(currentNode);
                
                    }
                }
                if(calculateRightHeight(currentNode) - calculateLeftHeight(currentNode) >= 2) {
                    balanceRecur(currentNode.getRight());
                }
        }
        
            
            
        } 
        if(calculateLeftHeight(currentNode) - calculateRightHeight(currentNode) >= 2) {
            rotateRight(currentNode);
            balanceRecur(currentNode.getLeft());
            if(calculateRightHeight(currentNode) - calculateLeftHeight(currentNode) >= 2) { 
                rotateLeft(currentNode);
            }
        }
    }




    
    

    
    
    
    public void printInOrder() {         
        printInOrder(getRoot());
    }

    private void printInOrder(BinaryNode<Type> newRoot) {   
        if(newRoot != null) {      
            if(newRoot.getLeft() != null) {
                printInOrder(newRoot.getLeft()); // goes until the line ends but save the instance and print when the line is over in sequential order
            }
            System.out.println(newRoot.getData());
            if(newRoot.getRight() != null) { 
                printInOrder(newRoot.getRight());
            }
        }
        //printInOrder(newRoot.getAbove().getAbove()); // get the node above the last
    }

    
    





    @Override
    public String toString() { 
        return "Root: " + root;
    }

    public int compareTo(Type data) { 
        return 0;  // what the fuck do i do here / need this for
    }


}