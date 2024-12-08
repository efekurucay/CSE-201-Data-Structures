/**---------------------------------------------------
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████
██████ *Akdeniz University CSE201 Data Structures Homeworks                   ██████
██████                                                                        ██████
██████ *Yahya Efe Kurucay                                                     ██████
██████                                                                        ██████
██████ *09.12.2024                                                            ██████
██████                                                                        ██████
██████ *Description: Homework03                                               ██████ 
██████                                                                        ██████
██████ *Proposed grade: ?                                                     ██████ 
██████                                                                        ██████
██████ *Website: https://efekurucay.com                                       ██████
██████                                                                        ██████
██████ *                                                                      ██████
██████ *                                                                      ██████
██████ *    ███████ ███████ ███████   |    ███████ ███████ ███████            ██████ 
██████ *    ██      ██      ██        |    ██      ██      ██                 ██████ 
██████ *    █████   █████   █████     |    █████   █████   █████              ██████ 
██████ *    ██      ██      ██        |    ██      ██      ██                 ██████ 
██████ *    ███████ ██      ███████   |    ███████ ██      ███████            ██████ 
██████ *                                                                      ██████
██████ *                                                                      ██████
██████ *                                                                      ██████
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████                          
 */


import java.util.Stack;

public class HW03 {
    public static void main(String[] args) {
        /* File f = new File("./test.html");
        try (Scanner reader = new Scanner(f)) {
            StringBuilder content = new StringBuilder();
            while (reader.hasNextLine()) {
                content.append(reader.nextLine()).append(System.lineSeparator());
            }
            String fileContent = content.toString();
            
            System.out.println(isHTMLMatching(fileContent));
        } catch (Exception e) {
            System.out.println(e);
        } */

        //System.out.println(evaluate("(15+5)*3"));
        //System.out.println(reverse("abcd"));
        // Test cases
        System.out.println("Testing isMatching:");
        System.out.println(isMatching("((()))"));  // true
        System.out.println(isMatching("({[]})"));  // true
        System.out.println(isMatching("({[}])"));  // false

        System.out.println("\nTesting reverse:");
        System.out.println(reverse("Hello"));  // olleH
        System.out.println(reverse("Java"));   // avaJ

        System.out.println("\nTesting evaluate:");
        System.out.println(evaluate("(15+5)*3"));  // 60
        System.out.println(evaluate("2+3*4"));     // 14

        System.out.println("\nTesting isHTMLMatching:");
        System.out.println(isHTMLMatching("<div><p>Hello</p></div>"));  // true
        System.out.println(isHTMLMatching("<div><p>Hello</div></p>"));  // false
    }

    public static boolean isMatching(String expression) {
            /*
         * Given a string containing parentheses and other characters,
         *  write a function that determines whether the parentheses
         *  in the string are balanced. A string is said to have balanced
         *  parentheses if every opening parenthesis ((, {, [) has a
         *  corresponding closing parenthesis (), }, ]), and the pairs
         *  are properly nested.
         */
        // Your code here..

        

        Stack <Character> stack = new Stack<>();
        
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
        
    }

    public static String reverse(String str) {
            /*
         * Write a function that reverses a given string using a stack.
         *  In this problem, you will leverage the Last-In-First-Out (LIFO)
         *  property of a stack to reverse the order of characters in a string.
         */
        // Your code here..

        if (str == null) return null;
        
        Stack<Character> stack = new Stack<>();
        // Push all characters onto stack
        for (char c : str.toCharArray()) {
            stack.push(c);
        }
        
        // Pop characters to create reversed string
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
        // Your code here..





        Stack<String> stack = new Stack<>();
        int i = 0;
        
        while (i < html.length()) {
            // Find next '<' character
            if (html.charAt(i) == '<') {
                int j = i + 1;
                // Find the closing '>'
                while (j < html.length() && html.charAt(j) != '>') j++;
                
                if (j == html.length()) return false;
                
                String tag = html.substring(i + 1, j);
                
                // Check if it's a closing tag
                if (tag.startsWith("/")) {
                    if (stack.isEmpty()) return false;
                    
                    String openTag = stack.pop();
                    String closeTag = tag.substring(1);
                    
                    if (!openTag.equals(closeTag)) return false;
                } else {
                    // Opening tag
                    stack.push(tag);
                }
                
                i = j;
            }
            i++;
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
         */
        // Your code here..


            if (expression == null || expression.trim().isEmpty()) 
            throw new IllegalArgumentException("Expression cannot be null or empty");
        
        LinkedStack<Integer> numbers = new LinkedStack<>();
        LinkedStack<Character> operators = new LinkedStack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isWhitespace(c)) continue;
            
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Integer.parseInt(num.toString()));
            }
            else if (c == '(') {
                operators.push(c);
            }
            else if (c == ')') {
                while (!operators.isEmpty() && operators.top() != '(') {
                    evaluateTop(numbers, operators);
                }
                if (!operators.isEmpty()) {
                    operators.pop(); // Remove '('
                }
            }
            else if (isOperator(c)) {
                while (!operators.isEmpty() && operators.top() != '(' && 
                       precedence(operators.top()) >= precedence(c)) {
                    evaluateTop(numbers, operators);
                }
                operators.push(c);
            }
        }
        
        while (!operators.isEmpty()) {
            evaluateTop(numbers, operators);
        }
        
        return numbers.pop();
    }

    private static void evaluateTop(LinkedStack<Integer> numbers, LinkedStack<Character> operators) {
        int b = numbers.pop();
        int a = numbers.pop();
        char op = operators.pop();
        numbers.push(applyOperator(a, b, op));
    }
    
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }
    
    private static int applyOperator(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

}

interface List <E> {
    int size();
    boolean isEmpty();
}

interface IStack <E> extends List <E> {
    void push(E e);
    E top();
    E pop();
}

interface IQueue <E> extends List<E> {
    void enqueue(E e);
    E dequeue();
    E first();
}

class ArrayStack <E> implements IStack <E> {

        /*
     * Data Fields: necessary data fields
     * Constuctors: ArrayStack(): default capacity = 1000, ArrayStack(int capacity)
     * Methods: Required methods, toString
     */ // Your code here..

     //data fields
    private E[] data;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 1000;
    public E[] getData() {
         return data;
     }

     //constructors
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    //methods
    @Override
    public void push(E e) {
        if (size == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[size++] = e;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return data[size - 1];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E answer = data[--size];
        data[size] = null;
        return answer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = size - 1; i >= 0; i--) {
            sb.append(data[i]);
            if (i > 0) sb.append(", ");
        }
        return sb.append(")").toString();
    }


}

class Node <E> {

    /*
     * Data Fields: element, next
     * Constuctor: Node(element, next)
     * Methods: getElement, getNext, setNext, toString
     */

     // Your code here..

     private E element;
    private Node<E> next;

    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return element.toString();
    }
    
}

class LinkedStack <E> implements IStack <E> {
        /*
     * Data Fields: necessary data fields
     * Constuctor: LinkedStack()
     * Methods: Required methods, toString
     */

     // Your code here..
    private Node<E> top = null;
    private int size = 0;
    public Node<E> getTop() {return top;}

    @Override
    public void push(E e) {
        top = new Node<>(e, top);
        size++;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.getElement();
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E answer = top.getElement();
        top = top.getNext();
        size--;
        return answer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> current = top;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        return sb.append(")").toString();
    }



}

class ArrayQueue <E> implements IQueue <E> {
    /*
     * Data Fields: necessary data fields
     * Constuctors: ArrayQueue(): default capacity = 1000, ArrayQueue(int capacity)
     * Methods: Required methods, toString
     */

     // Your code here..


    private E[] data;
    private int front = 0;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 1000;

    public E[] getData() {
        return data;
    }
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    
    @Override
    public void enqueue(E e) {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }
        int avail = (front + size) % data.length;
        data[avail] = e;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E answer = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return answer;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return data[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % data.length]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append(")").toString();
    }



}

class LinkedQueue<E> implements IQueue<E> {
    /*
     * Data Fields: necessary data fields
     * Constuctors: LinkedQueue()
     * Methods: Required methods, toString
     */

     // Your code here..

     public Node<E> getHead() {
         return head;
     }

     public Node<E> getTail() {
         return tail;
     }
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;


     

    @Override
    public void enqueue(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return answer;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.getElement();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        return sb.append(")").toString();
    }




}

