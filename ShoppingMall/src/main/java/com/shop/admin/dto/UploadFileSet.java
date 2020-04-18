package com.shop.admin.dto;

public class UploadFileSet {
	
	private String originFileName;
	private String extName;
	private String saveFileName;
	
	public UploadFileSet(String originFileName, String extName, String saveFileName) {
		super();
		this.originFileName = originFileName;
		this.extName = extName;
		this.saveFileName = saveFileName;
	}
	
	public String getOriginFileName() {
		return originFileName;		
	}
	
	public String getExtName() {
		return extName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	
}
