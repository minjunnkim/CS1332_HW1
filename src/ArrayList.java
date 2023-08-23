/**
 * Your implementation of an ArrayList.
 *
 * @author Minjun Kim
 * @version 1.0
 * @userid mkim925
 * @GTID 903873051
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];

        //Size starts as 0 as no data is in the newly made ArrayList.
        size = 0;
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     * @throws IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        //Index out of bounds check.
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be within the ArrayList's bounds (0~size)");
        }
        //Illegal Argument check.
        illegalArg(data);

        //Redo the backing array if the backing array runs out of space.
        if (size == backingArray.length) {
            redoBackingArray();
        }

        //For when index is not size.
        if (index != size) {
            //Shift one index forward for all values from the end until before the index.
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
        }

        //Input new data in the desired index.
        backingArray[index] = data;

        //Increase size by one to accommodate for the new data.
        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        //Illegal Argument check.
        illegalArg(data);

        //Simply use the already-written addAtIndex() method.
        addAtIndex(0,data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        //Illegal Argument check.
        illegalArg(data);

        //Simply use the already-written addAtIndex() method.
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        //Index out of bounds check.
        indexOOB(index);

        //Save the data that will be removed for output later in the method.
        T removedData = get(index);

        //For when index is not size - 1.
        if (index != size-1) {
            //Shift the relevant data backwards by one index.
            for (int i = index; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
        }

        //Set the removed index to null.
        backingArray[size - 1] = null;

        //Decrease size to accommodate for the removed data.
        size--;

        //Return the data formerly located at the index.
        return removedData;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        //No Such Element check.
        noSE();

        //Simply use the already written removeAtIndex() method.
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        //No Such Element check.
        noSE();

        //Simply use the already written removeAtIndex() method.
        return removeAtIndex(size-1);
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        //Index out of bounds check.
        indexOOB(index);

        //Get the value from the backing array.
        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        //Check and return
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        //Reset backing array & size.
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Private Helper Methods
     */

    //Index Out of Bounds Exception
    private void indexOOB(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be within the ArrayList's bounds.");
        }
    }

    //Illegal Argument Exception
    private void illegalArg(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        }
    }

    //No Such Element Exception
    private void noSE() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("List is empty");
        }
    }

    //Extend/update the backing array
    private void redoBackingArray() {
        int newCapacity = backingArray.length * 2;
        T[] newBackingArray = (T[]) new Object[newCapacity];

        // Manual arraycopy
        for (int i = 0; i < size; i++) {
            newBackingArray[i] = backingArray[i];
        }

        // Set all unused positions in the new backing array to null
        for (int i = size; i < newBackingArray.length; i++) {
            newBackingArray[i] = null;
        }

        backingArray = newBackingArray;

    }

}
