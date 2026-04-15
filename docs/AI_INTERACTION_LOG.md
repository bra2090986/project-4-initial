Model used: Gemini 3.1 Pro

1. Generate a generic class called Bag that implements the Container interface below. The Container interface included in the template is defined as follows:
                                                                                      ```java
                                                                                      public interface Container<E> extends Iterable<E> {
                                                                                          void add(E item);
                                                                                          boolean remove(E item);
                                                                                          boolean contains(E item);
                                                                                          int size();
                                                                                          boolean isEmpty();
                                                                                      }
                                                                                      ```
   Bag implementation ***must*** use the Java ArrayList as the backing data structure.  Do not change or add to the Container interface.

    I have created the Bag class implementation for you. It implements the Container interface and uses an ArrayList as the backing data structure as requested.
   The new file is located at src/main/java/com/example/iterable/Bag.java. Here is a summary of the implementation:
   •
   It is a generic class Bag<E> that implements Container<E>.
   •
   It initializes an ArrayList in the constructor to hold the items.
   •
   It provides simple pass-through methods to the underlying ArrayList for add, remove, contains, size, isEmpty, and the iterator method required by the Iterable super-interface.
   •
   The Container interface was left completely unchanged.


2. generate comprehensive unit tests for your Bag implementation. The tests should cover edge cases, normal operations, and the iterator functionality.
    I've added comprehensive unit tests for the Bag class in the BagTest.java file. The tests use JUnit 5 and cover a wide range of scenarios, including:
    1.
    Initial State: Ensuring the bag is empty when first created (testInitialState).
    2.
    Add and Size Operations: Verifying that elements are added, including duplicates, and the size updates correctly (testAddAndSize).
    3.
    Contains: Checking whether elements are correctly identified as being present or absent (testContains).
    4.
    Remove Operations: Removing elements, handling duplicate element removal (removes only one occurrence at a time), removing elements that don't exist, and handling removal from an empty bag (testRemove, testRemoveFromEmptyBag).
    5.
    Iterator Functionality:
    ◦
    Verifying sequential access using standard hasNext() and next() methods (testIteratorNormalOperation).
    ◦
    Testing the standard enhanced for-loop for (E item : bag) (testIteratorWithEnhancedForLoop).
    ◦
    Ensuring NoSuchElementException is thrown when calling next() on an empty bag (testIteratorOnEmptyBag).
    ◦
    Checking that iterator.remove() successfully removes elements from the underlying bag (testIteratorRemove).
    6.
    Null Elements: Verifying that the bag properly supports null elements (since ArrayList supports them) with add, contains, and remove operations (testAddNull).