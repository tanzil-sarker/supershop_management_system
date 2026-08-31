package database;

import model.Transaction; // Imports the Transaction model
import javax.swing.*;
import java.io.*;

public class DatabaseManager 
{
    private File file;
    private FileWriter fw;
    private FileReader fr;

    public void insertInfo(Transaction t) 
    {
        try 
        {
            file = new File("userdata.txt");
            if (!file.exists()) 
            {
                file.createNewFile();
            }
            fw = new FileWriter(file, true);
            fw.write(t.getS1() + "\n");
            fw.write(t.getS2() + "\n");
            fw.write(t.getS3() + "\n");
            fw.write(t.getS4() + "\n");
            fw.write(t.getS5() + "\n");
            fw.flush();
            fw.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public String[] getInfo(String customerName) 
    {
        try 
        {
            file = new File("userdata.txt");
            if (!file.exists()) return null;
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String a, b, c, d, e;
            while ((a = br.readLine()) != null) 
            {
                b = br.readLine(); c = br.readLine(); d = br.readLine(); e = br.readLine();
                if (a.equalsIgnoreCase(customerName)) 
                {
                    br.close();
                    return new String[]{a, b, c, d, e};
                }
            }
            br.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return null;
    }

    public boolean updateInfo(String customerName, Transaction t) 
    {
        return rewriteInfo(customerName, true, t);
    }

    public boolean deleteInfo(String customerName) 
    {
        return rewriteInfo(customerName, false, null);
    }

    private boolean rewriteInfo(String customerName, boolean update, Transaction t) 
    {
        boolean found = false;
        try 
        {
            file = new File("userdata.txt");
            if (!file.exists()) return false;
            File tempFile = new File("temp.txt");
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            FileWriter tempWriter = new FileWriter(tempFile);
            String a, b, c, d, e;

            while ((a = br.readLine()) != null) 
            {
                b = br.readLine(); c = br.readLine(); d = br.readLine(); e = br.readLine();
                if (a.equalsIgnoreCase(customerName)) 
                {
                    found = true;
                    if (update && t != null) 
                    {
                        tempWriter.write(t.getS1() + "\n");
                        tempWriter.write(t.getS2() + "\n");
                        tempWriter.write(t.getS3() + "\n");
                        tempWriter.write(t.getS4() + "\n");
                        tempWriter.write(t.getS5() + "\n");
                    }
                } 
                else 
                {
                    tempWriter.write(a + "\n");
                    tempWriter.write(b + "\n");
                    tempWriter.write(c + "\n");
                    tempWriter.write(d + "\n");
                    tempWriter.write(e + "\n");
                }
            }
            br.close();
            tempWriter.flush();
            tempWriter.close();

            if (file.delete()) tempFile.renameTo(file);
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return found;
    }
}