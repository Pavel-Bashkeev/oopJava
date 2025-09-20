package ru.bashkeev.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class AdvancedLinkedListTest {

    private AdvancedLinkedList<String> list;
    private AdvancedLinkedList<String> emptyList;

    @BeforeEach
    void setUp() {
        list = new AdvancedLinkedList<>();
        list.addFirst("Third");
        list.addFirst("Second");
        list.addFirst("First");

        emptyList = new AdvancedLinkedList<>();
    }

    @Test
    void testAddFirstToEmptyList() {
        AdvancedLinkedList.Node<String> node = emptyList.addFirst("Test");
        assertNotNull(node);
        assertEquals("Test", node.getNodeData());
        assertEquals(1, emptyList.size());
        assertEquals(emptyList.getHead(), node);
        assertEquals(emptyList.getTail(), node);
    }

    @Test
    void testAddFirstToNonEmptyList() {
        int initialSize = list.size();
        AdvancedLinkedList.Node<String> node = list.addFirst("NewFirst");

        assertEquals(initialSize + 1, list.size());
        assertEquals("NewFirst", list.getHead().getNodeData());
        assertEquals("First", list.getHead().next.getNodeData());
        assertNull(list.getHead().prev);
    }

    @Test
    void testAddLastToEmptyList() {
        AdvancedLinkedList.Node<String> node = emptyList.addLast("Test");
        assertNotNull(node);
        assertEquals("Test", node.getNodeData());
        assertEquals(1, emptyList.size());
        assertEquals(emptyList.getHead(), node);
        assertEquals(emptyList.getTail(), node);
    }

    @Test
    void testAddLastToNonEmptyList() {
        int initialSize = list.size();
        AdvancedLinkedList.Node<String> node = list.addLast("NewLast");

        assertEquals(initialSize + 1, list.size());
        assertEquals("NewLast", list.getTail().getNodeData());
        assertEquals("Third", list.getTail().prev.getNodeData());
        assertNull(list.getTail().next);
    }

    @Test
    void testAddAfterHead() {
        AdvancedLinkedList.Node<String> head = list.getHead();
        AdvancedLinkedList.Node<String> newNode = list.addAfter(head, "AfterHead");

        assertEquals(4, list.size());
        assertEquals("AfterHead", head.next.getNodeData());
        assertEquals("Second", newNode.next.getNodeData());
        assertEquals("First", newNode.prev.getNodeData());
    }

    @Test
    void testAddAfterTail() {
        AdvancedLinkedList.Node<String> tail = list.getTail();
        AdvancedLinkedList.Node<String> newNode = list.addAfter(tail, "AfterTail");

        assertEquals(4, list.size());
        assertEquals("AfterTail", list.getTail().getNodeData());
        assertEquals("Third", newNode.prev.getNodeData());
        assertNull(newNode.next);
    }

    @Test
    void testAddAfterMiddleNode() {
        AdvancedLinkedList.Node<String> middle = list.getHead().next;
        AdvancedLinkedList.Node<String> newNode = list.addAfter(middle, "AfterMiddle");

        assertEquals(4, list.size());
        assertEquals("AfterMiddle", middle.next.getNodeData());
        assertEquals("Third", newNode.next.getNodeData());
        assertEquals("Second", newNode.prev.getNodeData());
    }

    @Test
    void testAddAfterWithNullNode() {
        assertThrows(NullPointerException.class, () -> list.addAfter(null, "Test"));
    }

    @Test
    void testAddAfterWithForeignNode() {
        AdvancedLinkedList<String> otherList = new AdvancedLinkedList<>();
        AdvancedLinkedList.Node<String> foreignNode = otherList.addFirst("Foreign");

        assertThrows(IllegalArgumentException.class, () -> list.addAfter(foreignNode, "Test"));
    }

    @Test
    void testAddBeforeHead() {
        AdvancedLinkedList.Node<String> head = list.getHead();
        AdvancedLinkedList.Node<String> newNode = list.addBefore(head, "BeforeHead");

        assertEquals(4, list.size());
        assertEquals("BeforeHead", list.getHead().getNodeData());
        assertEquals("First", newNode.next.getNodeData());
        assertNull(newNode.prev);
    }

    @Test
    void testAddBeforeTail() {
        AdvancedLinkedList.Node<String> tail = list.getTail();
        AdvancedLinkedList.Node<String> newNode = list.addBefore(tail, "BeforeTail");

        assertEquals(4, list.size());
        assertEquals("BeforeTail", tail.prev.getNodeData());
        assertEquals("Third", newNode.next.getNodeData());
        assertEquals("Second", newNode.prev.getNodeData());
    }

    @Test
    void testAddBeforeMiddleNode() {
        AdvancedLinkedList.Node<String> middle = list.getTail().prev;
        AdvancedLinkedList.Node<String> newNode = list.addBefore(middle, "BeforeMiddle");

        assertEquals(4, list.size());
        assertEquals("BeforeMiddle", list.getHead().next.getNodeData());
        assertEquals("Second", newNode.next.getNodeData());
        assertEquals("First", newNode.prev.getNodeData());
    }

    @Test
    void testAddBeforeWithNullNode() {
        assertThrows(NullPointerException.class, () -> list.addBefore(null, "Test"));
    }

    @Test
    void testAddBeforeWithForeignNode() {
        AdvancedLinkedList<String> otherList = new AdvancedLinkedList<>();
        AdvancedLinkedList.Node<String> foreignNode = otherList.addFirst("Foreign");

        assertThrows(IllegalArgumentException.class, () -> list.addBefore(foreignNode, "Test"));
    }

    @Test
    void testRemoveHead() {
        AdvancedLinkedList.Node<String> head = list.getHead();
        String removed = list.remove(head);

        assertEquals("First", removed);
        assertEquals(2, list.size());
        assertEquals("Second", list.getHead().getNodeData());
        assertNull(list.getHead().prev);
    }

    @Test
    void testRemoveTail() {
        AdvancedLinkedList.Node<String> tail = list.getTail();
        String removed = list.remove(tail);

        assertEquals("Third", removed);
        assertEquals(2, list.size());
        assertEquals("Second", list.getTail().getNodeData());
        assertNull(list.getTail().next);
    }

    @Test
    void testRemoveMiddle() {
        AdvancedLinkedList.Node<String> middle = list.getHead().next;
        String removed = list.remove(middle);

        assertEquals("Second", removed);
        assertEquals(2, list.size());
        assertEquals("Third", list.getHead().next.getNodeData());
        assertEquals("First", list.getTail().prev.getNodeData());
    }

    @Test
    void testRemoveSingleNode() {
        AdvancedLinkedList<String> singleList = new AdvancedLinkedList<>();
        AdvancedLinkedList.Node<String> node = singleList.addFirst("Single");
        String removed = singleList.remove(node);

        assertEquals("Single", removed);
        assertEquals(0, singleList.size());
        assertNull(singleList.getHead());
        assertNull(singleList.getTail());
    }

    @Test
    void testRemoveWithNullNode() {
        assertThrows(NullPointerException.class, () -> list.remove(null));
    }

    @Test
    void testRemoveWithForeignNode() {
        AdvancedLinkedList<String> otherList = new AdvancedLinkedList<>();
        AdvancedLinkedList.Node<String> foreignNode = otherList.addFirst("Foreign");

        assertThrows(IllegalArgumentException.class, () -> list.remove(foreignNode));
    }

    @Test
    void testSize() {
        assertEquals(3, list.size());
        assertEquals(0, emptyList.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(emptyList.isEmpty());
        assertFalse(list.isEmpty());
    }

    @Test
    void testGetHead() {
        assertNotNull(list.getHead());
        assertEquals("First", list.getHead().getNodeData());
        assertNull(emptyList.getHead());
    }

    @Test
    void testGetTail() {
        assertNotNull(list.getTail());
        assertEquals("Third", list.getTail().getNodeData());
        assertNull(emptyList.getTail());
    }

    @Test
    void testGetNodeByIndexFromStart() {
        AdvancedLinkedList.Node<String> node = list.getNodeByIndex(1);
        assertNotNull(node);
        assertEquals("Second", node.getNodeData());
    }

    @Test
    void testGetNodeByIndexFromEnd() {
        AdvancedLinkedList.Node<String> node = list.getNodeByIndex(2);
        assertNotNull(node);
        assertEquals("Third", node.getNodeData());
    }

    @Test
    void testGetNodeByIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.getNodeByIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.getNodeByIndex(3));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.getNodeByIndex(0));
    }

    @Test
    void testToString() {
        assertEquals("[First, Second, Third]", list.toString());
        assertEquals("[]", emptyList.toString());
    }

    @Test
    void testIterator() {
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("First", iterator.next());
        assertEquals("Second", iterator.next());
        assertEquals("Third", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorOnEmptyList() {
        Iterator<String> iterator = emptyList.iterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorConcurrentModification() {
        Iterator<String> iterator = list.iterator();
        iterator.next();

        list.addLast("Fourth");

        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    void testNodeGetSetData() {
        AdvancedLinkedList.Node<String> node = list.getHead();
        assertEquals("First", node.getNodeData());

        node.setNodeData("Updated");
        assertEquals("Updated", node.getNodeData());
        assertEquals("Updated", list.getHead().getNodeData());
    }

    @Test
    void testComplexScenario() {
        AdvancedLinkedList<Integer> complexList = new AdvancedLinkedList<>();

        AdvancedLinkedList.Node<Integer> first = complexList.addFirst(1);
        AdvancedLinkedList.Node<Integer> last = complexList.addLast(3);
        AdvancedLinkedList.Node<Integer> middle = complexList.addBefore(last, 2);

        assertEquals(3, complexList.size());
        assertEquals(Integer.valueOf(1), complexList.getHead().getNodeData());
        assertEquals(Integer.valueOf(3), complexList.getTail().getNodeData());
        assertEquals(middle, complexList.getHead().next);
        assertEquals(first, complexList.getTail().prev.prev);

        complexList.remove(middle);
        assertEquals(2, complexList.size());
        assertEquals(complexList.getHead().next, complexList.getTail());
        assertEquals(complexList.getTail().prev, complexList.getHead());
    }

    @Test
    void testMultipleOperations() {
        AdvancedLinkedList<String> testList = new AdvancedLinkedList<>();

        AdvancedLinkedList.Node<String> node1 = testList.addFirst("A");
        AdvancedLinkedList.Node<String> node2 = testList.addLast("C");
        AdvancedLinkedList.Node<String> node3 = testList.addBefore(node2, "B");
        AdvancedLinkedList.Node<String> node4 = testList.addAfter(node3, "D");

        assertEquals(4, testList.size());
        assertEquals("[A, B, D, C]", testList.toString());

        testList.remove(node3);
        assertEquals(3, testList.size());
        assertEquals("[A, D, C]", testList.toString());

        testList.remove(node1);
        assertEquals(2, testList.size());
        assertEquals("[D, C]", testList.toString());

        testList.remove(node4);
        assertEquals(1, testList.size());
        assertEquals("[C]", testList.toString());

        testList.remove(node2);
        assertEquals(0, testList.size());
        assertTrue(testList.isEmpty());
    }

    @Test
    void testNodeOwnership() {
        AdvancedLinkedList.Node<String> node = list.getHead();
        assertEquals(list, node.owner);

        list.remove(node);
        assertNull(node.owner);
        assertNull(node.next);
        assertNull(node.prev);
        assertNull(node.data);
    }
}