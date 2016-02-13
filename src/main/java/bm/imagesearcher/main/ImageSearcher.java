package bm.imagesearcher.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

public class ImageSearcher 
{

	private ArrayList<String> acceptableExtensions;
	private String baseDirectory;
	private String destinationDirectory;

	public ImageSearcher(ArrayList<String> acceptableExtensions, String baseDirectory, String destinationDirectory) 
	{
		this.acceptableExtensions = acceptableExtensions;
		this.baseDirectory = baseDirectory;
		this.destinationDirectory = destinationDirectory;

	}

	public void findFiles() 
	{
		getAllFiles(baseDirectory);
	}

	private void getAllFiles(String dir) {

		try {

			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) 
			{
				if (file.isDirectory()) 
				{
					getAllFiles(file.getAbsolutePath());
				} 
				else 
				{
					// String path =
					// (file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator)));
					for(String ext: acceptableExtensions){
						if(file.getName().toLowerCase().contains(ext.toLowerCase())){
							//System.out.println(file.getAbsolutePath());
							
							File src = new File(file.getAbsolutePath());
							File dest = new File(destinationDirectory);
							FileUtils.copyFileToDirectory(src, dest);
						}
					
					}
				}
			}
		}
		catch (NullPointerException e) 
		{
			System.err.println("Err Caught: Not a Directory " + e.getMessage());
		} 
		catch (IOException e) 
		{
			System.err.println("Err Caught: IO Problem" + e.getMessage());
		} 
		catch (Exception e) 
		{
			System.err.println("Err Caught: " + e.getMessage());
		}
	}

	public static void main(String[] args) 
	{
		//Add your file extensions to this array.
		ArrayList<String> arr = new ArrayList<String>(Arrays.asList(".jpg",".png", ".JPEG"));
		//Add the array, the drive to search and the destination drive
		ImageSearcher search = new ImageSearcher(arr, "D://PhotoSampleSort","D://picturetest");
		//Call findFiles() to begin searching.
		search.findFiles();
	}
}
