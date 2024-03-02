package zoho_3_helper;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFile
{
    public String directory, fileName, fileContent;
    public MyFile(String dir, String fileName, String fileContent)
    {
        this.directory = dir;
        this.fileName = fileName;
        this.fileContent = fileContent;

        try{

        File file = new File(dir+"/"+fileName);
        FileWriter writer = new FileWriter(file);
        writer.write(fileContent);
        writer.close();

        System.out.println("Created File Successfully");
    }
    catch (IOException e){
        System.out.println("Error");
    }

    }
    public String toString()
    {
        return this.fileName;
    }
    public void updateContent()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the content: ");
        String content = sc.nextLine().trim();
        this.fileContent = content;

        try
        {
            File file = new File(this.directory+"/"+this.fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(this.fileContent);
            writer.close();
            System.out.println("Updated File Successfully");
        }

        catch (IOException e){
        System.out.println("Error");
        }
    }
    public void rename()
    {
        Scanner sc = new Scanner(System.in);
        File oldFile = new File(this.directory+"/"+this.fileName);
        System.out.println("Enter the new name of the file: ");
        String newName = sc.nextLine().trim();
        this.fileName = newName;
        File newFile = new File(this.directory+"/"+newName);
        if(oldFile.renameTo(newFile)) { System.out.println("Successfully updated the File name!"); }
        else { System.out.println("Failed to rename the File"); }
    }

    public void deleteFile()
    {
        Scanner sc = new Scanner(System.in);
        File file = new File(this.directory+"/"+this.fileName);

        if(file.delete()) { System.out.println("Successfully deleted the File name!"); }
        else { System.out.println("Failed to delete the File"); }
    }
}
