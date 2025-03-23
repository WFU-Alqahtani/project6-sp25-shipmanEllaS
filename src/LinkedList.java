/**********************************************************************************************
 * @file : LinkedList.java
 * @description : A linked list class that manages nodes with data of type Card. Notable
 *                functions include shuffling the cards, adding/removing cards, printing
 *                individual nodes, checking for an empty list, and a sanity check.
 * @author : Ella Shipman
 * @date : 23 March 2025
 *********************************************************************************************/

import java.util.Random;

public class LinkedList {

    public Node head;
    public Node tail;
    public int size = 0;

    LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /*  --------------------------------------------------------------------------------------
     *   shuffle - shuffles the cards in the deck
     *   int shuffle_count : the number of times the deck is shuffled
     */
    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for(int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            if (r1 == r2) {
                r2 += 1;
            }
            swap(r1,r2); // swap nodes at these indices
        }
    }

    /*  --------------------------------------------------------------------------------------
     *   remove_from_index - removes card at a certain index
     *   int index : the card to be removed
     */
    public Card remove_from_index(int index) {
        size--;
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
        for (int i = index; i <= size; i++) {
            prev.data = curr.data;
            prev = prev.next;
            curr = curr.next;
        }
        return atIndex;
    }

    /*  --------------------------------------------------------------------------------------
     *   insert_from_index - inserts card at a certain index
     *   Card x : the card to be inserted
     *   int index : the place to insert the card
     */
    public void insert_at_index(Card x, int index) {
        size++;
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
            while (place <= index && after != null) {
                if (place == index) {
                    prev.next = newNode;
                    newNode.next = after;
                }
                prev = prev.next;
                after = after.next;
                place++;
            }
        }
    }

    /*  --------------------------------------------------------------------------------------
     *   swap - swaps the positions of two cards at specific indexes
     *   int index1 : the first index to swap
     *   int index2 + the second index to swap
     */    public void swap(int index1, int index2) {
        Node card1 = new Node();
        Node card2 = new Node();

        Node curr = head;
        int place = 0;
        //Finding card1 and card2
        while (curr != null) {
            if (place == index1) {
                card1 = curr;
            }
            if (place == index2) {
                card2 = curr;
            }
            curr = curr.next;
            place++;
        }

        //Swapping
        Node temp = new Node(card1.data);
        card1.data = card2.data;
        card2.data = temp.data;
    }

    /*  --------------------------------------------------------------------------------------
     *   add_at_tail - appends card to the end of the list
     *   Card data : the card to be added
     */
    public void add_at_tail(Card data) {
        size++;
        Node newTail = new Node(data);
        if (head == null) {     //No head
            head = newTail;
            tail = newTail;
        } else if (head != null && head.next == null) {
            head.next = newTail;
            tail = newTail;
            tail.prev = head;
        } else {        //Add to end
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
    }

    /*  --------------------------------------------------------------------------------------
     *   remove_from_head - removes card at the top of the deck
     */
    public Card remove_from_head() {
        size--;
        Node prev = head;
        Node curr = head.next;

        Card atIndex = head.data;
        if (curr == null) {
            head = null;
            return atIndex;
        }

        while (curr != null) {
            prev.data = curr.data;
            if (curr.next == null) {
                prev.next = null;
                tail = prev;
            }
            prev = prev.next;
            curr = curr.next;
        }
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

    /*  --------------------------------------------------------------------------------------
     *   print - printing the deck
     */
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

    /*  --------------------------------------------------------------------------------------
     *   isEmpty - checks if the list is empty
     */
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }
}