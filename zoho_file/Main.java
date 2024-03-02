import zoho_3_helper.*;
import java.util.*;
import java.io.File;


public class Main
{
    public static HashMap<String, ArrayList<MyFile>> directories = new HashMap<String, ArrayList<MyFile>>();
    public static HashMap<String, ArrayList<MyFile>> deletedFiles = new HashMap<String, ArrayList<MyFile>>();

    public static ArrayList<HashMap<String, ArrayList<MyFile>>> deletedDirectories = new ArrayList<HashMap<String, ArrayList<MyFile>>>();

    public static boolean recursiveDelete(File folder)
    {
        if(folder.isDirectory())
        {
            File[] files = folder.listFiles();
            if(files != null)
            {
                for(File file: files)
                {
                    if(!recursiveDelete(file)) return false;
                }
            }
        }
        return folder.delete();
    }

    public static void directoryAndFileCreator()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 to create a file\nEnter 2 to Create a folder:");
        int choice = Integer.parseInt(sc.nextLine().trim());
        switch (choice)
        {
        case 1:
            {
                    System.out.println("Enter the name of the directory to create the file in: ");
                    String dir = sc.nextLine().trim();
                    if(!directories.containsKey(dir))
                    {
                        System.out.println("Directory doesnt exist!");
                        return;
                    }
                    else
                    {
                        System.out.println("Enter the file name: ");
                        String fileName = sc.nextLine().trim(), content = "hi";
                        MyFile myFile = new MyFile(dir, fileName, content);
                        ArrayList<MyFile> list = directories.get(dir);
                        list.add(myFile);
                    }
                    break;
            }
        case 2:
            {
                System.out.println("Enter the name of the directory with the entire path:");
                String dir = sc.nextLine().trim();
                if(directories.containsKey(dir))
                {
                    System.out.println("Directory already exist!");
                    return;
                }
                else
                {
                    File folder = new File(dir);
                    if(folder.mkdir())
                    {
                        System.out.println("Successfully created folder");
                        directories.put(dir, new ArrayList<MyFile>());
                    }
                    else{System.out.println("Failed to create folder");}
                }
                break;
            }

        }
    }


    public static void listAllDirectoriesAndFiles()
    {
        System.out.println(directories);
        for(String dir: directories.keySet())
        {
            System.out.println("Directory " + dir);
            for(MyFile fileName: directories.get(dir))
                System.out.println("-------------------File " + fileName);
        }
    }


    public static void updateFileContent()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the folder name: ");
        String dir = sc.nextLine().trim();
        if(directories.containsKey(dir))
        {
            ArrayList<MyFile> list = directories.get(dir);
            System.out.println("Enter the file name: ");
            String fName = sc.nextLine().trim();
            for(MyFile file : list)
            {
                if(file.fileName.equals(fName))
                {
                    file.updateContent();
                    return;
                }
            }
            System.out.println("File doesnt exist!");
        }
        else
        {
            System.out.println("No such directory exists");
            return;
        }
    }
    public static void updateDirectoryAndFileNames()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 to update a foldername\nEnter 2 to update a filename:");
        int choice = Integer.parseInt(sc.nextLine().trim());
        switch (choice)
        {
        case 1:
            {
                    System.out.println("Enter the name of the directory to update: ");
                    String dir = sc.nextLine().trim();
                    if(!directories.containsKey(dir))
                    {
                        System.out.println("Directory doesnt exist!");
                        return;
                    }
                    else
                    {
                        File oldFolder = new File(dir);
                        System.out.println("Enter the new name of the folder: ");
                        String newName = sc.nextLine().trim();
                        File newFolder = new File(newName);
                        if(oldFolder.renameTo(newFolder))
                            {
                                System.out.println("Successfully updated the folder name!");
                                ArrayList<MyFile> list = directories.get(dir);
                                directories.remove(dir);
                                directories.put(newName, list);
                            }
                        else { System.out.println("Failed to rename the folder"); }
                    }
                    break;
            }
        case 2:
            {
                    System.out.println("Enter the name of the directory of the file: ");
                    String dir = sc.nextLine().trim();
                    if(!directories.containsKey(dir))
                    {
                        System.out.println("Directory doesnt exist!");
                        return;
                    }
                    else
                    {
                        System.out.println("Enter the name of the file to update the file name: ");
                        String fileName = sc.nextLine().trim();
                        ArrayList<MyFile> list = directories.get(dir);
                        for(MyFile file: list)
                        {
                            if(fileName.equals(fileName))
                            {
                                file.rename();
                                return;
                            }
                        }
                        System.out.println("Failed to rename the file!");
                    }
                    break;
            }
        default:
            System.out.println("Invalid option");
        }
    }

    public static void deleteDirectoryAndFiles()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 to delete a folder\nEnter 2 to delete a file:");
        int choice = Integer.parseInt(sc.nextLine().trim());
        switch (choice)
        {
        case 1:
            {
                    System.out.println("Enter the name of the directory to delete: ");
                    String dir = sc.nextLine().trim();
                    if(!directories.containsKey(dir))
                    {
                        System.out.println("Directory doesnt exist!");
                        return;
                    }
                    else
                    {
                        File folder = new File(dir);
                        // take the copy of the key-value pair that is about to be deleted
                        HashMap<String, ArrayList<MyFile>> aboutToDelete = new HashMap<String, ArrayList<MyFile>>();
                        aboutToDelete.put(dir, directories.get(dir));
                        deletedDirectories.add(aboutToDelete);
                        if(recursiveDelete(folder))
                        {
                            System.out.println("Folder deleted Successfully");
                            directories.remove(dir);
                        }
                    }
                    break;
                }
            case 2:
                {
                    System.out.println("Enter the name of the directory that has the file to delete: ");
                    String dir = sc.nextLine().trim();
                    if(!directories.containsKey(dir))
                    {
                        System.out.println("Directory doesnt exist!");
                        return;
                    }
                    else
                    {
                        System.out.println("Enter the name of the file to delete: ");
                        String fileName = sc.nextLine().trim();
                        ArrayList<MyFile> list = directories.get(dir);
                        deletedFiles.put(dir, new ArrayList<MyFile>());
                        ArrayList<MyFile> delList = deletedFiles.get(dir);
                        int x = 0, f = 0;
                        for(MyFile file: list)
                        {
                            if(fileName.equals(fileName))
                            {
                                delList.add(file);
                                file.deleteFile();
                                f = 1;
                                break;
                            }
                            x++;
                        }
                        list.remove(x);
                        directories.remove(dir);
                        directories.put(dir, list);
                        if(f == 1) System.out.println("Successfully deleted the file");
                        else
                        System.out.println("Failed to delete the file!");
                    }
                    break;
                }
            default:
                System.out.println("Invalid choice");
        }
    }
    public static void viewDeletedDirectoriesAndFiles()
    {
        System.out.println("Deleted Files: " + deletedFiles);
        System.out.println("Deleted Directories: " + deletedDirectories);

    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int choice = -999;
        while (choice != -1)
        {
            System.out.println("1. Create new file/directory");
            System.out.println("2. List all directories and files");
            System.out.println("3. Update file content");
            System.out.println("4. Update directory and file names");
            System.out.println("5. Delete Directory and files");
            System.out.println("6. View deleted directories and files");
            choice = Integer.parseInt(sc.nextLine().trim());
            switch(choice)
            {
            case 1:
                directoryAndFileCreator();
                break;
            case 2:
                listAllDirectoriesAndFiles();
                break;
            case 3:
                updateFileContent();
                break;
            case 4:
                updateDirectoryAndFileNames();
                break;
            case 5:
                deleteDirectoryAndFiles();
                break;
            case 6:
                viewDeletedDirectoriesAndFiles();
                break;
            default:
                System.out.println("Invalid input!");
            }
        }
    }
}
