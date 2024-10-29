public class Lab05 {
    public static void main(String[] args) {
        
        System.out.println(isMatching("{[()]}")); // true
        System.out.println(reverse("Yahyaefe")); // efeayhaY
        System.out.println(isHTMLMatching("<div><p></p></div>")); // true
        System.out.println(evaluate("3 + (2 * 5)")); // 13
    }

    public static boolean isMatching(String expression) {
        IStack<Character> stack = new ArrayStack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (!isPair(top, c)) return false;
            }
        }
        return stack.isEmpty();

          /*
         * Given a string containing parentheses and other characters,
         *  write a function that determines whether the parentheses
         *  in the string are balanced. A string is said to have balanced
         *  parentheses if every opening parenthesis ((, {, [) has a
         *  corresponding closing parenthesis (), }, ]), and the pairs
         *  are properly nested.
         */
    }

    private static boolean isPair(char open, char close) {
       
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    public static String reverse(String str) {
         /*
         * Write a function that reverses a given string using a stack.
         *  In this problem, you will leverage the Last-In-First-Out (LIFO)
         *  property of a stack to reverse the order of characters in a string.
         */
        IStack<Character> stack = new ArrayStack<>();
        for (char c : str.toCharArray()) {
            stack.push(c);
        }
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }
        return reversed.toString();
    }

    public static boolean isHTMLMatching(String html) {
        /*
         * In HTML, every opening tag (like <div>) must have a corresponding closing
         *  tag (like </div>). Write a function to check whether all the HTML tags in
         *  a given string are properly matched. A properly matched HTML string has balanced
         *  and correctly nested tags.
         */
        IStack<String> stack = new LinkedStack<>();
        int index = 0;
        while (index < html.length()) {
            int start = html.indexOf('<', index);
            if (start == -1) break;
            int end = html.indexOf('>', start);
            if (end == -1) break;

            String tag = html.substring(start + 1, end).trim();
            if (!tag.startsWith("/")) {
                stack.push(tag);
            } else {
                if (stack.isEmpty() || !stack.pop().equals(tag.substring(1))) {
                    return false;
                }
            }
            index = end + 1;
        }
        return stack.isEmpty();
    }

    public static int evaluate(String expression) {
        /*
         * Write a function to evaluate a given arithmetic expression containing integers,
         *  parentheses, and operators. The function should correctly follow the order of
         *  operations (precedence) and handle parentheses appropriately.
         *  Your task is to complete the evaluate(String expression) method
         *  which returns the result of the expression.
         * 
         * You may also need following methods or any other:
         * boolean isOperator(char c): returns true if given character is an operator
         * int precedance(char op): returns the precedance of the character
         * int applyOp(char op, int b, int a): applys the operator on operands
         */
        IStack<Integer> values = new ArrayStack<>();
        IStack<Character> ops = new ArrayStack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isWhitespace(c)) continue;
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                values.push(Integer.parseInt(sb.toString()));
                i--;
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.top() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop(); // pop '('
            } else if (isOperator(c)) {
                while (!ops.isEmpty() && precedance(ops.top()) >= precedance(c)) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }
        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedance(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }
}

interface List<E> {
    int size();
    boolean isEmpty();
}

interface IStack<E> extends List<E> {
    void push(E e);
    E top();
    E pop();
}

interface IQueue<E> extends List<E> {
    void enqueue(E e);
    E dequeue();
    E first();
}

class ArrayStack<E> implements IStack<E> {
     /*
     * Data Fields: necessary data fields
     * Constuctors: ArrayStack(): default capacity = 1000, ArrayStack(int capacity)
     * Methods: Required methods, toString
     */
    private Object[] stack;
    private int top;
    
    public ArrayStack() {
        this.stack = new Object[1000];
        this.top = -1;
    }

    public ArrayStack(int capacity) {
        this.stack = new Object[capacity];
        this.top = -1;
    }

    public void push(E e) {
        stack[++top] = e;
    }

    public E top() {
        return (E) stack[top];
    }

    public E pop() {
        return (E) stack[top--];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(stack[i]).append(" ");
        }
        return sb.toString();
    }
}

class Node<E> {

      /*
     * Data Fields: data, next
     * Constuctor: Node(data, next)
     * Methods: getData, getNext, setNext, toString
     */
    private E data;
    private Node<E> next;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

class LinkedStack<E> implements IStack<E> {

    /*
     * Data Fields: necessary data fields
     * Constuctor: LinkedStack()
     * Methods: Required methods, toString
     */

    private Node<E> top;
    private int size;

    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(E e) {
        top = new Node<>(e, top);
        size++;
    }

    public E top() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return top.getData();
    }

    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        E data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = top;
        while (current != null) {
            sb.append(current.getData()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }
}

class ArrayQueue<E> implements IQueue<E> {
     /*
     * Data Fields: necessary data fields
     * Constuctors: ArrayQueue(): default capacity = 1000, ArrayStack(int capacity)
     * Methods: Required methods, toString
     */
    private Object[] queue;
    private int front, rear, size;

    public ArrayQueue() {
        this.queue = new Object[1000];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public ArrayQueue(int capacity) {
        this.queue = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public void enqueue(E e) {
        queue[rear++] = e;
        size++;
        if (rear == queue.length) rear = 0; // wrap around
    }

    public E dequeue() {
        E data = (E) queue[front++];
        size--;
        if (front == queue.length) front = 0; // wrap around
        return data;
    }

    public E first() {
        return (E) queue[front];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(queue[(front + i) % queue.length]).append(" ");
        }
        return sb.toString();
    }
}

class LinkedQueue<E> implements IQueue<E> {
     /*
     * Data Fields: necessary data fields
     * Constuctors: LinkedQueue()
     * Methods: Required methods, toString
     */
    private Node<E> front;
    private Node<E> rear;
    private int size;

    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        E data = front.getData();
        front = front.getNext();
        size--;
        if (isEmpty()) rear = null; // reset rear if queue is empty
        return data;
    }

    public E first() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return front.getData();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = front;
        while (current != null) {
            sb.append(current.getData()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }
}
