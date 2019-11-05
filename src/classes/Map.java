package classes;


import interfaces.MapInterface;

public class Map<K extends Comparable<K>, V> implements MapInterface<K, V> {

    class Node {

        private Node parent;
        private Node leftChild;
        private Node rightChild;
        private K key;
        private V value;
        private boolean color;                  //red = true, black = false

        Node(K newKey, V newValue) {
            value = newValue;
            key = newKey;
            parent = null;
            leftChild = null;
            rightChild = null;
            color = true;
        }

        Node() {
            parent = null;
            leftChild = null;
            rightChild = null;
            color = true;
        }

        public String toString() {
            String colorName = color ? "RED" : "BLACK";
            String msg = "key: " + this.key;
            if (this.parent != null)
                msg += ", parent's key: " + parent.key;
            if (this.leftChild != null)
                msg += ", leftChild: " + leftChild.key;
            msg += ", value: " + this.value;
            if (this.rightChild != null)
                msg += ", rightChild: " + rightChild.key;
            msg += ", color: " + colorName;
            return msg;
        }

    }

    private Node root = null;
    private Node inputNode;


    @Override
    public void setValue(K key, V value) {

        Node newNode = new Node(key, value);

        if (root == null) {
            root = newNode;
            root.color = false;
            return;
        }

        Node actualNode = root;
        boolean running = true;

        while (running) {

            if (actualNode.key.compareTo(key) < 0 && actualNode.rightChild != null) {               //prawa droga
                actualNode = actualNode.rightChild;
            } else if (actualNode.key.compareTo(key) < 0) {
                newNode.parent = actualNode;
                actualNode.rightChild = newNode;
                actualNode = actualNode.rightChild;
                running = false;
            }

            if (actualNode.key.compareTo(key) > 0 && actualNode.leftChild != null) {                //lewa droga
                actualNode = actualNode.leftChild;
            } else if (actualNode.key.compareTo(key) > 0) {
                newNode.parent = actualNode;
                actualNode.leftChild = newNode;
                actualNode = actualNode.leftChild;
                running = false;
            }
        }
        restore(actualNode);
    }

    private void restore(Node actualNode) {

        while (actualNode != root && actualNode.parent.color) {

            switch (isLeftChild(actualNode.parent)) {

                case 1:                                                     //ojciec jest lewym synem
                    if (brotherColor(actualNode.parent)) {                  //brat ojca czerwony
                        actualNode.parent.color = false;
                        actualNode.parent.parent.rightChild.color = false;
                        actualNode = actualNode.parent.parent;
                        actualNode.color = true;
                        break;
                    }                                                       //brat ojca jest czarny
                    if (isLeftChild(actualNode) == 0) {                     //actualNode jest prawym synem
                        actualNode = actualNode.parent;
                        leftRotate(actualNode);
                    }
                    actualNode.parent.color = false;                        //actualNode jest lewym synem
                    actualNode.parent.parent.color = true;
                    rightRotate(actualNode.parent.parent);
                    break;
                case 0:                                                     //ojciec jest prawym synem
                    if (brotherColor(actualNode.parent)) {                  //brat ojca czerwony
                        actualNode.parent.color = false;
                        actualNode.parent.parent.leftChild.color = false;
                        actualNode = actualNode.parent.parent;
                        actualNode.color = true;
                        break;
                    }                                                       //brat ojca jest czarny
                    if (isLeftChild(actualNode) == 1) {                     //actualNode jest lewym synem
                        actualNode = actualNode.parent;
                        rightRotate(actualNode);
                    }
                    actualNode.parent.color = false;                        //actualNode jest prawym synem
                    actualNode.parent.parent.color = true;
                    leftRotate(actualNode.parent.parent);
                    break;
            }

            root.color = false;
        }

    }

    private int isLeftChild(Node node) {
        if (node.parent.leftChild != null) {
            if (node.parent.leftChild.key == node.key)
                return 1;                                         //jest lewym synem - 1, jest prawym 0
            else return 0;
        } else return 0;
    }

    private boolean brotherColor(Node node) {
        if (node.parent.leftChild == node) {
            if (node.parent.rightChild == null) return false;
            else return node.parent.rightChild.color;
        } else if (node.parent.leftChild == null) return false;
        else return node.parent.leftChild.color;
    }


    private void rightRotate(Node node) {
        inputNode = node;
        Node P = node.leftChild;

        if (inputNode == root) {
            root = P;
        } else if (isLeftChild(inputNode) == 1) {
            inputNode.parent.leftChild = P;
        } else {
            inputNode.parent.rightChild = P;
        }

        if (P.rightChild != null) {
            node.leftChild = P.rightChild;
            P.rightChild.parent = node;
        } else node.leftChild = null;

        P.parent = inputNode.parent;
        node.parent = P;
        P.rightChild = node;


    }

    private void leftRotate(Node node) {
        inputNode = node;
        Node Q = node.rightChild;

        if (inputNode == root) {
            root = Q;
        } else if (isLeftChild(inputNode) == 1) {
            inputNode.parent.leftChild = Q;
        } else {
            inputNode.parent.rightChild = Q;
        }

        if (Q.leftChild != null) {
            node.rightChild = Q.leftChild;
            node.rightChild.parent = node;
        } else node.rightChild = null;

        Q.parent = inputNode.parent;
        node.parent = Q;
        Q.leftChild = node;
    }

    public void write() {
        writeRecursive(root);
    }

    private void writeRecursive(Node actualNode) {
        if (actualNode != null) {
            System.out.println(actualNode);
            writeRecursive(actualNode.leftChild);
            writeRecursive(actualNode.rightChild);
        }
    }

    @Override
    public V getValue(K key) {

        Node actualNode = root;

        while (actualNode != null) {
            if (actualNode.key == key) {
                return actualNode.value;
            }
            if (actualNode.key.compareTo(key) > 0) {
                actualNode = actualNode.leftChild;
            } else actualNode = actualNode.rightChild;
        }
        return null;
    }
}
