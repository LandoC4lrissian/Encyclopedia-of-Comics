package project;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class LinkedList {
    private Node head;

    private class Node {
        Entry entry;
        Node next;

        Node(Entry entry) {
            this.entry = entry;
            this.next = null;
        }
    }

    public void add(Entry entry) {
        Node newNode = new Node(entry);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.entry.getTitle() + ", " + current.entry.getAuthor());
            current = current.next;
        }
    }

    
    public void addToListModel(DefaultListModel<String> listModel) {
        Node current = head;
        while (current != null) {
            listModel.addElement(current.entry.getTitle());
            current = current.next;
        }
    }

    
    public void displayGenres(DefaultListModel<String> listModel, String genre) {
        Node current = head;
        while (current != null) {
            if (current.entry.getGenre().equalsIgnoreCase(genre)) {
                listModel.addElement(current.entry.getTitle());
            }
            current = current.next;
        }
    }

    public void displayPublishers(DefaultListModel<String> listModel, String publisher) {
        Node current = head;
        while (current != null) {
            if (current.entry.getPublisher().equalsIgnoreCase(publisher)) {
                listModel.addElement(current.entry.getTitle());
            }
            current = current.next;
        }
    }

    
    public Entry findEntry(String title) {
        Node current = head;
        while (current != null) {
            if (current.entry.getTitle().equalsIgnoreCase(title)) {
                return current.entry;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(String title) {
        Node current = head, prev = null;
        
        
        if (current != null && current.entry.getTitle().equalsIgnoreCase(title)) {
            head = current.next; 
            return;
        }

        
        while (current != null && !current.entry.getTitle().equalsIgnoreCase(title)) {
            prev = current;
            current = current.next;
        }

        
        if (current == null) return;

        
        prev.next = current.next;
    }

    public void iterate(DefaultListModel<String> listModel) {
        Node current = head;
        while (current != null) {
            listModel.addElement(current.entry.getTitle());
            current = current.next;
        }
    }
     public Iterable<Entry> entries() {
        List<Entry> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.entry);
            current = current.next;
        }
        return list;
    }
}
