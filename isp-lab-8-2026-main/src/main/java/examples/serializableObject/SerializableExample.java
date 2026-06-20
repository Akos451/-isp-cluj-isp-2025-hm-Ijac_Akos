/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.serializableObject;

import examples.files.FilesAndFoldersUtil;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author mihai.hulea
 */
public class SerializableExample {
   static void writeVehicle(Vehicle v, String destinationFile) throws IOException{
       try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destinationFile))){
           os.writeObject(v);
           os.flush();
        }
   } 
   
   static Vehicle readVehicle(String sourceFile) throws IOException, ClassNotFoundException{
       try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(sourceFile))){
           return (Vehicle)in.readObject();
       }
   }
   
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String workingFolder = Paths.get("data", "serializableObject").toAbsolutePath().toString();
        FilesAndFoldersUtil.createFolder(workingFolder);

        writeVehicle(new Vehicle("CJ01AAA", "150,78", "673,90", 70), Paths.get(workingFolder, "car1.dat").toString());
        writeVehicle(new Vehicle("CJ02AAA", "150,78", "673,90", 50), Paths.get(workingFolder, "car2.dat").toString());
        writeVehicle(new Vehicle("CJ03AAA", "150,78", "673,90", 90), Paths.get(workingFolder, "car3.dat").toString());
        writeVehicle(new Vehicle("CJ04AAA", "150,78", "673,90", 170), Paths.get(workingFolder, "car4.dat").toString());

        FilesAndFoldersUtil.getFilesInFolder(workingFolder).stream().forEach((s)->System.out.println(s));

          List<String> files = FilesAndFoldersUtil.getFilesInFolder(workingFolder);
          for(String f: files){
              Vehicle v = readVehicle(Paths.get(workingFolder, f).toString());
              System.out.println(v);
          }
    }
}
