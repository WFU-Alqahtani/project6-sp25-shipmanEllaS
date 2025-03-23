import java.util.Random;

// linked list class for a deck of cards
public class LinkedList {

    public Node head;
    public Node tail;
    public int size = 0;

    LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for(int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            swap(r1,r2); // swap nodes at these indices
        }
    }

    // remove a card from a specific index
    public Card remove_from_index(int index) {
        Node prev = head;
        Node curr = head.next;

        if (index >= size) {
            System.out.println("Cannot remove card at index " + index);
            System.exit(1);
        }

        Card atIndex;
        for (int i = 0; i <= index; i++) {
            prev = prev.next;
            curr = curr.next;
        }
        atIndex = curr.data;
        for (int i = index; i <= size -1; i++) {
            prev.data = curr.data;
            prev = prev.next;
            curr = curr.next;
        }
        size--;
        return atIndex;
    }

    // insert a card at a specific index
    public void insert_at_index(Card x, int index) {
        Node prev = head;
        Node after = head.next;
        Node temp;
        int place = 0;

        if (index >= size) {
            System.out.println("Cannot insert Card " + x + " at index " + index);
        }

        Node newNode = new Node(x);

        if (index == 0) {       //Insert at the head
            newNode.next = head;
            head = newNode;
        } else {                //Insert at other places
            while (index <= size -1) {
                if (place == index) {
                    prev.next = newNode;
                    newNode.next = after;
                }
                prev = prev.next;
                after = after.next;
                place++;
            }
        }
        size++;
    }

    // swap two cards in the deck at the specific indices
    public void swap(int index1, int index2) {
        Node card1 = new Node();
        Node card2 = new Node();

        //int maxIndex = Math.max(index1, index2);
        Node prev = head;
        Node curr = head.next;
        int place = 0;
        //Finding card1 and card2
        while (curr != null) {
            if (place == index1) {
                card1 = curr;
            }
            if (place == index2) {
                card2 = curr;
            }
            prev = prev.next;
            curr = curr.next;
            place++;
        }

        //Swapping
        Node temp = new Node(card1.data);
        card1.data = card2.data;
        card2.data = temp.data;
    }

    // add card at the end of the list
    public void add_at_tail(Card data) {
        Node newTail = new Node(data);
        if (head == null) {     //No head
            head = newTail;
            tail = newTail;
            tail.prev = null;
            tail.next = null;
        } else {        //Add to end
            tail.next = newTail;
            newTail.prev = tail;
            newTail.next = null;
            tail = newTail;
        }
        size++;
    }

    // remove a card from the beginning of the list
    public Card remove_from_head() {
        Node prev = head;
        Node curr = head.next;

        Card atIndex = head.data;
        while (curr != null) {
            prev.data = curr.data;
            prev = prev.next;
            curr = curr.next;
        }
        size--;
        return atIndex;
    }

    // check to make sure the linked list is implemented correctly by iterating forwards and backwards
    // and verifying that the size of the list is the same when counted both ways.
    // 1) if a node is incorrectly removed
    // 2) and head and tail are correctly updated
    // 3) each node's prev and next elements are correctly updated
    public void sanity_check() {
        // count nodes, counting forward
        Node curr = head;
        int count_forward = 0;
        while (curr != null) {
            curr = curr.next;
            count_forward++;
        }

        // count nodes, counting backward
        curr = tail;
        int count_backward = 0;
        while (curr != null) {
            curr = curr.prev;
            count_backward++;
        }

        // check that forward count, backward count, and internal size of the list match
        if (count_backward == count_forward && count_backward == size) {
            System.out.println("Basic sanity Checks passed");
        }
        else {
            // there was an error, here are the stats
            System.out.println("Count forward:  " + count_forward);
            System.out.println("Count backward: " + count_backward);
            System.out.println("Size of LL:     " + size);
            System.out.println("Sanity checks failed");
            System.exit(-1);
        }
    }

    // print the deck
    public void print() {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            curr.data.print_card();
            if(curr.next != null)
                System.out.print(" -->  ");
            else
                System.out.println(" X");

            if (i % 7 == 0) System.out.println("");
            i = i + 1;
            curr = curr.next;
        }
        System.out.println("");
    }
}