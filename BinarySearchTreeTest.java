
import java.util.Scanner;
import java.io.*;

class BinarySearchTreeTest{
	BufferedReader reader;
	BinarySearchTreeBuilder dbTree;

	public static void main(String[] args){
		BinarySearchTreeTest tree = new BinarySearchTreeTest("datafile.txt");
		tree.dbTree.print(); // Prints titles of all elements in the bst sorted by keyword.
		tree.dbTree.delete("database");
		tree.dbTree.delete("image-retrieval");
		System.out.print("\n\n\n");
		tree.dbTree.print();
	}

	BinarySearchTreeTest(String filename){
		try {
			dbTree = new BinarySearchTreeBuilder();
			reader = new BufferedReader(new FileReader(filename));
			/* READS DATAFILE.TXT INTO DATASTRUCTURE  */
			FileData fd;
			while ((fd = readNextRecord()) != null){
				for (int i=0; i<fd.keywords.length; i++){
					dbTree.insert(fd.keywords[i], fd);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (reader!=null) reader.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public FileData readNextRecord(){
		if(reader == null){
			System.out.println("Error: You must open the file first.");
			return null;
		}
		else{
			FileData readData;
			try{
				String data=reader.readLine();
				if(data==null) return null;
				// FileData(int id, String title,      String author,     int keywordCount)
				readData= new FileData(Integer.parseInt(data), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()));
				/*
	        4
	        database
	        image-retrieval
	        content-based
	        medical

				 */
				for (int i=0; i<readData.keywords.length; i++) {
					readData.keywords[i] = reader.readLine();
				}
				String space=reader.readLine();
				if((space!=null) && (!space.trim().equals(""))){
					System.out.println("Error in file format");
					return null;
				}
			}
			catch(NumberFormatException e){
				System.out.println("Error Number Expected! ");
				return null;
			}
			catch(Exception e){
				System.out.println("Fatal Error: "+e);
				return null;
			}
			return readData;
		}
	}
}