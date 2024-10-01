class BinaryNode<T extends Comparable<T>>{ 

    T data = null;
    BinaryNode<T> left = null;
    BinaryNode<T> right = null;

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data; 
        this.left = left; 
        this.right = right;  
    }

    public BinaryNode(T data) { 
        this.data = data; 
        left = null; 
        right = null; 
    }

   


    public T getData() { 
        return data; 
    }

    public BinaryNode<T> getRight() { 
        return right; 
    }
    public BinaryNode<T> getLeft() { 
        return left; 
    }
    

    public void setRight(BinaryNode<T> node) { 
        right = node; 
    }
    public void setLeft(BinaryNode<T> node) { 
        left = node; 
    }
   

    public void setData(T data) { 
        this.data = data; 
    }

    public void setNode(BinaryNode<T> node) { 
        if(node != null) {
            data = node.getData();
            left = node.getLeft();
            right = node.getRight();
        } else { 
            data = null;
            left = null;
            right = null;
        }

    }


    @Override
    public String toString() { 

        if(left != null && right != null) { 
            return "{Data: " + data + "}, {AfterLeft: " + left.getData() + "}, {AfterRight: " + right.getData() + "}";
        }
        if (left == null && right == null) { 
            return "{Data: " + data + "}";
        }
        if(left != null && right == null) { 
            return "{Data: " + data + "}, {AfterLeft: " + left.getData() + "}";
        }
        if(left == null && right != null) { 
            return "{Data: " + data + "}, {AfterRight: " + right.getData() + "}";
        }
        if(left != null && right == null) { 
            return "{Data: " + data + "}, {AfterLeft: " + left.getData() + "}";
        }
        if(left == null && right != null) { 
            return "{Data: " + data + "}, {AfterRight: " + right.getData() + "}";
        } 
        if(left == null && right == null) { 
            return "{Data: " + data + "}";
        }
        if(left != null && right != null) { 
            return "{Data: " + data + "}, {AfterLeft: " + left.getData() + "}, {AfterRight: " + right.getData() + "}";
        }
        return "{Data: " + data + "}, {AfterLeft: " + left.getData() + "}, {AfterRight: " + right.getData() + "}";
    }

    

    public int compareTo(BinaryNode<T> other) {
        return data.compareTo(other.getData());
    }
}