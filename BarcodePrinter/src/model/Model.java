package model;

import java.util.LinkedList;
import java.util.List;

public class Model {
	private List<FileInfo> files = new LinkedList<>();

	private int rows, cols, paddingX, paddingY, marginLeft;

	public Model() {
		rows = 16;
		cols = 5;

		paddingX = 8;
		paddingY = 7;
		
		marginLeft = 0;
	}

	public List<FileInfo> getFiles() {
		return files;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPaddingX() {
		return paddingX;
	}

	public void setPaddingX(int padding) {
		this.paddingX = padding;
	}

	public int getPaddingY() {
		return paddingY;
	}

	public void setPaddingY(int paddingY) {
		this.paddingY = paddingY;
	}
	
	public int getMarginLeft() {
		return marginLeft;
	}
	
	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}

	public int getAmount() {
		int sum = 0;
		for (FileInfo fileInfo : files) {
			sum += fileInfo.getAmount();
		}
		return sum;
	}

	public int getPageMax() {
		return rows * cols;
	}
}
