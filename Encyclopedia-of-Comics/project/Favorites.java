package project;

import javax.swing.DefaultListModel;

public class Favorites {
    private LinkedList favouritesList;
    private DefaultListModel<String> listModel; 

    public Favorites(DefaultListModel<String> listModel) {
        this.favouritesList = new LinkedList();
        this.listModel = listModel; 
    }

    public void addFavourite(Entry entry) {
        favouritesList.add(entry);
        updateListModel(); 
    }

    private void updateListModel() {
        listModel.clear();
        for (Entry entry : favouritesList.entries()) {
            listModel.addElement(entry.getTitle());
        }
    }

    public void removeFavourite(String title) {
        favouritesList.remove(title);
        updateListModel(); 
    }

    
    public DefaultListModel<String> getListModel() {
        return listModel;
    }
}
