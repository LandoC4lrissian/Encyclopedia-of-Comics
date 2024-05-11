package project;

import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.List;

public class BST {
    private TreeNode root;

    private class TreeNode {
        List<Entry> entries;
        TreeNode left, right;

        TreeNode(Entry entry) {
            this.entries = new ArrayList<>();
            this.entries.add(entry);
            this.left = this.right = null;
        }
    }

    public void insert(Entry entry) {
        root = insertRec(root, entry);
    }

    private TreeNode insertRec(TreeNode root, Entry entry) {
        if (root == null) {
            root = new TreeNode(entry);
            return root;
        }

        int cmp = entry.getTitle().compareTo(root.entries.get(0).getTitle());
        if (cmp < 0) {
            root.left = insertRec(root.left, entry);
        } else if (cmp > 0) {
            root.right = insertRec(root.right, entry);
        } else {
            root.entries.add(entry);  
        }

        return root;
    }

    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            for (Entry entry : root.entries) {
                System.out.println(entry.getTitle() + ", " + entry.getAuthor());
            }
            inorder(root.right);
        }
    }

    public void inorderTraversalToList(DefaultListModel<String> listModel) {
        inorderToList(root, listModel);
    }

    private void inorderToList(TreeNode node, DefaultListModel<String> listModel) {
        if (node != null) {
            inorderToList(node.left, listModel);
            for (Entry entry : node.entries) {
                listModel.addElement(entry.getTitle());
            }
            inorderToList(node.right, listModel);
        }
    }

    public void searchAndDisplayResults(String searchText, JTextArea textArea) {
        inorderSearch(root, searchText, textArea);
    }

    private void inorderSearch(TreeNode node, String searchText, JTextArea textArea) {
        if (node != null) {
            inorderSearch(node.left, searchText, textArea);
            for (Entry entry : node.entries) {
                if (matchesSearch(entry, searchText)) {
                    textArea.append(formatEntryDescription(entry) + "\n");
                }
            }
            inorderSearch(node.right, searchText, textArea);
        }
    }

    private boolean matchesSearch(Entry entry, String searchText) {
        return entry.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
               entry.getAuthor().toLowerCase().contains(searchText.toLowerCase()) ||
               entry.getPublisher().toLowerCase().contains(searchText.toLowerCase()) ||
               Integer.toString(entry.getYear()).contains(searchText) ||
               entry.getGenre().toLowerCase().contains(searchText.toLowerCase());
    }

    private String formatEntryDescription(Entry entry) {
        return entry.getTitle() + " by " + entry.getAuthor() +
               ", Publisher: " + entry.getPublisher() +
               ", Year: " + entry.getYear() +
               ", Genre: " + entry.getGenre();
    }

    public Entry findEntry(String title) {
        return findRec(root, title);
    }

    private Entry findRec(TreeNode node, String title) {
        if (node == null) return null;

        int compareResult = title.compareTo(node.entries.get(0).getTitle());
        if (compareResult < 0) {
            return findRec(node.left, title);
        } else if (compareResult > 0) {
            return findRec(node.right, title);
        } else {
            return node.entries.get(0);  
        }
    }

    public void searchAndDisplayResults(String searchText, JTextArea textArea, DefaultListModel<String> listModel) {
        inorderSearch(root, searchText, textArea, listModel);
    }

    private void inorderSearch(TreeNode node, String searchText, JTextArea textArea, DefaultListModel<String> listModel) {
        if (node != null) {
            inorderSearch(node.left, searchText, textArea, listModel);
            for (Entry entry : node.entries) {
                if (matchesSearch(entry, searchText)) {
                    textArea.append(formatEntryDescription(entry) + "\n");
                    listModel.addElement(entry.getTitle());
                }
            }
            inorderSearch(node.right, searchText, textArea, listModel);
        }
    }

    public void searchAndDisplayResultsWithFilters(String genre, String publisher, String year, String author, JTextArea textArea, DefaultListModel<String> listModel) {
        inorderSearchWithFilters(root, genre, publisher, year, author, textArea, listModel);
    }
    
    
    private void inorderSearchWithFilters(TreeNode node, String genre, String publisher, String year, String author, JTextArea textArea, DefaultListModel<String> listModel) {
        if (node == null) return;
    
        inorderSearchWithFilters(node.left, genre, publisher, year, author, textArea, listModel);
    
        for (Entry entry : node.entries) {
            if ((genre.equals("All") || entry.getGenre().equalsIgnoreCase(genre)) &&
                (publisher.equals("All") || entry.getPublisher().equalsIgnoreCase(publisher)) &&
                (year.equals("All") || Integer.toString(entry.getYear()).equals(year)) &&
                (author.isEmpty() || entry.getAuthor().toLowerCase().contains(author.toLowerCase()))) {
                textArea.append(entry.getDetails() + "\n");
                listModel.addElement(entry.getTitle());
            }
        }
    
        inorderSearchWithFilters(node.right, genre, publisher, year, author, textArea, listModel);
    }
}
