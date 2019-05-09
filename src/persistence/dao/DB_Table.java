package persistence.dao;

public class DB_Table {

	private String tableName;
	private String[] fieldsNames;
	private String pkName;

	public DB_Table(String tableName, String[] fieldsNames) {
		this.tableName = tableName;
		this.fieldsNames = fieldsNames;
		this.pkName="id";
	}

	public String getTableName() {
		return tableName;
	}
	
	public String getPK_Name() {
		return pkName;
	}

	public String getAllFieldsNames() {
		return getFieldsNamesSince(0);
	}

	public String getFieldsNamesSince(int index) {
		String r = "";
		if (!(index < 0)) {
			for (int i = index; i < fieldsNames.length; i++) {
				r += fieldsNames[i];
				if (i < fieldsNames.length - 1)
					r += ",";
			}
		}
		return r;
	}
	
	public String getAllUnknowns() {
		return getUnknownsSince(0);
	}
	
	public String getUnknownsSince(int index) {
		String r = "";
		if (!(index < 0)) {
			for (int i = index; i < fieldsNames.length; i++) {
				r += "?";
				if (i < fieldsNames.length - 1)
					r += ",";
			}
		}
		return r;
	}
	
	public int getFieldsNamesLength() {
		return this.fieldsNames.length;
	}

}
