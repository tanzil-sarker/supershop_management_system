import gui.ShopGUI;                // Imports the GUI
import database.DatabaseManager;   // Imports the Database
import controller.ShopController;  // Imports the Controller

public class Start 
{
    public static void main(String[] args) 
    {
        ShopGUI gui = new ShopGUI();
        DatabaseManager db = new DatabaseManager();
        ShopController controller = new ShopController(gui, db);
        
        gui.setVisible(true);
    }
}