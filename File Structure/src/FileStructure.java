import java.util.ArrayList;
import java.util.Iterator;

public class FileStructure {

	private NLNode<FileObject> root; //declare the root of the FileStructurem tree

	/**
	 * This method prints the file structure for a given fileObject.
	 * This method calls the printStructure() method when the root node is declared, and if the fileObject in the root node is a directory.
	 * 
	 * @param fileObjectName
	 * @throws FileObjectException
	 */
	public FileStructure(String fileObjectName) throws FileObjectException {

		FileObject fileObj = new FileObject(fileObjectName); // store in root node

		root = new NLNode<FileObject>(fileObj, null); // set root's

		if (fileObj.isDirectory()) { //if the node is a directory
			printStructure(root); //print the structure of the directory
		} else {
			//otherwise do nothing
		}

	}

	/**
	 * Returns the root of this FileStructure object.
	 * 
	 * @return root
	 */
	public NLNode<FileObject> getRoot() {
		return root;

	}

	/**
	 * This method returns all files of a certain type.
	 * This method calls fileOfTypeAlgorithm after establishing an ArrayList for data storage.
	 * 
	 * @param type
	 * @return
	 */
	public Iterator<String> filesOfType(String type) {

		ArrayList<String> list = new ArrayList<String>(); //initialize new arraylist

		fileOfTypeAlgorithm(list, root, type); //call the fileOfTypeAlgorithm

		return list.iterator(); //return the arraylist with all elements inside it as an iterator
	}

	/**
	 * This method returns a certain file of a certain file name.
	 * This method calls findFileAlgorithm to help complete this process, after initializing other variables as well.
	 * 
	 * @param name
	 * @return file path (string)
	 */
	public String findFile(String name) {
		String finalString = ""; //declare empty string
		boolean found = false; //declare "found" boolean as false

		finalString = findFileAlgorithm(found, this.getRoot(), name); //run findFileAlgorithm and store it in the string

		return finalString; //return the final string
	}

	/**
	 * This method is a recursive algorithm that prints the structure of the file system. It is recursive.
	 * 
	 * @param r (root)
	 */
	public void printStructure(NLNode<FileObject> r) {
		if (r.getData().isFile()) { // if the data stored in the root is a file
			// nothing happens
		} else if (r.getData().isDirectory()) { //if the data stored in the root is a folder or directory
			FileObject f = r.getData(); //set a fileObject to store the data in the parent node
			Iterator<FileObject> iterator = f.directoryFiles(); //create an iterator that stores all of the directoryFiles of the fileObject just created

			while (iterator.hasNext()) { //while the iterator has other elements to iterate to
				FileObject nodeFile = iterator.next(); //create a new FileObject called nodeFile that stores each directory file from the iterator
				NLNode<FileObject> newNode = new NLNode<FileObject>(nodeFile, r); //create a new NLNode<FileObject> object with the new directoryFile as the data, and the parent being r
				r.addChild(newNode); //add the new node that was just created to the list of r's children
				printStructure(newNode); //recursively call the file structure for the child that was taken out of the iterator
			}

		}
	}

	/**
	 * This method is an algorithm to find a file of a certain type in the FileStructure. It is recursive.
	 * 
	 * @param list (list of files)
	 * @param r (root node)
	 * @param type (type of file)
	 */
	public void fileOfTypeAlgorithm(ArrayList<String> list, NLNode<FileObject> r, String type) {

		FileObject f = r.getData(); //get the data from the root node and store it in a fileObject (for first iteration)

		if (f.isFile()) { //if the data in the parent node is a file

			String longName = f.getLongName(); //get the long name of the file and store it in a string

			if (longName.endsWith(type)) { //if that long name ends with the file extension given as a parameter (type)
				list.add(longName); //add the long name of the file to the ArrayList
			}

		} else if (f.isDirectory()) { //if the date in the root node is a folder or directory
			Iterator<NLNode<FileObject>> i = r.getChildren(); //create an iterator, i, with the elements of the iterator being the children of the parent node

			while (i.hasNext()) { //while the iterator has another element to iterate to
				NLNode<FileObject> n = i.next(); //create a new FileObject from every child of the parent node
				fileOfTypeAlgorithm(list, n, type); //recursively call fileOfTypeAlgorithm to check every child node of the parent node for a file of the type specified
			}

		}

	}

	/**
	 * This method finds a specific file in the FileStructure and returns the full file path as a string.
	 * It is recursive.
	 * 
	 * @param found
	 * @param r
	 * @param name
	 * @return finalString (file path)
	 */
	public String findFileAlgorithm(boolean found, NLNode<FileObject> r, String name) {

		FileObject f = r.getData(); //get the data from the parent node (or root if 1st iteration) and store it in a string, f
		String longName = f.getLongName(); //get the long name of the created fileObject and store it in a string, longName
		String finalString = ""; //create a new String for the final path of the file to be returned to, called finalString

		if (f.isFile()) { //if the data in the parent node is that of a file

			if (f.getName().equals(name)) { //if the fileObject's "short" name (the file name with no full file path with folders and other directories in the name) equals the name passed into the method
				return longName; //return the full file path of the file (longName)
			} else
				return ""; //if the file's short name isn't the same as the name of the file being passed into the method, return nothing

		} else if (f.isDirectory() && found == false) { //if the data in the parent node is that of a folder or directory
			Iterator<NLNode<FileObject>> i = r.getChildren(); //get the children of the parent node and store them in an iterator, i

			while (i.hasNext() && found == false) { //while i has other elements to iterate to, and a file path has not been found
				NLNode<FileObject> n = i.next(); //create a new NLNode<FileObject> for every element in the iterator
				finalString = findFileAlgorithm(found, n, name); //recursively call the findFileAlgorithm with the parameter n (the child of the parent node)

				if (finalString.length() != 0) { //if the finalString length is not 0, that means the file has been found
					found = true; //set found to true
					return finalString; //return the finalString
				}

			}
		}

		return finalString; //if all else does not return anything, return finalString (which would be empty since there's no file found).
	}
}
