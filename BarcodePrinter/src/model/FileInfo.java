package model;

import java.io.File;

public class FileInfo implements Comparable<FileInfo> {
	private File file;
	private int amount;
	
	public FileInfo(File file) {
		this.file = file;
		amount = 1;
	}
	
	public File getFile() {
		return file;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(FileInfo o) {
		return file.getName().compareTo(o.getFile().getName());
	}
}
