import gui.ShopGUI;         
import database.DatabaseManager;   
import controller.ShopController;  

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
