package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    
    private JFrame frame;
    private JTextField searchField,authorTextField;
    private JTextArea textArea;
    private JList<String> entryList, favoritesList;
    private DefaultListModel<String> listModel, favoritesListModel;
    private LinkedList linkedList;
    private BST bst;
    private JButton favButton;
    private Favorites favorites;
    private JComboBox<String> genreComboBox, publisherComboBox, yearComboBox;

    public Main() {
        initializeDataStructures();
        initializeGUI();
    }

    private void initializeDataStructures() {
        linkedList = new LinkedList();
        bst = new BST();
        favoritesListModel = new DefaultListModel<>();
        favorites = new Favorites(favoritesListModel);
    
          
        linkedList.add(new Entry("Naruto", "Masashi Kishimoto", "Shueisha", 1999, "Adventure, Fantasy"));
        linkedList.add(new Entry("One Piece", "Eiichiro Oda", "Shueisha", 1997, "Adventure, Action"));
        linkedList.add(new Entry("Bleach", "Tite Kubo", "Shueisha", 2001, "Action, Supernatural"));
        linkedList.add(new Entry("Attack on Titan", "Hajime Isayama", "Kodansha", 2009, "Action, Dark Fantasy"));
        linkedList.add(new Entry("My Hero Academia", "Kohei Horikoshi", "Shueisha", 2014, "Superhero, Action"));
        linkedList.add(new Entry("Dragon Ball", "Akira Toriyama", "Shueisha", 1984, "Action, Adventure"));
        linkedList.add(new Entry("Hunter x Hunter", "Yoshihiro Togashi", "Shueisha", 1998, "Adventure, Fantasy"));
        linkedList.add(new Entry("Tokyo Ghoul", "Sui Ishida", "Shueisha", 2011, "Dark Fantasy, Horror"));
        linkedList.add(new Entry("Death Note", "Tsugumi Ohba", "Shueisha", 2003, "Mystery, Thriller"));
        linkedList.add(new Entry("Spider-Man: The Night Gwen Stacy Died", "Gerry Conway", "Marvel Comics", 1973, "Superhero, Drama"));
        linkedList.add(new Entry("The Avengers", "Stan Lee", "Marvel Comics", 1963, "Superhero, Action"));
        linkedList.add(new Entry("Black Panther", "Stan Lee", "Marvel Comics", 1966, "Superhero, Adventure"));
        linkedList.add(new Entry("X-Men: Days of Future Past", "Chris Claremont", "Marvel Comics", 1981, "Superhero, Sci-Fi"));
        linkedList.add(new Entry("Batman: The Killing Joke", "Alan Moore", "DC Comics", 1988, "Superhero, Psychological Thriller"));
        linkedList.add(new Entry("Watchmen", "Alan Moore", "DC Comics", 1986, "Superhero, Mystery"));
        linkedList.add(new Entry("The Dark Knight Returns", "Frank Miller", "DC Comics", 1986, "Superhero, Action"));
        linkedList.add(new Entry("Superman: Red Son", "Mark Millar", "DC Comics", 2003, "Superhero, Alternate History"));
        linkedList.add(new Entry("The Infinity Gauntlet", "Jim Starlin", "Marvel Comics", 1991, "Superhero, Cosmic"));
        linkedList.add(new Entry("Civil War", "Mark Millar", "Marvel Comics", 2006, "Superhero, Political"));
        linkedList.add(new Entry("Thor: Ragnarok", "Michael Avon Oeming", "Marvel Comics", 2004, "Superhero, Mythology"));
        linkedList.add(new Entry("Iron Man: Demon in a Bottle", "David Michelinie and Bob Layton", "Marvel Comics", 1979, "Superhero, Drama"));
        linkedList.add(new Entry("Daredevil: Born Again", "Frank Miller", "Marvel Comics", 1986, "Superhero, Crime"));
        linkedList.add(new Entry("The Ultimates", "Mark Millar", "Marvel Comics", 2002, "Superhero, Sci-Fi"));
        linkedList.add(new Entry("Hawkeye: My Life as a Weapon", "Matt Fraction", "Marvel Comics", 2012, "Superhero, Action"));
        linkedList.add(new Entry("Guardians of the Galaxy", "Dan Abnett and Andy Lanning", "Marvel Comics", 2008, "Superhero, Space Opera"));
        linkedList.add(new Entry("All Star Superman", "Grant Morrison", "DC Comics", 2005, "Superhero, Sci-Fi"));
        linkedList.add(new Entry("Green Lantern: Rebirth", "Geoff Johns", "DC Comics", 2004, "Superhero, Cosmic"));
        linkedList.add(new Entry("The Flash: Rebirth", "Geoff Johns", "DC Comics", 2009, "Superhero, Sci-Fi"));
        linkedList.add(new Entry("Aquaman: The Trench", "Geoff Johns", "DC Comics", 2011, "Superhero, Adventure"));
        linkedList.add(new Entry("Justice League: The New Frontier", "Darwyn Cooke", "DC Comics", 2004, "Superhero, Historical"));
        linkedList.add(new Entry("Batman: Year One", "Frank Miller", "DC Comics", 1987, "Superhero, Crime"));
        linkedList.add(new Entry("Wonder Woman: The Hiketeia", "Greg Rucka", "DC Comics", 2002, "Superhero, Mythology"));
        linkedList.add(new Entry("Sandman", "Neil Gaiman", "DC Comics", 1989, "Fantasy, Horror"));

        
        for (Entry entry : linkedList.entries()) {
            bst.insert(entry);
        }
    }

    private void initializeGUI() {
        frame = new JFrame("Encyclopedia of Comics and Manga");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        initSearchPanel();
        initListPanel();
        initFilterPanel();
        initDetailsPanel();

        frame.setVisible(true);
    }

    private void initSearchPanel() {
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); 
    
        
        searchField = new JTextField(25); 
    
        
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(120, 40)); 
        searchButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        searchButton.setBackground(new Color(100, 149, 237));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(e -> performSearch(searchField.getText()));
    
       
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
    
        
        frame.getContentPane().add(searchPanel, BorderLayout.NORTH);
    }
    
    private void initListPanel() {
        listModel = new DefaultListModel<>();
        entryList = new JList<>(listModel);
        entryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showDetails(entryList.getSelectedValue());
            }
        });
    
        favButton = new JButton("Add to Favorites");
        favButton.addActionListener(e -> addToFavorites(entryList.getSelectedValue()));
        favButton.setBackground(new Color(100, 149, 237));
        favButton.setForeground(Color.WHITE);
    
        JPanel listButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        listButtonsPanel.add(favButton);
    
        JScrollPane entryScrollPane = new JScrollPane(entryList);
        entryScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(entryScrollPane, BorderLayout.CENTER);
        listPanel.add(listButtonsPanel, BorderLayout.SOUTH);
        listPanel.setBackground(new Color(100, 149, 237));
    
        favoritesList = new JList<>(favorites.getListModel());
        JScrollPane favoritesScrollPane = new JScrollPane(favoritesList);
        favoritesScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    
        JPanel favoritesLabelPanel = new JPanel(new BorderLayout());
        favoritesLabelPanel.add(new JLabel("Favorites"), BorderLayout.CENTER);
        favoritesLabelPanel.setBackground(new Color(135, 206, 235)); 
        favoritesLabelPanel.setForeground(Color.WHITE);
    
        JPanel favoritesPanel = new JPanel(new BorderLayout());
        favoritesPanel.add(favoritesLabelPanel, BorderLayout.NORTH);
        favoritesPanel.add(favoritesScrollPane, BorderLayout.CENTER);
        favoritesPanel.setBackground(new Color(100, 149, 237)); 
    
        frame.getContentPane().add(listPanel, BorderLayout.LINE_START);
        frame.getContentPane().add(favoritesPanel, BorderLayout.LINE_END);
    }
    private void initFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
    
        genreComboBox = new JComboBox<>(new String[]{"All", "Adventure", "Action", "Supernatural", "Dark Fantasy", "Superhero", "Mystery", "Thriller", "Drama", "Sci-Fi", "Crime", "Space Opera", "Historical", "Horror", "Fantasy", "Alternate History", "Psychological Thriller", "Mythology", "Cosmic"});
        publisherComboBox = new JComboBox<>(new String[]{"All", "Shueisha", "Kodansha","Marvel Comics","DC Comics"});
        yearComboBox = new JComboBox<>(new String[]{"All","1984", "1986", "1988", "1991",  "1997", "1999", "2001","2002", "2003","2004", "2005", "2006", "2007","2008", "2009",  "2011", "2012", "2013","2014",  "2016", "2017", "2018", "2019", "2020", "2021"});
        authorTextField = new JTextField(10);
        JButton filterButton = new JButton("Apply Filter");
        filterButton.setBackground(new Color(100, 149, 237)); 
        filterButton.setForeground(Color.WHITE);
        filterButton.setFont(new Font("Arial", Font.BOLD, 12));
        filterButton.addActionListener(e -> applyAnnouncements());
    
        filterPanel.add(new JLabel("Genre:"), gbc);
        gbc.gridx = 1;
        filterPanel.add(genreComboBox, gbc);
        gbc.gridx = 0; gbc.gridy++;
        filterPanel.add(new JLabel("Publisher:"), gbc);
        gbc.gridx = 1;
        filterPanel.add(publisherComboBox, gbc);
        gbc.gridx = 0; gbc.gridy++;
        filterPanel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1;
        filterPanel.add(yearComboBox, gbc);
        gbc.gridx = 0; gbc.gridy++;
        filterPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        filterPanel.add(authorTextField, gbc);
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        filterPanel.add(filterButton, gbc);
    
        frame.getContentPane().add(filterPanel, BorderLayout.SOUTH);
    }
    
    private void initDetailsPanel() {
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void addToFavorites(String entryTitle) {
        if (entryTitle == null || entryTitle.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select a valid entry.");
            return;
        }
        Entry entry = bst.findEntry(entryTitle);
        if (entry != null) {
            favorites.addFavourite(entry);
            JOptionPane.showMessageDialog(frame, "Added to favorites: " + entry.getTitle());
        } else {
            JOptionPane.showMessageDialog(frame, "Entry not found: " + entryTitle);
        }
    }
    private void performSearch(String searchText) {
        textArea.setText("");  
        listModel.clear();  
        if (searchText.isEmpty()) {
            textArea.append("Please enter a search term.");
            return;
        }
    
        
        bst.searchAndDisplayResults(searchText, textArea, listModel);
    }
    private void showDetails(String entryTitle) {
        Entry entry = bst.findEntry(entryTitle);
        if (entry != null) {
            JOptionPane.showMessageDialog(frame, entry.getDetails(), "Details of " + entry.getTitle(), JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "No details found for: " + entryTitle);
        }
    }
    private void applyAnnouncements() {
        String genre = (String) genreComboBox.getSelectedItem();
        String publisher = (String) publisherComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();
        String author = authorTextField.getText().trim();
    
        
        textArea.setText(""); 
        listModel.clear(); 
    
        bst.searchAndDisplayResultsWithFilters(genre, publisher, year, author, textArea, listModel);
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
